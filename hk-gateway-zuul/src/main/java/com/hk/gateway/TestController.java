package com.hk.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: huangkai
 * @date 2018-06-14 15:11
 */
@RestController
@RequestMapping("others")
public class TestController {

    @GetMapping()
    public String others() {
        return "Others!";
    }
}
