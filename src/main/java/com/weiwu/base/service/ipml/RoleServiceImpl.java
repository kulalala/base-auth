package com.weiwu.base.service.ipml;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.weiwu.base.Enum.EnumCode;
import com.weiwu.base.entity.Role;
import com.weiwu.base.mapper.RoleMapper;
import com.weiwu.base.pojo.dto.ParamsDto;
import com.weiwu.base.pojo.vo.RoleVo;
import com.weiwu.base.service.RoleService;
import com.weiwu.base.utils.ResultUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    /**
     * 查询角色
     */
    public List<Role> findRoleByPage(Page<Role> page, ParamsDto dto) {
        return super.baseMapper.findRoleByPage(page, dto);
    }

    /**
     * 新增角色
     */
    public Object addRoles(RoleVo vo) {
        Role r = new Role();
        r.setName(vo.getName());
        r.setDescription(vo.getDescription());
        super.baseMapper.insert(r);
        return ResultUtil.result(EnumCode.OK.getValue(), "新增成功");
    }

    /**
     * 删除角色
     */
    public Object delRole(String[] ids) {

        for (String id : ids) {
            super.baseMapper.deleteById(id);
        }
        return ResultUtil.result(EnumCode.OK.getValue(), "删除成功");
    }

    /**
     * 绑定角色下拉框
     *
     * @return
     */
    public List<Role> findAllRoles() {
        return super.baseMapper.findAllRoles();
    }
}
