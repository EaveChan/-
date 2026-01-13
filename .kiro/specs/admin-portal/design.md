# Design Document - 系统管理员端

## Overview

系统管理员端是一个轻量级的管理后台，专注于用户管理、权限控制和系统监控。设计遵循简洁、安全、易用的原则，避免过度复杂的功能设计。

## Architecture

系统管理员端采用 Spring MVC 架构：

- **Controller Layer**: AdminController 处理 HTTP 请求
- **Service Layer**: 复用现有的 UserRepository 和 ClinicalTrialService
- **View Layer**: Thymeleaf 模板渲染页面
- **Security Layer**: Spring Security 基于角色的访问控制

## Components and Interfaces

### 1. AdminController

负责处理系统管理员端的所有请求：

```java
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('Admin','Default-Admin')")
public class AdminController {
    // 系统概览
    @GetMapping("/overview")
    String showOverview(Model model);
    
    // 角色权限管理
    @GetMapping("/roles")
    String showRoles(Model model);
    
    // 用户管理（已实现）
    // ...
}
```

### 2. 统计服务

创建一个简单的统计服务来获取系统概览数据：

```java
@Service
public class AdminStatisticsService {
    long getTotalUsers();
    long getUserCountByRole(String role);
    long getTotalTrials();
    Map<String, Long> getUserStatsByRole();
}
```

### 3. 角色权限配置

定义角色权限的静态配置：

```java
public class RolePermissions {
    public static final Map<String, List<String>> ROLE_PERMISSIONS = Map.of(
        "ADMIN", List.of("系统概览", "用户管理", "角色权限管理"),
        "RESEARCH_ASSISTANT", List.of("临床试验管理", "受试者管理", "智能匹配", "入组管理"),
        "PARTICIPANT", List.of("查看临床试验", "报名试验", "查看个人信息")
    );
}
```

## Data Models

### 统计数据 DTO

```java
public class SystemStatistics {
    private long totalUsers;
    private long adminCount;
    private long researchAssistantCount;
    private long participantCount;
    private long totalTrials;
    private String systemStatus; // "正常运行"
}
```

### 角色权限 DTO

```java
public class RolePermission {
    private String roleName;
    private String roleDisplayName;
    private List<String> permissions;
}
```

## Correctness Properties

*A property is a characteristic or behavior that should hold true across all valid executions of a system-essentially, a formal statement about what the system should do. Properties serve as the bridge between human-readable specifications and machine-verifiable correctness guarantees.*

### Property 1: 统计数据一致性

*For any* 系统状态，系统概览页面显示的用户总数应该等于各角色用户数量之和

**Validates: Requirements 1.1, 1.2**

### Property 2: 权限控制有效性

*For any* 非管理员用户，访问系统管理员端的任何页面都应该被拒绝

**Validates: Requirements 5.1**

### Property 3: 用户邮箱唯一性

*For any* 新创建的用户，如果邮箱已存在，则创建操作应该失败

**Validates: Requirements 2.2**

### Property 4: 密码加密一致性

*For any* 新创建或更新密码的用户，密码应该使用 BCrypt 算法加密存储

**Validates: Requirements 2.3, 5.4**

### Property 5: 禁用用户数据保留

*For any* 被禁用的用户，其数据应该保留在数据库中，仅状态字段被修改

**Validates: Requirements 2.6, 5.3**

### Property 6: 角色权限映射完整性

*For any* 预置角色，角色权限页面应该显示该角色的所有权限

**Validates: Requirements 3.2**

## Error Handling

### 用户管理错误

- **邮箱已存在**: 返回错误消息 "邮箱已存在"
- **用户不存在**: 返回错误消息 "用户不存在"
- **数据库操作失败**: 返回错误消息 "操作失败：{具体错误}"

### 权限错误

- **未授权访问**: 重定向到登录页面或显示 403 错误
- **会话过期**: 重定向到登录页面

### 输入验证错误

- **必填字段为空**: 前端表单验证提示
- **邮箱格式错误**: 前端表单验证提示
- **密码长度不足**: 前端表单验证提示

## Testing Strategy

### Unit Tests

- 测试 AdminStatisticsService 的统计方法
- 测试 AdminController 的各个端点
- 测试角色权限配置的正确性

### Property-Based Tests

- 测试用户创建时的邮箱唯一性约束
- 测试密码加密的一致性
- 测试统计数据的一致性
- 测试权限控制的有效性

### Integration Tests

- 测试系统概览页面的数据加载
- 测试用户管理的完整流程（创建、编辑、禁用、激活）
- 测试角色权限页面的数据展示

## Implementation Notes

### 系统概览页面

- 使用卡片式布局展示统计数据
- 使用图标和颜色区分不同的统计项
- 提供快捷入口按钮跳转到用户管理

### 角色权限页面

- 使用表格展示角色与权限的对应关系
- 使用徽章（badge）标识不同的角色
- 添加说明文字解释权限控制模型

### 用户管理优化

- 添加角色筛选下拉框
- 添加用户名搜索输入框
- 优化表格布局，确保操作按钮可见

### 导航优化

- 在侧边栏添加"系统概览"菜单项
- 在侧边栏添加"角色权限"菜单项
- 确保系统管理员登录后默认跳转到系统概览页面
