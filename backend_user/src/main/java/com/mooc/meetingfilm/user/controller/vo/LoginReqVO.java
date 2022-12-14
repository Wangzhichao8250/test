package com.mooc.meetingfilm.user.controller.vo;

import com.mooc.meetingfilm.utils.common.vo.BaseRequestVo;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import com.mooc.meetingfilm.utils.util.ToolUtils;
import lombok.Data;

/**
 * @author Wang.Z.C
 * @create 2021/7/24
 * @description : 登陆请求对象
 **/
@Data
public class LoginReqVO extends BaseRequestVo {

    private String username;
    private String password;


    @Override
    public void checkParam() throws CommonServiceException {
        // TODO 验证数据合法性
        if (ToolUtils.strIsNull(username) || ToolUtils.strIsNull(password)) {
            throw new CommonServiceException(404, "username 或 password不能为空");
        }
    }

}
