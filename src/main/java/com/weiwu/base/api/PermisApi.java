package com.weiwu.base.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.weiwu.base.Enum.EnumCode;
import com.weiwu.base.api.base.BaseApi;
import com.weiwu.base.entity.Permission;
import com.weiwu.base.pojo.dto.ParamsDto;
import com.weiwu.base.pojo.dto.PermisDto;
import com.weiwu.base.pojo.vo.PermisVo;
import com.weiwu.base.service.PermissionService;
import com.weiwu.base.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "PermisApi/v1")
public class PermisApi extends BaseApi {

    @Autowired
    private PermissionService permissionService;

    /**
     * 查询菜单
     */
    @RequestMapping(value = "/findPermissionByPage", method = RequestMethod.GET)
    public Object findPermissionByPage(ParamsDto dto) {

        Page<Permission> page = new Page<Permission>(dto.getStartPage(), dto.getPageSize());
        List<Permission> list = permissionService.findPermissionByPage(page, dto);
        return ResultUtil.result(EnumCode.OK.getValue(), EnumCode.OK.getText(), list, page.getTotal());
    }

    /**
     * 新增菜单
     */
    @RequestMapping(value = "/addPermissions", method = RequestMethod.POST)
    public Object addPermissions(@Valid PermisVo vo, BindingResult bindingResult) {
        vo.setUserName(super.getUserName());
        vo.setUserId(super.getUserId());
        return permissionService.addPermissions(vo);
    }

    /**
     * 删除菜单
     */
    @RequestMapping(value = "/delPermis", method = RequestMethod.POST)
    public Object delPermis(ParamsDto dto) {
        if (null == dto.getIds() || dto.getIds().length == 0) {
            return ResultUtil.result(EnumCode.BAD_REQUEST.getValue(), EnumCode.BAD_REQUEST.getText());
        }
        return permissionService.delPermis(dto.getIds());
    }

    /**
     * 查询父级菜单为0的所有菜单
     */
    @RequestMapping(value = "/findLastPermissionByType", method = RequestMethod.GET)
    public Object findAllBasePermission(String type) {
        List<Permission> list = permissionService.findLastPermissionByType(type);
        if (null == list || list.isEmpty()) {
            return ResultUtil.result(EnumCode.GONE.getValue(), "没有记录");
        }
        return ResultUtil.result(EnumCode.OK.getValue(), EnumCode.OK.getText(), list);
    }

    /**
     * 绑定树形菜单
     */
    @RequestMapping(value = "/findBasePermission", method = RequestMethod.GET)
    public Object findBasePermission() {
        List<PermisDto> list = permissionService.findBasePermission();
        return ResultUtil.result(EnumCode.OK.getValue(), EnumCode.OK.getText(), list);
    }
}
