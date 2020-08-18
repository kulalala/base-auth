package com.weiwu.base.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.weiwu.base.entity.Role;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.weiwu.base.pojo.dto.ParamsDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * @desc: 查询角色
     *
     */
    List<Role> findRoleByPage(Pagination page,@Param("dto") ParamsDto dto);

    /**
     * 绑定角色下拉框
     *
     * @return
     */
    List<Role> findAllRoles();
}