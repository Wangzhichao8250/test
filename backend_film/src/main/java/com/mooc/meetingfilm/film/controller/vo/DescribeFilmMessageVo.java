package com.mooc.meetingfilm.film.controller.vo;

import lombok.Data;

/**
 * @author Wang.Z.C
 * @create 2021/7/29
 * 根据主键获取影片信息对象
 */
@Data
public class DescribeFilmMessageVo {
    private String filmId;
    private String filmName;
    private String filmLength;
    private String filmCats;
    private String actors;
    private String imgAddress;
    private String subAddress;
}
