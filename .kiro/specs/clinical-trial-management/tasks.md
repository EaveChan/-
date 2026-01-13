# 实现计划：临床试验管理系统（管理者端）

## 概述

本实现计划将临床试验管理系统的管理者端功能分解为可执行的开发任务。重点实现智能匹配算法和管理者端的核心功能。

## 任务列表

### 第一阶段：数据模型与基础架构

- [ ] 1. 创建数据库实体类
  - [ ] 1.1 创建 ClinicalTrial 实体类
    - 包含字段：id, name, description, minAge, maxAge, genderRequirement, healthCondition, plannedEnrollment, status, createdAt, updatedAt
    - 添加 JPA 注解
    - _需求: 2.1, 3.1_
  
  - [ ] 1.2 创建 Subject 实体类
    - 包含字段：id, subjectCode, age, gender, healthConditions, medicalHistory, status, createdAt
    - 添加 JPA 注解
    - _需求: 5.1, 6.1_
  
  - [ ] 1.3 创建 EnrollmentApplication 实体类
    - 包含字段：id, clinicalTrial, subject, matchingScore, status, reviewNotes, appliedAt, reviewedAt, reviewedBy
    - 添加 JPA 注解和关联关系
    - _需求: 7.1, 9.1_
  
  - [ ] 1.4 更新 User 实体类
    - 确保包含 role 字段（ADMIN, RESEARCH_ASSISTANT, PARTICIPANT）
    - 添加 status 字段
    - _需求: 11.1_

- [ ] 2. 创建 Repository 接口
  - [ ] 2.1 创建 ClinicalTrialRepository
    - 添加按状态查询方法
    - 添加按创建时间排序方法
    - _需求: 2.1_
  
  - [ ] 2.2 创建 SubjectRepository
    - 添加按状态查询方法
    - 添加按受试者编号查询方法
    - _需求: 5.1_
  
  - [ ] 2.3 创建 EnrollmentApplicationRepository
    - 添加按状态查询方法
    - 添加按临床试验ID查询并按匹配分数排序方法
    - 添加按受试者ID查询方法
    - _需求: 7.1, 9.1_

### 第二阶段：核心业务逻辑（智能匹配算法）

- [x] 3. 实现智能匹配服务
  - [x] 3.1 创建 MatchingService 类
    - 创建基础服务类结构
    - 注入必要的 Repository
    - _需求: 7.1, 8.1_
  
  - [x] 3.2 实现匹配分数计算方法
    - 实现 calculateMatchingScore(Subject, ClinicalTrial) 方法
    - 实现年龄匹配检查逻辑
    - 实现性别匹配检查逻辑
    - 实现年龄优先级评分逻辑
    - 实现健康状况关键词匹配评分逻辑
    - _需求: 8.1, 8.2, 8.3, 8.4, 8.5_
  
  - [x] 3.3 实现批量匹配方法
    - 实现 getMatchedSubjects(Long trialId) 方法
    - 获取所有可用受试者
    - 批量计算匹配分数
    - 按分数降序排序
    - _需求: 7.1, 7.2_
  
  - [ ]* 3.4 编写匹配算法单元测试
    - 测试年龄不匹配场景（期望分数=0）
    - 测试性别不匹配场景（期望分数=0）
    - 测试完全匹配场景（期望分数=100）
    - 测试部分匹配场景
    - 测试边界值
    - _需求: 8.1, 8.2, 8.3, 8.4, 8.5_

### 第三阶段：临床试验管理功能

- [x] 4. 实现临床试验服务层
  - [x] 4.1 创建 ClinicalTrialService 类
    - 实现 getAllTrials() 方法
    - 实现 getTrialById(Long id) 方法
    - 实现 createTrial(ClinicalTrial) 方法
    - 实现 updateTrial(Long id, ClinicalTrial) 方法
    - 实现 closeTrial(Long id) 方法
    - 实现 getActiveTrials() 方法
    - _需求: 2.1, 3.1, 3.4_
  
  - [ ]* 4.2 编写临床试验服务单元测试
    - 测试创建试验
    - 测试更新试验
    - 测试关闭试验
    - _需求: 2.1, 3.1_

- [x] 5. 实现临床试验控制器
  - [x] 5.1 创建 ClinicalTrialController 类
    - 实现试验列表页面 GET /trials
    - 实现新建试验表单页面 GET /trials/add
    - 实现创建试验 POST /trials/add
    - 实现编辑试验表单页面 GET /trials/edit/{id}
    - 实现更新试验 POST /trials/edit/{id}
    - 实现试验详情页面 GET /trials/view/{id}
    - 实现关闭试验 POST /trials/close/{id}
    - _需求: 2.1, 2.3, 2.4, 3.1, 3.4, 4.1_

- [x] 6. 创建临床试验相关页面
  - [x] 6.1 创建试验列表页面 (trials/list.html)
    - 显示试验名称、描述、状态、入组人数
    - 添加筛选功能（按状态）
    - 添加新建、编辑、查看按钮
    - _需求: 2.1, 2.2_
  
  - [x] 6.2 创建试验表单页面 (trials/add.html 和 trials/edit.html)
    - 表单字段：名称、描述、年龄范围、性别要求、健康状况、计划招募人数
    - 添加表单验证
    - _需求: 3.1, 3.2, 3.3, 3.5_
  
  - [x] 6.3 创建试验详情页面 (trials/view.html)
    - 显示试验完整信息
    - 显示已报名和已入组人数
    - 添加跳转到匹配页面的按钮
    - _需求: 4.1, 4.2, 4.3, 4.4_

### 第四阶段：受试者管理功能

- [x] 7. 实现受试者服务层
  - [x] 7.1 创建 SubjectService 类
    - 实现 getAllSubjects() 方法
    - 实现 getSubjectById(Long id) 方法
    - 实现 createSubject(Subject) 方法
    - 实现 getSubjectsByStatus(String status) 方法
    - _需求: 5.1, 6.1_
  
  - [ ]* 7.2 编写受试者服务单元测试
    - 测试创建受试者
    - 测试按状态查询
    - _需求: 5.1_

- [x] 8. 实现受试者控制器
  - [x] 8.1 创建 SubjectController 类
    - 实现受试者列表页面 GET /subjects
    - 实现受试者详情页面 GET /subjects/view/{id}
    - 实现新建受试者表单页面 GET /subjects/add
    - 实现创建受试者 POST /subjects/add
    - _需求: 5.1, 5.3, 6.1_

- [x] 9. 创建受试者相关页面
  - [x] 9.1 创建受试者列表页面 (subjects/list.html)
    - 显示受试者编号、年龄、性别、健康状况、状态、匹配分数
    - 添加筛选功能（按状态）
    - 添加查看详情按钮
    - _需求: 5.1, 5.2, 5.4_
  
  - [x] 9.2 创建受试者详情页面 (subjects/view.html)
    - 显示基本信息
    - 显示健康状况和病史
    - 显示报名记录
    - 显示匹配分数和说明
    - _需求: 6.1, 6.2, 6.3, 6.4, 6.5_
  
  - [x] 9.3 创建受试者表单页面 (subjects/add.html 和 subjects/edit.html)
    - 表单字段：年龄、性别、健康状况、既往病史
    - 添加表单验证
    - _需求: 5.1_

### 第五阶段：智能匹配管理功能

- [x] 10. 实现匹配控制器
  - [x] 10.1 创建 MatchingController 类
    - 实现匹配主页面 GET /matching
    - 实现按试验查看匹配结果 GET /matching/trial/{trialId}
    - _需求: 7.1, 7.3, 7.4, 7.5, 7.6_

- [x] 11. 创建智能匹配页面
  - [x] 11.1 创建匹配主页面 (matching/index.html)
    - 显示临床试验选择区域
    - 提供试验下拉选择框
    - _需求: 7.1_
  
  - [x] 11.2 创建匹配结果列表页面 (matching/results.html)
    - 显示匹配的受试者列表
    - 按匹配分数降序排列
    - 显示受试者编号、年龄、性别、健康状况、匹配分数
    - 添加推荐入组、拒绝、人工复核按钮
    - _需求: 7.2, 7.3, 7.4, 7.5, 7.6_

### 第六阶段：入组管理功能

- [x] 12. 实现入组服务层
  - [x] 12.1 创建 EnrollmentService 类
    - 实现 createApplication(Long subjectId, Long trialId) 方法
    - 实现 approveEnrollment(Long applicationId, User reviewer) 方法
    - 实现 rejectEnrollment(Long applicationId, User reviewer, String notes) 方法
    - 实现 getPendingApplications() 方法
    - 实现 getApplicationsBySubject(Long subjectId) 方法
    - _需求: 9.1, 9.3, 9.4, 10.1, 10.2, 10.3, 10.4_
  
  - [ ]* 12.2 编写入组服务单元测试
    - 测试创建申请
    - 测试批准入组
    - 测试拒绝入组
    - 测试状态流转
    - _需求: 9.1, 9.3, 9.4, 10.1, 10.2, 10.3, 10.4_

- [x] 13. 实现入组控制器
  - [x] 13.1 创建 EnrollmentController 类
    - 实现入组审核列表页面 GET /enroll/enrollments
    - 实现申请入组 POST /enroll/apply
    - 实现批准入组 POST /enroll/approve/{id}
    - 实现拒绝入组 POST /enroll/reject/{id}
    - _需求: 9.1, 9.2_

- [x] 14. 创建入组管理页面
  - [x] 14.1 创建入组审核列表页面 (enrollments/list.html)
    - 显示待审核的入组申请
    - 显示受试者编号、所属试验、匹配分数、状态
    - 添加批准和拒绝按钮
    - _需求: 9.1, 9.2, 9.3, 9.4_
  
  - [ ] 14.2 创建入组历史页面 (enrollments/history.html)
    - 显示状态变更记录
    - 显示变更时间、操作人员、状态说明
    - _需求: 9.5, 10.5_

### 第七阶段：管理者仪表板

- [x] 15. 实现仪表板功能
  - [x] 15.1 更新 DashboardController
    - 获取正在招募的试验数量
    - 获取已报名受试者总数
    - 获取已入组受试者人数
    - 获取待审核受试者数量
    - 获取各试验入组进度数据
    - _需求: 1.1, 1.2, 1.3, 1.4, 1.5_
  
  - [x] 15.2 更新仪表板页面 (dashboard/dashboard.html)
    - 显示统计卡片（试验数、受试者数、入组数、待审核数）
    - 显示入组进度图表
    - 添加快捷跳转链接
    - _需求: 1.1, 1.2, 1.3, 1.4, 1.5_

### 第八阶段：用户权限管理

- [ ] 16. 实现用户管理服务
  - [ ] 16.1 创建 UserService 类（如果不存在）
    - 实现 createUser(User) 方法
    - 实现 getAllUsers() 方法
    - 实现 activateUser(Long userId) 方法
    - 实现 deactivateUser(Long userId) 方法
    - _需求: 11.1, 11.2, 11.4_
  
  - [ ]* 16.2 编写用户服务单元测试
    - 测试创建用户
    - 测试用户名唯一性验证
    - 测试激活/禁用用户
    - _需求: 11.1, 11.2, 11.4_

- [ ] 17. 配置 Spring Security
  - [ ] 17.1 更新 SecurityConfig 配置类
    - 配置 URL 权限规则
    - ADMIN 可访问所有功能
    - RESEARCH_ASSISTANT 可访问试验和受试者管理
    - PARTICIPANT 只能访问受试者端功能
    - _需求: 11.3, 11.5_
  
  - [ ] 17.2 实现自定义 UserDetailsService
    - 从数据库加载用户信息
    - 设置用户角色和权限
    - _需求: 11.5_

- [ ] 18. 创建用户管理页面
  - [ ] 18.1 创建用户列表页面 (admin/users.html)
    - 显示所有用户
    - 显示用户名、角色、状态
    - 添加创建、激活、禁用按钮
    - _需求: 11.1, 11.4_
  
  - [ ] 18.2 创建用户表单页面 (admin/user-form.html)
    - 表单字段：用户名、密码、角色
    - 添加表单验证
    - _需求: 11.1, 11.2, 11.3_

### 第九阶段：侧边栏和导航更新

- [x] 19. 更新侧边栏菜单
  - [x] 19.1 更新 sidebar.html
    - 添加临床试验管理菜单项
    - 添加受试者管理菜单项
    - 添加智能匹配菜单项
    - 添加入组管理菜单项
    - 根据用户角色显示/隐藏菜单项
    - 所有菜单项已中文化
    - _需求: 所有功能模块_

### 第十阶段：集成测试与优化

- [ ] 20. 检查点 - 确保所有功能正常运行
  - 测试临床试验的创建、编辑、查看、关闭流程
  - 测试受试者的创建和查看流程
  - 测试智能匹配算法的准确性
  - 测试入组审核流程
  - 测试用户权限控制
  - 确认所有页面样式正常
  - 确认所有链接跳转正确

- [ ]* 21. 性能优化（可选）
  - 添加数据库索引
  - 实现分页查询
  - 添加缓存机制
  - _需求: 12.1, 12.2, 12.3, 12.4_

- [ ]* 22. 编写集成测试（可选）
  - 测试完整的入组流程
  - 测试权限控制
  - 测试数据一致性
  - _需求: 所有功能模块_

## 注意事项

1. 标记 `*` 的任务为可选任务，可根据时间安排决定是否实现
2. 每个任务完成后，建议进行手动测试验证功能正确性
3. 智能匹配算法是系统核心，务必确保测试充分
4. 注意数据库事务管理，确保数据一致性
5. 前端页面使用现有的 Bootstrap 样式保持一致性
