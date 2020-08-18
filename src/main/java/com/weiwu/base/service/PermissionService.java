package com.weiwu.base.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.weiwu.base.entity.Permission;
import com.weiwu.base.pojo.dto.ParamsDto;
import com.weiwu.base.pojo.dto.PermisDto;
import com.weiwu.base.pojo.vo.PermisVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 查询菜单
     *
     * @param page
     * @param dto
     * @return
     */
    List<Permission> findPermissionByPage(Page<Permission> page, ParamsDto dto);

    /**
     * 新增菜单
     *
     * @param vo
     * @return
     */
    Object addPermissions(PermisVo vo);

    /**
     * 删除菜单
     *
     * @param ids
     * @return
     */
    Object delPermis(String[] ids);

    /**
     * 根据菜单查询菜单
     *
     * @param name
     * @return
     */
    List<Permission> findPermissionByName(String name);

    /**
     * 根据父级id查询上级菜单
     *
     * @param type
     * @return
     */
    List<Permission> findLastPermissionByType(String type);

    /**
     * 查询所有父级菜单绑定树形
     *
     * @return
     */
    List<PermisDto> findBasePermission();

    /**
     * 根据url查询记录
     *
     * @param requestUrl
     * @return
     */
    Integer findCountByUrl(String requestUrl);
}
