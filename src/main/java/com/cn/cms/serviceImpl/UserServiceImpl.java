package com.cn.cms.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cn.cms.dto.UserInfo;
import com.cn.cms.entity.User;
import com.cn.cms.dao.UserMapper;
import com.cn.cms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
@Service("IUserService")
public class UserServiceImpl  extends ServiceImpl<UserMapper, User>  implements IUserService {

    @Override
    public UserInfo findUserInfo(String userName) {
        return this.baseMapper.findUserInfo(userName);
    }

}