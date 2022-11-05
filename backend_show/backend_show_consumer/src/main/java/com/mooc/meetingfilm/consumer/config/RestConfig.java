package com.mooc.meetingfilm.consumer.config;

import com.mooc.meetingfilm.consumer.ribbon.Rule;
import com.netflix.loadbalancer.*;
import com.netflix.niws.loadbalancer.NIWSDiscoveryPing;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.client.RestTemplate;

/**
 * @author Wang.Z.C
 * @create 2021/7/27
 */
@Configuration
public class RestConfig {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 负载均衡规则
     *
     * @return
     */
    @Bean
    public IRule iRule() {
        return new RoundRobinRule();
//        return new RandomRule();
    }


    @Bean
    public IPing iPing() {
        //依赖于Eureka自身的判断
        return new NIWSDiscoveryPing();
//        return new PingUrl(false,"/abc");
    }

    //    @Bean
//    @LoadBalanced
//    RestOperations restTemplate(RestTemplateBuilder builder) {
//        return builder.build();
//    }


}
