package com.weiwu.base.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.weiwu.base.Enum.EnumCode;
import com.weiwu.base.entity.Attribute;
import com.weiwu.base.entity.AttributeDetail;
import com.weiwu.base.entity.LoginLog;
import com.weiwu.base.entity.OperatingRecord;
import com.weiwu.base.pojo.dto.AttributeDetailDto;
import com.weiwu.base.pojo.dto.ParamsDto;
import com.weiwu.base.pojo.vo.AttributeDetailVo;
import com.weiwu.base.service.AttributeDetailService;
import com.weiwu.base.service.AttributeService;
import com.weiwu.base.service.LoginLogService;
import com.weiwu.base.service.OperatingRecordService;
import com.weiwu.base.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "SysApi/v1")
public class SysApi {

    @Autowired
    /**
     * 属性
     */
    private AttributeService attributeService;

    @Autowired
    /**
     * 属性明细
     */
    private AttributeDetailService attributeDetailService;


    @Autowired
    /**
     * 操作记录
     */
    private OperatingRecordService operatingRecordService;

    @Autowired
    private LoginLogService loginLogService;

    /**
     * 查询属性
     */
    @RequestMapping(value = "/findAttributesByPage", method = RequestMethod.GET)
    public Object findAttributesByPage(ParamsDto dto) {

        Page<Attribute> page = new Page<Attribute>(dto.getStartPage(), dto.getPageSize());
        List<Attribute> list = attributeService.findAttributesByPage(page, dto);
        return ResultUtil.result(EnumCode.OK.getValue(), EnumCode.OK.getText(), list, page.getTotal());
    }

    /**
     * 查询属性明细
     */
    @RequestMapping(value = "/findAttributesDetailByPage", method = RequestMethod.GET)
    public Object findAttributesDetailByPage(ParamsDto dto) {

        Page<AttributeDetail> page = new Page<AttributeDetail>(dto.getStartPage(), dto.getPageSize());
        List<AttributeDetail> list = attributeDetailService.findAttributeDetailByPage(page, dto);
        return ResultUtil.result(EnumCode.OK.getValue(), EnumCode.OK.getText(), list, page.getTotal());
    }

    /**
     * 新增属性
     */
    @RequestMapping(value = "/addAttributes", method = RequestMethod.POST)
    public Object addAttributes(@Valid Attribute attribute, BindingResult bindingResult) {
        return attributeService.addAttributes(attribute);
    }

    /**
     * 新增属性明细
     */
    @RequestMapping(value = "/addAttributeDetail", method = RequestMethod.POST)
    public Object addAttributeDetail(@Valid AttributeDetailVo vo, BindingResult bindingResult) {
        return attributeDetailService.addAttributeDetail(vo);
    }

    /**
     * 删除属性
     */
    @RequestMapping(value = "/delAttributes", method = RequestMethod.POST)
    public Object delAttributes(ParamsDto dto) {
        if (null == dto.getIds() || dto.getIds().length == 0) {
            return ResultUtil.result(EnumCode.BAD_REQUEST.getValue(), EnumCode.BAD_REQUEST.getText());
        }
        return attributeService.delAttributes(dto.getIds());
    }

    /**
     * 删除属性明细
     */
    @RequestMapping(value = "/delAttributeDetails", method = RequestMethod.POST)
    public Object delAttributeDetails(ParamsDto dto) {
        if (null == dto.getIds() || dto.getIds().length == 0) {
            return ResultUtil.result(EnumCode.BAD_REQUEST.getValue(), EnumCode.BAD_REQUEST.getText());
        }
        return attributeDetailService.delAttributeDetails(dto.getIds());
    }

    /**
     * 根据属性id查询属性
     */
    @RequestMapping(value = "/findAttributeDetailByAttrId", method = RequestMethod.GET)
    public Object selAttributeDetailsByAttrId(ParamsDto dto) {
        if (StringUtils.isEmpty(dto.getId())) {
            return ResultUtil.result(EnumCode.BAD_REQUEST.getValue(), EnumCode.BAD_REQUEST.getText());
        }
        List<AttributeDetailDto> list = attributeDetailService.findAttributeDetailByAttrId(dto.getId());
        if (null == list || list.isEmpty()) {
            return ResultUtil.result(EnumCode.GONE.getValue(), "没有记录");
        }
        return ResultUtil.result(EnumCode.OK.getValue(), EnumCode.OK.getText(), list);
    }

    /**
     * 查询操作记录
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/findOperatingRecordByPage", method = RequestMethod.GET)
    public Object findOperatingRecordByPage(ParamsDto dto) {

        Page<OperatingRecord> page = new Page<OperatingRecord>(dto.getStartPage(), dto.getPageSize());
        List<OperatingRecord> list = operatingRecordService.findOperatingRecordByPage(page, dto);
        return ResultUtil.result(EnumCode.OK.getValue(), EnumCode.OK.getText(), list, page.getTotal());

    }

    /**
     * 用户登录日志
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/findUserLoginLogByPage", method = RequestMethod.GET)
    public Object findUserLoginLogByPage(ParamsDto dto) {

        Page<LoginLog> page = new Page<LoginLog>(dto.getStartPage(), dto.getPageSize());
        List<LoginLog> list = loginLogService.findUserLoginLogByPage(page, dto);
        return ResultUtil.result(EnumCode.OK.getValue(), EnumCode.OK.getText(), list, page.getTotal());

    }

    /**
     * 统计用户登录
     */
    @RequestMapping(value = "/findUserLoginTotal", method = RequestMethod.GET)
    public Object findUserLoginTotal() {
        return loginLogService.findUserLoginTotal();
    }

    /**
     * 访问统计
     */
    @RequestMapping(value = "/findUserReqTotal", method = RequestMethod.GET)
    public Object findUserReqTotal() {
        return operatingRecordService.findUserReqTotal();
    }


}