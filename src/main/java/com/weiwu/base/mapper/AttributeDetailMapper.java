package com.weiwu.base.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.weiwu.base.entity.AttributeDetail;
import com.weiwu.base.pojo.dto.AttributeDetailDto;
import com.weiwu.base.pojo.dto.ParamsDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 */
public interface AttributeDetailMapper extends BaseMapper<AttributeDetail> {

    /**
     * @desc: 查询属性明细
     *
     */
    List<AttributeDetail> findAttributeDetailByPage(Pagination page,@Param("dto") ParamsDto dto);

    /**
     * @desc: 根据属性id查询属性明细
     * 
     */
    List<AttributeDetailDto> findAttributeDetailByAttrId(@Param("attrId") String attrId);
}