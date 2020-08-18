package com.weiwu.base.pojo.dto;


/**
 * 登陆统计dto
 */
public class LoginTotalDto {
    /**
     * 姓名
     */
    private String[] name;

    /**
     * 登陆次数
     */
    private int[] total;

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public int[] getTotal() {
        return total;
    }

    public void setTotal(int[] total) {
        this.total = total;
    }
}
