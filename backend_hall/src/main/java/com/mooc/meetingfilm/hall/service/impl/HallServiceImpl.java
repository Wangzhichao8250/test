package com.mooc.meetingfilm.hall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mooc.meetingfilm.hall.controller.vo.HallSavedReqVo;
import com.mooc.meetingfilm.hall.controller.vo.HallsReqVo;
import com.mooc.meetingfilm.hall.controller.vo.HallsRespVo;
import com.mooc.meetingfilm.hall.dao.entity.MoocFieldT;
import com.mooc.meetingfilm.hall.dao.entity.MoocHallFilmInfoT;
import com.mooc.meetingfilm.hall.dao.mapper.MoocFieldTMapper;
import com.mooc.meetingfilm.hall.dao.mapper.MoocHallFilmInfoTMapper;
import com.mooc.meetingfilm.hall.service.HallService;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import com.mooc.meetingfilm.utils.util.ToolUtils;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

/**
 * @author Wang.Z.C
 * @create 2021/8/3
 */
@Service
public class HallServiceImpl implements HallService {

    @Autowired
    private MoocFieldTMapper fieldTMapper;

    @Autowired
    private MoocHallFilmInfoTMapper hallFilmInfoTMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient eurekaClient;

    /**
     * 获取播放厅列表
     *
     * @param hallsReqVo
     * @return
     * @throws CommonServiceException
     */
    @Override
    public IPage<HallsRespVo> describeHalls(HallsReqVo hallsReqVo) throws CommonServiceException {

        QueryWrapper<HallsRespVo> queryWrapper = new QueryWrapper<>();
        if (ToolUtils.strIsNotNul(hallsReqVo.getCinemaId())) {
            queryWrapper.eq("cinema_id", hallsReqVo.getCinemaId());
        }
        return fieldTMapper.describeHalls(new Page<>(hallsReqVo.getNowPage(), hallsReqVo.getPageSize()), queryWrapper);
    }

    /**
     * 新增播放厅
     *
     * @param reqVO
     * @throws CommonServiceException
     */
    @Override
    public void saveHall(HallSavedReqVo reqVO) throws CommonServiceException {
        //播放厅的列表数据
        MoocFieldT field = new MoocFieldT();
        field.setCinemaId(ToolUtils.str2Int(reqVO.getCinemaId()));
        field.setFilmId(ToolUtils.str2Int(reqVO.getFilmId()));
        field.setBeginTime(reqVO.getBeginTime());
        field.setEndTime(reqVO.getEndTime());
        field.setHallId(ToolUtils.str2Int(reqVO.getHallTypeId()));
        field.setHallName(reqVO.getHallName());
        field.setPrice(ToolUtils.str2Int(reqVO.getFilmPrice()));
        fieldTMapper.insert(field);


        /// 播放厅对应的影片数据， 影片冗余数据， 缓存里有一份
        MoocHallFilmInfoT hallFilmInfo = describeFilmInfo(reqVO.getFilmId());

        hallFilmInfoTMapper.insert(hallFilmInfo);
    }

    /**
     * 播放厅对应的影片数据， 影片冗余数据， 缓存里有一份
     * @param filmId
     * @return
     * @throws CommonServiceException
     */
    private MoocHallFilmInfoT describeFilmInfo(String filmId) throws CommonServiceException {
        // GET REGISTER
//        ServiceInstance choose = eurekaClient.choose("film-service");
//        // 组织调用参数
//        String hostname = choose.getHost();
//        int port = choose.getPort();

//        String uri = "/films/" + filmId;

        String url = "http://film-service/films/" + filmId;
        // 通过restTemplate调用影片服务
        JSONObject baseResponseVO = restTemplate.getForObject(url, JSONObject.class);

        // 解析返回值
        JSONObject dataJson = baseResponseVO.getJSONObject("data");

        MoocHallFilmInfoT hallFilmInfo = new MoocHallFilmInfoT();

        hallFilmInfo.setFilmId(dataJson.getIntValue("filmId"));
        hallFilmInfo.setFilmName(dataJson.getString("filmName"));
        hallFilmInfo.setFilmLength(dataJson.getString("filmLength"));
        hallFilmInfo.setFilmCats(dataJson.getString("filmCats"));
        hallFilmInfo.setActors(dataJson.getString("actors"));
        hallFilmInfo.setImgAddress(dataJson.getString("imgAddress"));

        return hallFilmInfo;
    }
}
