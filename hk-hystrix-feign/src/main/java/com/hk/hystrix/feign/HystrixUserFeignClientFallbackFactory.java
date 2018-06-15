package com.hk.hystrix.feign;

import com.hk.hystrix.vo.UserVo;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author: huangkai
 * @date 2018-06-12 14:51
 */
@Component
public class HystrixUserFeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {


    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {
            @Override
            public UserVo getUser(Long id) {
                UserVo userVo = new UserVo();
                userVo.setId(id);
                userVo.setUserName("fallbackFactory_user: " + throwable.getMessage());
                return userVo;
            }
        };
    }
}
