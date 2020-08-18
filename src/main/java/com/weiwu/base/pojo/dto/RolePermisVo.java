package com.weiwu.base.pojo.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 角色权限vo
 */
public class RolePermisVo {

    /**
     * 角色id
     */
    @NotBlank(message = "角色id不能为空")
    private String roleId;

    /**
     * 菜单ids
     */
    private String[] permiIds;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String[] getPermiIds() {
        return permiIds;
    }

    public void setPermiIds(String[] permiIds) {
        this.permiIds = permiIds;
    }
}
