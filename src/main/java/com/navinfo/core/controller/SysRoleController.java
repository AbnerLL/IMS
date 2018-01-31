package com.navinfo.core.controller;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.core.entity.SysRole;
import com.navinfo.core.service.SysRoleService;
import com.navinfo.core.so.SysRoleSearch;
import com.navinfo.core.vo.PermissionTreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.RequestWrapper;
import java.util.List;
import java.util.Set;


/**
 * 角色Controller
 * Created by luozhihui on 2017/10/27.
 */
@Controller
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 根据关键词获取角色
     * @param sysRoleSearch
     * @param pageObject
     * @return Msg
     */
    @ResponseBody
    @RequestMapping(value = "/roles",method= RequestMethod.GET)
    public Msg search(SysRoleSearch sysRoleSearch, PageObject pageObject){
        PageInfo pageInfo=sysRoleService.findRolesByPage(sysRoleSearch,pageObject);
        if(pageInfo!=null){
            return Msg.success().add("pageInfo",pageInfo);
        }else{
            return Msg.failure();
        }
    }

    /**
     * 根据表单新增角色
     * @param sysRole
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/role",method=RequestMethod.POST)
    public Msg saveRole(SysRole sysRole){
        boolean flag=sysRoleService.saveRole(sysRole);
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
    }

    /**
     * 更新用户
     * @param sysRole
     * @return
     */
    @ResponseBody
    @RequestMapping(value="role/{roleId}",method=RequestMethod.PUT)
    public Msg updateRole(SysRole sysRole){
        boolean flag=sysRoleService.updateRole(sysRole);
        return flag ? Msg.success():Msg.failure();
    }

    /**
     * 根据主键获取角色
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "role/{roleId}", method = RequestMethod.GET)
    public Msg findRoleById(@PathVariable("roleId") String roleId) {
        SysRole role=sysRoleService.findRoleById(roleId);
        return role!=null ?Msg.success().add("roles",role):Msg.failure();
    }

    /**
     * 根据主键删除一个或多个角色
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/role/{roleId}",method = RequestMethod.DELETE)
    public Msg DeleteRoles(@PathVariable("roleId") String ids){
        boolean flag=sysRoleService.deleteRoles(ids);
        return flag ? Msg.success():Msg.failure();
    }

    /**
     * 根据角色ID获取相应的权限
     * @return
     */
    @RequestMapping(value="/rolePermission/{roleId}",method = RequestMethod.GET)
    @ResponseBody
    public Msg loadRolePermission(@PathVariable String roleId){
        List<PermissionTreeVO> treeNodes=this.sysRoleService.findRolePermissionTree(roleId);
        return Msg.success().add("treeNodes",treeNodes);
    }

    /**
     * 加载所有权限
     * 用于页面编辑权限
     * @return
     */
    @RequestMapping(value="/rolePermissionAll/{roleId}",method = RequestMethod.GET)
    @ResponseBody
    public Msg loadAllModulePermission(@PathVariable String roleId){
        List<PermissionTreeVO> treeNodesAll=this.sysRoleService.findAllPermissionTree(roleId);
        return Msg.success().add("treeNodes",treeNodesAll);
    }

    /**
     * 保存用户权限
     * @return
     */
    @RequestMapping(value = "/rolePermission/{roleId}",method=RequestMethod.POST)
    @ResponseBody
    public Msg saveRolePermission(@PathVariable String roleId,@RequestBody PermissionTreeVO[] permissionTreeVOS){
        boolean flag=this.sysRoleService.saveSelectedRolePermission(roleId,permissionTreeVOS);
        return flag ? Msg.success() : Msg.failure();
    }

    /**
     * 获取所有的角色
     * @return
     */
    @RequestMapping(value = "/roleAll",method=RequestMethod.GET)
    @ResponseBody
    public Msg loadAllRole(){
        List<SysRole> sysRoleList=this.sysRoleService.findRoleBySearch(new SysRoleSearch());
        return Msg.success().add("roles",sysRoleList);
    }

    /**
     * 添加用户的角色
     * 可以添加多个用户的多个角色
     * @param users 用户Id集合
     * @param roles 角色id集合
     * @return
     */
    @RequestMapping(value = "/addUserRole",method=RequestMethod.POST)
    @ResponseBody
    public Msg addUserRole(@RequestParam String users,@RequestParam String roles){
        boolean flag=this.sysRoleService.saveUserRoles(users.split(","),roles.split(","));
        return flag ?Msg.success() :Msg.failure();
    }

    /**
     * 根据用户名获取角色
     * @param username
     * @return
     */
    @RequestMapping(value = "/role/userRole/{username}")
    @ResponseBody
    public Msg findRoleByUsername(@PathVariable String username){
        Set<SysRole> sysRoleSet=this.sysRoleService.findSysRoleByUsername(username);
        return Msg.success().add("entities",sysRoleSet);
    }

    /**
     * 根据用户id和角色id删除用户角色关联
     * @param userId
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/roleRel",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteUserRoleRel(String userId,String roleId){
        boolean flag=this.sysRoleService.deleteUserRoleRel(userId,roleId);
        return flag ? Msg.success() : Msg.failure();
    }
}
