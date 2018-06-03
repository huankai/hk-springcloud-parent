package com.hk.movie;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;

/**
 * 自定义Ribbon配置
 * 此文件写在 SpringApplication 能扫描的包下，需要排除此文件的版本配置。
 *
 * @author huangkai
 * @date 2018-6-3 16:49
 */
//@Configuration
//@ExcludeFromComponentScan
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
