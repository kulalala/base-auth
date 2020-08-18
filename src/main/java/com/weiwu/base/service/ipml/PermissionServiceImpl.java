package com.weiwu.base.service.ipml;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.weiwu.base.Enum.EnumCode;
import com.weiwu.base.entity.Permission;
import com.weiwu.base.mapper.PermissionMapper;
import com.weiwu.base.pojo.dto.ParamsDto;
import com.weiwu.base.pojo.dto.PermisDto;
import com.weiwu.base.pojo.vo.PermisVo;
import com.weiwu.base.service.PermissionService;
import com.weiwu.base.utils.ResultUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    /**
     * 查询菜单
     */
    public List<Permission> findPermissionByPage(Page<Permission> page, ParamsDto dto) {
        return super.baseMapper.findPermissionByPage(page, dto);
    }

    /**
     * 新增菜单
     */
    public Object addPermissions(PermisVo vo) {

        Permission p = new Permission();
        p.setName(vo.getName());
        p.setUrl(vo.getUrl());
        p.setType(vo.getType());
        p.setFatherId(vo.getLastId());
        p.setCreateTime(new Date());
        p.setCreater(vo.getUserName());
        baseMapper.insert(p);
        return ResultUtil.result(EnumCode.OK.getValue(), "新增成功");

    }

    /**
     * 删除菜单
     */
    public Object delPermis(String[] ids) {

        for (String id : ids) {
            super.baseMapper.deleteById(id);
        }
        return ResultUtil.result(EnumCode.OK.getValue(), "删除成功");
    }

    /**
     * 根据菜单查询菜单
     */
    public List<Permission> findPermissionByName(String name) {
        return super.baseMapper.findPermissionByName(name);
    }

    /**
     * 根据父级id查询上级菜单
     */
    public List<Permission> findLastPermissionByType(String type) {
        return super.baseMapper.findLastPermissionByType(type);
    }

    /**
     * 查询所有父级菜单绑定树形
     */
    public List<PermisDto> findBasePermission() {
        List<PermisDto> list = super.baseMapper.findBasePermission();
        if (null != list && !list.isEmpty()) {
            for (int i = 0, j = list.size(); i < j; i++) {
                List<PermisDto> children = super.baseMapper.findPermissionByFatherId(list.get(i).getId());
                if (null != children && !children.isEmpty()) {
                    list.get(i).setChildren((ArrayList<PermisDto>) children);
                    for (int i1 = 0, j1 = children.size(); i1 < j1; i1++) {
                        List<PermisDto> children1 = super.baseMapper.findPermissionByFatherId(children.get(i1).getId());
                        if (null != children1 && !children1.isEmpty()) {
                            children.get(i1).setChildren((ArrayList<PermisDto>) children1);
                        }
                    }
                }
            }
        }
        return list;
    }

    /**
     * 根据url查询记录
     *
     * @param requestUrl
     * @return
     */
    public Integer findCountByUrl(String requestUrl) {
        return super.baseMapper.findCountByUrl(requestUrl);
    }
}
