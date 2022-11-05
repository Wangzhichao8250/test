package com.mooc.meetingfilm.cinema.controller.vo;

import lombok.Data;

/**
 * @author Wang.Z.C
 * @create 2021/7/31
 */
@Data
public class CinemaSavedReqVo {

    private String brandId;
    private String areaId;
    private String hallTypeIds;
    private String cinemaName;
    private String cinemaAddress;
    private String cinemaTele;
    private String cinemaImgAddress;
    private String cinemaPrice;

}
