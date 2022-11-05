package com.mooc.meetingfilm.hall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mooc.meetingfilm.hall.controller.vo.HallSavedReqVo;
import com.mooc.meetingfilm.hall.controller.vo.HallsReqVo;
import com.mooc.meetingfilm.hall.controller.vo.HallsRespVo;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;

/**
 * @author Wang.Z.C
 * @create 2021/8/3
 */
public interface HallService {


    IPage<HallsRespVo> describeHalls(HallsReqVo hallsReqVo) throws CommonServiceException;

    void saveHall(HallSavedReqVo hallSavedReqVo) throws CommonServiceException;

}
