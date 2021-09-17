package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author MarkChern
 * @create 9/17/2021 1:54 PM
 */
public interface LoaderBalancer {
    ServiceInstance INSTANCES(List<ServiceInstance> serviceInstances);
}
