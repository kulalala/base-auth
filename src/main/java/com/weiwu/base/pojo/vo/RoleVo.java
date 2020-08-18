package com.weiwu.base.pojo.vo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *角色vo
 */
public class RoleVo {

    /**
     * 名称
     */
    @NotEmpty(message = "角色名称不能为空")
    private String name;

    /**
     * 描述
     */
    @NotEmpty(message = "角色描述不能为空")
    private String description;

    /**
     * 类型
     */
    private Integer type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
