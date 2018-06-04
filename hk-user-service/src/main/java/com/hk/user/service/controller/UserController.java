package com.hk.user.service.controller;

import com.hk.user.service.domain.User;
import com.hk.user.service.repository.UserRepository;
import com.hk.user.vo.UserVo;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author huangkai
 * @date 2018-6-3 11:21
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("{id}")
    public UserVo get(@PathVariable Long id) {
        User user = userRepository.findOne(id);
        UserVo vo = new UserVo();
        BeanUtils.copyProperties(user, vo);
        return vo;
    }

    /**
     * Feign 被调用方，必须与调用方一致，如请求方法 、@RequestBody
     * @param user
     * @return
     */
    @PostMapping("save")
    public User save(@RequestBody User user) {
        return user;
    }

    @GetMapping("get")
    public User getUser(User user) {
        return user;
    }

    @Autowired
    private EurekaClient eurekaClient;

    /**
     * 获取实例信息
     *
     * @return
     */
    @RequestMapping("instanceinfo")
    public InstanceInfo getInstanceInfo() {
        return eurekaClient.getNextServerFromEureka("user-service", false);
    }
}
