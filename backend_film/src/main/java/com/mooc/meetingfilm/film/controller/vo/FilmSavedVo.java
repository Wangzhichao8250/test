package com.mooc.meetingfilm.film.controller.vo;

import com.mooc.meetingfilm.utils.common.vo.BaseRequestVo;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import lombok.Data;

/**
 * @author Wang.Z.C
 * @create 2021/7/29
 * 保存电影信息对象
 */
@Data
public class FilmSavedVo{

    private String filmStatus;
    private String filmName;
    private String filmEnName;
    private String mainImgAddress;
    private String filmScore;
    private String filmScorers;
    private String preSaleNum;
    private String boxOffice;
    private String filmTypeId;
    private String filmSourceId;
    private String filmCatIds;
    private String areaId;
    private String dateId;
    private String filmTime;
    private String directorId;
    private String actIds;      // actIds与RoleNames是不是能在数量上对应上
    private String roleNames;
    private String filmLength;
    private String biography;
    private String filmImgs;

}
