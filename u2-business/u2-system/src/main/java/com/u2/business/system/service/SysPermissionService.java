package com.u2.business.system.service;

import com.u2.api.system.domain.SysUser;

import java.util.Set;

/**
 * 权限信息 服务层
 *
 * @author vhans
 */
public interface SysPermissionService {
    /**
     * 获取角色数据权限
     *
     * @return 角色权限信息
     */
    Set<String> getRolePermission(SysUser user);

    /**
     * 获取菜单数据权限
     *
     * @return 菜单权限信息
     */
    Set<String> getMenuPermission(SysUser user);
}
