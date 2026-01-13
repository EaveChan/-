# Requirements Document - 系统管理员端

## Introduction

系统管理员端（Admin Portal）用于系统层面的运行维护与安全管理，主要负责用户管理、权限控制以及系统基础运行保障。本文档定义系统管理员端的功能需求。

## Glossary

- **System_Admin**: 系统管理员，负责系统层面的运行维护与安全管理
- **User_Management**: 用户管理模块，用于创建、编辑、启用、禁用用户账号
- **Role**: 用户角色，包括 ADMIN（管理员）、RESEARCH_ASSISTANT（研究助理）、PARTICIPANT（受试者）
- **System_Overview**: 系统概览页面，展示系统整体运行状态和统计信息
- **Permission_Management**: 权限管理模块，展示不同角色的访问权限

## Requirements

### Requirement 1: 系统概览页面

**User Story:** 作为系统管理员，我想查看系统的整体运行状态，以便快速了解系统的使用情况。

#### Acceptance Criteria

1. WHEN 系统管理员访问系统概览页面 THEN THE System SHALL 显示系统用户总数
2. WHEN 系统管理员访问系统概览页面 THEN THE System SHALL 显示各角色用户数量统计（管理员/研究助理/受试者）
3. WHEN 系统管理员访问系统概览页面 THEN THE System SHALL 显示临床试验总数量
4. WHEN 系统管理员访问系统概览页面 THEN THE System SHALL 显示系统当前运行状态
5. WHEN 系统管理员访问系统概览页面 THEN THE System SHALL 提供快捷入口跳转到用户管理

### Requirement 2: 用户管理功能（已实现）

**User Story:** 作为系统管理员，我想管理系统用户账号，以便控制系统访问权限。

#### Acceptance Criteria

1. WHEN 系统管理员访问用户列表 THEN THE System SHALL 显示所有用户的基本信息（ID、姓名、邮箱、角色、状态）
2. WHEN 系统管理员创建新用户 THEN THE System SHALL 验证邮箱唯一性
3. WHEN 系统管理员创建新用户 THEN THE System SHALL 加密存储用户密码
4. WHEN 系统管理员编辑用户 THEN THE System SHALL 允许修改姓名、邮箱、角色
5. WHEN 系统管理员编辑用户密码 THEN THE System SHALL 仅在提供新密码时更新密码
6. WHEN 系统管理员禁用用户 THEN THE System SHALL 将用户状态设置为禁用
7. WHEN 系统管理员激活用户 THEN THE System SHALL 将用户状态设置为活跃
8. WHEN 被禁用的用户尝试登录 THEN THE System SHALL 拒绝登录请求

### Requirement 3: 角色与权限管理

**User Story:** 作为系统管理员，我想查看不同角色的权限说明，以便了解系统的权限控制规则。

#### Acceptance Criteria

1. WHEN 系统管理员访问角色权限页面 THEN THE System SHALL 显示所有预置角色（ADMIN、RESEARCH_ASSISTANT、PARTICIPANT）
2. WHEN 系统管理员访问角色权限页面 THEN THE System SHALL 显示每个角色的权限列表
3. WHEN 系统管理员访问角色权限页面 THEN THE System SHALL 以清晰的表格形式展示角色与权限的对应关系
4. THE System SHALL 使用基于角色的访问控制（RBAC）模型
5. THE System SHALL 在后端通过角色注解方式实现权限控制

### Requirement 4: 用户筛选与搜索

**User Story:** 作为系统管理员，我想按角色筛选和搜索用户，以便快速找到目标用户。

#### Acceptance Criteria

1. WHEN 系统管理员在用户列表页面选择角色筛选 THEN THE System SHALL 仅显示该角色的用户
2. WHEN 系统管理员在用户列表页面输入用户名搜索 THEN THE System SHALL 显示匹配的用户
3. WHEN 系统管理员清除筛选条件 THEN THE System SHALL 显示所有用户

### Requirement 5: 安全性要求

**User Story:** 作为系统管理员，我想确保系统的安全性，以便保护用户数据和系统资源。

#### Acceptance Criteria

1. THE System SHALL 仅允许具有 ADMIN 或 Default-Admin 角色的用户访问系统管理员端
2. THE System SHALL 不允许删除系统管理员账号
3. THE System SHALL 在禁用用户时保留用户数据
4. THE System SHALL 使用 BCrypt 算法加密用户密码
5. WHEN 用户创建失败 THEN THE System SHALL 显示明确的错误信息

### Requirement 6: 导航与用户体验

**User Story:** 作为系统管理员，我想有清晰的导航结构，以便快速访问各个功能模块。

#### Acceptance Criteria

1. WHEN 系统管理员登录 THEN THE System SHALL 默认显示系统概览页面
2. THE System SHALL 在侧边栏显示系统管理员端的所有功能菜单
3. THE System SHALL 在页面顶部显示当前页面标题和面包屑导航
4. WHEN 操作成功 THEN THE System SHALL 显示成功提示消息
5. WHEN 操作失败 THEN THE System SHALL 显示错误提示消息
