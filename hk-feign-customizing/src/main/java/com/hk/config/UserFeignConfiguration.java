package com.hk.config;

import feign.Contract;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangkai
 * @date 2018-6-4 22:07
 */
@Configuration
public class UserFeignConfiguration {

    /**
     * 使用 Feign 契约，默认使用的是 SpringMvcContract
     * <p>
     * http://cloud.spring.io/spring-cloud-static/Dalston.SR5/single/spring-cloud.html#spring-cloud-feign
     *
     * @return
     */
    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

    @Bean
    Logger.Level level(){
        return Logger.Level.FULL;
    }
}
