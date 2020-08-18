package com.weiwu.base.service.ipml;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.weiwu.base.Enum.EnumCode;
import com.weiwu.base.entity.AttributeDetail;
import com.weiwu.base.mapper.AttributeDetailMapper;
import com.weiwu.base.pojo.dto.AttributeDetailDto;
import com.weiwu.base.pojo.dto.ParamsDto;
import com.weiwu.base.pojo.vo.AttributeDetailVo;
import com.weiwu.base.service.AttributeDetailService;
import com.weiwu.base.utils.ResultUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 */
@Service
public class AttributeDetailServiceImpl extends ServiceImpl<AttributeDetailMapper, AttributeDetail> implements AttributeDetailService {

    /**
     * @desc: 查询属性明细
     */
    public List<AttributeDetail> findAttributeDetailByPage(Page<AttributeDetail> page, ParamsDto dto) {
        return super.baseMapper.findAttributeDetailByPage(page, dto);
    }

    /**
     * @desc: 新增属性明细
     */
    public Object addAttributeDetail(AttributeDetailVo vo) {
        AttributeDetail attributeDetail = new AttributeDetail();
        attributeDetail.setAttrId(vo.getId());
        attributeDetail.setName(vo.getName());
        Integer rows = super.baseMapper.insert(attributeDetail);
        if (rows > 0) {
            return ResultUtil.result(EnumCode.OK.getValue(), "新增成功");
        }
        return ResultUtil.result(EnumCode.INTERNAL_SERVER_ERROR.getValue(), "新增失败");
    }

    /**
     * @desc: 根据属性id查询属性明细
     */
    public List<AttributeDetailDto> findAttributeDetailByAttrId(String attrId) {
        return super.baseMapper.findAttributeDetailByAttrId(attrId);
    }

    /**
     * @desc: 删除属性明细
     */
    public Object delAttributeDetails(String[] ids) {

        for (String id : ids) {
            super.baseMapper.deleteById(id);
        }

        return ResultUtil.result(EnumCode.OK.getValue(), "删除成功");
    }
}
