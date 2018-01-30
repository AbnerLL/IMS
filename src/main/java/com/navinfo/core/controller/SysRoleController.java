package com.navinfo.core.controller;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.core.entity.SysRole;
import com.navinfo.core.service.SysRoleService;
import com.navinfo.core.vo.PermissionTreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


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
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/roles",method= RequestMethod.GET)
    public Msg findRoles(HttpServletRequest request, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize",defaultValue="10") Integer pageSize){
        String roleName=request.getParameter("keyword");
        PageInfo pageInfo=sysRoleService.getRolesByPage(roleName,pageNum,pageSize);
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
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
    }

    /**
     * 根据主键获取用户
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "role/{roleId}", method = RequestMethod.GET)
    public Msg findRoleById(@PathVariable("roleId") String roleId) {
        SysRole role=sysRoleService.findRoleById(roleId);
        if(role!=null){
            return Msg.success().add("roles",role);
        }else{
            return Msg.failure();
        }
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
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
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
}
