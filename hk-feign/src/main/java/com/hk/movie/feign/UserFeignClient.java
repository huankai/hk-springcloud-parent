package com.hk.movie.feign;

import com.hk.user.vo.UserVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author huangkai
 * @date 2018-6-4 20:25
 */
@FeignClient("user-service")
public interface UserFeignClient {

    /**
     * 使用 Feign 第一个坑， @PathVariable 必须指定 value ,否则启动报错
     *
     * @param id
     * @return
     */
//    @RequestMapping("user/{id}")
    @GetMapping("user/{id}")
    UserVo getById(@PathVariable("id") Long id);

    /**
     * 坑二： 如果参数对象是复杂类型，必须使用 Post 传递参数，即使使用 GET，feign还是会转换为 POST
     * 并在参数加上 @RequestBody，两边都要加，如果不加，服务被调用方接收不到任何参数值
     *
     * @param userVo
     * @return
     */
    @PostMapping("user/save")
    UserVo saveUser(@RequestBody UserVo userVo);
}
