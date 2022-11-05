package com.mooc.meetingfilm.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Wang.Z.C
 * @create 2021/3/10
 */
@Component
public class RedisUtils {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 根据key删除
     * @param key
     */
    public void keyDelete(String key){
        redisTemplate.delete(key);
    };

    /**
     * 根据key查询是否存在
     *
     * @param key
     * @return
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }


    /**
     * 设置指定 key 的值，并设置过期时间
     *
     * @param key
     * @param timeout
     * @return
     */
    public void setExpire(String key,String value,long timeout) {
        //存入key，value并设置60秒的超时时间
        redisTemplate.opsForValue().set(key, value,timeout, TimeUnit.SECONDS);

        redisTemplate.opsForList().range("testList",0,-1);

        redisTemplate.opsForList().index("testList",0);
    }

    /**
     * 根据index获取list
     * @param key
     * @param index
     * @return
     */
    public String getList(String key,int index){
        return redisTemplate.opsForList().index(key, index);

    }





    /**
     * 设置指定 key 的值
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }
    /**
     * 获取指定 key 的值
     * @param key
     * @return
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

}
