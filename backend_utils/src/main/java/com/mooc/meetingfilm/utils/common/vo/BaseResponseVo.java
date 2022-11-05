package com.mooc.meetingfilm.utils.common.vo;

import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import lombok.Data;

/**
 * @author Wang.Z.C
 * @create 2021/7/18
 * 表现层公共返回
 */
@Data
public class BaseResponseVo {

    private Integer code;   //业务编号
    private String message; //异常信息
    private Object data; //业务数据返回对象

    //私有构造方法，不允许创造实例
    private BaseResponseVo() {
    }

    //成功但是无数据
    public static BaseResponseVo success() {
        BaseResponseVo responseVo = new BaseResponseVo();
        responseVo.setCode(200);
        responseVo.setMessage("");
        return responseVo;
    }

    //成功但是无数据
    public static BaseResponseVo error(String str) {
        BaseResponseVo responseVo = new BaseResponseVo();
        responseVo.setCode(500);
        responseVo.setMessage(str);
        return responseVo;
    }


    //成功有数据
    public static <T> BaseResponseVo success(T data) {
        BaseResponseVo responseVo = new BaseResponseVo();
        responseVo.setCode(200);
        responseVo.setMessage("");
        responseVo.setData(data);
        return responseVo;
    }

    //出现业务异常
    public static BaseResponseVo serviceException(CommonServiceException e) {
        BaseResponseVo responseVo = new BaseResponseVo();
        responseVo.setCode(e.getCode());
        responseVo.setMessage(e.getMessage());
        return responseVo;
    }
}
