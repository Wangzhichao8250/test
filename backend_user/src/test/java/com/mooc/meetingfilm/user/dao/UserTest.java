package com.mooc.meetingfilm.user.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.mooc.meetingfilm.user.controller.RedisUtils;
import com.mooc.meetingfilm.user.dao.entity.MoocUserT;
import com.mooc.meetingfilm.user.dao.mapper.MoocBackendUserMapper;
import com.mooc.meetingfilm.utils.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Wang.Z.C
 * @create 2021/7/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserTest {

    @Autowired
    private MoocBackendUserMapper backendUserTMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public static void main(String[] args) {
        System.out.println(21 % 10);
    }

    @Test
    public void test() throws InterruptedException {
        MoocUserT userT = new MoocUserT();

//        HashMap map = new HashMap();
//        map.put("s2","1");
//        map.put("s1","1");
//        map.put("s2","11212");
//        System.out.println(map);
//        map.clear();
//        System.out.println(map);
//        System.out.println(map.size());
//
//
//        List<Integer> list = new ArrayList<>();
//        list.add(2);
//        list.add(35);
//        list.add(4);
//        list.add(221);
//
//        list.remove(3);
//        System.out.println(list);


//        Integer a = Integer.parseInt(redisTemplate.opsForValue().get("a"));
//        System.out.println(c);

//        if (page != key1) {
//            System.out.println("还在加载中");
//        }else{
//            System.out.println("马上就来");
//        }

//        Long s = redisTemplate.opsForList().rightPush("testlist",userT.toString());
//        redisTemplate.opsForList().set("testlist1",3,userT.toString());


//        for (int i = 0; i < 140; i++) {
//            userT.setUuid((i+1));
//            userT.setUserName("chao"+(i+1));
//            userT.setUserPwd("aaa"+(i+1));
//            userT.setUserPhone("xsgi"+(i+1));
//            redisTemplate.opsForList().rightPush("testList",JSONArray.toJSONString(userT));
//        }
//        System.out.println(s);
        List<Long> list = pageList(15l, 10l);
////        List<String> testList = redisTemplate.opsForList().range("testList", list.get(0), list.get(1));
//        List<String> testList = redisTemplate.opsForList().range("testList", 142, 142);
//        Long testList1 = redisTemplate.opsForList().size("testList");
//        System.out.println(testList1);
//        System.out.println(testList);

//        if (testList.size() > 0) {
////            List<MoocUserT> moocUserTList = new ArrayList<>();
////            for (int i = 0; i < testList.size(); i++) {
////                moocUserTList.add(JSONObject.parseObject(testList.get(i), MoocUserT.class));
////            }
//            System.out.println("testList"+testList);
//        } else {
//            Thread.sleep(1000);
//            test();
//            System.out.println("没出来呢");
//        }


//        System.out.println(JSONArray.toJSONString(userT));

//        MoocUserT ms = JSONObject.parseObject(JSONArray.toJSONString(userT), MoocUserT.class);
//        System.out.println(moocUserTList);
    }


    public void testData() {

    }


    @Test
    public void addPwd() {
        MoocUserT user = new MoocUserT();
        user.setUserName("admin");
        user.setUserPwd(MD5Util.encrypt("admin123"));
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("UUID", 4);
        backendUserTMapper.update(user, queryWrapper);
    }

    @Test
    public void add() {
        for (int i = 0; i < 6; i++) {
            MoocUserT user = new MoocUserT();
            user.setUserName("admin1" + i);
            user.setUserPwd("admin1" + i);
            user.setUserPhone("13441508250" + i);
            backendUserTMapper.insert(user);
        }
    }

    @Test
    public void select() {
        QueryWrapper queryWrapper = new QueryWrapper();

        List<MoocUserT> moocBackendUserTList = backendUserTMapper.selectList(queryWrapper);

        moocBackendUserTList.forEach(
                System.out::print
        );
    }

    private List pageList(Long page, Long limit) {
        List<Long> returnList = new ArrayList();
        //计算开始页面
        //不是第一页的情况下
        if (page > 1) {
            Long i = (page - 1) * limit;
            Long finish = (i + limit) - 1;
            returnList.add(i);
            returnList.add(finish);
            return returnList;
            //处在第一页的情况下
        } else {
            Long i = page - 1;
            Long finish = limit - 1;
            returnList.add(i);
            returnList.add(finish);
            return returnList;
        }
    }


}
