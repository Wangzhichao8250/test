package com.mooc.meetingfilm.utils.common.vo;

import com.mooc.meetingfilm.utils.exception.CommonServiceException;

/**
 * @author Wang.Z.C
 * @create 2021/7/24
 * 公共请求对象
 */
public abstract class BaseRequestVo {

    /**
     * 公共的参数验证方法
     */
    public abstract void checkParam() throws CommonServiceException;
}
