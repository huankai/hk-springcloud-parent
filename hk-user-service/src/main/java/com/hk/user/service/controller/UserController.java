package com.hk.user.service.controller;

import com.hk.user.service.domain.User;
import com.hk.user.service.repository.UserRepository;
import com.hk.user.vo.UserVo;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("save")
    public User save(User user) {
        return userRepository.save(user);
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
