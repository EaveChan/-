-- 清理可能插入失败的临床试验数据（考虑外键约束）

-- 1. 先查看是否有 updated_user_id 为 NULL 或 0 的临床试验记录
SELECT id, trial_name, created_user_id, updated_user_id 
FROM clinical_trials 
WHERE updated_user_id IS NULL OR updated_user_id = 0;

-- 2. 查看这些临床试验是否有关联的入组申请
SELECT ea.id, ea.trial_id, ct.trial_name 
FROM enrollment_applications ea
JOIN clinical_trials ct ON ea.trial_id = ct.id
WHERE ct.updated_user_id IS NULL OR ct.updated_user_id = 0;

-- 3. 先删除关联的入组申请记录
DELETE FROM enrollment_applications 
WHERE trial_id IN (
    SELECT id FROM clinical_trials 
    WHERE updated_user_id IS NULL OR updated_user_id = 0
);

-- 4. 再删除有问题的临床试验记录
DELETE FROM clinical_trials 
WHERE updated_user_id IS NULL OR updated_user_id = 0;

-- 5. 验证删除结果
SELECT COUNT(*) as '剩余临床试验数量' FROM clinical_trials;
SELECT COUNT(*) as '剩余入组申请数量' FROM enrollment_applications;
