package com.navinfo.IMS.service;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.entity.SysRole;

/**
 * 角色的业务层接口
 * Created by luozhihui on 2017/10/15.
 */
public interface SysRoleService {

    public PageInfo getRolesByPage(String roleName, int pageNum, int pageSize);

    /**
     * 根据主键查询
     * @param roleId
     * @return
     */
    public SysRole findRoleById(String roleId);
    /**
     * 根据表单数据保存角色
     * @param sysRole
     * @return
     */
    public boolean saveRole(SysRole sysRole);

    public boolean updateRole(SysRole sysRole);

    public boolean deleteRoles(String ids);
}
