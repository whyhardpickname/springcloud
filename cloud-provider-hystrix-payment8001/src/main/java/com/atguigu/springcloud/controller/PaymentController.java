package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author MarkChern
 * @create 9/18/2021 6:52 AM
 */
@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id")Integer id) {
        String info_ok = paymentService.paymentInfo_OK(id);
        return info_ok;
    }

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String paymentInfo_timeout(@PathVariable("id")Integer id) {
        String info_timeOut = paymentService.paymentInfo_TimeOut(id);
        return info_timeOut;
    }

    @GetMapping(value = "/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id")Integer id) {
        String circutBreaker = paymentService.paymentCircutBreaker(id);
        return circutBreaker;
    }
}
