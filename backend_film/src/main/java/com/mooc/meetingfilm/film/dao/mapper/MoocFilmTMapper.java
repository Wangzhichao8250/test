package com.mooc.meetingfilm.film.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mooc.meetingfilm.film.controller.vo.DescribeFilmMessageVo;
import com.mooc.meetingfilm.film.controller.vo.DescribeFilmsResVo;
import com.mooc.meetingfilm.film.dao.entity.MoocFilmT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 影片主表 Mapper 接口
 * </p>
 *
 * @author chaochao
 * @since 2021-07-29
 */
public interface MoocFilmTMapper extends BaseMapper<MoocFilmT> {

    IPage<DescribeFilmsResVo> describeFilms(Page<DescribeFilmsResVo> page);

    DescribeFilmMessageVo describeFilmByIdInfo(@Param("filmId") String filmId);

}
