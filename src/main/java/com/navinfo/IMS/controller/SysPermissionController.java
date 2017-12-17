package com.navinfo.IMS.controller;

import com.github.pagehelper.PageHelper;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
