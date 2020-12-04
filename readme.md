# boot框架组件库
集成验证码，消息通知，jwt验证，数据字典等常用模块库，避免重复工作，可直接使用该组件库内的所有模块进行组装成需要的产品。

## 项目依赖
- Java8+
- maven3+
- Spring Boot

## 后端启动
后台服务访问路径：http://localhost:8001/
```mvn
mvn spring-boot:run
```

|  应用代码        | 服务         | 应用端口                  |
| --------------- | ------------ | ----------------------- | 
|  barrel-gateway | 网关服务      | http://localhost:9999/   |
|  barrel-auth    | 认证中心      | http://localhost:9901/   |
|  barrel-system  | 系统管理模块      | http://localhost:9902/   |
|  barrel-admin   | Spring Boot Admin 服务      | http://localhost:9998/   |
|  barrel-zipkin  | 服务链路跟踪      | http://localhost:9411/   |





## 前端启动
前台UI访问路径：http://localhost:8000/
```nodemon
yarn install && yarn start
```