package com.weiwu.base.service.ipml;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.weiwu.base.Enum.EnumCode;
import com.weiwu.base.entity.LoginLog;
import com.weiwu.base.entity.User;
import com.weiwu.base.entity.UserRole;
import com.weiwu.base.exception.MyException;
import com.weiwu.base.mapper.UserMapper;
import com.weiwu.base.pojo.dto.ParamsDto;
import com.weiwu.base.pojo.dto.RolePermisDto;
import com.weiwu.base.pojo.dto.UserDto;
import com.weiwu.base.pojo.dto.UserInfoDto;
import com.weiwu.base.pojo.vo.UserInfoVo;
import com.weiwu.base.pojo.vo.UserVo;
import com.weiwu.base.service.LoginLogService;
import com.weiwu.base.service.RolePermissionService;
import com.weiwu.base.service.UserRoleService;
import com.weiwu.base.service.UserService;
import com.weiwu.base.utils.ResultUtil;
import com.xiaoleilu.hutool.crypto.SecureUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private RolePermissionService rolePermissionService;


    /**
     * 登录
     *
     * @param name
     * @param pass
     * @param session
     * @param request
     * @return
     */
    public Object login(String name, String pass, HttpSession session, HttpServletRequest request) {

        UsernamePasswordToken upToken = new UsernamePasswordToken(name, SecureUtil.md5(pass));
        Subject subject = SecurityUtils.getSubject();
        subject.login(upToken);
        UserInfoDto userInfoDto = (UserInfoDto) subject.getPrincipal();
        session.setAttribute("user", userInfoDto);

        // 登录日志
        LoginLog loginLog = new LoginLog();
        loginLog.setUid(userInfoDto.getId());
        loginLog.setLoginTime(new Date());
        loginLog.setLoginIP(request.getRemoteAddr());
        loginLog.setLoginTotal(loginLogService.findMaxLoginTatalByUserId(userInfoDto.getId())); // 登录总次数
        loginLogService.insert(loginLog);

        // 一级 模块
        List<RolePermisDto> parentList = rolePermissionService.findRolesPermisByFatherId(null, null);
        List<RolePermisDto> sonList = null;
        List<RolePermisDto> sonssonList = null;
        String rid = userInfoDto.getRoleName().equals("admin") ? null : userInfoDto.getRoleid();
        for (int i = 0, j = parentList.size(); i < j; i++) {

            List<RolePermisDto> trueChildrenList = new ArrayList<>();

            // 二级 页面
            sonList = rolePermissionService.findRolesPermisByFatherId(parentList.get(i).getId(), null);
            for (int k = 0, l = sonList.size(); k < l; k++) {

                // 三级按钮
                sonssonList = rolePermissionService.findRolesPermisByFatherId(sonList.get(k).getId(), rid);
                // 如果按钮级拥有权限说明页面也有权限
                if (!sonssonList.isEmpty() && sonssonList.size() > 0) {
                    trueChildrenList.add(sonList.get(k));
                }
            }
            parentList.get(i).setChildren((ArrayList<RolePermisDto>) trueChildrenList);
        }
        userInfoDto.setRolePermis((ArrayList) parentList);
        return ResultUtil.result(EnumCode.OK.getValue(), "登陆成功", JSON.toJSON(userInfoDto));
    }

    /**
     * 新增用户
     */
    @Transactional
    public Object addUser(UserVo vo) {

        Map<String, Object> map = new HashMap<>();
        map.put("nickname", vo.getName().trim());
        List<User> list = super.baseMapper.selectByMap(map);
        if (list.size() > 0) {
            throw new MyException(ResultUtil.result(EnumCode.BAD_REQUEST.getValue(), "用户已经存在", null));
        }
        User user = new User();
        user.setNickname(vo.getName());
        user.setPswd(SecureUtil.md5(vo.getPass()));
        user.setEmail(vo.getEmail());
        user.setCreateTime(new Date());
        super.baseMapper.insert(user);

        UserRole ur = new UserRole();
        ur.setUid(user.getId());
        ur.setRid(vo.getRole());
        Boolean result = userRoleService.insert(ur);

        if (!result) {
            throw new MyException(ResultUtil.result(EnumCode.INTERNAL_SERVER_ERROR.getValue(), EnumCode.INTERNAL_SERVER_ERROR.getText(), null));
        }
        return ResultUtil.result(EnumCode.OK.getValue(), "新增成功");
    }

    /**
     * 查询用户
     *
     * @param page 分页
     * @param dto  参数dto
     */
    public List<UserDto> findUserByPage(Page<UserDto> page, ParamsDto dto) {
        List<UserDto> list = super.baseMapper.findUserByPage(page, dto);
        return list;
    }

    /**
     * 批量删除用户
     */
    public Object delUsers(String[] ids) {
        for (String id : ids) {
            baseMapper.deleteById(id);
        }
        return ResultUtil.result(EnumCode.OK.getValue(), "删除成功");
    }

    /**
     * 登陆验证
     */
    public List<UserInfoDto> checkUser(String name, String pass) {
        return super.baseMapper.checkUser(name, pass);
    }

    /**
     * 修改用户状态
     *
     * @param id
     * @param type
     * @return
     */
    public Object editUserStatus(String id, Integer type) {
        User user = new User();
        user.setId(id);
        user.setStatus(type);
        Integer row = super.baseMapper.updateById(user);
        return row > 0 ? ResultUtil.result(EnumCode.OK.getValue(), type == 0 ? "已禁止登陆" : "已允许登陆") : ResultUtil.result(EnumCode.INTERNAL_SERVER_ERROR.getValue(), "修改失败");
    }

    /**
     * 用户修改用户个人信息
     */
    public Object editUserInfo(UserInfoVo vo) {
        User user = new User();
        user.setId(vo.getId());
        user.setNickname(vo.getName());
        user.setEmail(vo.getEmail());
        user.setHeadPortraits(vo.getUserImg());
        Integer row = super.baseMapper.updateById(user);
        if (row > 0) {
            return ResultUtil.result(EnumCode.OK.getValue(), "修改成功，下次登录生效");
        }
        return ResultUtil.result(EnumCode.INTERNAL_SERVER_ERROR.getValue(), "修改失败");
    }
}
