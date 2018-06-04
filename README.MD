Feign使用与自定义配置：

**涉及项目**
- hk-eureka-simple ： eureka server
- hk-user-service：用户微服务
- hk-feign：feign 测试
- hk-feign-customizing：feign自定义配置

# 一、基本使用 #

## 1、创建项目 ##
创建项目 `hk-feign` ，添加相关依赖，请查看 `hk-feign` 工程的 pom.xml 文件。

## 2、启动类 ##
在启动类上添加 `org.springframework.cloud.netflix.feign.EnableFeignClients` 注解，详见`com.hk.movie.FeignApplication` 类

## 3、创建 `UserFeignClient` 接口 ##
类上标注 `org.springframework.cloud.netflix.feign.FeignClient`注解，此注解的name可以指定需要调用的是哪个微服务(如这里配置的 user-service，会调用用户微服务)。然后定义相关测试方法，Feign 默认使用的契约是 SpringMvcContract，所以可以使用 Spring mvc 相关注解。
<font color='red'>**注意：**</font>
- @PathVariable 必须指定 value ,否则启动报错，如 `@PathVariable("id") Long id`，不能简写成 `@PathVariable Long id`，此种写法在 Spring mvc 中可以，在这里不可以
- 如果参数对象是复杂类型，必须使用 Post 传递参数，即使使用 GET，feign还是会转换为 POST，并在参数加上 @RequestBody，两边都要加，如果不加，服务被调用方接收不到任何参数值。即使你修改了契约为Feigin自身，也如此，后面自定义配置中会讲到。

4、定义 `com.hk.movie.controller.UserController` 调用 UserFeignClient中的方法。

# 二、自定义配置 #
我们知道，Spring Application也是父子容器，每一个 FeignClient 都是一个应用上下文(都有自己的容器)，即，每一个FeignClient都可以有自己的相关配置，互不影响。

1、创建新工程 : `hk-feign-customizing`

2、添加配置：
在 `com.hk.movie.feign.UserFeignClient` 类上的 `org.springframework.cloud.netflix.feign.FeignClient` 添加 `configuration`属性值，指定此FeignClient的配置信息，注意:此文件的放置目录和 ribbon 一样，不应当被Application所在的包扫描到，原因见 Ribbon中的说明 。

3、在配置文件中修改 Feign默认的 Contract ,
默认的 Contract 为 `org.springframework.cloud.netflix.feign.support.SpringMvcContract`，修改为 feign 的Contract 后，就可以使用feign相关的注解了，查看`com.hk.movie.feign.UserFeignClient`中的方法注解，使用的feign注解，关于feign注解的使用，可以查看 https://github.com/OpenFeign/feign

```
@Bean
public Contract feignContract() {
    return new feign.Contract.Default();
}
```

4、添加Basic认证测试:
查看 `com.hk.movie.feign.EurekaFeignClient` 注释
查看 `com.hk.config.EurekaFeignConfiguration` 注释

# 三、开启Feign日志 #

在自定义配置中添加内容如下：
```
@Bean
Logger.Level level(){
    return Logger.Level.FULL;
}
```

再开启日志：
log4j2.xml添加：

```
<logger name="com.hk.movie.feign.UserFeignClient" level="debug" additivity="false">
    <appender-ref ref="Console"/>
</logger>
```