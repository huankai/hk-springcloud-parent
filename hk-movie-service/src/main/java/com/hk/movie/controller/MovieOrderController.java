package com.hk.movie.controller;

import com.hk.movie.domain.MovieOrder;
import com.hk.movie.repository.MovieOrderRepository;
import com.hk.user.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author huangkai
 * @date 2018-6-3 15:07
 */
@RestController
@RequestMapping("movieorder")
public class MovieOrderController {

    @Autowired
    private MovieOrderRepository movieOrderRepository;

    @RequestMapping("save")
    public MovieOrder save(MovieOrder order) {
        return movieOrderRepository.save(order);
    }

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 电影微服务调用用户微服务
     *
     * @param userId
     */
    @RequestMapping("user/{userId}")
    public UserVo getUser(@PathVariable Long userId) {
        /*
        上一个版本（V1.0）中，使用的是配置文件中的 用户微服务的地址，存在一些弊端
        这里使用 vip (Virtual IP) ，就是需要调用的微服务(用户微服务)的 spring.application.name属性值
         */
        return restTemplate.getForObject("http://user-service/user/" + userId, UserVo.class);
    }

}
