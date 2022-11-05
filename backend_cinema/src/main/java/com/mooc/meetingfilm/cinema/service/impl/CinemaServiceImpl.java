package com.mooc.meetingfilm.cinema.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mooc.meetingfilm.cinema.controller.vo.CinemaSavedReqVo;
import com.mooc.meetingfilm.cinema.controller.vo.DescribeCinemasResVo;
import com.mooc.meetingfilm.cinema.dao.entity.MoocCinemaT;
import com.mooc.meetingfilm.cinema.dao.mapper.MoocCinemaTMapper;
import com.mooc.meetingfilm.cinema.service.CinemaService;
import com.mooc.meetingfilm.utils.common.vo.BaseResponseVo;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Wang.Z.C
 * @create 2021/7/31
 */
@Service
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    private MoocCinemaTMapper cinemaTMapper;

    /**
     * 保存影院方法
     *
     * @param cinemaAddReqVO
     * @return
     * @throws CommonServiceException
     */
    @Override
    public boolean saveCinema(CinemaSavedReqVo cinemaAddReqVO) throws CommonServiceException {
        MoocCinemaT cinemaT = new MoocCinemaT();
        cinemaT.setCinemaName(cinemaAddReqVO.getCinemaName());
        cinemaT.setCinemaPhone(cinemaAddReqVO.getCinemaTele());
        cinemaT.setBrandId(Integer.parseInt(cinemaAddReqVO.getBrandId()));
        cinemaT.setAreaId(Integer.parseInt(cinemaAddReqVO.getAreaId()));
        cinemaT.setHallIds(cinemaAddReqVO.getHallTypeIds());
        cinemaT.setImgAddress(cinemaAddReqVO.getCinemaImgAddress());
        cinemaT.setCinemaAddress(cinemaAddReqVO.getCinemaAddress());
        cinemaT.setMinimumPrice(Integer.parseInt(cinemaAddReqVO.getCinemaPrice()));

        int insert = cinemaTMapper.insert(cinemaT);
        if (insert > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 浏览影院列表
     *
     * @param nowPage
     * @param pageSize
     * @return
     * @throws CommonServiceException
     */
    @Override
    public BaseResponseVo describeCinemas(int nowPage, int pageSize) throws CommonServiceException {

        try {
            IPage<MoocCinemaT> moocCinemaTIPage = cinemaTMapper.selectPage(new Page<>(nowPage, pageSize), null);
            Map<String, Object> result = Maps.newHashMap();
            result.put("nowPage", moocCinemaTIPage.getCurrent());
            result.put("pageSize", moocCinemaTIPage.getSize());
            result.put("totalPages", moocCinemaTIPage.getPages());
            result.put("totalSize", moocCinemaTIPage.getTotal());
            if (moocCinemaTIPage.getTotal() == 0) {
                return BaseResponseVo.success();
            } else {
                List<MoocCinemaT> recordsData = moocCinemaTIPage.getRecords();
                List<DescribeCinemasResVo> resCinemaVo = Lists.newArrayList();
                for (int i = 0; i < recordsData.size(); i++) {
                    DescribeCinemasResVo vo =
                            DescribeCinemasResVo.builder()
                                    .brandId(recordsData.get(i).getBrandId().toString())
                                    .areaId(recordsData.get(i).getAreaId().toString())
                                    .hallTypeIds(recordsData.get(i).getHallIds())
                                    .cinemaName(recordsData.get(i).getCinemaName())
                                    .cinemaTele(recordsData.get(i).getCinemaPhone())
                                    .cinemaAddress(recordsData.get(i).getCinemaAddress())
                                    .cinemaPrice(recordsData.get(i).getMinimumPrice().toString())
                                    .build();
                    resCinemaVo.add(i, vo);
                }
                result.put("cinemas", resCinemaVo);
                return BaseResponseVo.success(result);
            }
        } catch (Exception e) {
            throw new CommonServiceException(500, e.getMessage());
        }

    }
}
