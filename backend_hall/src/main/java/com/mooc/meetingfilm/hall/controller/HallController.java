package com.mooc.meetingfilm.hall.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.mooc.meetingfilm.hall.controller.vo.HallSavedReqVo;
import com.mooc.meetingfilm.hall.controller.vo.HallsReqVo;
import com.mooc.meetingfilm.hall.controller.vo.HallsRespVo;
import com.mooc.meetingfilm.hall.service.HallService;
import com.mooc.meetingfilm.utils.common.vo.BaseResponseVo;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Wang.Z.C
 * @create 2021/8/3
 */
@RestController
@RequestMapping(value = "/halls")
public class HallController {


    @Autowired
    private HallService hallService;

    /**
     * 新增播放厅
     *
     * @param hallSavedReqVo
     * @return
     * @throws CommonServiceException
     */
    @RequestMapping(value = "/hall:add", method = RequestMethod.POST)
    public BaseResponseVo saveHall(@RequestBody HallSavedReqVo hallSavedReqVo) throws CommonServiceException {

        hallService.saveHall(hallSavedReqVo);
        return BaseResponseVo.success();
    }


    /**
     * 获取播放厅列表
     *
     * @param hallsReqVo
     * @return
     * @throws CommonServiceException
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public BaseResponseVo describeHalls(HallsReqVo hallsReqVo) throws CommonServiceException {
        //装个样得了
        hallsReqVo.checkParam();

        IPage<HallsRespVo> page = hallService.describeHalls(hallsReqVo);

        Map<String, Object> halls = describePageResult(page, "halls");


        return BaseResponseVo.success(halls);
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
