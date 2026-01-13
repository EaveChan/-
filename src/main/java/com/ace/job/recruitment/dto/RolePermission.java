package com.ace.job.recruitment.dto;

import java.util.List;

/**
 * 角色权限 DTO
 * 用于角色权限管理页面展示角色与权限的对应关系
 */
public class RolePermission {
	private String roleName;
	private String roleDisplayName;
	private List<String> permissions;

	public RolePermission() {
	}

	public RolePermission(String roleName, String roleDisplayName, List<String> permissions) {
		this.roleName = roleName;
		this.roleDisplayName = roleDisplayName;
		this.permissions = permissions;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDisplayName() {
		return roleDisplayName;
	}

	public void setRoleDisplayName(String roleDisplayName) {
		this.roleDisplayName = roleDisplayName;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
}
