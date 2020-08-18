package com.weiwu.base.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.weiwu.base.entity.OperatingRecord;
import com.weiwu.base.pojo.dto.ParamsDto;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 */
public interface OperatingRecordService extends IService<OperatingRecord> {

    /**
     * 查询操作记录
     *
     * @param page
     * @param dto
     * @return
     */
    List<OperatingRecord> findOperatingRecordByPage(Page<OperatingRecord> page, ParamsDto dto);

    /**
     * 访问统计
     *
     * @return
     */
    Object findUserReqTotal();
}
