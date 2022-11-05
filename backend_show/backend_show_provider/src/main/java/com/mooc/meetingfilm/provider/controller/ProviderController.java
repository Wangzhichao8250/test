package com.mooc.meetingfilm.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Wang.Z.C
 * @create 2021/7/27
 * 提供者
 */
@Slf4j
@RestController
public class ProviderController {

    @Value("${server.port}")
    private int port;

    @RequestMapping(value = "/provider/sayHello")
    public String providerSayHello(String message) {
        log.error("provider sayHello port:{},message:{}", port, message);
        return "Provider sayHello port:" + port + "message:" + message;
    }
}
