package com.hk.movie.feign;

import com.hk.config.EurekaFeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>
 *
 * @author huangkai
 * @FeignClient 注解中
 * name ：必须指定
 * url： 可以不指定，如果指定了url，name就只是一个名称，如果不指定 name就是需要调用微服务的名称
 * <p>
 * </p>
 * @date 2018-6-4 22:40
 */
@FeignClient(name = "xxx", url = "http://localhost:8761",configuration = EurekaFeignConfiguration.class)
public interface EurekaFeignClient {

    /**
     * 查看Eureka 某个服务的信息
     * Eureka 可以通过  http://localhost:8761/eureka/apps/${serviceName} 查看指定的服务信息
     *
     * @param serverName
     * @return
     */
    @GetMapping("eureka/apps/{serverName}")
    String serverInfo(@PathVariable("serverName") String serverName);
}
