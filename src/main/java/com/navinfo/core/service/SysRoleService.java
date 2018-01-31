package com.navinfo.core.service;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.core.entity.SysRole;
import com.navinfo.core.so.SysRoleSearch;
import com.navinfo.core.vo.PermissionTreeVO;

import java.util.List;
import java.util.Set;

/**
 * 角色的业务层接口
 * Created by luozhihui on 2017/10/15.
 */
public interface SysRoleService {
    /**
     * 根据查询条件分页查询
     * @param sysRoleSearch
     * @param pageObject
     * @return
     */
    PageInfo findRolesByPage(SysRoleSearch sysRoleSearch, PageObject pageObject);

    /**
     * 根据查询条件获取对象
     * 不分页
     * @param sysRoleSearch
     * @return List<SysRole>
     */
    List<SysRole> findRoleBySearch(SysRoleSearch sysRoleSearch);
    /**
     * 根据主键查询
     * @param roleId
     * @return
     */
    SysRole findRoleById(String roleId);
    /**
     * 根据表单数据保存角色
     * @param sysRole
     * @return
     */
    boolean saveRole(SysRole sysRole);

    boolean updateRole(SysRole sysRole);

    boolean deleteRoles(String ids);

    /**
     * 根据用户ID获取用户名下的角色
     * @return
     */
    Set<SysRole> findSysRoleByUsername(String username);

    /**
     * 根据角色ID获取权限树
     * @param roleId
     * @return
     */
    List<PermissionTreeVO> findRolePermissionTree(String roleId);

    /**
     * 获取所有权限的树
     * @return
     */
    List<PermissionTreeVO> findAllPermissionTree(String roleId);

    boolean saveSelectedRolePermission(String roleId, PermissionTreeVO[] permissionTreeVOS);

    /**
     * 添加用户的角色（支持多个用户多个角色）
     * @param users
     * @param roles
     * @return
     */
    boolean saveUserRoles(String[] users, String[] roles);

    /**
     * 删除用户角色关联
     * @param userId
     * @param roleId
     * @return
     */
    boolean deleteUserRoleRel(String userId, String roleId);
}
