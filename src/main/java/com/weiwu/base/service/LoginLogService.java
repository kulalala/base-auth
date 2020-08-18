package com.weiwu.base.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.weiwu.base.entity.LoginLog;
import com.weiwu.base.pojo.dto.ParamsDto;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 */
public interface LoginLogService extends IService<LoginLog> {

    /**
     * 查询登录次数
     *
     * @param id
     * @return
     */
    Integer findMaxLoginTatalByUserId(String id);


    /**
     * 用户登录日志
     *
     * @param page
     * @param dto
     * @return
     */
    List<LoginLog> findUserLoginLogByPage(Page<LoginLog> page, ParamsDto dto);

    /**
     * 统计登陆
     *
     * @return
     */
    Object findUserLoginTotal();
}
