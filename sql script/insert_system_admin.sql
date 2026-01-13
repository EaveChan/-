-- 创建系统管理员账号
-- 邮箱: system_admin@gmail.com
-- 密码: 123456 (BCrypt 加密后的值)

-- 检查是否已存在该邮箱的用户
SELECT id, name, email, role FROM user WHERE email = 'system_admin@gmail.com';

-- 如果不存在，则插入系统管理员账号
INSERT INTO user (name, email, password, role, status, age, gender, health_conditions, medical_history)
VALUES (
    '系统管理员',
    'system_admin@gmail.com',
    '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC',  -- 123456 的 BCrypt 加密值
    'Admin',
    1,  -- 状态：活跃
    NULL,  -- 年龄：管理员不需要
    NULL,  -- 性别：管理员不需要
    NULL,  -- 健康状况：管理员不需要
    NULL   -- 病史：管理员不需要
);

-- 验证插入结果
SELECT id, name, email, role, status FROM user WHERE email = 'system_admin@gmail.com';
