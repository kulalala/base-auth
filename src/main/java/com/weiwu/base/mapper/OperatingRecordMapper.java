package com.weiwu.base.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.weiwu.base.entity.OperatingRecord;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.weiwu.base.pojo.dto.ParamsDto;
import com.weiwu.base.pojo.dto.ReqTotalDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 */
public interface OperatingRecordMapper extends BaseMapper<OperatingRecord> {

    /**
     * 查询操作记录
     *
     * @param page 分页
     * @param dto 参数dto
     * @return
     */
    List<OperatingRecord> findOperatingRecordByPage(Pagination page, @Param("dto") ParamsDto dto);

    /**
     * 统计所有 请求
     *
     */
    List<ReqTotalDto> findAllRequstCount();
}