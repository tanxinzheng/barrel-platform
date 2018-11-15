# 字典翻译注解支持
在开发过程中，有许多字段在对象类中是对应的字典表中的代码，比如字典代码，各种状态代码等，而在前端界面展示时，应该转换为字典中翻译的代码描述，
常规操作下，我们需要在sql中关联字典表将代码转译为代码描述，但是每次都写sql的话会有很大的工作量，该工具包可直接使用注解直接将代码转译为代码描述。

## 依赖
- JDK1.8+
- Maven
- Spring Boot

## 使用指南

- 添加maven依赖
```
<dependency>
    <groupId>com.github.tanxinzheng</groupId>
    <artifactId>boot-support-dictionary-annotation</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

- 修改配置类
```java
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class AppTestConfig extends WebMvcConfigurerAdapter {
    @Bean
    public DictionaryInterpreterService getDictionaryInterpreterService(){
        return new DictionaryInterpreterService() {
            @Override
            public Map<String, Object> translateDictionary(String dictionaryType, String dictionaryCode) {
                Map<String, Object> data = Maps.newHashMap();
                if(dictionaryType.equalsIgnoreCase("SEX")){
                    data.put("W", "女");
                    data.put("M", "男");
                }else if(dictionaryType.equalsIgnoreCase("DISABLE")){
                    data.put("true", "禁用");
                    data.put("false", "启用");
                }
                return data;
            }
        };
    }

    @Bean
    public DictionaryAnnotationIntrospector getDictionaryIntrospector(){
        DictionaryAnnotationIntrospector dictionaryAnnotationIntrospector = new DictionaryAnnotationIntrospector();
        dictionaryAnnotationIntrospector.setApplicationContext(applicationContext);
        return dictionaryAnnotationIntrospector;
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.annotationIntrospector(getDictionaryIntrospector());
        return builder.build();
    }
}
```

- 在需要转译对象的字段中添加注解
```java
@Data
public class DemoModel {

    private String id;
    @DictionaryInterpreter(index = "USER", fieldName = "account")
    private String userId;
    @DictionaryInterpreter(index = "SEX")
    private String sex;
    @DictionaryInterpreter(index = "DISABLE", fieldName = "disableName")
    private String disable;
    
}
```

- 添加注解之前的效果
```json
[
  {
    "id": "81e0ccea-93b0-4f05-8727-0351e590f3eb",
    "userId": "1",
    "sex": "W",
    "disable": "false"
  },
  {
    "id": "f930acef-b9ba-43cf-9334-6ddac1bb9d79",
    "userId": "2",
    "sex": "M",
    "disable": "true"
  }
]
```
- 添加注解之后的效果
```json
[
  {
    "id": "a48e539a-0474-4ee1-9f2a-54af2256c5cd",
    "userId": "1",
    "account": {
      "link": "http://avatar.xxx.com/avatar/1.jpg",
      "userName": "管理员",
      "userId": "1"
    },
    "sex": "W",
    "sexDesc": "女",
    "disable": "false",
    "disableName": "启用"
  },
  {
    "id": "940b7fe5-bedb-4346-9e85-159e557df615",
    "userId": "2",
    "account": {
      "link": "http://avatar.xxx.com/avatar/2.jpg",
      "userName": "用户",
      "userId": "2"
    },
    "sex": "M",
    "sexDesc": "男",
    "disable": "true",
    "disableName": "禁用"
  }
]
```
