使用 ribbon + restTemplate 实现负载均衡

## 1.添加配置 ##
在 pom中添加 spring-cloud-start-ribbon 包，其它 spring-cloud-start-eureka就已经依赖了此包，所以不需要配置。
然后在 `RestTemplate`这个Bean 添加 `org.springframework.cloud.client.loadbalancer.LoadBalanced` 注解，实现负载均衡
```
@Bean
@LoadBalanced
public RestTemplate restTemplate() {
    return new RestTemplate();
}
```

## 3.启动服务 ##
启动 eureka server 、movie-service 微服务，并启动两个 user-service 微服务
关于如何在 idea中一个工程启动多个实例，请查看 https://blog.csdn.net/forezp/article/details/76408139

## 4、测试 ##
使用电影微服务调用用户微服务，查看控制台日志，可知电影微服务已启用了负载均衡，spring cloud默认会使用 轮询的方式对每个用户微服务执行请求。

## 5、自定义 ribbon 轮询方式 ##
- 在 SpringApplication不能被扫描的路径下创建自定类，内容如下

```
package com.hk.ribbon.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;

/**
 * 自定义Ribbon配置
 * 此文件不能写在 SpringApplication 能扫描的包下
 *
 * @author huangkai
 * @date 2018-6-3 16:49
 */

public class CustomUserServiceConfiguration {

	/**
     * 默认的配置查看 {@link org.springframework.cloud.netflix.ribbon.RibbonClientConfiguration#ribbonRule(IClientConfig)}
     *
     * @param config
     * @return
     */
    @Bean
    public IRule ribbonRule(IClientConfig config) {
        return new RandomRule();
    }
    
}

```

然后在启动类上添加注解

```
/**
 * name: 需要作用微服务的名称
 * configuration: 配置的类名
 */
@RibbonClients(value = @RibbonClient(name = "user-service",configuration = CustomUserServiceConfiguration.class))
```

通过以上的配置，电影微服务调用用户微服务时，就可以使用随机的方式来调用。
可以启动多个用户微服务，在浏览器访问 http://192.168.1.103:8891/movieorder/user/1 查看是哪个用户微服务的日志信息被打印。

- 如果你非得在启动类路径下创建 自定义 ribbon的的配置，方式如下：

1、在 Application启动类目录或子目录中创建自定注解如下：
```
package com.hk.movie;

/**
 * 自定义注解，不需要有任何属性
 * @author huangkai
 * @date 2018-6-3 17:39
 */
public @interface ExcludeFromComponentScan {
}
```

2、在Application启动类目录或子目录创建配置类如下：
```
package com.hk.movie;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义Ribbon配置
 * 此文件不能写在 SpringApplication 能扫描的包下
 *
 * @author huangkai
 * @date 2018-6-3 16:49
 */
@Configuration
@ExcludeFromComponentScan
public class CustomUserServiceConfiguration {

    /**
     * 默认的配置查看 {@link org.springframework.cloud.netflix.ribbon.RibbonClientConfiguration#ribbonRule(IClientConfig)}
     *
     * @param config
     * @return
     */
    @Bean
    public IRule ribbonRule(IClientConfig config) {
        return new RandomRule();
    }

}
```
3、在Application启动类上添加配置如下：
```
package com.hk.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

/**
 * @author huangkai
 * @date 2018-6-3 15:00
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClients(value = @RibbonClient(name = "user-service",configuration = CustomUserServiceConfiguration.class))
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,value = ExcludeFromComponentScan.class))
public class MovieApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }
}

```
最后的效果和第一种方式相同。

- 使用 application.yml 配置文件的方式（此种方式从 V1.2开始支持）
参考资料：http://cloud.spring.io/spring-cloud-static/Dalston.SR5/single/spring-cloud.html#_customizing_the_ribbon_client_using_properties
此种方式的优先级高于通过java Bean 配置的方法，更高于spring cloud内置的配置方式
只需要在需要调用的微服务`application.yml` 文件中添加 `被调用的微服务名.ribbon.NFLoadBalancerRuleClassName`

假设用户微服务的 `spring.application.name=user-service`，电影微服务需要调用用户微服务，需要在电影微服务的 `application.yml`配置文件中添加
```
user-service:
  ribbon:
    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule
``` 
