package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author MarkChern
 */
@RestController
@Slf4j
public class paymentController {

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {

        int result = paymentService.create(payment);
        log.info("插入结果: " + result);

        if (result > 0) {
            return new CommonResult(200, "插入数据成功, server port: " + serverPort, result);
        }
        else {
            return new CommonResult(444, "插入数据失败, server port: " + serverPort, null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable(value = "id") Long id) {

        Payment result = paymentService.getPaymentById(id);
        log.info("查询结果: " + result);

        if (result != null) {
            return new CommonResult(200, "查询数据成功, server port: " + serverPort, result);
        }
        else {
            return new CommonResult(444, "ID: " + id + ",查询数据失败", null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery() {

        List<String> services = discoveryClient.getServices();
        for (String service: services) {
            log.info(service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance: instances) {
            log.info(instance.getInstanceId() +
                    "\n" + instance.getHost() +
                    "\n" + instance.getPort() +
                    "\n" + instance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping(value = "/payment/zipkin")
    public String paymentZipkin() {
        return serverPort + " zipkin";
    }
}
