package com.navinfo.IMS.controller;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.entity.SysRole;
import com.navinfo.IMS.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


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
}
