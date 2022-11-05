package com.mooc.meetingfilm.hall.controller.vo;

import com.mooc.meetingfilm.utils.common.vo.BasePageVo;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import lombok.Data;

/**
 * @author Wang.Z.C
 * @create 2021/8/3
 */
@Data
public class HallsReqVo extends BasePageVo {

    private String cinemaId;

    @Override
    public void checkParam() {
    }
}
