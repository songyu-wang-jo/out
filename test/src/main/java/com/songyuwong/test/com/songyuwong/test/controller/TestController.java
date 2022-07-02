package com.songyuwong.test.com.songyuwong.test.controller;

import com.songyuwong.infrastructure.api.starter.exception.ApiException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * </p>
 *
 * @author SongYu
 * @since 2022/7/2
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/exception")
    public String exception(@RequestParam("trigger")Boolean trigger){
        if (trigger){
            ApiException.throwApiException("参数异常测试");
        }
        return "OK, no exceptions here";
    }

}
