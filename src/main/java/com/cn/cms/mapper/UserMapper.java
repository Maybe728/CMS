package com.cn.cms.mapper;

import com.cn.cms.dto.UserInfo;
import com.cn.cms.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author Auto Generator
 * @since 2018-12-06
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    UserInfo findUserInfo(String userName);
}
