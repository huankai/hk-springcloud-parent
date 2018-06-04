package com.hk.movie.controller;

import com.hk.movie.feign.UserFeignClient;
import com.hk.user.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author huangkai
 * @date 2018-6-4 20:27
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("{id}")
    public UserVo get(@PathVariable Long id) {
        return userFeignClient.getById(id);
    }

    @GetMapping("save")
    public UserVo save(UserVo userVo) {
        return userFeignClient.saveUser(userVo);
    }
}
