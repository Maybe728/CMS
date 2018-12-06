package com.cn.cms.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cn.cms.entity.Role;
import com.cn.cms.mapper.RoleMapper;
import com.cn.cms.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
@Service("IRoleService")
public class RoleServiceImpl  extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public Boolean saveRole(Role role) {
        Boolean res = false;
        if (role.getId() == null) {
            res = this.insert(role);
        } else {
            res = this.updateById(role);
        }
        return res;
    }
}