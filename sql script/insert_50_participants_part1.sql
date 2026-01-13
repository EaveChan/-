-- 批量添加50个典型受试者 第1部分（36-60号）
-- 密码统一为 123456

-- 36. 江三八 - 30岁女性，健康志愿者
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('江三八', 'jiangsanba@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 30, 'F', '身体健康，无慢性疾病', '无疾病史，生活规律');

-- 37. 童三九 - 65岁男性，阿尔茨海默病早期
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('童三九', 'tongsanjiu@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 65, 'M', '阿尔茨海默病早期，记忆力下降', '1年前确诊轻度认知障碍，服用改善记忆药物');

-- 38. 颜四十 - 48岁女性，卵巢囊肿
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('颜四十', 'yansishi@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 48, 'F', '卵巢囊肿，定期观察', 'B超发现卵巢囊肿5cm，暂不需手术');

-- 39. 汤四一 - 26岁男性，健康志愿者
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('汤四一', 'tangsiyi@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 26, 'M', '身体健康，经常运动', '无疾病史，体检正常');

-- 40. 尹四二 - 61岁女性，帕金森病
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('尹四二', 'yinsier@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 61, 'F', '帕金森病，手抖症状', '2年前确诊帕金森病，服用左旋多巴');

-- 41. 黎四三 - 45岁男性，睡眠呼吸暂停综合征
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('黎四三', 'lisisan@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 45, 'M', '睡眠呼吸暂停综合征，打鼾严重', '睡眠监测确诊中重度睡眠呼吸暂停，使用呼吸机');

-- 42. 易四四 - 38岁女性，多囊卵巢综合征
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('易四四', 'yisisi@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 38, 'F', '多囊卵巢综合征，月经不调', '5年前确诊多囊卵巢，激素治疗中');

-- 43. 常四五 - 52岁男性，胆结石
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('常四五', 'changsiwu@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 52, 'M', '胆囊多发结石，无症状', 'B超发现胆结石，暂不需手术');

-- 44. 武四六 - 35岁女性，健康志愿者
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('武四六', 'wusiliu@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 35, 'F', '身体健康，无不良嗜好', '无疾病史，定期体检');

-- 45. 乔四七 - 58岁男性，前列腺癌术后
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('乔四七', 'qiaosiqi@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 58, 'M', '前列腺癌根治术后，定期复查', '1年前前列腺癌手术，目前恢复良好');

-- 46. 贾四八 - 42岁女性，系统性红斑狼疮
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('贾四八', 'jiasiba@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 42, 'F', '系统性红斑狼疮，激素治疗中', '3年前确诊红斑狼疮，长期服用激素');

-- 47. 路四九 - 29岁男性，健康志愿者
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('路四九', 'lusijiu@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 29, 'M', '身体健康，规律作息', '无疾病史，不吸烟不饮酒');

-- 48. 娄五十 - 54岁女性，乳腺癌术后
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('娄五十', 'louwushi@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 54, 'F', '乳腺癌术后化疗，定期复查', '2年前乳腺癌手术，已完成化疗');

-- 49. 危五一 - 47岁男性，肝硬化早期
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('危五一', 'weiwuyi@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 47, 'M', '肝硬化早期，肝功能代偿期', '乙肝病史20年，发展为肝硬化');

-- 50. 江五二 - 33岁女性，甲状腺功能亢进
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('江五二', 'jiangwuer@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 33, 'F', '甲状腺功能亢进，心悸手抖', '1年前确诊甲亢，服用抗甲状腺药物');

-- 51. 童五三 - 60岁男性，慢性阻塞性肺病
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('童五三', 'tongwusan@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 60, 'M', '慢性阻塞性肺病，呼吸困难', '吸烟史40年，肺功能中度下降');

-- 52. 颜五四 - 41岁女性，抑郁症
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('颜五四', 'yanwusi@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 41, 'F', '抑郁症，情绪低落', '2年前确诊抑郁症，服用抗抑郁药');

-- 53. 纪五五 - 28岁男性，健康志愿者
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('纪五五', 'jiwuwu@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 28, 'M', '身体健康，经常健身', '无疾病史，BMI正常');

-- 54. 舒五六 - 55岁女性，类风湿关节炎
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('舒五六', 'shuwuliu@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 55, 'F', '类风湿关节炎，关节肿痛', '10年类风湿病史，服用免疫抑制剂');

-- 55. 屈五七 - 50岁男性，心房颤动
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('屈五七', 'quwuqi@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 50, 'M', '心房颤动，心律不齐', '3年前确诊房颤，服用抗凝药和控制心率药物');

-- 56. 项五八 - 36岁女性，健康志愿者
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('项五八', 'xiangwuba@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 36, 'F', '身体健康，无慢性疾病', '无疾病史，生活规律');

-- 57. 祝五九 - 64岁男性，脑梗死后遗症
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('祝五九', 'zhuwujiu@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 64, 'M', '脑梗死后遗症，左侧肢体活动不利', '1年前脑梗死，康复治疗中');

-- 58. 董六十 - 43岁女性，子宫内膜异位症
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('董六十', 'dongliushi@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 43, 'F', '子宫内膜异位症，痛经严重', '5年前确诊子宫内膜异位症，激素治疗');

-- 59. 梁六一 - 32岁男性，健康志愿者
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('梁六一', 'liangliuyi@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 32, 'M', '身体健康，无不良嗜好', '无疾病史，定期体检');

-- 60. 杜六二 - 57岁女性，慢性心力衰竭
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES ('杜六二', 'duliuer@gmail.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC', 'PARTICIPANT', 1, 57, 'F', '慢性心力衰竭，活动后气促', '冠心病导致心功能不全，服用强心利尿药');
