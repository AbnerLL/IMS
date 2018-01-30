package com.navinfo.core.service;

import com.navinfo.core.entity.SysPermission;

import java.util.List;
import java.util.Set;

/**
 * 权限业务层接口
 * Created by luozhihui on 2017/10/15.
 */
public interface SysPermissionService {
    List findPermissionAll();
    /**
     *根据角色ID获取相应的权限对象集合
     * @return
     */
    Set<SysPermission> findPermissionByRoleId(String roleId);

    List<SysPermission> findPermissionByModuleId(String moduleId);

    boolean savePermission(SysPermission sysPermission);

    boolean updatePermission(SysPermission sysPermission);

    boolean deletePermissionById(String permissionId);
}
