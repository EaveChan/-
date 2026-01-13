-- 批量添加受试者用户
-- 密码统一为 123456，使用 BCrypt 加密后的值为：$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC

-- 1. 张三 - 45岁男性，高血压患者
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('张三', 'zhangsan@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 45, 'M', '高血压，血压控制良好', '2年前确诊高血压，目前服用降压药');

-- 2. 李四 - 52岁女性，糖尿病患者
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('李四', 'lisi@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 52, 'F', '2型糖尿病，血糖控制稳定', '5年前确诊糖尿病，规律服用二甲双胍');

-- 3. 王五 - 38岁男性，健康志愿者
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('王五', 'wangwu@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 38, 'M', '身体健康，无慢性疾病', '无重大疾病史');

-- 4. 赵六 - 60岁女性，关节炎患者
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('赵六', 'zhaoliu@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 60, 'F', '骨关节炎，膝关节疼痛', '3年前确诊骨关节炎，偶尔服用止痛药');

-- 5. 孙七 - 28岁男性，健康志愿者
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('孙七', 'sunqi@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 28, 'M', '身体健康，定期运动', '无疾病史，体检正常');

-- 6. 周八 - 55岁男性，高血压和糖尿病
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('周八', 'zhouba@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 55, 'M', '高血压和2型糖尿病，药物控制中', '高血压8年，糖尿病4年，规律服药');

-- 7. 吴九 - 42岁女性，哮喘患者
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('吴九', 'wujiu@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 42, 'F', '支气管哮喘，季节性发作', '儿童期确诊哮喘，使用吸入性激素控制');

-- 8. 郑十 - 35岁女性，健康志愿者
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('郑十', 'zhengshi@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 35, 'F', '身体健康，无慢性疾病', '无重大疾病史，偶尔感冒');

-- 9. 陈十一 - 48岁男性，冠心病患者
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('陈十一', 'chenshiyi@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 48, 'M', '冠心病，已放置支架', '2年前心肌梗死，已行支架植入术，服用抗凝药');

-- 10. 刘十二 - 58岁女性，高血脂患者
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('刘十二', 'liushier@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 58, 'F', '高血脂，胆固醇偏高', '3年前确诊高血脂，服用他汀类药物');

-- 11. 黄十三 - 32岁男性，健康志愿者
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('黄十三', 'huangshisan@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 32, 'M', '身体健康，无不良嗜好', '无疾病史，不吸烟不饮酒');

-- 12. 杨十四 - 50岁女性，甲状腺功能减退
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('杨十四', 'yangshisi@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 50, 'F', '甲状腺功能减退，激素替代治疗中', '4年前确诊甲减，每日服用左甲状腺素');

-- 13. 朱十五 - 40岁男性，慢性胃炎
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('朱十五', 'zhushiwu@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 40, 'M', '慢性浅表性胃炎，偶有胃痛', '胃镜检查确诊慢性胃炎，注意饮食');

-- 14. 林十六 - 63岁男性，前列腺增生
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('林十六', 'linshiliu@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 63, 'M', '良性前列腺增生，排尿困难', '5年前确诊前列腺增生，服用α受体阻滞剂');

-- 15. 何十七 - 29岁女性，健康志愿者
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('何十七', 'heshiqi@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 29, 'F', '身体健康，规律作息', '无疾病史，每年体检正常');

-- 查询验证
SELECT id, name, email, role, age, gender, health_conditions FROM user WHERE role = 'PARTICIPANT' ORDER BY id;
