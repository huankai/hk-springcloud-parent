package com.hk.hystrix.feign;

import com.hk.hystrix.vo.UserVo;
import org.springframework.stereotype.Component;

/**
 * @author: huangkai
 * @date 2018-06-11 17:52
 */
@Component
public class HystrixUserFeignClient implements UserFeignClient {

    /**
     * 如果调用用户微服务不成功，会调用此方法
     *
     * @param userId
     * @return
     */
    @Override
    public UserVo getUser(Long id) {
        UserVo user = new UserVo();
        user.setId(id);
        user.setUserName("default");
        return user;
    }
}
