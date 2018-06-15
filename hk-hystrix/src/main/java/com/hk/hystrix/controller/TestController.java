package com.hk.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author huangkai
 * @date 2018-6-10 11:19
 */
@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    public static class UserVo {

        private Long id;

        private String userName;

        private String sex;

        private String email;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    /**
     * 电影微服务调用用户微服务,
     * 如果调用用户微服务不成功，会调用 defaultUser  方法
     *
     * @param userId
     */
    @RequestMapping("user/{userId}")
    @HystrixCommand(fallbackMethod = "defaultUser",commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")})
    public UserVo getUser(@PathVariable Long userId) {
        return restTemplate.getForObject("http://user-service/user/" + userId, UserVo.class);
    }

    /**
     * 如果以上方法调用用户微服务不成功，会调用此方法
     *
     * @param userId
     * @return
     */
    public UserVo defaultUser(Long userId) {
        UserVo user = new UserVo();
        user.setId(userId);
        user.setUserName("default");
        return user;
    }
}
