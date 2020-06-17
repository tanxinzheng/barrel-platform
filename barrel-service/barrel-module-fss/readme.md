# store存储服务
文件存储服务，适配阿里云oss服务

## 使用指引

- 添加Maven依赖
```xml
    <dependency>
        <groupId>com.github.tanxinzheng</groupId>
        <artifactId>barrel-support-store</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>
```

- 添加配置参数
```yaml
fss:
  access-key-id: xxxxx
  access-key-secret: xxxxxxxx
  bucket-name: bucket-test
  endpoint: oss-cn-hangzhou.aliyuncs.com
```

- 在启动类中添加@EnableFSSAliyun注解
```java
@EnableFSSAliyun
@SpringBootApplication
public class FSSStartApp {

    public static void main(String[] args) {
        SpringApplication.run(FSSStartApp.class, args);
    }
    
}
```