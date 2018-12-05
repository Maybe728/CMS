package com.cn.cms.dao;

import com.cn.cms.dto.UserInfo;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
public interface UserMapper {

    UserInfo findUserInfo(String userName);

}
