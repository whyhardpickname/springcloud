package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author MarkChern
 * @create 9/15/2021 2:47 PM
 */
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/payment/consul")
    public String paymentConsul(){
        return ("springcloud with consul: "+serverPort+"\t"+ UUID.randomUUID().toString());
    }
}

