package com.weiwu.base.service;

import com.baomidou.mybatisplus.service.IService;
import com.weiwu.base.entity.RolePermission;
import com.weiwu.base.pojo.dto.ParamsDto;
import com.weiwu.base.pojo.dto.RolePermisDto;
import com.weiwu.base.pojo.dto.RolePermisVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 */
public interface RolePermissionService extends IService<RolePermission> {

    /**
     * 添加角色权限
     *
     * @param vo
     * @return
     */
    Object addRolesPermis(RolePermisVo vo);

    /**
     * 根据角色查询角色权限
     *
     * @param dto
     * @return
     */
    List<RolePermission> findRolesPermisByRole(ParamsDto dto);

    /**
     * 根据角色id查询记录数
     *
     * @param roleId
     * @return
     */
    Integer findCountByRole(String roleId, String url);

    /**
     * 根据父id\角色id查询角色菜单
     *
     * @param fatherId
     * @param rid
     * @return
     */
    List<RolePermisDto> findRolesPermisByFatherId(String fatherId, String rid);
}
