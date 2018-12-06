package com.cn.cms.service;

import com.cn.cms.dto.PermissionInfo;
import com.cn.cms.entity.Permission;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 系统权限表 服务类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-12-06
 */
public interface IPermissionService extends IService<Permission> {
    List<Permission> getAllPermissions();

    Boolean savePermission(Permission permission);

    Boolean delBatchPermission(List<Integer> ids);

    List<PermissionInfo> allPermissionInfo();

    List<Permission> getMenuPermissions(String code);

    List<Permission> getAllDirectoryPermissions();
}
