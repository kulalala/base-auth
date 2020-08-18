package com.weiwu.base.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.weiwu.base.entity.Role;
import com.weiwu.base.pojo.dto.ParamsDto;
import com.weiwu.base.pojo.vo.RoleVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 */
public interface RoleService extends IService<Role> {

    /**
     * 查询角色
     *
     * @param page
     * @param dto
     * @return
     */
    List<Role> findRoleByPage(Page<Role> page, ParamsDto dto);

    /**
     * 新增角色
     *
     * @param vo
     * @return
     */
    Object addRoles(RoleVo vo);

    /**
     * 删除角色
     *
     * @param ids
     * @return
     */
    Object delRole(String[] ids);

    /**
     * 绑定角色下拉框
     *
     * @return
     */
    List<Role> findAllRoles();
}
