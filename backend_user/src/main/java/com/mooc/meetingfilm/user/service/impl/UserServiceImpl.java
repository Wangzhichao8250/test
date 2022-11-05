package com.mooc.meetingfilm.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mooc.meetingfilm.user.dao.entity.MoocUserT;
import com.mooc.meetingfilm.user.dao.mapper.MoocBackendUserMapper;
import com.mooc.meetingfilm.user.service.UserService;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import com.mooc.meetingfilm.utils.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Wang.Z.C
 * @create 2021/7/25
 * 用户模块业务实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MoocBackendUserMapper backendUserMapper;

    /**
     * @param username
     * @param password
     * @throws CommonServiceException
     */
    @Override
    public String checkUserLogin(String username, String password) throws CommonServiceException {

        //根据用户名获取用户信息
        MoocUserT value = backendUserMapper.selectOne(new QueryWrapper<MoocUserT>().eq("user_name",username));

        if (value == null) {
            throw new CommonServiceException(404, "用户名输入有误");
        }
        System.out.println("password:" + password);

        //验证密码是否正确
        String encrypt = MD5Util.encrypt(password);

        System.out.println("encrypt:" + encrypt);
        System.out.println("value:" + value.getUserPwd());
        if (!encrypt.equals(value.getUserPwd())) {
            throw new CommonServiceException(500, "用户密码输入错误");
        } else {
            return value.getUuid() + "";
        }
    }
}
