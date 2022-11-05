package com.mooc.meetingfilm.user.controller;

import com.mooc.meetingfilm.user.controller.vo.LoginReqVO;
import com.mooc.meetingfilm.user.service.UserService;
import com.mooc.meetingfilm.utils.common.vo.BaseResponseVo;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import com.mooc.meetingfilm.utils.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.PrimitiveIterator;

/**
 * @author Wang.Z.C
 * @create 2021/7/24
 * 用户模块表现层
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResponseVo login(@RequestBody LoginReqVO reqVO) throws CommonServiceException {

        //数据验证
        reqVO.checkParam();

        String userId = userService.checkUserLogin(reqVO.getUsername(), reqVO.getPassword());

        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

        //生成randomKey
        String randomKey = jwtTokenUtil.getRandomKey();
        //根据randomKey和uuid生成token
        String token = jwtTokenUtil.generateToken(userId,randomKey);


        //randomKey,token
        Map<String, String> result = new HashMap<>();
        result.put("randomKey", randomKey);
        result.put("token", token);


        return BaseResponseVo.success(result);
    }

}
