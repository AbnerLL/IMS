package com.navinfo.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.utils.ListUtils;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.IMS.utils.StringUtil;
import com.navinfo.core.dao.ModulePermissionRelMapper;
import com.navinfo.core.dao.RoleModulePermissionRelMapper;
import com.navinfo.core.dao.SysRoleMapper;
import com.navinfo.core.dao.UserRoleRelMapper;
import com.navinfo.core.entity.*;
import com.navinfo.core.service.SysRoleService;
import com.navinfo.core.so.SysRoleSearch;
import com.navinfo.core.vo.PermissionTreeVO;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Autowired
    private RoleModulePermissionRelMapper roleModulePermissionRelMapper;
    @Autowired
    private ModulePermissionRelMapper modulePermissionRelMapper;

    /**
     * 根据页面的查询参数生成example
     * @param sysRoleSearch
     * @return
     */
    private SysRoleExample createSearchExample(SysRoleSearch sysRoleSearch){
        SysRoleExample example=new SysRoleExample();
        example.createCriteria();
        if (StringUtil.notNull(sysRoleSearch.getRoleId())){
            example.getOredCriteria().get(0).andRoleIdEqualTo(sysRoleSearch.getRoleId());
        }
        if (StringUtil.notNull(sysRoleSearch.getRoleName())){
            example.getOredCriteria().get(0).andRoleNameEqualTo(sysRoleSearch.getRoleName());
        }
        if (StringUtil.notNull(sysRoleSearch.getDescription())){
            example.getOredCriteria().get(0).andDescriptionLike("%"+sysRoleSearch.getDescription()+"%");
        }
        if (StringUtil.notNull(sysRoleSearch.getKeyword())){
            example.or().andRoleNameLike("%"+sysRoleSearch.getKeyword()+"%");
            example.or().andDescriptionLike("%"+sysRoleSearch.getKeyword()+"%");
        }
        return example;
    }
    /**
     * 根据角色名模糊查询
     * @return
     */
    public PageInfo findRolesByPage(SysRoleSearch sysRoleSearch, PageObject pageObject){
        SysRoleExample sysRoleExample=this.createSearchExample(sysRoleSearch);
        //增序排序
        sysRoleExample.setOrderByClause("sort_index");
        PageHelper.startPage(pageObject.getPageNum(),pageObject.getPageSize());
        List<SysRole> list=sysRoleMapper.selectByExample(sysRoleExample);
        return new PageInfo(list);
    }

    /**
     * 根据查询条件获取对象
     * 不分页
     * @param sysRoleSearch
     * @return List<SysRole>
     */
    public List<SysRole> findRoleBySearch(SysRoleSearch sysRoleSearch){
        SysRoleExample sysRoleExample=this.createSearchExample(sysRoleSearch);
        //增序排序
        sysRoleExample.setOrderByClause("sort_index");
        return sysRoleMapper.selectByExample(sysRoleExample);
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
    /**
     * 根据角色ID获取权限树
     * @param roleId
     * @return
     */
    public List<PermissionTreeVO> findRolePermissionTree(String roleId){
        return this.sysRoleMapper.loadModuleTree(roleId);
    }
    /**
     * 获取所有权限的树
     * @return
     */
    public List<PermissionTreeVO> findAllPermissionTree(String roleId){
        //获取所有权限
        List<PermissionTreeVO> treeNodes=this.sysRoleMapper.loadAllModuleTree();
        //获取当前角色的所有权限
        List<PermissionTreeVO> checkedNodes=this.sysRoleMapper.loadModuleTree(roleId);
        //将所有权限中当前角色已有的权限的checked值改为true
        for(PermissionTreeVO checkedNode:checkedNodes){
            for(PermissionTreeVO treeNode:treeNodes){
                if(checkedNode.equals(treeNode)){
                    treeNode.setChecked(true);
                    break;
                }
            }
        }
        return treeNodes;
    }

    /**
     * 根据角色名添加选中的权限
     * @param roleId
     * @param permissionTreeVOS
     * @return
     */
    public boolean saveSelectedRolePermission(String roleId, PermissionTreeVO[] permissionTreeVOS){
        //1.先删除该角色下的权限关联
        RoleModulePermissionRelExample roleModulePermissionRelExample=new RoleModulePermissionRelExample();
        roleModulePermissionRelExample.createCriteria().andRoleIdEqualTo(roleId);
        this.roleModulePermissionRelMapper.deleteByExample(roleModulePermissionRelExample);
        //2.再根据permissionVOS添加角色的权限
        //一级权限模块
        List<PermissionTreeVO> levelOneModule=new ArrayList<PermissionTreeVO>();
        //二级权限模块
        List<PermissionTreeVO> levelTwoModule=new ArrayList<PermissionTreeVO>();
        //三级权限模块
        List<PermissionTreeVO> modulePermission=new ArrayList<PermissionTreeVO>();
        //用于存放一级权限模块的ID
        List<String> levelOneModuleIds=new ArrayList<String>();
        //用于存放三级权限模块的ID
        List<String> modulePermissionIds=new ArrayList<String>();
        //查找出一级权限模块
        for(PermissionTreeVO permissionTreeVO:permissionTreeVOS){
            if(permissionTreeVO!=null&&permissionTreeVO.getPid()==null){
                levelOneModule.add(permissionTreeVO);
                levelOneModuleIds.add(permissionTreeVO.getId());
            }
        }
        //根据一级权限模块查找二级权限模块
        for(PermissionTreeVO permissionTreeVO:permissionTreeVOS){
            for (PermissionTreeVO levelOne:levelOneModule) {
                if(permissionTreeVO.getPid()!=null&&permissionTreeVO.getPid().equals(levelOne.getId())){
                    levelTwoModule.add(permissionTreeVO);
                }
            }
        }
        //根据二级权限模块查找三级权限模块
        for(PermissionTreeVO permissionTreeVO:permissionTreeVOS){
            for (PermissionTreeVO levelTwo:levelTwoModule) {
                if(permissionTreeVO.getPid()!=null&&permissionTreeVO.getPid().equals(levelTwo.getId())){
                    modulePermission.add(permissionTreeVO);
                    modulePermissionIds.add(permissionTreeVO.getId());
                }
            }
        }
        //用来存放要插入的角色权限关联表
        List<RoleModulePermissionRel> roleModulePermissionRelList=new ArrayList<RoleModulePermissionRel>();
        //根据权限查询出模块权限表中的主键
        ModulePermissionRelExample modulePermissionRelExample=new ModulePermissionRelExample();
        modulePermissionRelExample.createCriteria().andModuleIdIn(levelOneModuleIds);
        modulePermissionRelExample.or().andPermissionIdIn(modulePermissionIds);
        List<ModulePermissionRel> modulePermissionRelList=this.modulePermissionRelMapper.selectByExample(modulePermissionRelExample);
        for (ModulePermissionRel permissionRel:modulePermissionRelList){
            RoleModulePermissionRel roleModulePermissionRel=new RoleModulePermissionRel();
            roleModulePermissionRel.setRelId(UUID.randomUUID().toString());
            roleModulePermissionRel.setRoleId(roleId);
            roleModulePermissionRel.setModulePermissionId(permissionRel.getRelId());
            roleModulePermissionRelList.add(roleModulePermissionRel);
        }
        for(RoleModulePermissionRel roleModulePermissionRel:roleModulePermissionRelList){
            this.roleModulePermissionRelMapper.insert(roleModulePermissionRel);
        }
        return true;
    }
    /**
     * 添加用户的角色（支持多个用户多个角色）
     * @param users
     * @param roles
     * @return
     */
    public boolean saveUserRoles(String[] users, String[] roles){
        if(users==null||users.length==0||roles==null||roles.length==0){
            return false;
        }
        List<String> usersId=Arrays.asList(users);
        List<String> rolesId=Arrays.asList(roles);
        //先查询出这些用户的所有角色
        for (String userId:usersId){
            if (!StringUtil.notNull(userId)){
                continue;
            }
            UserRoleRelExample userRoleRelExample=new UserRoleRelExample();
            userRoleRelExample.or().andUsernameEqualTo(userId);
            List<UserRoleRel> userRoleRels=this.userRoleRelMapper.selectByExample(userRoleRelExample);
            List<String> saveRoleIdList=new ArrayList<String>();
            saveRoleIdList.addAll(rolesId);
            //过滤已有的角色
            for (UserRoleRel userRoleRel:userRoleRels){
                if (rolesId.contains(userRoleRel.getRoleId())){
                    saveRoleIdList.remove(userRoleRel.getRoleId());
                }
            }
            //插入新的用户角色关联数据
            for (String saveRoleId:saveRoleIdList){
                if (!StringUtil.notNull(saveRoleId)){
                    continue;
                }
                UserRoleRel insertRel=new UserRoleRel();
                insertRel.setRelId(UUID.randomUUID().toString());
                insertRel.setUsername(userId);
                insertRel.setRoleId(saveRoleId);
                this.userRoleRelMapper.insert(insertRel);
            }
        }
        return true;
    }
    /**
     * 删除用户角色关联
     * @param userId
     * @param roleId
     * @return
     */
    public boolean deleteUserRoleRel(String userId, String roleId){
        UserRoleRelExample userRoleRelExample=new UserRoleRelExample();
        userRoleRelExample.createCriteria().andUsernameEqualTo(userId).andRoleIdEqualTo(roleId);
        return this.userRoleRelMapper.deleteByExample(userRoleRelExample)!=0;
    }
}
