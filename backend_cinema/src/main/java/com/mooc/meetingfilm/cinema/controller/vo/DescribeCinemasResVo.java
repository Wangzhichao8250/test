package com.mooc.meetingfilm.cinema.controller.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author Wang.Z.C
 * @create 2021/7/31
 */
@Data
@Builder
public class DescribeCinemasResVo {

    private String brandId;
    private String areaId;
    private String hallTypeIds;
    private String cinemaName;
    private String cinemaAddress;
    private String cinemaTele;
    private String cinemaImgAddress;
    private String cinemaPrice;

}
