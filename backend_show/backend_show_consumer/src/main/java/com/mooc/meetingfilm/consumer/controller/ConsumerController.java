package com.mooc.meetingfilm.consumer.controller;

import com.mooc.meetingfilm.consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wang.Z.C
 * @create 2021/7/27
 */
@RestController
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @RequestMapping(value = "/consumer/sayHello")
    public String sayHello(String message){
        return consumerService.sayHello(message);
    }
}
