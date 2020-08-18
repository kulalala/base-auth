package com.weiwu.base.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.weiwu.base.entity.RolePermission;
import com.weiwu.base.pojo.dto.ParamsDto;
import com.weiwu.base.pojo.dto.RolePermisDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    /**
     * 根据角色查询角色权限
     *
     * @param dto
     * @return
     */
    List<RolePermission> findRolesPermisByRole(@Param("dto") ParamsDto dto);

    /**
     * 根据角色id查询记录数
     * @param roleId
     * @return
     */
    Integer findCountByRole(@Param("roleId") String roleId,@Param("url") String url);

    /**
     * 根据父id\角色id查询角色菜单
     *
     */
    List<RolePermisDto> findRolesPermisByFatherId(@Param("fatherId") String fatherId, @Param("rid") String rid);
}