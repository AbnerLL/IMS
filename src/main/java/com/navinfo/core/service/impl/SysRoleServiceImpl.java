package com.navinfo.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.utils.ListUtils;
import com.navinfo.core.dao.SysRoleMapper;
import com.navinfo.core.dao.UserRoleRelMapper;
import com.navinfo.core.entity.SysRole;
import com.navinfo.core.entity.SysRoleExample;
import com.navinfo.core.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 角色业务层实现类
 * Created by luozhihui on 2017/10/15.
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private UserRoleRelMapper userRoleRelMapper;

    /**
     * 根据角色名模糊查询
     * @param roleName
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo getRolesByPage(String roleName,int pageNum,int pageSize){
        SysRoleExample sysRoleExample=new SysRoleExample();
        if(roleName!=null&&!"".equals(roleName)){
            sysRoleExample.or().andRoleNameLike("%"+roleName+"%");
        }
        //增序排序
        PageHelper.startPage(pageNum,pageSize);
        List list=sysRoleMapper.selectByExample(sysRoleExample);
        return new PageInfo(list);
    }

    /**
     * 根据主键获取角色
     * @param roleId
     * @return
     */
    public SysRole findRoleById(String roleId){
        return sysRoleMapper.selectByPrimaryKey(roleId);
    }
    /**
     * 根据表单数据保存角色
     * @param sysRole
     * @return
     */
    public boolean saveRole(SysRole sysRole){
        if(sysRole.getRoleId()==null){
            String maxId=sysRoleMapper.getMaxIdByParentId(sysRole.getParentId());
            if(maxId!=null&&!"".equals(maxId)){
                sysRole.setRoleId((Long.parseLong(maxId)+1)+"");
            }else{
                sysRole.setRoleId("1000");
            }
        }
        int num=sysRoleMapper.insertSelective(sysRole);
        return num!=0;
    }

    /**
     * 更新角色
     * @param sysRole
     * @return
     */
    public boolean updateRole(SysRole sysRole){
        int num=sysRoleMapper.updateByPrimaryKeySelective(sysRole);
        return num!=0;
    }

    /**
     * 删除一个或多个对象
     * @param ids
     * @return
     */
    public boolean deleteRoles(String ids){
        SysRoleExample sysRoleExample=new SysRoleExample();
        if(ids.contains(",")){
            List idList= Arrays.asList(ids.split(","));
            sysRoleExample.or().andRoleIdIn(idList);
        }else{
            sysRoleExample.or().andRoleIdEqualTo(ids);
        }
        int num=sysRoleMapper.deleteByExample(sysRoleExample);
        return num!=0;
    }

    /**
     * 根据用户ID获取用户名下的角色
     * @return
     */
    public Set<SysRole> findSysRoleByUsername(String username){
        List<SysRole> sysRoleList= this.sysRoleMapper.findSysRoleByUsername(username);
        return ListUtils.ListToSet(sysRoleList);
    }

}
