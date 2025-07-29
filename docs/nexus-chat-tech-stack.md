# NexusChat 技术栈与模块设计文档

## 项目概述

NexusChat 是一个基于 Java 技术栈的多模型 AI 对话系统，支持接入多种大型语言模型，提供统一的对话界面和管理功能。本文档详细说明项目的技术选型和模块架构。

## 项目架构图

```ascii
                                    [ 前端层 ]
                                        ↓
                    [ API网关层 (Spring Cloud Gateway) ]
                                        ↓
     [ 业务服务层 (微服务架构) ]        [ 基础设施层 ]
     - 用户服务                         - 注册中心(Nacos)
     - 认证服务                         - 配置中心(Nacos)
     - AI模型服务                       - 消息队列(RocketMQ)
     - 会话管理服务                     - 缓存(Redis)
     - 统计服务                         - 监控(Prometheus)
                                        ↓
                    [ 数据持久层 (多数据源) ]
                    - MySQL (用户数据/配置数据)
                    - MongoDB (聊天记录)
                    - InfluxDB (统计数据)

```

## 模块结构

### 1. 基础设施层 (Infrastructure)

```
nexus-dependencies (依赖管理)
nexus-common (公共模块)
├── nexus-common-core (核心工具类)
├── nexus-common-redis (Redis工具)
├── nexus-common-log (日志模块)
└── nexus-common-swagger (API文档)
nexus-gateway (网关模块)
```

### 2. 认证授权模块 (Authentication & Authorization)

```
nexus-auth/
├── nexus-auth-api (认证服务API)
├── nexus-auth-service (认证服务实现)
└── nexus-auth-client (认证客户端)
```

### 3. 用户服务模块 (User Service)

```
nexus-user/
├── nexus-user-api
└── nexus-user-service
```

### 4. AI模型网关模块 (AI Gateway)

```
nexus-ai/
├── nexus-ai-api (统一AI接口定义)
├── nexus-ai-adapter (各模型适配器)
│   ├── openai-adapter
│   ├── claude-adapter
│   ├── baidu-adapter
│   └── xunfei-adapter
└── nexus-ai-service (AI服务实现)
```

### 5. 会话管理模块 (Chat Session)

```
nexus-chat/
├── nexus-chat-api
└── nexus-chat-service
```

### 6. 统计模块 (Statistics)

```
nexus-stats/
├── nexus-stats-api
└── nexus-stats-service
```

### 7. 前端模块 (Frontend)

```
nexus-ui/
├── nexus-admin-ui (管理后台)
└── nexus-web-ui (用户前端)
```

### 8. 运维监控模块 (Operations)

```
nexus-ops/
├── nexus-monitor (监控服务)
└── nexus-log (日志服务)
```

## 技术栈详情

### 1. 核心框架
- Spring Boot 3.x
- Spring Cloud 2023.x
- Spring Cloud Alibaba
- Spring Security
- Spring WebFlux (响应式编程)

### 2. 数据访问
- MyBatis-Plus
- Spring Data MongoDB
- Spring Data Redis
- Spring Data JPA (可选)

### 3. 数据库
- MySQL 8.x (用户数据、配置数据)
- MongoDB (聊天记录)
- Redis (缓存、会话管理)
- InfluxDB (Token统计)

### 4. 消息队列
- RocketMQ

### 5. 服务治理
- 注册中心：Nacos
- 配置中心：Nacos
- 网关：Spring Cloud Gateway
- 负载均衡：Spring Cloud Loadbalancer
- 熔断限流：Sentinel

### 6. 监控运维
- 监控：Prometheus + Grafana
- 日志：ELK Stack
- 链路追踪：Skywalking
- 服务监控：Spring Boot Admin

### 7. 前端技术
- Vue 3
- TypeScript
- Vite
- Element Plus
- Pinia
- Vue Router
- Axios

### 8. 开发工具
- Hutool
- MapStruct
- Lombok
- Guava
- Knife4j (Swagger增强)

### 9. 测试框架
- JUnit 5
- Mockito
- Spring Test

### 10. 构建部署
- Maven
- Docker
- Kubernetes
- Jenkins

## 模块功能说明

### 基础设施层
- 提供基础工具类和通用功能
- 处理跨域请求
- 统一异常处理
- 统一响应封装
- 统一日志处理

### 认证授权模块
- 用户认证
- JWT token管理
- 权限控制
- OAuth2集成
- SSO支持

### 用户服务模块
- 用户管理
- 角色管理
- 权限管理
- 部门管理
- 个人设置

### AI模型网关模块
- 统一AI接口定义
- 多模型适配
- 模型负载均衡
- 调用限流
- Token计费

### 会话管理模块
- 会话创建与管理
- 实时消息处理
- 历史记录管理
- 会话持久化
- WebSocket通信

### 统计模块
- Token使用统计
- 用户行为分析
- 模型调用统计
- 性能监控
- 报表导出

### 前端模块
- 响应式布局
- 实时对话
- 多主题支持
- 国际化
- 富文本编辑

### 运维监控模块
- 服务健康检查
- 性能监控
- 日志收集
- 告警通知
- 链路追踪

## 部署架构

### 开发环境
- JDK 17+
- Maven 3.8+
- Node.js 16+
- Docker
- IDE推荐：IntelliJ IDEA

### 测试环境
- 单机部署
- Docker Compose
- 基础监控

### 生产环境
- Kubernetes集群
- 高可用配置
- 完整监控体系
- 灾备方案

## 安全设计

### 应用安全
- Spring Security
- JWT + OAuth2
- 密码加密
- XSS防护
- SQL注入防护

### 数据安全
- 数据加密
- 敏感信息脱敏
- 数据备份
- 访问审计

### 运维安全
- HTTPS
- 防火墙
- 漏洞扫描
- 日志审计

## 扩展性设计

### 水平扩展
- 服务无状态
- 会话共享
- 分布式缓存
- 负载均衡

### 垂直扩展
- 模块化设计
- 插件化架构
- 新模型快速接入
- 业务能力扩展

## 性能优化

### 应用层
- 多级缓存
- 异步处理
- 连接池优化
- JVM调优

### 数据库层
- 读写分离
- 分库分表
- 索引优化
- SQL优化

### 网络层
- CDN加速
- Gzip压缩
- 连接复用
- 超时控制 