package com.hk.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangkai
 * @date 2018-6-4 22:47
 */
@Configuration
public class EurekaFeignConfiguration {

    /**
     * Eureka Server 需要使用Basic认证，这里添加Interceptor
     *
     * @return
     */
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "password123");
    }
}
