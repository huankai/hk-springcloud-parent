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

# 三、添加电影微服务，并微服务发布到 eureka上 #
重新创建项目 `hk-movie-service`，配置相关信息，请看项目配置文件`application.yml`

启动后，会自动注册到Eureka Server上，使用电影微服务调用用户微服务
```
@RestController
@RequestMapping("movieorder")
public class MovieOrderController {
	/**
     * 用户微服务path
     */
    @Value("${user.service.path}")
    private String userServicePath;

	/**
	 * 电影微服务调用用户微服务
	 *
	 * @param userId
	 */
	@RequestMapping("user/{userId}")
	public UserVo getUser(@PathVariable Long userId) {
	    return restTemplate.getForObject(userServicePath + "user/" + userId, UserVo.class);
	}
}
```
在浏览器输入 http://localhost:8991/movieorder/user/1 查看结果。


以上存在的问题：
 - userServicePath 用户微服务路径写在了 电影微服务配置文件中，一旦用户微服务部署环境发生变化，电影微服务也需要修改配置，如果用户微服务被很多其它的微服务所调用，则其它所有的微服务都需要修改配置，这是不能容忍的。
- 一旦用户微服务不能使用，会影响到所有其它需要调用的微服务，用户微服务没有做到负载均衡。

 