package com.navinfo.core.controller;

import com.github.pagehelper.PageHelper;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.core.entity.SysPermission;
import com.navinfo.core.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统权限Controller
 * Created by luozhihui on 2017/11/16.
 */
@Controller
public class SysPermissionController {
    @Autowired
    private SysPermissionService sysPermissionService;

    /**
     * 分页查询权限
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping("/permissions")
    @ResponseBody
    public Msg findPermissions(@RequestParam(value = "pageSize") Integer pageSize, @RequestParam(value = "pageNum") Integer pageNum){
        if(pageSize!=null&&pageNum!=null){
            PageHelper.startPage(pageNum,pageSize);
        }
        List permissions=sysPermissionService.findPermissionAll();
        return Msg.success().add("data",permissions);
    }

    /**
     * 新增权限
     * @return
     */
    @RequestMapping(value = "/permission" ,method = RequestMethod.POST)
    @ResponseBody
    public Msg addPermission(SysPermission sysPermission){
        boolean flag=this.sysPermissionService.savePermission(sysPermission);
        return flag ? Msg.success():Msg.failure();
    }

    /**
     * 修改权限
     * @param sysPermission
     * @return
     */
    @RequestMapping(value = "/permission/{permissionId}" ,method = RequestMethod.PUT)
    @ResponseBody
    public Msg editPermission(SysPermission sysPermission){
        boolean flag=this.sysPermissionService.updatePermission(sysPermission);
        return flag ? Msg.success():Msg.failure();
    }
    /**
     * 根据模块id获取权限
     * @param moduleId
     * @return
     */
    @RequestMapping(value="/permissionByModuleId/{moduleId}")
    @ResponseBody
    public Msg findPermissionsByModuleId(@PathVariable String moduleId){
        List<SysPermission> sysPermissionList=this.sysPermissionService.findPermissionByModuleId(moduleId);
        return Msg.success().add("entities",sysPermissionList);

    }

    /**
     *删除权限
     * @return
     */
    @RequestMapping(value = "/permission/{permissionId}",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deletePermission(@PathVariable String permissionId){
        boolean flag=this.sysPermissionService.deletePermissionById(permissionId);
        return flag ? Msg.success():Msg.failure();
    }
}
