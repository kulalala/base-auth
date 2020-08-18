package com.weiwu.base.pojo.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 属性明细vo
 */
public class AttributeDetailVo {

    /**
     * 属性id
     */
    @NotBlank(message = "属性id不能为空")
    private String id;
    /**
     * 属性明细名称
     */
    @NotBlank(message = "属性明细名称不能为空")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
