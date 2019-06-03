[![Build Status](https://travis-ci.org/zhoutaoo/SpringCloud.svg?branch=master)](https://travis-ci.org/zhoutaoo/SpringCloud)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![codecov](https://codecov.io/gh/zhoutaoo/SpringCloud/branch/master/graph/badge.svg)](https://codecov.io/gh/zhoutaoo/SpringCloud)

## 快速开始

### 先决条件

- [git](https://git-scm.com/)
- [java8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) 
- [maven](http://maven.apache.org/) 
- [postgresql](http://www.postgresql.org/)
- [redis](http://redis.io/download)
- [rabbitmq](http://rabbitmq.io/download)

### 开发环境

1. 克隆代码库： `git clone https://gitee.com/toopoo/SpringCloud.git`

2. 生成ide配置： `mvn idea:idea` 并导入对应的ide进行开发，IDE安装lombok插件

### 编译 & 启动

* 1.启动基础服务：`docker-compose -f docker-compose.yml` 或单个启动`docker-compose up 服务名`

|  服务          |   服务名         |  端口     | 备注                                            |
|---------------|-----------------|-----------|------------------------------------------------|
|  数据库        |   postgres      |  5432     |  目前各应用共用1个实例，各应用可建不同的database    |
|  KV缓存       |   redis         |  6379     |  目前共用，原则上各应用单独实例    |
|  消息中间件    |   rabbitmq      |  5672     |  共用                          |
|  日志收集中间件 |   zipkin-server |  9411     |  共用                          |
|  搜索引擎中间件 |   elasticsearch |  9200     |  共用    |
|  日志分析工具   |   kibana        |  5601     |  共用    |
|  数据可视化工具 |   grafana       |  3000     |  共用    |

* 2.创建数据库及表

**子项目脚本**

路径一般为：子项目/db

如：`auth/db` 下的脚本

**应用脚本**

路径一般为：子项目/应用名/src/main/db

如：demos/producer/src/main/db 下的脚本

* 3.进入相关应用目录，启动应用： `mvn spring-boot:run` 

| 服务分类  | 服务名                     |   简介     |  应用地址                | 文档 |
|----------|---------------------------|-----------|-------------------------|------|
|  center  | eureka-server             | 注册中心   |  http://localhost:8761  | [注册中心文档](./center/eureka)      |
|  center  | bus-server                | 消息中心   |  http://localhost:8071  | [消息中心文档](./center/bus)         |
|  center  | config-server             | 配置中心   |  http://localhost:8888  | [配置中心文档](./center/config)      |
|  auth    | authorization-server      | 授权服务   |  http://localhost:8000  | [权限服务文档](./auth) 、[授权Server文档](./auth/authorization-server)     |
|  auth    | authentication-server     | 签权服务   |  http://localhost:8001  | [认证Server文档](./auth/authentication-server)    |
|  auth    | authentication-client     | 签权客户端  |  jar包引入              |      |
|  gateway | gateway-web               | WEB网关    |  http://localhost:8443 |  [WEB网关文档](./center/eureka)       |
|  gateway | gateway-admin             | 网关管理    |  http://localhost:8445 |  [网关管理后台文档](./center/eureka)   |
|  monitor | admin                     | 总体监控    |  http://localhost:8022 |      |
|  monitor | hystrix-dashboard         | 性能指标展示 |  http://localhost:8021 |      |
|  monitor | turbine                   | 性能指标收集 |  http://localhost:8031 |      |

### 测试

运行 `mvn test` 启动测试.

## 架构

[架构](https://www.processon.com/view/link/597ffa52e4b06a973c4d86ba)

## 开发指南

### 项目目录结构

```
├── auth                           --授权认证子项目
│   ├── authentication-server        --认证组件服务端 
│   ├── authentication-client        --认证组件客户端 
│   ├── authorization-server         --授权组件服务端
│   ├── db                           --子项目公共数据库脚本
│   └── pom.xml                      --子项目maven配置文件
├── center               --中心子项目
│   ├── bus                --消息中心
│   ├── config             --配置中心
│   ├── eureka             --注册中心 
│   └── pom.xml
├── common               --通用子项目
│   ├── core               --核心类库
│   ├── test               --测试工具类库
│   ├── web                --WEB核心类库
│   └── pom.xml          
├── docs                 --文档及资源文件
├── data                 --server及服务数据存储目录
│   ├── elasticsearch      --elasticsearch配置数据存储位置
│   ├── postgres           --postgres数据库文件存储目录 
│   ├── rabbitmq           --rabbitmq数据文件存储目录
│   └── redis              --redis数据文件存储目录
├── gateway              --网关子项目  
│   ├── gateway-web        --基于springcloud gateway的网关
│   ├── gateway-admin      --springcloud gateway的网关管理模块
│   └── pom.xml
├── monitor              --监控、日志及服务管理子项目
│   ├── admin              --springboot admin管理
│   ├── hystrix-dashboard  --hystrix监控
│   ├── turbine            --turbine监控聚集 
│   └── pom.xml
├── demos                --demos子项目
│   ├── consumer-feign     --消费者服务 feign demo
│   ├── consumer-ribbon    --消费者服务 ribbon demo 
│   ├── producer           --服务提供者，产品服务
│   ├── producer-jpa       --服务提供者，产品服务,jpa和hateoas
│   └── pom.xml
├── .env                 --docker-compose环境变量配置文件
├── readme.md            --readme文档入口
├── docker-compose.yml            --docker compose配置文件，基础组件如数据库、运维组件 
├── docker-compose.override.yml   --docker compose配置文件，注册中心、网关、授权认证服务等 
├── docker-compose.apps.yml       --docker compose配置文件，应用demo 
├── docker-compose.config.yml     --docker compose配置文件，apollo配置中心 
└── pom.xml              --业务服务子项目
```

### module目录结构

```
├── logs                     --日志目录
│   ├── spring.log
│   └── spring.log.2018-04-15.0.gz
├── pom.xml                  --module maven配置文件
├── src                      --源码目录
│   ├── main                   --源文件
│   │   ├── db                 --服务db脚本目录
│   │   │   ├── db.sql           --创建库的脚本
│   │   │   ├── ddl              --建表语句等ddl
│   │   │   │   ├── mysql          --mysql ddl
│   │   │   │   └── postgres       --postgres ddl
│   │   │   └── dml              --基础数据dml
│   │   ├── docker             --docker相关配置文件
│   │   │   └── Dockerfile       --dockerfile
│   │   ├── docs               --接口文档目录，一般由swagger生成
│   │   ├── java               --java源码目录
│   │   │   ├── dao              --数据操作层
│   │   │   ├── service          --业务逻辑层
│   │   │   ├── provider         --调用第三方服务的提供类
│   │   │   ├── rest             --接口controller
│   │   │   ├── entity           --实体类
│   │   │   │   ├── form           --rest表单校验
│   │   │   │   ├── param          --dao参数，可以由form转化来
│   │   │   │   ├── po             --实体类
│   │   │   │   └── vo             --视图对象
│   │   │   ├── events           --事件或消息处理类
│   │   │   ├── config           --配置类
│   │   │   ├── exception        --异常处理相关类
│   │   │   ├── interceptor      --拦截器相关类
│   │   │   └── task             --定时任务
│   │   └── resources          --配置文件目录 
│   │       ├── application.yml  --springboot的应用配置文件
│   │       └── bootstrap.yml    --springboot的配置文件
│   └── test                   --测试目录
│       ├── java                 --java测试案例目录
│       └── resources          --配置文件目录 
│          └── application.yml   --springboot test的配置文件
└── target                     --编译目标目录
```
### 开发规范

[规范文档](docs/pattern.md)


## 功能特性

### 基础模块

|  服务     | 使用技术                 |   进度        |    备注   |
|----------|-------------------------|---------------|-----------|
|  注册中心 | Eureka                  |   ✅          |           |
|  配置中心 | Appollo                 |   ✅          |           |
|  消息总线 | SpringCloud Bus+Rabbitmq|   ✅          |           |
|  灰度分流 | OpenResty + lua         |   🏗          |           |
|  动态网关 | SpringCloud Gateway     |   ✅          |  多种维度的流量控制（服务、IP、用户等），后端可配置化🏗          |
|  授权认证 | Spring Security OAuth2  |   ✅          |  Jwt模式   |
|  服务容错 | SpringCloud Hystrix     |   ✅          |           |
|  服务调用 | SpringCloud OpenFeign   |   ✅          |           |
|  对象存储 | FastDFS                 |   🏗          |           |
|  任务调度 | Elastic-Job             |   🏗          |           |
|  分库分表 | Mycat                   |   🏗          |           |
|  数据权限 |                         |   🏗          |  使用mybatis对原查询做增强，业务代码不用控制，即可实现。         |

### 开发管理

|  服务     | 使用技术                 |   进度         |    备注   |
|----------|-------------------------|---------------|-----------|
|  代码生成 |                         |   🏗          |  前后端代码的生成，支持Vue         |
|  测试管理 |                         |   🏗          |           |
|  文档管理 | Swagger2                |   ✅          |           |

### 运维监控

|  服务     | 使用技术                 |   进度         |    备注   |
|----------|-------------------------|---------------|-----------|
|  服务监控 | Spring Boot Admin       |   ✅          |           |
|  链路追踪 | Pinpoint、SkyWalking    |   🏗          |           |
|  操作审计 |                         |   🏗          |  系统关键操作日志记录和查询         |
|  日志管理 | ES + Kibana、Zipkin     |   ✅          |           |
|  监控告警 | Grafana                 |   ✅          |           |

### 平台功能

|  服务     | 使用技术     |   进度         |    备注   |
|----------|-------------|---------------|-----------|
|  用户管理 | 自开发       |   🏗          |  用户是系统操作者，该功能主要完成系统用户配置。          |
|  角色管理 | 自开发       |   🏗          |  角色菜单权限分配、设置角色按机构进行数据范围权限划分。   |
|  菜单管理 | 自开发       |   🏗          |  配置系统菜单，操作权限，按钮权限标识等。               |
|  机构管理 | 自开发       |   🏗          |  配置系统组织机构，树结构展现，可随意调整上下级。        |
|  通知平台 | 自开发       |   🏗          |  短信、邮件、微信模板发送                             |

## 联系交流

EMail：zhoutaoo@foxmail.com

![wechat](docs/wechat.png)
