package com.hk.ribbon.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * 自定义Ribbon配置
 * 此文件不写在 SpringApplication 能扫描的包下
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
//    @Bean
    public IRule ribbonRule(IClientConfig config) {
//        if (this.propertiesFactory.isSet(IRule.class, name)) {
//            return this.propertiesFactory.get(IRule.class, config, name);
//        }
//        ZoneAvoidanceRule rule = new ZoneAvoidanceRule();
//        rule.initWithNiwsConfig(config);
        return new RandomRule();
    }

}
