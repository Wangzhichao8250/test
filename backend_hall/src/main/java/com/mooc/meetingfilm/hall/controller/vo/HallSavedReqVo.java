package com.mooc.meetingfilm.hall.controller.vo;

import lombok.Data;

/**
 * @author Wang.Z.C
 * @create 2021/8/3
 */
@Data
public class HallSavedReqVo {

    private String cinemaId;
    private String filmId;
    private String hallTypeId;
    private String beginTime;
    private String endTime;
    private String filmPrice;
    private String hallName;
}
