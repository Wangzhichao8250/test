package com.mooc.meetingfilm.consumer.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;

/**
 * @author Wang.Z.C
 * @create 2021/8/5
 */
public class Rule extends AbstractLoadBalancerRule {

    public Rule(){}

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {
        return null;
    }
}
