package com.hk.hystrix.controller;

import com.hk.hystrix.feign.UserFeignClient;
import com.hk.hystrix.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangkai
 * @date 2018-6-10 11:19
 */
@RestController
public class TestController {

    @Autowired
    private UserFeignClient userFeignClient;

    /**
     * @param userId
     */
    @RequestMapping("user/{userId}")
    public UserVo getUser(@PathVariable Long userId) {
        return userFeignClient.getUser(userId);
    }

}
