package com.navinfo.core.service.impl;

import com.navinfo.IMS.utils.ListUtils;
import com.navinfo.core.dao.SysPermissionMapper;
import com.navinfo.core.entity.SysPermission;
import com.navinfo.core.entity.SysRole;
import com.navinfo.core.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 权限业务层接口实现类
 * Created by luozhihui on 2017/10/15.
 */
@Service("sysPermissionService")
public class SysPermissionImpl implements SysPermissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    /**
     * 查询所有权限列表所有数据
     * @return
     */
    public List findPermissionAll() {
        return sysPermissionMapper.selectByExample(null);
    }

    /**
     *根据角色ID获取相应的权限对象集合
     * @return
     */
    public Set<SysPermission> findPermissionByRoleId(String roleId){
        List<SysPermission> sysPermissionList=this.sysPermissionMapper.findPermissionByRoleId(roleId);
        return ListUtils.ListToSet(sysPermissionList);
    }
}
