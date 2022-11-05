package com.mooc.meetingfilm.film.controller.vo;

import lombok.Data;

/**
 * @author Wang.Z.C
 * @create 2021/7/29
 * 获取影片列表返回对象
 */
@Data
public class DescribeFilmsResVo {
    private String filmId;
    private String filmStatus;
    private String filmName;
    private String filmEnName;
    private String filmScore;
    private String preSaleNum;
    private String boxOffice;
    private String filmTime;
    private String filmLength;
    private String mainImg;

}
