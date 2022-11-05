package com.mooc.meetingfilm.cinema.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.mooc.meetingfilm.cinema.controller.vo.CinemaSavedReqVo;
import com.mooc.meetingfilm.cinema.service.CinemaService;
import com.mooc.meetingfilm.hall.service.HallService;
import com.mooc.meetingfilm.utils.common.vo.BasePageVo;
import com.mooc.meetingfilm.utils.common.vo.BaseRequestVo;
import com.mooc.meetingfilm.utils.common.vo.BaseResponseVo;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Wang.Z.C
 * @create 2021/7/31
 * 影院模块表现层
 */
@RestController
@RequestMapping(value = "/cinemas")
public class CinemaController {

    private static final Logger logger = LoggerFactory.getLogger(CinemaController.class);

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private HallService hallService;

    /**
     * 添加影院
     * @param cinemaSavedVo
     * @return
     * @throws CommonServiceException
     */
    @RequestMapping(value = "/cinema:add",method = RequestMethod.POST)
    public BaseResponseVo saveCinema(@RequestBody CinemaSavedReqVo cinemaSavedVo) throws CommonServiceException {
        logger.info("cinemaSavedVo[{}]",cinemaSavedVo);
        boolean resBool = cinemaService.saveCinema(cinemaSavedVo);
        if (resBool) {
            return BaseResponseVo.success("新增影院信息成功");
        } else {
            return BaseResponseVo.error("新增影院信息失败");
        }
    }

    /**
     * 查询所有影院列表
     * @param pageVo
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResponseVo describeCinemas(BasePageVo pageVo) throws CommonServiceException  {
        return cinemaService.describeCinemas(pageVo.getNowPage(),pageVo.getPageSize());
    }

}
