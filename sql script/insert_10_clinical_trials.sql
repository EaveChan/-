-- 批量添加10个典型临床试验
-- created_user_id 设置为 1（假设管理员ID为1）
-- updated_user_id 也设置为 1，updated_date 和 updated_time 设置为当前时间

-- 1. 高血压新药临床试验
INSERT INTO clinical_trials (trial_name, trial_description, age_min, age_max, gender_requirement, required_conditions, exclusion_criteria, status, trial_type, duration, expected_subject_count, trial_phase, institution, created_date, created_time, updated_date, updated_time, created_user_id, updated_user_id)
VALUES ('高血压新药III期临床试验', '评估新型降压药物的有效性和安全性，适用于轻中度高血压患者。本研究采用随机双盲对照设计，观察12周治疗期间血压变化及不良反应。', 40, 70, 'Any', '高血压，血压控制不佳', '孕妇，哺乳期，严重心脏病，肝肾功能不全，近期参加过其他临床试验', 'RECRUITING', '药物试验', 12, 100, 'Phase III', '北京协和医院', CURDATE(), CURTIME(), CURDATE(), CURTIME(), 1, 1);

-- 2. 2型糖尿病新药试验
INSERT INTO clinical_trials (trial_name, trial_description, age_min, age_max, gender_requirement, required_conditions, exclusion_criteria, status, trial_type, duration, expected_subject_count, trial_phase, institution, created_date, created_time, updated_date, updated_time, created_user_id, updated_user_id)
VALUES ('2型糖尿病口服降糖药临床研究', '研究新型口服降糖药物对2型糖尿病患者血糖控制的疗效。要求患者糖化血红蛋白在7.5%-10%之间，评估24周治疗效果。', 35, 65, 'Any', '2型糖尿病，血糖控制不佳', '1型糖尿病，孕妇，严重肝肾疾病，糖尿病酮症酸中毒，近期手术史', 'RECRUITING', '药物试验', 24, 150, 'Phase II', '上海瑞金医院', CURDATE(), CURTIME(), CURDATE(), CURTIME(), 1, 1);

-- 3. 类风湿关节炎生物制剂试验
INSERT INTO clinical_trials (trial_name, trial_description, age_min, age_max, gender_requirement, required_conditions, exclusion_criteria, status, trial_type, duration, expected_subject_count, trial_phase, institution, created_date, created_time, updated_date, updated_time, created_user_id, updated_user_id)
VALUES ('类风湿关节炎生物制剂疗效研究', '评估新型生物制剂治疗中重度类风湿关节炎的疗效和安全性。采用皮下注射给药方式，观察关节肿痛改善情况。', 30, 70, 'Any', '类风湿关节炎，关节肿痛', '活动性感染，结核病史，恶性肿瘤，严重心肝肾疾病，孕妇哺乳期', 'RECRUITING', '生物制剂试验', 16, 80, 'Phase III', '广州中山大学附属第一医院', CURDATE(), CURTIME(), CURDATE(), CURTIME(), 1, 1);

-- 4. 阿尔茨海默病新药试验
INSERT INTO clinical_trials (trial_name, trial_description, age_min, age_max, gender_requirement, required_conditions, exclusion_criteria, status, trial_type, duration, expected_subject_count, trial_phase, institution, created_date, created_time, updated_date, updated_time, created_user_id, updated_user_id)
VALUES ('阿尔茨海默病认知改善药物研究', '针对轻中度阿尔茨海默病患者，评估新药对认知功能的改善作用。通过认知量表评分观察患者记忆力、注意力等改善情况。', 55, 85, 'Any', '阿尔茨海默病，记忆力下降，认知障碍', '严重精神疾病，脑卒中，帕金森病，严重心肝肾疾病，近期参加过其他试验', 'RECRUITING', '神经系统药物试验', 20, 120, 'Phase II', '四川大学华西医院', CURDATE(), CURTIME(), CURDATE(), CURTIME(), 1, 1);

-- 5. 慢性阻塞性肺病吸入剂试验
INSERT INTO clinical_trials (trial_name, trial_description, age_min, age_max, gender_requirement, required_conditions, exclusion_criteria, status, trial_type, duration, expected_subject_count, trial_phase, institution, created_date, created_time, updated_date, updated_time, created_user_id, updated_user_id)
VALUES ('慢阻肺新型吸入剂临床研究', '评估新型长效支气管扩张剂对慢性阻塞性肺病患者肺功能的改善作用。要求患者有明确吸烟史或职业暴露史。', 45, 75, 'Any', '慢性阻塞性肺病，呼吸困难，咳嗽咳痰', '哮喘，活动性肺结核，肺癌，严重心脏病，孕妇，近期急性加重', 'RECRUITING', '呼吸系统药物试验', 12, 90, 'Phase III', '中日友好医院', CURDATE(), CURTIME(), CURDATE(), CURTIME(), 1, 1);

-- 6. 骨质疏松症新药试验
INSERT INTO clinical_trials (trial_name, trial_description, age_min, age_max, gender_requirement, required_conditions, exclusion_criteria, status, trial_type, duration, expected_subject_count, trial_phase, institution, created_date, created_time, updated_date, updated_time, created_user_id, updated_user_id)
VALUES ('绝经后骨质疏松症治疗药物研究', '针对绝经后女性骨质疏松患者，评估新型抗骨质疏松药物提高骨密度的效果。通过骨密度检测评估治疗效果。', 50, 75, 'F', '骨质疏松，绝经后女性，骨密度下降', '骨折未愈合，恶性肿瘤，严重肝肾疾病，甲状旁腺功能异常，孕妇', 'RECRUITING', '骨科药物试验', 18, 100, 'Phase III', '北京积水潭医院', CURDATE(), CURTIME(), CURDATE(), CURTIME(), 1, 1);

-- 7. 抑郁症新型抗抑郁药试验
INSERT INTO clinical_trials (trial_name, trial_description, age_min, age_max, gender_requirement, required_conditions, exclusion_criteria, status, trial_type, duration, expected_subject_count, trial_phase, institution, created_date, created_time, updated_date, updated_time, created_user_id, updated_user_id)
VALUES ('抑郁症新型抗抑郁药临床研究', '评估新型抗抑郁药物对中重度抑郁症患者的疗效和安全性。采用抑郁量表评分，观察情绪改善情况和不良反应。', 25, 60, 'Any', '抑郁症，情绪低落，兴趣减退', '双相情感障碍，精神分裂症，药物滥用，严重自杀倾向，孕妇哺乳期，近期使用其他抗抑郁药', 'RECRUITING', '精神科药物试验', 16, 80, 'Phase II', '北京大学第六医院', CURDATE(), CURTIME(), CURDATE(), CURTIME(), 1, 1);

-- 8. 冠心病介入治疗研究
INSERT INTO clinical_trials (trial_name, trial_description, age_min, age_max, gender_requirement, required_conditions, exclusion_criteria, status, trial_type, duration, expected_subject_count, trial_phase, institution, created_date, created_time, updated_date, updated_time, created_user_id, updated_user_id)
VALUES ('冠心病新型支架临床研究', '评估新型药物洗脱支架在冠心病患者中的安全性和有效性。观察支架植入后血管再狭窄率和心血管事件发生率。', 40, 75, 'Any', '冠心病，心绞痛，心肌缺血', '急性心肌梗死，严重心力衰竭，出血倾向，严重肝肾疾病，恶性肿瘤，预期寿命小于1年', 'RECRUITING', '介入治疗研究', 12, 60, 'Phase IV', '中国医学科学院阜外医院', CURDATE(), CURTIME(), CURDATE(), CURTIME(), 1, 1);

-- 9. 银屑病外用药临床试验
INSERT INTO clinical_trials (trial_name, trial_description, age_min, age_max, gender_requirement, required_conditions, exclusion_criteria, status, trial_type, duration, expected_subject_count, trial_phase, institution, created_date, created_time, updated_date, updated_time, created_user_id, updated_user_id)
VALUES ('银屑病新型外用制剂疗效研究', '评估新型外用制剂治疗轻中度银屑病的疗效。观察皮损面积改善情况和局部不良反应，要求患者皮损面积占体表面积10%-30%。', 18, 65, 'Any', '银屑病，皮肤红斑鳞屑', '脓疱型银屑病，红皮病型银屑病，严重感染，孕妇哺乳期，近期使用过系统性治疗', 'RECRUITING', '皮肤科药物试验', 8, 70, 'Phase II', '北京大学人民医院', CURDATE(), CURTIME(), CURDATE(), CURTIME(), 1, 1);

-- 10. 健康志愿者药代动力学研究
INSERT INTO clinical_trials (trial_name, trial_description, age_min, age_max, gender_requirement, required_conditions, exclusion_criteria, status, trial_type, duration, expected_subject_count, trial_phase, institution, created_date, created_time, updated_date, updated_time, created_user_id, updated_user_id)
VALUES ('新药药代动力学和安全性研究', '在健康志愿者中评估新药的药代动力学特征和安全性。要求受试者身体健康，无慢性疾病，BMI在正常范围。', 20, 45, 'Any', '身体健康，无慢性疾病', '任何慢性疾病，过敏体质，吸烟酗酒，孕妇哺乳期，近3个月参加过其他临床试验', 'RECRUITING', '药代动力学研究', 4, 40, 'Phase I', '中国人民解放军总医院', CURDATE(), CURTIME(), CURDATE(), CURTIME(), 1, 1);

-- 查询验证
SELECT id, trial_name, age_min, age_max, gender_requirement, status, expected_subject_count FROM clinical_trials ORDER BY id;
