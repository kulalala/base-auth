package com.weiwu.base.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.weiwu.base.entity.Attribute;
import com.weiwu.base.pojo.dto.ParamsDto;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 */
public interface AttributeService extends IService<Attribute> {


    /**
     * 查询属性
     *
     * @param page
     * @param dto
     * @return
     */
    List<Attribute> findAttributesByPage(Page<Attribute> page, ParamsDto dto);

    /**
     * 新增属性
     *
     * @param attribute
     * @return
     */
    Object addAttributes(Attribute attribute);

    /**
     * 删除属性
     *
     * @param ids
     * @return
     */
    Object delAttributes(String[] ids);

}
