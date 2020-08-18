package com.weiwu.base.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.weiwu.base.entity.AttributeDetail;
import com.baomidou.mybatisplus.service.IService;
import com.weiwu.base.pojo.dto.AttributeDetailDto;
import com.weiwu.base.pojo.dto.ParamsDto;
import com.weiwu.base.pojo.vo.AttributeDetailVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 */
public interface AttributeDetailService extends IService<AttributeDetail> {


    /**
     * 查询属性明细
     *
     * @param page
     * @param dto
     * @return
     */
    List<AttributeDetail> findAttributeDetailByPage(Page<AttributeDetail> page, ParamsDto dto);

    /**
     * 新增属性明细
     *
     * @param attributeDetail
     * @return
     */
    Object addAttributeDetail(AttributeDetailVo attributeDetail);

    /**
     * 根据属性id查询属性明细
     *
     * @param attrId
     * @return
     */
    List<AttributeDetailDto> findAttributeDetailByAttrId(String attrId);

    /**
     * 删除属性明细
     *
     * @param ids
     * @return
     */
    Object delAttributeDetails(String[] ids);
}
