package com.hk.hystrix.feign;

import com.hk.hystrix.vo.UserVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 如果都定义了 fallbackFactory 和 fallback，会使用 fallback 对应的方法
 *
 * @author: huangkai
 * @date 2018-06-11 17:50
 */
@FeignClient(name = "user-service", fallbackFactory = HystrixUserFeignClientFallbackFactory.class, fallback = HystrixUserFeignClient.class)
public interface UserFeignClient {

    @GetMapping("user/{id}")
    UserVo getUser(@PathVariable("id") Long id);
}
