package com.mooc.meetingfilm.consumer.service.impl;

import com.mooc.meetingfilm.consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * @author Wang.Z.C
 * @create 2021/7/27
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private LoadBalancerClient eurekaClient;

    @Override
    public String sayHello(String message) {

//        ServiceInstance choose = eurekaClient.choose("hello-service-provider1");
//        String hostName = choose.getHost();
//        int port = choose.getPort();


        String uri = "/provider/sayHello?message=" + message;


//        String url = "http://" + hostName + ":" + port + uri;
        String url = "http://hello-service-provider" + uri;
        System.out.println("url::"+url);
        return restTemplate.getForObject(url, String.class);
    }
}
