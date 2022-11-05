package com.mooc.meetingfilm.film.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mooc.meetingfilm.common.config.MybatisPlusConfig;
import com.mooc.meetingfilm.film.controller.vo.DescribeActorsResVo;
import com.mooc.meetingfilm.film.controller.vo.DescribeFilmMessageVo;
import com.mooc.meetingfilm.film.controller.vo.DescribeFilmsResVo;
import com.mooc.meetingfilm.film.controller.vo.FilmSavedVo;
import com.mooc.meetingfilm.film.service.FilmService;
import com.mooc.meetingfilm.utils.common.vo.BasePageVo;
import com.mooc.meetingfilm.utils.common.vo.BaseResponseVo;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import com.mooc.meetingfilm.utils.util.ToolUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.Origin;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.JSONPatch.OperationType.test;

/**
 * @author Wang.Z.C
 * @create 2021/7/29
 * 影片模块表现层
 */
@RestController
@RequestMapping(value = "/films")
public class FilmController {

    private static final Logger logger = LoggerFactory.getLogger(FilmController.class);

    @Autowired
    private FilmService filmService;

    @Autowired
    private MybatisPlusConfig mybatisPlusConfig;


    /**
     * 获取演员列表
     *
     * @param basePageVo
     * @return
     * @throws CommonServiceException
     */
    @RequestMapping(value = "/actors", method = RequestMethod.GET)
    public BaseResponseVo describeActors(BasePageVo basePageVo) throws CommonServiceException {
        logger.info("basePageVo1:[{}]", basePageVo);
        logger.info("basePageVo2:{}", basePageVo);
        basePageVo.checkParam();
        //调用逻辑层
        IPage<DescribeActorsResVo> resultActors = filmService.describeActors(basePageVo.getNowPage(), basePageVo.getPageSize());


        return BaseResponseVo.success(describePageResult(resultActors, "actors"));
        
    }



    /**
     * 获取影片列表
     *
     * @param basePageVo
     * @return
     * @throws CommonServiceException
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public BaseResponseVo describeFilms(BasePageVo basePageVo) throws CommonServiceException {
        basePageVo.checkParam();

        return BaseResponseVo.success(describePageResult(filmService.describeFilms(basePageVo.getNowPage(), basePageVo.getPageSize()), "movies"));
    }


    /**
     * 根据电影主键获取电影详情
     *
     * @param filmId
     * @return
     * @throws CommonServiceException
     */
    @RequestMapping(value = "/{filmId}", method = RequestMethod.GET)
    public BaseResponseVo describeFilmMessageById(@PathVariable("filmId") String filmId) throws CommonServiceException {

        DescribeFilmMessageVo filmMessageVo = filmService.describeFilmMessageById(filmId);
        if (filmMessageVo == null) {
            return BaseResponseVo.success();
        } else {
            return BaseResponseVo.success(filmMessageVo);
        }
    }

    public static void main(String[] args) {
        Timestamp localDateTime = ToolUtils.str2LocalDateTime("2018-07-05" + " 00:00:00");
        System.out.println(localDateTime);
    }


    @RequestMapping(value = "/film:add", method = RequestMethod.POST)
    public BaseResponseVo saveFilmInfo(@RequestBody FilmSavedVo filmSavedVo) throws CommonServiceException {

        boolean resBool = filmService.saveFilmInfo(filmSavedVo);
        if (resBool) {
            return BaseResponseVo.success("新增成功");
        } else {
            return BaseResponseVo.error("新增失败");
        }

    }


    /**
     * 获取分页对象的公共接口
     *
     * @param iPage
     * @param title
     * @return
     */
    private Map<String, Object> describePageResult(IPage iPage, String title) {
        Map<String, Object> result = Maps.newHashMap();
        result.put("totalSize", iPage.getTotal());
        result.put("totalPages", iPage.getPages());
        result.put("pageSize", iPage.getSize());
        result.put("nowPage", iPage.getCurrent());
        System.out.println("result:" + result);

        result.put(title, iPage.getRecords());

        return result;
    }

}
