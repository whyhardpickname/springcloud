package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author MarkChern
 * @create 9/19/2021 6:45 AM
 */
@Component
public class PaymentFallbckService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "fallback ok";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "fallback timeout";
    }
}
