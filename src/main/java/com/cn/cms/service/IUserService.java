package com.cn.cms.service;

import com.cn.cms.dto.UserInfo;
import com.cn.cms.entity.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-12-06
 */
public interface IUserService extends IService<User> {
    UserInfo findUserInfo(String userName);
}
