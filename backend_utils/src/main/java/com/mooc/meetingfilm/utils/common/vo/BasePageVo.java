package com.mooc.meetingfilm.utils.common.vo;

import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import com.mooc.meetingfilm.utils.util.ToolUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Wang.Z.C
 * @create 2021/7/29
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BasePageVo extends BaseRequestVo {

    private Integer nowPage = 1;
    private Integer pageSize = 10;

    @Override
    public void checkParam() throws CommonServiceException {
        //验证数据合法性
        if (ToolUtils.strIsNull(nowPage.toString()) || ToolUtils.strIsNull(pageSize.toString())) {
            throw new CommonServiceException(500, "nowPage 或 pageSize必须填写参数");
        }
    }
}
