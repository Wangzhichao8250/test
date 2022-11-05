package com.mooc.meetingfilm.hall.conf;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Wang.Z.C
 * @create 2021/8/4
 */
@Configuration
public class RestConf {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
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

}
