package com.weiwu.base.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.weiwu.base.entity.User;
import com.weiwu.base.pojo.dto.ParamsDto;
import com.weiwu.base.pojo.dto.UserDto;
import com.weiwu.base.pojo.dto.UserInfoDto;
import com.weiwu.base.pojo.vo.UserInfoVo;
import com.weiwu.base.pojo.vo.UserVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 服务类
 */
public interface UserService extends IService<User> {

    /**
     * 登录
     *
     * @param name
     * @param pass
     * @param session
     * @param request
     * @return
     */
    Object login(String name, String pass, HttpSession session, HttpServletRequest request);

    /**
     * 新增用户
     *
     * @param userVo 用户vo
     */
    Object addUser(UserVo userVo);


    /**
     * 查询用户
     *
     * @param page
     * @param dto
     * @return
     */
    List<UserDto> findUserByPage(Page<UserDto> page, ParamsDto dto);

    /**
     * 批量删除用户
     *
     * @param ids
     * @return
     */
    Object delUsers(String[] ids);

    /**
     * 登陆验证
     *
     * @param name
     * @param pass
     * @return
     */
    List<UserInfoDto> checkUser(String name, String pass);

    /**
     * 修改用户状态
     *
     * @param id
     * @param type
     * @return
     */
    Object editUserStatus(String id, Integer type);

    /**
     * 修改用户个人信息
     *
     * @param vo
     * @return
     */
    Object editUserInfo(UserInfoVo vo);
}
