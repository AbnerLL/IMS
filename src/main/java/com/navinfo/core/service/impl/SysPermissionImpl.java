package com.navinfo.core.service.impl;

import com.navinfo.IMS.utils.ListUtils;
import com.navinfo.core.dao.ModulePermissionRelMapper;
import com.navinfo.core.dao.SysPermissionMapper;
import com.navinfo.core.entity.ModulePermissionRel;
import com.navinfo.core.entity.ModulePermissionRelExample;
import com.navinfo.core.entity.SysPermission;
import com.navinfo.core.entity.SysRole;
import com.navinfo.core.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * 权限业务层接口实现类
 * Created by luozhihui on 2017/10/15.
 */
@Service("sysPermissionService")
public class SysPermissionImpl implements SysPermissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;
    @Autowired
    private ModulePermissionRelMapper modulePermissionRelMapper;

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

    /**
     * 新增权限
     * 注：新增权限也需要新增模块和权限的关联
     * @param sysPermission
     * @return
     */
    public boolean savePermission(SysPermission sysPermission){
        sysPermission.setPermissionId(UUID.randomUUID().toString());
        boolean flag1=this.sysPermissionMapper.insertSelective(sysPermission)!=0;
        ModulePermissionRel modulePermissionRel=new ModulePermissionRel();
        modulePermissionRel.setRelId(UUID.randomUUID().toString());
        modulePermissionRel.setPermissionId(sysPermission.getPermissionId());
        modulePermissionRel.setModuleId(sysPermission.getModuleId());
        boolean flag2=this.modulePermissionRelMapper.insertSelective(modulePermissionRel)!=0;
        return flag1&&flag2;
    }

    /**
     * 根据模块ID获取其相应的权限
     * @param moduleId
     * @return
     */
    public List<SysPermission> findPermissionByModuleId(String moduleId){
        List<SysPermission> sysPermissionList=this.sysPermissionMapper.findPermissionByModuleId(moduleId);
        return sysPermissionList;
    }

    /**
     * 根据主键更新权限
     * @param sysPermission
     * @return
     */
    public boolean updatePermission(SysPermission sysPermission){
        return this.sysPermissionMapper.updateByPrimaryKeySelective(sysPermission)!=0;
    }

    /**
     * 根据主键删除权限
     * 注：删除权限也需删除模块与权限的关联数据
     * @param permissionId
     * @return
     */
    public boolean deletePermissionById(String permissionId){
        boolean flag1=this.sysPermissionMapper.deleteByPrimaryKey(permissionId)!=0;
        ModulePermissionRelExample modulePermissionRelExample=new ModulePermissionRelExample();
        modulePermissionRelExample.createCriteria().andPermissionIdEqualTo(permissionId);
        boolean flag2=this.modulePermissionRelMapper.deleteByExample(modulePermissionRelExample)!=0;
        return flag1&&flag2;
    }
}
