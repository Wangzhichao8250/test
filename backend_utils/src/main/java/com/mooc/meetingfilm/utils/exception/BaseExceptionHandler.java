package com.mooc.meetingfilm.utils.exception;

import com.mooc.meetingfilm.utils.common.vo.BaseResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Wang.Z.C
 * @create 2021/7/25
 * 公共异常处理
 */
@Slf4j
@ControllerAdvice   //全局异常处理
public class BaseExceptionHandler {


    /**
     * CommonServiceException异常时，会执行该方法
     *
     * @return
     */
    @ExceptionHandler(CommonServiceException.class)
    @ResponseBody
    public BaseResponseVo serviceExceptionHandler(HttpServletRequest request, CommonServiceException e) {
        log.error("CommonServiceException,code:{},message:{}",e.getCode(),e.getMessage());
        return BaseResponseVo.serviceException(e);
    }
}
