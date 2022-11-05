package com.mooc.meetingfilm.cinema.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mooc.meetingfilm.cinema.controller.vo.CinemaSavedReqVo;
import com.mooc.meetingfilm.cinema.controller.vo.DescribeCinemasResVo;
import com.mooc.meetingfilm.utils.common.vo.BaseResponseVo;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;

/**
 * @author Wang.Z.C
 * @create 2021/7/31
 */
public interface CinemaService {
    boolean saveCinema(CinemaSavedReqVo reqVo) throws CommonServiceException;

    BaseResponseVo describeCinemas(int nowPage, int pageSize) throws CommonServiceException;
}
