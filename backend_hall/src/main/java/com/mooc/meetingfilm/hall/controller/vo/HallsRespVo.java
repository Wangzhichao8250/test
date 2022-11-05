package com.mooc.meetingfilm.hall.controller.vo;

import lombok.Data;

/**
 * @author Wang.Z.C
 * @create 2021/8/3
 */
@Data
public class HallsRespVo {
    private String cinemaName;
    private String hallName;
    private String filmName;
    private String hallTypeName;
    private String beginTime;
    private String endTime;
    private String filmPrice;
}
