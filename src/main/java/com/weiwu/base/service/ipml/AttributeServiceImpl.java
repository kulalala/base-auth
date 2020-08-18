package com.weiwu.base.service.ipml;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.weiwu.base.Enum.EnumCode;
import com.weiwu.base.entity.Attribute;
import com.weiwu.base.mapper.AttributeMapper;
import com.weiwu.base.pojo.dto.AttributeDetailDto;
import com.weiwu.base.pojo.dto.ParamsDto;
import com.weiwu.base.service.AttributeDetailService;
import com.weiwu.base.service.AttributeService;
import com.weiwu.base.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 */
@Service
public class AttributeServiceImpl extends ServiceImpl<AttributeMapper, Attribute> implements AttributeService {

    @Autowired
    private AttributeDetailService attributeDetailService;

    /**
     * 查询属性
     */
    public List<Attribute> findAttributesByPage(Page<Attribute> page, ParamsDto dto) {
        return super.baseMapper.findAttributesByPage(page, dto);
    }

    /**
     * 新增属性
     */
    public Object addAttributes(Attribute attribute) {
        baseMapper.insert(attribute);
        return ResultUtil.result(EnumCode.OK.getValue(), "新增成功");
    }

    /**
     * 删除属性
     */
    @Transactional
    public Object delAttributes(String[] ids) {
        for (String id : ids) {
            super.baseMapper.deleteById(id);
            List<AttributeDetailDto> list = attributeDetailService.findAttributeDetailByAttrId(id);
            if (null != list && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    attributeDetailService.deleteById(list.get(i).getId());
                }
            }
        }
        return ResultUtil.result(EnumCode.OK.getValue(), "删除成功");
    }
}
