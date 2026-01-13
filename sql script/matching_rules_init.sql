-- 智能匹配规则初始化脚本
-- 用于初始化系统的排除规则和优先规则

-- 创建匹配规则表（如果不存在）
CREATE TABLE IF NOT EXISTS matching_rules (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    rule_type VARCHAR(20) NOT NULL COMMENT '规则类型：EXCLUSION（排除）、PRIORITY（优先）、REQUIRED（必需）',
    keywords TEXT NOT NULL COMMENT '关键词（多个关键词用逗号分隔）',
    description TEXT COMMENT '规则描述',
    priority INT NOT NULL COMMENT '优先级（数值越大优先级越高）',
    weight_score INT COMMENT '权重分数（用于优先条件加分）',
    is_active BOOLEAN NOT NULL DEFAULT TRUE COMMENT '是否启用',
    created_at DATETIME NOT NULL COMMENT '创建时间',
    updated_at DATETIME COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='智能匹配规则表';

-- 清空现有规则（可选，首次运行时注释掉）
-- DELETE FROM matching_rules;

-- ========== 排除规则（EXCLUSION） ==========
-- 这些规则用于自动剔除不符合条件的受试者

-- 1. 孕妇相关排除
INSERT INTO matching_rules (rule_type, keywords, description, priority, weight_score, is_active, created_at)
VALUES ('EXCLUSION', '孕妇,怀孕,妊娠,孕期,妊娠期', '排除孕妇或妊娠期女性', 100, NULL, TRUE, NOW());

-- 2. 过敏史排除
INSERT INTO matching_rules (rule_type, keywords, description, priority, weight_score, is_active, created_at)
VALUES ('EXCLUSION', '严重过敏,过敏性休克,药物过敏史', '排除有严重过敏史的受试者', 90, NULL, TRUE, NOW());

-- 3. 近期参加临床试验排除
INSERT INTO matching_rules (rule_type, keywords, description, priority, weight_score, is_active, created_at)
VALUES ('EXCLUSION', '近期参加试验,正在参加试验,3个月内参加,近期入组', '排除近期参加过其他临床试验的受试者', 85, NULL, TRUE, NOW());

-- 4. 严重肝肾功能异常排除
INSERT INTO matching_rules (rule_type, keywords, description, priority, weight_score, is_active, created_at)
VALUES ('EXCLUSION', '肝功能衰竭,肾功能衰竭,肝硬化失代偿,尿毒症', '排除严重肝肾功能异常的受试者', 95, NULL, TRUE, NOW());

-- 5. 恶性肿瘤排除
INSERT INTO matching_rules (rule_type, keywords, description, priority, weight_score, is_active, created_at)
VALUES ('EXCLUSION', '恶性肿瘤,癌症,白血病,淋巴瘤', '排除患有恶性肿瘤的受试者', 95, NULL, TRUE, NOW());

-- 6. 精神疾病排除
INSERT INTO matching_rules (rule_type, keywords, description, priority, weight_score, is_active, created_at)
VALUES ('EXCLUSION', '精神分裂症,严重抑郁症,双相情感障碍', '排除患有严重精神疾病的受试者', 80, NULL, TRUE, NOW());

-- 7. 酗酒或药物滥用排除
INSERT INTO matching_rules (rule_type, keywords, description, priority, weight_score, is_active, created_at)
VALUES ('EXCLUSION', '酗酒,酒精依赖,药物滥用,吸毒', '排除有酗酒或药物滥用史的受试者', 85, NULL, TRUE, NOW());

-- 8. 哺乳期排除
INSERT INTO matching_rules (rule_type, keywords, description, priority, weight_score, is_active, created_at)
VALUES ('EXCLUSION', '哺乳期,正在哺乳,母乳喂养期', '排除哺乳期女性', 90, NULL, TRUE, NOW());

-- ========== 优先规则（PRIORITY） ==========
-- 这些规则用于为符合特定条件的受试者加分

-- 1. 依从性良好优先
INSERT INTO matching_rules (rule_type, keywords, description, priority, weight_score, is_active, created_at)
VALUES ('PRIORITY', '依从性好,配合度高,按时服药,定期复查', '优先推荐依从性良好的受试者', 70, 5, TRUE, NOW());

-- 2. 病史清晰优先
INSERT INTO matching_rules (rule_type, keywords, description, priority, weight_score, is_active, created_at)
VALUES ('PRIORITY', '病史清晰,资料完整,诊断明确', '优先推荐病史资料完整的受试者', 60, 3, TRUE, NOW());

-- 3. 本地受试者优先
INSERT INTO matching_rules (rule_type, keywords, description, priority, weight_score, is_active, created_at)
VALUES ('PRIORITY', '本地,本市,交通便利,就近', '优先推荐本地或交通便利的受试者', 50, 3, TRUE, NOW());

-- 4. 既往参与经验优先
INSERT INTO matching_rules (rule_type, keywords, description, priority, weight_score, is_active, created_at)
VALUES ('PRIORITY', '曾参加试验,有试验经验,完成过试验', '优先推荐有临床试验参与经验的受试者', 65, 4, TRUE, NOW());

-- 5. 特定疾病史优先（根据试验需求）
INSERT INTO matching_rules (rule_type, keywords, description, priority, weight_score, is_active, created_at)
VALUES ('PRIORITY', '高血压病史,糖尿病病史,心血管病史', '优先推荐有特定疾病史的受试者（根据试验类型）', 55, 5, TRUE, NOW());

-- ========== 必需规则（REQUIRED） ==========
-- 这些规则用于标记必须满足的条件（可选，暂时不启用）

-- 1. 知情同意能力
INSERT INTO matching_rules (rule_type, keywords, description, priority, weight_score, is_active, created_at)
VALUES ('REQUIRED', '具有完全民事行为能力,能够理解知情同意', '受试者必须具有知情同意能力', 100, NULL, FALSE, NOW());

-- 查询验证
SELECT 
    id,
    rule_type AS '规则类型',
    keywords AS '关键词',
    description AS '描述',
    priority AS '优先级',
    weight_score AS '权重分数',
    is_active AS '是否启用'
FROM matching_rules
ORDER BY rule_type, priority DESC;

-- 统计信息
SELECT 
    rule_type AS '规则类型',
    COUNT(*) AS '规则数量',
    SUM(CASE WHEN is_active = TRUE THEN 1 ELSE 0 END) AS '启用数量'
FROM matching_rules
GROUP BY rule_type;
