# JWT token认证服务
使用jwt token认证协议，并结合redis缓存机制，实现分布式集群认证

## 使用指引

- 添加Maven依赖
```xml
    <dependency>
        <groupId>com.github.tanxinzheng</groupId>
        <artifactId>boot-support-jwt</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>
```

- 添加配置参数
```yaml
jwt:
  secret: 123456
  expiration: 2 # 以小时为时间单位
  permit-urls: /nologin,/anonymous # 不需要登录即可访问URL，通过逗号分割

# 依赖redis服务
spring:
  cache:
    type: redis
  redis:
    host: localhost
    port: 6379
```

- 添加bean依赖配置文件
```java
@Configuration
public class JwtAuthConfig extends JwtConfigAdapter {

    @Bean
    @Override
    public JwtLoadService getJwtLoadService() {
        return new TestJwtLoadService();
    }
}
```

- 在启动类中添加@EnableJWT注解
```java
@EnableJWT
@SpringBootApplication
public class JwtStartApp {

    public static void main(String[] args) {
        SpringApplication.run(JwtStartApp.class, args);
    }
    
}
```