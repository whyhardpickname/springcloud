package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MarkChern
 * @create 9/22/2021 6:22 AM
 */
@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${config.info}")
    private String info;

    @GetMapping("/configInfo")
    public String getInfo(){
        return info;
    }
}

