package com.hk.movie.feign;

import com.hk.config.UserFeignConfiguration;
import com.hk.user.vo.UserVo;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author huangkai
 * @date 2018-6-4 20:25
 */
@FeignClient(value = "user-service", configuration = UserFeignConfiguration.class)
public interface UserFeignClient {

    /**
     * 使用 Feign
     *
     * @param id
     * @return
     */
    @RequestLine("GET /user/{id}")
    UserVo getById(@Param("id") Long id);

    /**
     * 使用 POST 请求，如果是复杂对象，服务调用者需要使用 @RequqstBody 来接收
     *
     * @param userVo
     * @return
     */
    @RequestLine("POST /user/save")
    UserVo saveUser(UserVo userVo);


    /**
     * 复杂对象必须使用 POST调用，此方法使用GET，会报错
     *
     * @param userVo
     * @return
     */
    @RequestLine("GET /user/get")
    UserVo getUser(UserVo userVo);
}
