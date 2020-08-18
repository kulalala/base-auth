package com.weiwu.base.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.weiwu.base.entity.Attribute;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.weiwu.base.pojo.dto.ParamsDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 */
public interface AttributeMapper extends BaseMapper<Attribute> {
    /**
     * @desc: 查询属性
     *
     */
    List<Attribute> findAttributesByPage(Pagination page, @Param("dto") ParamsDto dto);
}