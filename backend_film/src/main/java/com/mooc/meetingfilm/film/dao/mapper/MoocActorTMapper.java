package com.mooc.meetingfilm.film.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mooc.meetingfilm.film.controller.vo.DescribeActorsResVo;
import com.mooc.meetingfilm.film.dao.entity.MoocActorT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 演员表 Mapper 接口
 * </p>
 *
 * @author chaochao
 * @since 2021-07-29
 */
public interface MoocActorTMapper extends BaseMapper<MoocActorT> {

    IPage<DescribeActorsResVo> describeActors(@Param("page") Page<DescribeActorsResVo> page);

}
