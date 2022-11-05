package com.mooc.meetingfilm.hall.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mooc.meetingfilm.hall.controller.vo.HallsRespVo;
import com.mooc.meetingfilm.hall.dao.entity.MoocFieldT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 放映场次表 Mapper 接口
 * </p>
 *
 * @author chaochao
 * @since 2021-08-03
 */
public interface MoocFieldTMapper extends BaseMapper<MoocFieldT> {

    IPage<HallsRespVo> describeHalls(Page<HallsRespVo> page, @Param("ew") QueryWrapper<HallsRespVo> queryWrapper);
}
