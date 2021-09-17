package com.atguigu.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author MarkChern
 * @create 9/12/2021 2:08 PM
 */
@RestController
public class OrderZKController {

    public static final String INVOKE_URL = "http://cloud-providerconsul-payment8006";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consul/payment/consul")
    public String paymentInfo() {
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
        return result;
    }

    @GetMapping(value = "/test")
    public String test() {
        return "test";
    }
}
