package com.mooc.meetingfilm.user.service;

import com.mooc.meetingfilm.utils.exception.CommonServiceException;

/**
 * @author Wang.Z.C
 * @create 2021/7/25
 * 用户接口
 */
public interface UserService {

    String checkUserLogin(String username,String password) throws CommonServiceException;
}
