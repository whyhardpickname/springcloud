package com.atguigu.springcloud.config;

/**
 * @author MarkChern
 * @create 9/3/2021 2:56 PM
 */

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
//    @LoadBalanced   //负载均衡
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }
}
