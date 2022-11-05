package com.mooc.meetingfilm.film.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mooc.meetingfilm.film.controller.vo.DescribeActorsResVo;
import com.mooc.meetingfilm.film.controller.vo.DescribeFilmMessageVo;
import com.mooc.meetingfilm.film.controller.vo.DescribeFilmsResVo;
import com.mooc.meetingfilm.film.controller.vo.FilmSavedVo;
import com.mooc.meetingfilm.utils.common.vo.BasePageVo;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;

/**
 * @author Wang.Z.C
 * @create 2021/7/29
 * 影片逻辑层
 */
public interface FilmService {


    IPage<DescribeActorsResVo> describeActors(int nowPage,int pageSize) throws CommonServiceException;

    IPage<DescribeFilmsResVo> describeFilms(int nowPage, int pageSize) throws CommonServiceException;

    DescribeFilmMessageVo describeFilmMessageById(String filmId) throws CommonServiceException;

    boolean saveFilmInfo(FilmSavedVo savedVo) throws CommonServiceException;
}
