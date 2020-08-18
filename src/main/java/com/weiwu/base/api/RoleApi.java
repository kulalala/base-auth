package com.weiwu.base.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.weiwu.base.Enum.EnumCode;
import com.weiwu.base.entity.Role;
import com.weiwu.base.entity.RolePermission;
import com.weiwu.base.pojo.dto.ParamsDto;
import com.weiwu.base.pojo.dto.RolePermisVo;
import com.weiwu.base.pojo.vo.RoleVo;
import com.weiwu.base.service.RolePermissionService;
import com.weiwu.base.service.RoleService;
import com.weiwu.base.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "RoleApi/v1")
public class RoleApi {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * 查询角色
     */
    @RequestMapping(value = "/findRoleByPage", method = RequestMethod.GET)
    public Object findRoleByPage(ParamsDto dto) {

        Page<Role> page = new Page<Role>(dto.getStartPage(), dto.getPageSize());
        List<Role> list = roleService.findRoleByPage(page, dto);
        return ResultUtil.result(EnumCode.OK.getValue(), EnumCode.OK.getText(), list, page.getTotal());
    }

    /**
     * 新增角色
     */
    @RequestMapping(value = "/addRoles", method = RequestMethod.POST)
    public Object addRoles(@Valid RoleVo vo, BindingResult bindingResult) {
        return roleService.addRoles(vo);
    }

    /**
     * 删除角色
     */
    @RequestMapping(value = "/delRoles", method = RequestMethod.POST)
    public Object delRoles(ParamsDto dto) {
        if (null == dto.getIds() || dto.getIds().length == 0) {
            return ResultUtil.result(EnumCode.BAD_REQUEST.getValue(), EnumCode.BAD_REQUEST.getText());
        }
        return roleService.delRole(dto.getIds());
    }

    /**
     * 根据角色查询角色权限
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/findRolesPermisByRole", method = RequestMethod.GET)
    public Object findRolesPermisByRole(ParamsDto dto) {
        if (null == dto.getId()) {
            return ResultUtil.result(EnumCode.BAD_REQUEST.getValue(), EnumCode.BAD_REQUEST.getText());
        }
        List<RolePermission> list = rolePermissionService.findRolesPermisByRole(dto);
        String[] arr = new String[list.size()];
        for (int i = 0, j = list.size(); i < j; i++) {
            arr[i] = list.get(i).getPid();
        }
        return ResultUtil.result(EnumCode.OK.getValue(), EnumCode.OK.getText(), arr);
    }

    /**
     * 添加角色权限
     *
     * @param vo
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/addRolesPermis", method = RequestMethod.POST)
    public Object addRolesPermis(RolePermisVo vo, BindingResult bindingResult) {
        return rolePermissionService.addRolesPermis(vo);
    }

    /**
     * 绑定角色下拉框
     *
     * @param vo
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/findAllRoles", method = RequestMethod.GET)
    public Object findAllRoles(RolePermisVo vo, BindingResult bindingResult) {
        List<Role> list = roleService.findAllRoles();
        return ResultUtil.result(EnumCode.OK.getValue(), EnumCode.OK.getText(), list);
    }

}
