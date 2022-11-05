package com.mooc.meetingfilm.film.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.mooc.meetingfilm.film.controller.vo.DescribeActorsResVo;
import com.mooc.meetingfilm.film.controller.vo.DescribeFilmMessageVo;
import com.mooc.meetingfilm.film.controller.vo.DescribeFilmsResVo;
import com.mooc.meetingfilm.film.controller.vo.FilmSavedVo;
import com.mooc.meetingfilm.film.dao.entity.MoocFilmActorT;
import com.mooc.meetingfilm.film.dao.entity.MoocFilmInfoT;
import com.mooc.meetingfilm.film.dao.entity.MoocFilmT;
import com.mooc.meetingfilm.film.dao.mapper.MoocActorTMapper;
import com.mooc.meetingfilm.film.dao.mapper.MoocFilmActorTMapper;
import com.mooc.meetingfilm.film.dao.mapper.MoocFilmInfoTMapper;
import com.mooc.meetingfilm.film.dao.mapper.MoocFilmTMapper;
import com.mooc.meetingfilm.film.service.FilmService;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import com.mooc.meetingfilm.utils.util.ToolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Wang.Z.C
 * @create 2021/7/29
 * 影片模块实现层
 */
@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private MoocActorTMapper actorTMapper;

    @Autowired
    private MoocFilmTMapper filmTMapper;

    @Autowired
    private MoocFilmInfoTMapper filmInfoTMapper;

    @Autowired
    private MoocFilmActorTMapper filmActorTMapper;

    /**
     * 浏览演员列表
     *
     * @param nowPage
     * @param pageSize
     * @return
     * @throws CommonServiceException
     */
    @Override
    public IPage<DescribeActorsResVo> describeActors(int nowPage, int pageSize) throws CommonServiceException {
        return actorTMapper.describeActors(new Page<>(nowPage, pageSize));
    }

    /**
     * 获取影片列表
     *
     * @param nowPage
     * @param pageSize
     * @return
     * @throws CommonServiceException
     */
    @Override
    public IPage<DescribeFilmsResVo> describeFilms(int nowPage, int pageSize) throws CommonServiceException {
        return filmTMapper.describeFilms(new Page<>(nowPage, pageSize));
    }

    /**
     * 根据影片ID获取电影详情
     *
     * @param filmId
     * @return
     * @throws CommonServiceException
     */
    @Override
    public DescribeFilmMessageVo describeFilmMessageById(String filmId) throws CommonServiceException {
        return filmTMapper.describeFilmByIdInfo(filmId);
    }


    /**
     * 保存电影信息
     *
     * @param savedVo
     * @throws CommonServiceException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveFilmInfo(FilmSavedVo savedVo) throws CommonServiceException {
        System.out.println("savedVo:"+savedVo);
        try {
            //保存电影主表
            MoocFilmT film = new MoocFilmT();
            film.setFilmName(savedVo.getFilmName());
            film.setFilmType(ToolUtils.str2Int(savedVo.getFilmTypeId()));
            film.setImgAddress(savedVo.getMainImgAddress());
            film.setFilmScore(savedVo.getFilmScore());
            film.setFilmPresalenum(ToolUtils.str2Int(savedVo.getPreSaleNum()));
            film.setFilmBoxOffice(ToolUtils.str2Int(savedVo.getBoxOffice()));
            film.setFilmSource(ToolUtils.str2Int(savedVo.getFilmSourceId()));
            film.setFilmCats(savedVo.getFilmCatIds());
            film.setFilmArea(ToolUtils.str2Int(savedVo.getActIds()));
            film.setFilmDate(ToolUtils.str2Int(savedVo.getDateId()));
            film.setFilmTime(ToolUtils.str2LocalDateTime(savedVo.getFilmTime() + " 00:00:00"));
            film.setFilmStatus(ToolUtils.str2Int(savedVo.getFilmStatus()));

            int insertFirst = filmTMapper.insert(film);
            //保存电影字表 信息表
            MoocFilmInfoT filmInfo = new MoocFilmInfoT();
            filmInfo.setFilmId(film.getUuid() + "");
            filmInfo.setFilmEnName(savedVo.getFilmEnName());
            filmInfo.setFilmScore(savedVo.getFilmScore());
            filmInfo.setFilmScoreNum(ToolUtils.str2Int(savedVo.getFilmScorers()));
            filmInfo.setFilmLength(ToolUtils.str2Int(savedVo.getFilmLength()));
            filmInfo.setBiography(savedVo.getBiography());
            filmInfo.setDirectorId(ToolUtils.str2Int(savedVo.getDirectorId()));
            filmInfo.setFilmImgs(savedVo.getFilmImgs());

            int insertSecond = filmInfoTMapper.insert(filmInfo);

            String[] actorId = savedVo.getActIds().split("#");
            String[] roleNames = savedVo.getRoleNames().split("#");

            if (actorId.length != roleNames.length) {
                throw new CommonServiceException(500, "演员和角色名数量不匹配!");
            }
            ArrayList<Integer> insertThirds = Lists.newArrayList();
            for (int i = 0; i < actorId.length; i++) {
                //保存演员映射表
                MoocFilmActorT filmActor = new MoocFilmActorT();
                filmActor.setFilmId(film.getUuid());
                filmActor.setActorId(ToolUtils.str2Int(actorId[i]));
                filmActor.setRoleName(roleNames[i]);
                int insertThird = filmActorTMapper.insert(filmActor);
                insertThirds.add(insertFirst);
            }
            if (insertThirds.size() > 0 && insertSecond > 0 && insertFirst > 0) {
                return true;
            }


        } catch (Exception e) {
            throw new CommonServiceException(500, e.getMessage());
        }
        return false;
    }
}
