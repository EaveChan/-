# 临床受试者智能匹配与管理系统 - 完整设计文档

## 文档信息

- **项目名称**：临床受试者智能匹配与管理系统（Clinical Trial Management System, CTMS）
- **版本**：v1.0
- **最后更新**：2026年1月14日
- **文档类型**：系统设计与实现文档

---

## 目录

1. [项目概述](#1-项目概述)
2. [系统角色设计](#2-系统角色设计)
3. [功能模块设计](#3-功能模块设计)
4. [技术架构](#4-技术架构)
5. [数据库设计](#5-数据库设计)
6. [核心功能实现](#6-核心功能实现)
7. [智能匹配算法](#7-智能匹配算法)
8. [页面设计](#8-页面设计)
9. [安全与权限](#9-安全与权限)
10. [移动端适配](#10-移动端适配)
11. [部署与运维](#11-部署与运维)
12. [未来规划](#12-未来规划)

---

## 1. 项目概述

### 1.1 项目背景

临床试验是新药研发和医疗技术验证的关键环节，受试者招募是临床试验成功的重要因素。传统的受试者招募方式存在以下问题：

- **效率低下**：人工筛选受试者耗时耗力
- **匹配不准**：难以快速找到最合适的受试者
- **信息分散**：受试者信息管理混乱
- **沟通困难**：受试者与研究机构沟通不畅

### 1.2 项目目标

本系统旨在构建一个智能化的临床试验受试者管理平台，实现：

✅ **智能匹配**：基于多维度评分算法，自动匹配最合适的受试者  
✅ **高效管理**：统一管理临床试验、受试者、入组申请  
✅ **便捷报名**：受试者可在线浏览试验并提交报名  
✅ **移动支持**：支持移动端访问，随时随地管理  
✅ **权限分离**：多角色权限管理，保障数据安全

### 1.3 核心价值

- **提高招募效率**：智能匹配算法将筛选时间从数小时缩短至数秒
- **提升匹配准确度**：多维度评分确保推荐最合适的受试者
- **优化用户体验**：简洁直观的界面，降低使用门槛
- **保障数据安全**：严格的权限控制和数据加密



---

## 2. 系统角色设计

### 2.1 角色概览

系统设计了三种核心角色，每种角色具有不同的权限和功能：

| 角色 | 英文标识 | 主要职责 | 访问路径 |
|------|---------|---------|---------|
| 系统管理员 | ADMIN | 系统管理、用户管理、权限控制 | `/admin/**` |
| 研究助理 | RESEARCH_ASSISTANT | 临床试验管理、受试者管理、智能匹配 | `/trials/**`, `/subjects/**`, `/matching/**` |
| 受试者 | PARTICIPANT | 浏览试验、在线报名、查看状态 | `/participant/**` |

### 2.2 系统管理员（ADMIN）

#### 职责定位
系统管理员负责系统层面的运行维护与安全管理，不直接参与临床试验管理或受试者匹配。

#### 核心功能
1. **系统概览**
   - 查看系统运行状态
   - 统计各角色用户数量
   - 监控临床试验总数
   - 查看系统关键指标

2. **用户管理**
   - 创建、编辑、查看用户
   - 按角色筛选用户
   - 按用户名搜索
   - 启用/禁用账号
   - 重置用户密码

3. **角色与权限管理**
   - 查看角色权限列表
   - 了解各角色功能范围
   - 权限说明和文档

#### 权限范围
- ✅ 访问系统管理员端（`/admin/**`）
- ✅ 管理所有用户账号
- ✅ 查看系统统计数据
- ❌ 不能直接管理临床试验
- ❌ 不能参与受试者匹配决策

#### 业务规则
- 系统管理员账号不允许被删除
- 禁用操作仅改变状态，不删除数据
- 新创建用户使用初始密码（123456）

### 2.3 研究助理（RESEARCH_ASSISTANT）

#### 职责定位
研究助理是临床试验的核心执行者，负责试验管理、受试者招募、智能匹配和入组决策。

#### 核心功能
1. **临床试验管理**
   - 创建、编辑、查看、删除临床试验
   - 设置试验基本信息（名称、描述、类型）
   - 定义入组条件（年龄、性别、健康要求）
   - 设置排除条件
   - 管理试验状态（招募中/已完成）

2. **受试者管理**
   - 添加、编辑、查看、删除受试者
   - 维护受试者基本信息
   - 记录健康状况和既往病史
   - 搜索和筛选受试者

3. **智能匹配**
   - 为临床试验智能匹配受试者
   - 查看匹配分数和排名
   - 查看匹配说明和评分细节
   - 查看不符合条件的受试者

4. **入组管理**
   - 查看所有报名申请
   - 审核报名申请（批准/拒绝/人工复核）
   - 查看智能匹配分数
   - 管理入组流程

5. **数据统计**
   - 查看仪表板统计数据
   - 临床试验进展分析
   - 受试者匹配情况
   - 入组率统计

#### 权限范围
- ✅ 访问临床试验管理（`/trials/**`）
- ✅ 访问受试者管理（`/subjects/**`）
- ✅ 访问智能匹配（`/matching/**`）
- ✅ 访问入组管理（`/enroll/**`）
- ✅ 访问仪表板（`/dashboard`）
- ❌ 不能访问系统管理员端
- ❌ 不能管理用户账号

### 2.4 受试者（PARTICIPANT）

#### 职责定位
受试者是临床试验的潜在参与者，可以浏览试验信息、在线报名并跟踪申请状态。

#### 核心功能
1. **受试者首页**
   - 查看系统功能简介
   - 查看可报名试验数量
   - 查看我的报名数量
   - 快捷入口导航

2. **浏览临床试验**
   - 查看所有招募中的试验
   - 查看试验基本信息
   - 查看入组条件和排除条件
   - 识别已报名的试验

3. **在线报名**
   - 查看试验详细信息
   - 填写报名表单
   - 提交健康状况描述
   - 提交既往病史
   - 系统自动计算匹配分数

4. **我的报名**
   - 查看所有报名记录
   - 查看审核状态
   - 跟踪申请进展
   - 了解状态说明

5. **个人信息管理**
   - 维护基本信息（年龄、性别）
   - 更新健康状况描述
   - 更新既往病史
   - 查看账号信息

#### 权限范围
- ✅ 访问受试者端（`/participant/**`）
- ✅ 浏览临床试验
- ✅ 提交报名申请
- ✅ 查看个人报名记录
- ✅ 管理个人信息
- ❌ 不能访问管理端
- ❌ 不能查看其他受试者信息
- ❌ 不能查看匹配分数细节

#### 业务规则
- 每个临床试验只能报名一次
- 必须填写健康状况描述
- 年龄和性别从个人信息读取
- 报名时自动计算匹配分数
- 个人信息修改会影响后续匹配



---

## 3. 功能模块设计

### 3.1 功能架构图

```
临床受试者智能匹配与管理系统
├── 系统管理员端
│   ├── 系统概览
│   ├── 用户管理
│   └── 角色权限管理
├── 研究助理端
│   ├── 仪表板
│   ├── 临床试验管理
│   ├── 受试者管理
│   ├── 智能匹配
│   └── 入组管理
└── 受试者端
    ├── 受试者首页
    ├── 临床试验列表
    ├── 试验详情与报名
    ├── 我的报名
    └── 个人信息管理
```

### 3.2 临床试验管理模块

#### 功能描述
管理临床试验的完整生命周期，包括创建、编辑、查看、删除和状态管理。

#### 核心功能
1. **试验列表**
   - 展示所有临床试验
   - 支持搜索和筛选
   - 显示试验状态
   - 显示已入组人数

2. **创建试验**
   - 填写试验基本信息
   - 设置入组条件
   - 定义排除条件
   - 设置试验状态

3. **编辑试验**
   - 修改试验信息
   - 更新入组条件
   - 调整排除条件

4. **查看试验详情**
   - 查看完整试验信息
   - 查看入组统计
   - 查看报名列表

5. **删除试验**
   - 删除试验记录
   - 级联删除相关数据

#### 数据字段
- 试验名称（必填）
- 试验描述（必填）
- 试验类型（药物/器械/疫苗等）
- 疾病领域
- 年龄范围（最小-最大）
- 性别要求（男/女/不限）
- 健康要求
- 排除条件
- 持续时间（天数）
- 预期受试者数量
- 研究机构
- 试验状态（招募中/已完成）

### 3.3 受试者管理模块

#### 功能描述
管理受试者的基本信息、健康状况和参与记录。

#### 核心功能
1. **受试者列表**
   - 展示所有受试者
   - 支持搜索和筛选
   - 显示基本信息
   - 显示参与状态

2. **添加受试者**
   - 填写基本信息
   - 记录健康状况
   - 记录既往病史

3. **编辑受试者**
   - 更新基本信息
   - 更新健康状况
   - 更新既往病史

4. **查看受试者详情**
   - 查看完整信息
   - 查看参与历史
   - 查看匹配记录

5. **删除受试者**
   - 删除受试者记录
   - 级联删除相关数据

#### 数据字段
- 姓名（必填）
- 年龄（必填）
- 性别（必填）
- 联系方式
- 健康状况描述
- 既往病史
- 账号状态（活跃/禁用）

### 3.4 智能匹配模块

#### 功能描述
基于多维度评分算法，为临床试验自动匹配最合适的受试者。

#### 核心功能
1. **智能匹配列表**
   - 选择临床试验
   - 查看所有试验
   - 查看试验基本信息

2. **匹配结果展示**
   - 显示匹配分数（0-100分）
   - 显示匹配等级（优秀/良好/基本）
   - 显示匹配说明
   - 按分数降序排列

3. **查看不符合条件的受试者**
   - 显示被排除的受试者
   - 显示排除原因
   - 帮助理解筛选逻辑

4. **人工复核**
   - 标记需要人工复核的申请
   - 进一步评估边缘案例

#### 匹配算法
详见 [第7章：智能匹配算法](#7-智能匹配算法)

### 3.5 入组管理模块

#### 功能描述
管理受试者的报名申请，包括审核、批准、拒绝和入组流程。

#### 核心功能
1. **申请列表**
   - 展示所有报名申请
   - 按状态筛选
   - 按试验筛选
   - 显示匹配分数

2. **审核申请**
   - 查看申请详情
   - 查看匹配分数
   - 查看受试者信息
   - 做出审核决策

3. **批准申请**
   - 批准符合条件的申请
   - 更新申请状态为"已批准"
   - 通知受试者

4. **拒绝申请**
   - 拒绝不符合条件的申请
   - 更新申请状态为"已拒绝"
   - 记录拒绝原因

5. **人工复核**
   - 标记需要进一步评估的申请
   - 更新申请状态为"人工复核"
   - 安排专家评估

6. **入组管理**
   - 将已批准的申请标记为"已入组"
   - 更新试验入组人数
   - 记录入组时间

#### 申请状态流转
```
PENDING（待审核）
    ↓
    ├→ APPROVED（已批准）→ ENROLLED（已入组）
    ├→ REJECTED（已拒绝）
    └→ MANUAL_REVIEW（人工复核）→ APPROVED/REJECTED
```

### 3.6 数据统计模块

#### 功能描述
提供系统运行数据的统计和可视化展示。

#### 核心功能
1. **仪表板概览**
   - 临床试验总数
   - 活跃试验数量
   - 受试者总数
   - 待审核申请数
   - 总入组人数

2. **图表展示**
   - 试验进展趋势图
   - 入组率统计图
   - 匹配分数分布图
   - 申请状态饼图

3. **数据导出**
   - 导出统计报表
   - 导出受试者列表
   - 导出申请记录



---

## 4. 技术架构

### 4.1 技术栈概览

#### 后端技术
| 技术 | 版本 | 用途 |
|------|------|------|
| Java | 17 | 编程语言 |
| Spring Boot | 2.7.x | 应用框架 |
| Spring Security | 5.7.x | 安全框架 |
| Spring Data JPA | 2.7.x | 数据访问 |
| Hibernate | 5.6.x | ORM框架 |
| MySQL | 8.0 | 关系数据库 |
| Maven | 3.8.x | 项目管理 |

#### 前端技术
| 技术 | 版本 | 用途 |
|------|------|------|
| Thymeleaf | 3.0.x | 模板引擎 |
| Bootstrap | 4.6.2 | UI框架 |
| jQuery | 3.6.4 | JavaScript库 |
| Font Awesome | 5.15.4 | 图标库 |
| Chart.js | 3.x | 图表库 |
| Bootstrap Icons | 1.10.x | 图标库 |

#### 开发工具
- **IDE**: IntelliJ IDEA / Eclipse
- **数据库工具**: Navicat / MySQL Workbench
- **版本控制**: Git / GitHub
- **API测试**: Postman
- **浏览器**: Chrome DevTools

### 4.2 系统架构

#### 分层架构
```
┌─────────────────────────────────────┐
│         表现层 (Presentation)        │
│  Thymeleaf + Bootstrap + jQuery     │
└─────────────────────────────────────┘
                  ↓
┌─────────────────────────────────────┐
│          控制层 (Controller)         │
│    Spring MVC + REST API            │
└─────────────────────────────────────┘
                  ↓
┌─────────────────────────────────────┐
│          业务层 (Service)            │
│    业务逻辑 + 智能匹配算法           │
└─────────────────────────────────────┘
                  ↓
┌─────────────────────────────────────┐
│         数据访问层 (Repository)       │
│    Spring Data JPA + Hibernate      │
└─────────────────────────────────────┘
                  ↓
┌─────────────────────────────────────┐
│          数据层 (Database)           │
│           MySQL 8.0                 │
└─────────────────────────────────────┘
```

#### 核心模块
1. **安全模块** (Security)
   - 用户认证
   - 权限控制
   - 密码加密
   - 会话管理

2. **业务模块** (Business)
   - 临床试验管理
   - 受试者管理
   - 智能匹配
   - 入组管理

3. **工具模块** (Utility)
   - 文本标准化
   - 近义词匹配
   - 数据验证
   - 异常处理

### 4.3 项目结构

```
src/main/java/com/ace/job/recruitment/
├── config/                          # 配置类
│   ├── WebSecurityConfig.java      # 安全配置
│   ├── CustomAuthenticationSuccessHandler.java  # 登录成功处理
│   └── DefaultAdminInitializer.java # 默认管理员初始化
├── controller/                      # 控制器
│   ├── AdminController.java        # 系统管理员控制器
│   ├── ClinicalTrialController.java # 临床试验控制器
│   ├── SubjectController.java      # 受试者控制器
│   ├── MatchingController.java     # 智能匹配控制器
│   ├── EnrollmentController.java   # 入组管理控制器
│   ├── ParticipantController.java  # 受试者端控制器
│   ├── DashboardController.java    # 仪表板控制器
│   └── HomeController.java         # 首页控制器
├── entity/                          # 实体类
│   ├── User.java                   # 用户实体
│   ├── ClinicalTrial.java          # 临床试验实体
│   ├── EnrollmentApplication.java  # 报名申请实体
│   ├── MatchingRule.java           # 匹配规则实体
│   └── PasswordReset.java          # 密码重置实体
├── repository/                      # 数据访问层
│   ├── UserRepository.java
│   ├── ClinicalTrialRepository.java
│   ├── EnrollmentRepository.java
│   └── MatchingRuleRepository.java
├── service/                         # 服务接口
│   ├── UserService.java
│   ├── ClinicalTrialService.java
│   ├── SubjectService.java
│   ├── MatchingService.java
│   ├── EnrollmentService.java
│   ├── AdminStatisticsService.java
│   └── DashboardService.java
├── service/impl/                    # 服务实现
│   ├── UserServiceImpl.java
│   ├── ClinicalTrialServiceImpl.java
│   ├── SubjectServiceImpl.java
│   ├── MatchingServiceImpl.java    # 智能匹配算法实现
│   ├── EnrollmentServiceImpl.java
│   ├── AdminStatisticsServiceImpl.java
│   └── DashboardServiceImpl.java
├── dto/                             # 数据传输对象
│   ├── MatchingResult.java         # 匹配结果DTO
│   ├── MatchingConfig.java         # 匹配配置DTO
│   ├── SystemStatistics.java       # 系统统计DTO
│   ├── RolePermission.java         # 角色权限DTO
│   └── DashboardCountDTO.java      # 仪表板统计DTO
├── util/                            # 工具类
│   └── TextNormalizer.java         # 文本标准化工具
└── model/                           # 模型类
    └── AppUserDetails.java         # 用户详情模型
```

### 4.4 数据流

#### 用户登录流程
```
用户输入账号密码
    ↓
Spring Security 拦截
    ↓
AppUserDetailsService 加载用户
    ↓
BCryptPasswordEncoder 验证密码
    ↓
CustomAuthenticationSuccessHandler 处理登录成功
    ↓
根据角色跳转到对应页面
```

#### 智能匹配流程
```
研究助理选择临床试验
    ↓
MatchingController 接收请求
    ↓
MatchingService 执行匹配算法
    ├→ 加载所有活跃受试者
    ├→ 逐个计算匹配分数
    ├→ 生成匹配说明
    └→ 按分数降序排序
    ↓
返回匹配结果列表
    ↓
页面展示匹配结果
```

#### 受试者报名流程
```
受试者浏览临床试验
    ↓
选择试验查看详情
    ↓
填写报名表单
    ↓
ParticipantController 接收报名
    ↓
EnrollmentService 创建申请
    ↓
MatchingService 计算匹配分数
    ↓
保存申请记录（状态：PENDING）
    ↓
返回报名成功页面
```

### 4.5 安全设计

#### 认证机制
- **Spring Security** 提供认证和授权
- **BCrypt** 加密用户密码
- **Session** 管理用户会话
- **Remember Me** 支持记住登录

#### 权限控制
```java
// 基于角色的访问控制（RBAC）
.antMatchers("/admin/**").hasAnyAuthority("Admin", "Default-Admin")
.antMatchers("/trials/**").hasAnyAuthority("Admin", "Default-Admin", "RESEARCH_ASSISTANT")
.antMatchers("/participant/**").hasAnyAuthority("PARTICIPANT")
```

#### 数据安全
- 密码使用 BCrypt 加密存储
- 敏感数据传输使用 HTTPS（生产环境）
- SQL 注入防护（JPA 参数化查询）
- XSS 防护（Thymeleaf 自动转义）
- CSRF 防护（Spring Security 默认启用）



---

## 5. 数据库设计

### 5.1 数据库概览

- **数据库类型**：MySQL 8.0
- **字符集**：UTF-8
- **存储引擎**：InnoDB
- **核心表数量**：5个

### 5.2 核心数据表

#### 5.2.1 用户表 (user)

存储系统所有用户的基本信息和认证信息。

| 字段名 | 类型 | 说明 | 约束 |
|--------|------|------|------|
| id | BIGINT | 主键 | PK, AUTO_INCREMENT |
| name | VARCHAR(100) | 用户姓名 | NOT NULL |
| email | VARCHAR(100) | 邮箱（登录账号） | NOT NULL, UNIQUE |
| password | VARCHAR(255) | 加密密码 | NOT NULL |
| role | VARCHAR(50) | 角色 | NOT NULL |
| age | INT | 年龄 | NULL |
| gender | VARCHAR(10) | 性别 | NULL |
| health_condition | TEXT | 健康状况描述 | NULL |
| medical_history | TEXT | 既往病史 | NULL |
| status | BOOLEAN | 账号状态 | DEFAULT TRUE |
| created_at | DATETIME | 创建时间 | DEFAULT CURRENT_TIMESTAMP |
| updated_at | DATETIME | 更新时间 | ON UPDATE CURRENT_TIMESTAMP |

**角色枚举**：
- `Admin` / `Default-Admin` - 系统管理员
- `RESEARCH_ASSISTANT` - 研究助理
- `PARTICIPANT` - 受试者

#### 5.2.2 临床试验表 (clinical_trials)

存储临床试验的详细信息和入组条件。

| 字段名 | 类型 | 说明 | 约束 |
|--------|------|------|------|
| id | BIGINT | 主键 | PK, AUTO_INCREMENT |
| trial_name | VARCHAR(200) | 试验名称 | NOT NULL |
| description | TEXT | 试验描述 | NOT NULL |
| trial_type | VARCHAR(50) | 试验类型 | NOT NULL |
| disease_area | VARCHAR(100) | 疾病领域 | NULL |
| age_min | INT | 最小年龄 | NOT NULL |
| age_max | INT | 最大年龄 | NOT NULL |
| gender_requirement | VARCHAR(20) | 性别要求 | NULL |
| health_requirements | TEXT | 健康要求 | NULL |
| exclusion_criteria | TEXT | 排除条件 | NULL |
| duration_days | INT | 持续时间（天） | NULL |
| expected_participants | INT | 预期受试者数 | NULL |
| research_institution | VARCHAR(200) | 研究机构 | NULL |
| status | VARCHAR(20) | 试验状态 | DEFAULT 'RECRUITING' |
| created_at | DATETIME | 创建时间 | DEFAULT CURRENT_TIMESTAMP |
| created_user_id | BIGINT | 创建人ID | FK → user(id) |
| updated_at | DATETIME | 更新时间 | ON UPDATE CURRENT_TIMESTAMP |
| updated_user_id | BIGINT | 更新人ID | FK → user(id) |

**试验状态枚举**：
- `RECRUITING` - 招募中
- `COMPLETED` - 已完成

#### 5.2.3 报名申请表 (enrollment_applications)

存储受试者的报名申请记录和审核状态。

| 字段名 | 类型 | 说明 | 约束 |
|--------|------|------|------|
| id | BIGINT | 主键 | PK, AUTO_INCREMENT |
| user_id | BIGINT | 受试者ID | FK → user(id), NOT NULL |
| trial_id | BIGINT | 临床试验ID | FK → clinical_trials(id), NOT NULL |
| age | INT | 报名时年龄 | NOT NULL |
| gender | VARCHAR(10) | 报名时性别 | NOT NULL |
| health_condition | TEXT | 健康状况描述 | NOT NULL |
| medical_history | TEXT | 既往病史 | NULL |
| matching_score | INT | 智能匹配分数 | NULL |
| status | VARCHAR(20) | 申请状态 | DEFAULT 'PENDING' |
| created_at | DATETIME | 报名时间 | DEFAULT CURRENT_TIMESTAMP |
| reviewed_at | DATETIME | 审核时间 | NULL |
| reviewed_by | BIGINT | 审核人ID | FK → user(id), NULL |

**申请状态枚举**：
- `PENDING` - 待审核
- `APPROVED` - 已批准
- `REJECTED` - 已拒绝
- `ENROLLED` - 已入组
- `MANUAL_REVIEW` - 人工复核

**唯一约束**：`UNIQUE(user_id, trial_id)` - 每个受试者每个试验只能报名一次

#### 5.2.4 匹配规则表 (matching_rules)

存储智能匹配算法使用的排除规则和优先规则。

| 字段名 | 类型 | 说明 | 约束 |
|--------|------|------|------|
| id | BIGINT | 主键 | PK, AUTO_INCREMENT |
| rule_type | VARCHAR(20) | 规则类型 | NOT NULL |
| keywords | TEXT | 关键词（逗号分隔） | NOT NULL |
| description | VARCHAR(500) | 规则描述 | NULL |
| priority | INT | 优先级 | DEFAULT 50 |
| weight_score | INT | 权重分数 | NULL |
| is_active | BOOLEAN | 是否启用 | DEFAULT TRUE |
| created_at | DATETIME | 创建时间 | DEFAULT CURRENT_TIMESTAMP |

**规则类型枚举**：
- `EXCLUSION` - 排除规则（命中则淘汰）
- `PRIORITY` - 优先规则（命中则加分）

#### 5.2.5 密码重置表 (password_reset)

存储密码重置请求的验证令牌。

| 字段名 | 类型 | 说明 | 约束 |
|--------|------|------|------|
| id | BIGINT | 主键 | PK, AUTO_INCREMENT |
| user_id | BIGINT | 用户ID | FK → user(id), NOT NULL |
| token | VARCHAR(255) | 重置令牌 | NOT NULL, UNIQUE |
| expiry_date | DATETIME | 过期时间 | NOT NULL |
| created_at | DATETIME | 创建时间 | DEFAULT CURRENT_TIMESTAMP |

### 5.3 数据库关系图

```
user (用户表)
  ├─→ clinical_trials (创建的临床试验)
  ├─→ enrollment_applications (提交的报名申请)
  ├─→ enrollment_applications (审核的申请)
  └─→ password_reset (密码重置请求)

clinical_trials (临床试验表)
  └─→ enrollment_applications (收到的报名申请)

matching_rules (匹配规则表)
  └── 独立表，供匹配算法使用
```

### 5.4 索引设计

#### 用户表索引
```sql
CREATE INDEX idx_user_email ON user(email);
CREATE INDEX idx_user_role ON user(role);
CREATE INDEX idx_user_status ON user(status);
```

#### 临床试验表索引
```sql
CREATE INDEX idx_trial_status ON clinical_trials(status);
CREATE INDEX idx_trial_created_at ON clinical_trials(created_at);
```

#### 报名申请表索引
```sql
CREATE INDEX idx_application_user_id ON enrollment_applications(user_id);
CREATE INDEX idx_application_trial_id ON enrollment_applications(trial_id);
CREATE INDEX idx_application_status ON enrollment_applications(status);
CREATE INDEX idx_application_created_at ON enrollment_applications(created_at);
```

#### 匹配规则表索引
```sql
CREATE INDEX idx_rule_type ON matching_rules(rule_type);
CREATE INDEX idx_rule_active ON matching_rules(is_active);
```

### 5.5 数据初始化

#### 默认管理员账号
```sql
-- 系统管理员
INSERT INTO user (name, email, password, role, status)
VALUES ('System Admin', 'system_admin@gmail.com', 
        '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHCYAKMn.0VUgKXVCh0OoC',
        'Admin', TRUE);
```

#### 匹配规则初始化
详见 `sql script/matching_rules_init.sql`



---

## 6. 核心功能实现

### 6.1 用户认证与授权

#### 登录流程
1. 用户访问 `/login` 页面
2. 输入邮箱和密码
3. Spring Security 拦截登录请求
4. `AppUserDetailsService` 从数据库加载用户
5. `BCryptPasswordEncoder` 验证密码
6. `CustomAuthenticationSuccessHandler` 根据角色跳转：
   - Admin → `/admin/overview`
   - RESEARCH_ASSISTANT → `/trials`
   - PARTICIPANT → `/participant`

#### 权限控制实现
```java
@Configuration
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/admin/**").hasAnyAuthority("Admin", "Default-Admin")
            .antMatchers("/trials/**").hasAnyAuthority("Admin", "RESEARCH_ASSISTANT")
            .antMatchers("/participant/**").hasAnyAuthority("PARTICIPANT")
            .anyRequest().authenticated();
        return http.build();
    }
}
```

### 6.2 临床试验管理实现

#### 创建试验
```java
@PostMapping("/add")
public String addTrial(@ModelAttribute ClinicalTrial trial) {
    trial.setCreatedUserId(getCurrentUserId());
    trial.setStatus("RECRUITING");
    clinicalTrialService.saveTrial(trial);
    return "redirect:/trials";
}
```

#### 查询试验
```java
@GetMapping
public String listTrials(Model model) {
    List<ClinicalTrial> trials = clinicalTrialService.getAllTrials();
    model.addAttribute("trials", trials);
    return "trials/list";
}
```

### 6.3 智能匹配实现

详见 [第7章：智能匹配算法](#7-智能匹配算法)

### 6.4 报名申请处理

#### 受试者提交报名
```java
@PostMapping("/trials/{id}/apply")
public String applyForTrial(@PathVariable Long id, 
                           @ModelAttribute EnrollmentApplication application) {
    User currentUser = getCurrentUser();
    ClinicalTrial trial = clinicalTrialService.getTrialById(id);
    
    // 设置申请信息
    application.setUserId(currentUser.getId());
    application.setTrialId(id);
    application.setAge(currentUser.getAge());
    application.setGender(currentUser.getGender());
    application.setStatus("PENDING");
    
    // 计算匹配分数
    int matchingScore = matchingService.calculateMatchingScore(currentUser, trial);
    application.setMatchingScore(matchingScore);
    
    // 保存申请
    enrollmentService.saveApplication(application);
    
    return "redirect:/participant/my-applications";
}
```

#### 研究助理审核申请
```java
@PostMapping("/enroll/{id}/approve")
public String approveApplication(@PathVariable Long id) {
    EnrollmentApplication application = enrollmentService.getApplicationById(id);
    application.setStatus("APPROVED");
    application.setReviewedAt(LocalDateTime.now());
    application.setReviewedBy(getCurrentUserId());
    enrollmentService.updateApplication(application);
    return "redirect:/enroll";
}
```

---

## 7. 智能匹配算法

### 7.1 算法概述

智能匹配算法是系统的核心功能，基于多维度评分机制，为临床试验自动筛选和推荐最合适的受试者。

### 7.2 评分体系（总分100分）

| 维度 | 分值 | 说明 |
|------|------|------|
| 硬性条件 | 0分（不通过） | 年龄、性别、排除条件必须符合 |
| 基础分 | 60分 | 满足所有硬性条件后的基础分 |
| 年龄优先级 | 0-20分 | 年龄越接近理想值，分数越高 |
| 健康状况匹配 | 0-20分 | 健康状况与试验要求的匹配度 |
| 优先条件加分 | 0-10分 | 命中优先规则的额外加分 |

### 7.3 匹配流程

```
1. 加载所有活跃受试者
    ↓
2. 加载匹配规则（排除规则 + 优先规则）
    ↓
3. 逐个受试者评分：
    ├→ 检查年龄范围（不符合 → 0分）
    ├→ 检查性别要求（不符合 → 0分）
    ├→ 检查排除条件（命中 → 0分）
    ├→ 基础分：60分
    ├→ 年龄优先级分：0-20分
    ├→ 健康状况匹配分：0-20分
    └→ 优先条件加分：0-10分
    ↓
4. 生成匹配说明
    ↓
5. 按分数降序排序
    ↓
6. 返回匹配结果列表
```

### 7.4 核心算法代码

```java
public int calculateMatchingScore(User subject, ClinicalTrial trial) {
    // 1. 硬性条件检查
    if (subject.getAge() < trial.getAgeMin() || subject.getAge() > trial.getAgeMax()) {
        return 0; // 年龄不符
    }
    
    if (!checkGenderRequirement(subject, trial)) {
        return 0; // 性别不符
    }
    
    if (checkExclusionCriteria(subject, trial)) {
        return 0; // 命中排除条件
    }
    
    // 2. 基础分
    int score = 60;
    
    // 3. 年龄优先级分（0-20分）
    score += calculateAgeScore(subject.getAge(), trial.getAgeMin(), trial.getAgeMax());
    
    // 4. 健康状况匹配分（0-20分）
    score += calculateHealthMatchScore(subject.getHealthCondition(), 
                                      trial.getHealthRequirements());
    
    // 5. 优先条件加分（0-10分）
    score += calculatePriorityScore(subject);
    
    return Math.min(score, 100); // 最高100分
}
```

### 7.5 算法优势

- ✅ **自动化筛选**：秒级完成数百名受试者的匹配
- ✅ **多维度评估**：综合考虑年龄、性别、健康状况等因素
- ✅ **可解释性强**：每个分数都有明确的计算依据
- ✅ **灵活可配置**：支持自定义排除规则和优先规则
- ✅ **准确度高**：基于规则的匹配，避免误判

详细算法设计请参考：`智能匹配算法设计文档.md`

---

## 8. 页面设计

### 8.1 设计原则

- **简洁直观**：界面清晰，操作简单
- **响应式设计**：支持PC端和移动端
- **一致性**：统一的视觉风格和交互模式
- **可访问性**：符合无障碍设计标准

### 8.2 色彩方案

| 用途 | 颜色 | 色值 |
|------|------|------|
| 主色调 | 紫色 | #667eea, #764ba2 |
| 成功 | 绿色 | #28a745 |
| 警告 | 黄色 | #ffc107 |
| 危险 | 红色 | #dc3545 |
| 信息 | 蓝色 | #17a2b8 |
| 文字 | 深灰 | #333333 |
| 背景 | 浅灰 | #f8f9fa |

### 8.3 核心页面

#### 8.3.1 登录页面
- Logo展示
- 邮箱和密码输入
- 记住我选项
- 忘记密码链接
- 错误提示

#### 8.3.2 系统管理员端
1. **系统概览** (`/admin/overview`)
   - 统计卡片（用户数、试验数等）
   - 系统状态展示
   - 快捷入口

2. **用户管理** (`/admin/users`)
   - 用户列表表格
   - 搜索和筛选
   - 添加/编辑/禁用操作

3. **角色权限** (`/admin/roles`)
   - 角色列表
   - 权限说明
   - 功能范围展示

#### 8.3.3 研究助理端
1. **仪表板** (`/dashboard`)
   - 统计卡片
   - 图表展示
   - 数据分析

2. **临床试验列表** (`/trials`)
   - 试验列表表格
   - 搜索和筛选
   - 添加/编辑/删除操作

3. **智能匹配** (`/matching`)
   - 试验选择
   - 匹配结果展示
   - 分数和说明

4. **入组管理** (`/enroll`)
   - 申请列表
   - 状态筛选
   - 审核操作

#### 8.3.4 受试者端
1. **受试者首页** (`/participant`)
   - 功能简介
   - 统计信息
   - 快捷入口

2. **临床试验列表** (`/participant/trials`)
   - 试验卡片展示
   - 试验详情
   - 报名按钮

3. **我的报名** (`/participant/my-applications`)
   - 报名记录列表
   - 状态展示
   - 详情查看

4. **个人信息** (`/participant/profile`)
   - 信息展示
   - 编辑表单
   - 保存按钮

### 8.4 移动端设计

详见 [第10章：移动端适配](#10-移动端适配)

---

## 9. 安全与权限

### 9.1 认证机制

- **Spring Security** 框架
- **BCrypt** 密码加密
- **Session** 会话管理
- **Remember Me** 记住登录

### 9.2 权限控制

#### 基于角色的访问控制（RBAC）
```
Admin → 系统管理员端 + 所有功能
RESEARCH_ASSISTANT → 研究助理端功能
PARTICIPANT → 受试者端功能
```

#### URL权限配置
```java
/admin/** → Admin, Default-Admin
/trials/** → Admin, RESEARCH_ASSISTANT
/subjects/** → Admin, RESEARCH_ASSISTANT
/matching/** → Admin, RESEARCH_ASSISTANT
/enroll/** → Admin, RESEARCH_ASSISTANT, PARTICIPANT
/participant/** → PARTICIPANT
```

### 9.3 数据安全

- 密码BCrypt加密存储
- SQL注入防护（JPA参数化）
- XSS防护（Thymeleaf转义）
- CSRF防护（Spring Security）
- 敏感数据访问控制

---

## 10. 移动端适配

### 10.1 适配方案

采用**响应式Web设计**，无需安装APP，直接通过手机浏览器访问。

### 10.2 核心特性

1. **底部导航栏**
   - 固定在屏幕底部
   - 4个主要导航项
   - 当前页面高亮

2. **布局优化**
   - 隐藏PC端侧边栏和顶部栏
   - 全屏显示内容
   - 为底部导航预留空间

3. **触摸优化**
   - 按钮最小44x44px
   - 全宽按钮设计
   - 增加元素间距

4. **字体和间距**
   - 标题1.5rem（移动端）
   - 正文0.875-1rem
   - 卡片内边距1rem

### 10.3 支持设备

- ✅ iPhone (iOS 12+)
- ✅ Android 手机 (Android 8+)
- ✅ iPad / Android 平板
- ✅ 主流移动浏览器

详细说明请参考：`移动端使用说明.md`

---

## 11. 部署与运维

### 11.1 环境要求

- **JDK**: 17+
- **MySQL**: 8.0+
- **Maven**: 3.8+
- **内存**: 最低2GB

### 11.2 部署步骤

1. **克隆项目**
```bash
git clone https://github.com/your-repo/clinical-trial-system.git
cd clinical-trial-system
```

2. **配置数据库**
```properties
# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/clinical_trial_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. **初始化数据库**
```bash
# 执行SQL脚本
mysql -u root -p clinical_trial_db < sql script/matching_rules_init.sql
mysql -u root -p clinical_trial_db < sql script/insert_system_admin.sql
```

4. **编译项目**
```bash
mvnw clean package
```

5. **运行应用**
```bash
java -jar target/recruitment-0.0.1-SNAPSHOT.jar
```

6. **访问系统**
```
http://localhost:8080
```

### 11.3 默认账号

| 角色 | 邮箱 | 密码 |
|------|------|------|
| 系统管理员 | system_admin@gmail.com | 123456 |

### 11.4 运维建议

- 定期备份数据库
- 监控系统性能
- 定期更新依赖
- 查看应用日志
- 配置HTTPS（生产环境）

---

## 12. 未来规划

### 12.1 短期优化（1-3个月）

- [ ] 添加邮件通知功能
- [ ] 优化智能匹配算法（引入机器学习）
- [ ] 添加数据导出功能
- [ ] 优化移动端体验
- [ ] 添加多语言支持

### 12.2 中期规划（3-6个月）

- [ ] 引入消息推送
- [ ] 添加在线聊天功能
- [ ] 实现PWA支持
- [ ] 添加数据可视化大屏
- [ ] 集成第三方支付

### 12.3 长期愿景（6-12个月）

- [ ] AI智能推荐
- [ ] 区块链数据存证
- [ ] 多中心协同管理
- [ ] 国际化部署
- [ ] 移动APP开发

---

## 附录

### A. 相关文档

- `智能匹配算法设计文档.md` - 详细算法设计
- `智能匹配算法优化方案.md` - 算法优化方案
- `受试者端功能说明.md` - 受试者端功能
- `移动端使用说明.md` - 移动端使用指南
- `移动端测试清单.md` - 移动端测试清单
- `优化功能使用说明.md` - 优化功能说明

### B. SQL脚本

- `matching_rules_init.sql` - 匹配规则初始化
- `insert_system_admin.sql` - 系统管理员账号
- `insert_10_clinical_trials.sql` - 示例临床试验
- `insert_50_participants_part1.sql` - 示例受试者（1-25）
- `insert_50_participants_part2.sql` - 示例受试者（26-50）

### C. 技术支持

如有问题，请联系开发团队或查阅相关文档。

---

**文档版本**：v1.0  
**最后更新**：2026年1月14日  
**作者**：临床受试者智能匹配与管理系统开发团队

---

## 结语

本系统通过智能化的匹配算法和完善的管理功能，显著提高了临床试验受试者招募的效率和准确度。系统采用现代化的技术栈，具有良好的可扩展性和可维护性，为临床试验管理提供了强有力的技术支持。

感谢您阅读本文档！
