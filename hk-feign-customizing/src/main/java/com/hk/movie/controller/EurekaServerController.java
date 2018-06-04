package com.hk.movie.controller;

import com.hk.movie.feign.EurekaFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangkai
 * @date 2018-6-4 22:45
 */
@RestController
public class EurekaServerController {

    @Autowired
    private EurekaFeignClient eurekaFeignClient;

    @GetMapping("{serverName}")
    public String serverInfo(@PathVariable String serverName) {
        return eurekaFeignClient.serverInfo(serverName);
    }
}
