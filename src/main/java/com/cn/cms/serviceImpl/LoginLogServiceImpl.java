package com.cn.cms.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cn.cms.entity.LoginLog;
import com.cn.cms.mapper.LoginLogMapper;
import com.cn.cms.service.ILoginLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统登录日志表 服务实现类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-10-01
 */
@Service("ILoginLogService")
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements ILoginLogService {

}
