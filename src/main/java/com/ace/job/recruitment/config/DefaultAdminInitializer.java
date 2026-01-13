package com.ace.job.recruitment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ace.job.recruitment.entity.User;
import com.ace.job.recruitment.service.UserService;

@Component
public class DefaultAdminInitializer implements CommandLineRunner {
	@Autowired
	UserService userService;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultAdminInitializer.class);

	@Override
	public void run(String... args) throws Exception {
		logger.info("Checking if default admin user exists...");
		// First check by role (backward compatibility)
		User existingAdminByRole = userService.getUserByRole("Default-Admin");
		// Then check by email with status true (for login compatibility)
		User existingAdminByEmail = userService.getUserByEmailSingle("defaultAdmin@gmail.com");
		
		if (existingAdminByEmail == null || !existingAdminByEmail.isStatus()) {
			logger.info("Default admin user not found or inactive, creating/updating...");
			User defaultAdmin = existingAdminByRole != null ? existingAdminByRole : new User();
			defaultAdmin.setName("default-admin");
			defaultAdmin.setEmail("defaultAdmin@gmail.com");
			defaultAdmin.setStatus(true);
			defaultAdmin.setPassword(passwordEncoder.encode("default-admin"));
			defaultAdmin.setRole("Default-Admin");
			if (existingAdminByRole != null) {
				userService.updateUser(defaultAdmin);
				logger.info("Default admin user updated successfully with email: defaultAdmin@gmail.com");
			} else {
				userService.addUser(defaultAdmin);
				logger.info("Default admin user created successfully with email: defaultAdmin@gmail.com");
			}
		} else {
			logger.info("Default admin user already exists and active with email: {}", existingAdminByEmail.getEmail());
		}
	}

}
