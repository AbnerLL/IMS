package com.navinfo.core.controller;

import com.navinfo.IMS.entity.Emp;
import com.navinfo.IMS.service.EmpService;
import com.navinfo.core.entity.SysUser;
import com.navinfo.core.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 控制器的基类
 * Created by luozhihui on 2018/1/30.
 */
public class BaseController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private EmpService empService;
    /**
     * 定义数据绑定
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){
        //注册日期的编辑器(此处采用spring提供的编辑器，可自定义编辑器)
        binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy/MM/dd"),true));
    }

    /**
     * 获取当前用户
     * @return
     */
    protected SysUser getCurrentUser(){
        String username=(String) SecurityUtils.getSubject().getPrincipal();
        return sysUserService.getUserById(username).get(0);
    }

    /**
     * 判断当前用户是否拥有某一权限
     * @return
     */
    protected boolean hasPermission(String permission){
        Subject currentSubject=SecurityUtils.getSubject();
        return currentSubject.isPermitted(permission);
    }

    /**
     * 判断当前访问用户是否拥有某一角色
     * @param role
     * @return
     */
    protected boolean hasRole(String role){
        Subject currentSubject=SecurityUtils.getSubject();
        return currentSubject.hasRole(role);
    }

    /**
     * 获取当前访问用户的员工信息
     * @return
     */
    protected Emp getCurrentEmp(){
        String username=(String) SecurityUtils.getSubject().getPrincipal();
        List<Emp> empList=this.empService.getEmpById(username);
        return empList!=null ?empList.get(0):null;
    }
}