package com.cn.cms.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cn.cms.dto.PermissionInfo;
import com.cn.cms.entity.Permission;

import java.util.List;

/**
 * <p>
 * 系统权限表 Mapper 接口
 * </p>
 *
 * @author Auto Generator
 * @since 2018-12-06
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    List<PermissionInfo> allPermissionInfo();
}
