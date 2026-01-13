-- 批量添加20个受试者用户
-- 密码统一为 123456，使用 BCrypt 加密后的值为：$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC

-- 16. 梁十八 - 47岁女性，乳腺增生
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('梁十八', 'liangshiba@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 47, 'F', '乳腺增生，定期复查', '2年前B超检查发现乳腺增生，每年复查');

-- 17. 宋十九 - 36岁男性，健康志愿者
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('宋十九', 'songshijiu@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 36, 'M', '身体健康，经常健身', '无疾病史，BMI正常');

-- 18. 唐二十 - 54岁男性，脂肪肝
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('唐二十', 'tangershi@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 54, 'M', '中度脂肪肝，肝功能正常', '3年前体检发现脂肪肝，控制饮食中');

-- 19. 许二一 - 41岁女性，偏头痛
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('许二一', 'xueryi@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 41, 'F', '偏头痛，月经期加重', '10年偏头痛史，偶尔服用止痛药');

-- 20. 韩二二 - 59岁男性，慢性支气管炎
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('韩二二', 'hanerer@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 59, 'M', '慢性支气管炎，冬季易发', '吸烟史30年，已戒烟5年，慢性咳嗽');

-- 21. 冯二三 - 33岁女性，健康志愿者
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('冯二三', 'fengersan@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 33, 'F', '身体健康，无慢性疾病', '无疾病史，生活规律');

-- 22. 曹二四 - 51岁男性，痛风
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('曹二四', 'caoersi@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 51, 'M', '痛风，尿酸偏高', '2年前首次痛风发作，服用降尿酸药物');

-- 23. 彭二五 - 44岁女性，子宫肌瘤
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('彭二五', 'pengerwu@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 44, 'F', '子宫肌瘤，定期观察', '1年前B超发现子宫肌瘤，暂不需手术');

-- 24. 董二六 - 37岁男性，健康志愿者
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('董二六', 'dongerliu@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 37, 'M', '身体健康，无不良嗜好', '无疾病史，定期体检');

-- 25. 袁二七 - 56岁女性，骨质疏松
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('袁二七', 'yuanerqi@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 56, 'F', '骨质疏松，补钙治疗中', '绝经后骨密度下降，服用钙片和维生素D');

-- 26. 潘二八 - 43岁男性，颈椎病
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('潘二八', 'panerba@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 43, 'M', '颈椎病，颈部疼痛', '长期伏案工作，颈椎退行性改变');

-- 27. 于二九 - 31岁女性，健康志愿者
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('于二九', 'yuerjiu@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 31, 'F', '身体健康，作息规律', '无疾病史，不吸烟不饮酒');

-- 28. 苏三十 - 62岁男性，白内障
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('苏三十', 'susanshi@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 62, 'M', '双眼白内障，视力下降', '老年性白内障，计划手术治疗');

-- 29. 卢三一 - 49岁女性，更年期综合征
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('卢三一', 'lusanyi@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 49, 'F', '更年期综合征，潮热盗汗', '围绝经期，激素水平波动，中药调理');

-- 30. 丁三二 - 39岁男性，脂肪肝和高尿酸
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('丁三二', 'dingsaner@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 39, 'M', '轻度脂肪肝，尿酸偏高', '体检发现脂肪肝和高尿酸，控制饮食');

-- 31. 贺三三 - 27岁女性，健康志愿者
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('贺三三', 'hesansan@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 27, 'F', '身体健康，经常运动', '无疾病史，健康生活方式');

-- 32. 顾三四 - 53岁男性，慢性肾病
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('顾三四', 'gusansi@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 53, 'M', '慢性肾病3期，肾功能轻度下降', '高血压肾损害，控制血压保护肾功能');

-- 33. 毛三五 - 46岁女性，甲状腺结节
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('毛三五', 'maosanwu@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 46, 'F', '甲状腺多发结节，良性', '体检发现甲状腺结节，定期复查B超');

-- 34. 邱三六 - 34岁男性，健康志愿者
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('邱三六', 'qiusanliu@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 34, 'M', '身体健康，无慢性疾病', '无疾病史，每年体检正常');

-- 35. 秦三七 - 57岁女性，高血压和高血脂
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('秦三七', 'qinsanqi@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 57, 'F', '高血压和高血脂，药物控制中', '高血压6年，高血脂4年，规律服药');

-- 查询验证
SELECT id, name, email, role, age, gender, health_conditions FROM user WHERE role = 'PARTICIPANT' ORDER BY id;
