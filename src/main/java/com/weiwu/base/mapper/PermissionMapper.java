package com.weiwu.base.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.weiwu.base.entity.Permission;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.weiwu.base.pojo.dto.ParamsDto;
import com.weiwu.base.pojo.dto.PermisDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     *  查询菜单
     *
     */
    List<Permission> findPermissionByPage(Pagination page,@Param("dto") ParamsDto dto);

    /**
     *  根据菜单查询菜单
     * 
     */
    List<Permission> findPermissionByName(@Param("name") String name);

    /**
     *  查询所有父级菜单绑定下拉框
     *
     */
    List<Permission> findLastPermissionByType(@Param("type") String type);

    /**
     *  查询所有父级菜单绑定树形
     * 
     */
    List<PermisDto> findBasePermission();

    /**
     *  根据父级id查询菜单
     * 
     */
    List<PermisDto> findPermissionByFatherId(@Param("fatherId") String fatherId);

    /**
     * 根据url查询记录
     *
     * @param requestUrl
     * @return
     */
    Integer findCountByUrl(@Param("requestUrl") String requestUrl);
}