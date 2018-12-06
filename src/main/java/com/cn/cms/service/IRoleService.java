package com.cn.cms.service;

import com.cn.cms.entity.Role;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-12-06
 */
public interface IRoleService extends IService<Role> {
    Boolean saveRole(Role role);
}
