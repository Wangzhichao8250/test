package com.mooc.meetingfilm.utils.exception;

import lombok.Data;

/**
 * @author Wang.Z.C
 * @create 2021/7/24
 * 公共的业务逻辑错误
 */
@Data
public class CommonServiceException extends Exception {

    private Integer code;
    private String message;

    public CommonServiceException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
