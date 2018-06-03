# 一、项目介绍 #

- hk-eureka-simple
    eureka 服务端
    
- hk-user-service
    用户微服务

- hk-user-vo
    用户微服务vo类
    
# 二、将用户微服务发布到 eureka上 #
## 2.1、启动 eureka Server ##
启动 hk-eureka-simple项目，使用浏览器访问 http://localhost:8761 进入 eureka Server控制台，可以查看相关信息。

## 2.2、启动 user微服务 ##
启动 hk-user-service 项目，刷新浏览器
在 eureka server控制台查看 `Instances currently registered with Eureka` ，可以发现， hk-user-service 发布到了eureka Server上。

## 2.3、修改用户微服务实例id ##
在用户微服务`application.yml`配置文件中添加

```
eureka:
  instance:
    instance-id: ${spring.application.name}:${server.port}
```

## 2.4、为Eureka Server 添加认证 ##

在Eureka Server项目的 `pom.xml`文件中添加 security 依赖
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

在Eureka Server项目的 `application.yml`文件中添加
```
eureka:
  instance:
  client:
    service-url:
      # 添加Eureka Server 登陆功能，使用Basic
      defaultZone: http://user:password123@${eureka.instance.hostname}:${server.port}/eureka
security:
  basic:
    enabled: true
  user:
    name: user
    password: password123
```

重启 Eureka Server，再次访问时，会弹出登陆框认证，如果Eureka Server 启动了认证，所有向此Eureka Server注册的Client也都需要添加认证。如 user 微服务需要将 application.yml中修改为:
```
eureka:
  client:
    service-url:
      defaultZone: http://user:password123@localhost:8761/eureka  #注册到 eureka服务中,添加认证
```

## 2.5、状态与健康指示 ##

需要在 `pom.xml`文件中添加依赖
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-actuator</artifactId>
</dependency>
```
重启服务，你会在日志台看到有很多 /info 、/health 等这样的url日志(前提是日志开始了debug),然后就可在以浏览器访问相应的地址查看信息了。
如 http://192.168.1.103:8881/health

如果需要想修改这些访问路径 ，可以在 `application.yml` 文件中修改配置：
```
eureka:
  instance:
#    status-page-url-path: /info
#    health-check-url-path: /health
```
## 2.6、使用 EurekaClient ##
使用 EurekaClient 查看实例信息：

```
@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
    private EurekaClient eurekaClient;

    /**
     * 获取实例信息
     *
     * @return
     */
    @RequestMapping("instanceinfo")
    public InstanceInfo getInstanceInfo() {
        return eurekaClient.getNextServerFromEureka("user-service", false);
    }
}
```