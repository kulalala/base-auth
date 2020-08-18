package com.weiwu.base.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.weiwu.base.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.weiwu.base.pojo.dto.ParamsDto;
import com.weiwu.base.pojo.dto.UserDto;
import com.weiwu.base.pojo.dto.UserInfoDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询用户
     * @param page 分页
     * @param dto  参数dto
     *
     */
    List<UserDto> findUserByPage(Pagination page, @Param("dto") ParamsDto dto);

    /**
     * 登陆验证
     */
    List<UserInfoDto> checkUser(@Param("name") String name, @Param("pass") String pass);
}