package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author MarkChern
 * @create 9/18/2021 6:49 AM
 */
@Service
public class PaymentService {
    //正常访问 OK
    public String paymentInfo_OK(Integer id){
        return "线程池：  "+Thread.currentThread().getName()+"   paymentInfo_OK,id：  "+id+"成功……^_^";
    }

    //延时 模拟故障
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_TimeOut(Integer id){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：  "+Thread.currentThread().getName()+"   paymentInfo_TimeOut,id：  "+id+"超时……^_^3秒钟";
    }


    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池：  "+Thread.currentThread().getName()+"   paymentInfo_TimeOut,id：  "+id+"超时,请稍后重试";
    }

    //circut breaker
    @HystrixCommand(fallbackMethod = "paymentCircutBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),

    })
    public String paymentCircutBreaker(@PathVariable("id")Integer id) {
        if (id < 0) {
            throw new RuntimeException("id can't be negative.");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + " serialNumber: " +  serialNumber;

    }

    public String paymentCircutBreakerFallback(Integer id) {
        return "paymentCircutBreakerFallback";
    }
}
