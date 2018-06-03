package com.hk.movie.controller;

import com.hk.movie.domain.MovieOrder;
import com.hk.movie.repository.MovieOrderRepository;
import com.hk.user.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
     * 用户微服务path
     */
    @Value("${user.service.path}")
    private String userServicePath;

    /**
     * 电影微服务调用用户微服务
     *
     * @param userId
     */
    @RequestMapping("user/{userId}")
    public UserVo getUser(@PathVariable Long userId) {
        return restTemplate.getForObject(userServicePath + "user/" + userId, UserVo.class);
    }

}
