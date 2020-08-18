package com.weiwu.base.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.weiwu.base.entity.LoginLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.weiwu.base.pojo.dto.ParamsDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 */
public interface LoginLogMapper extends BaseMapper<LoginLog> {

    /**
     * 查询登录次数
     *
     * @param id
     * @return
     */
    Integer findMaxLoginTatalByUserId(@Param("id") String id);

    /**
     * 用户登录日志
     *
     * @param dto 参数dto
     * @return
     */
    List<LoginLog> findUserLoginLogByPage(Pagination page, @Param("dto") ParamsDto dto);

    /**
     * 查询用户登录总次数
     *
     */
    List<LoginLog> findUserLoginTotal();
}