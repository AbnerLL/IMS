package com.navinfo.core.controller;

import com.navinfo.core.entity.SysUser;
import com.navinfo.core.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 控制器的基类
 * Created by luozhihui on 2018/1/30.
 */
public class BaseController {
    @Autowired
    private SysUserService sysUserService;
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
    public SysUser getCurrentUser(){
        String username=(String) SecurityUtils.getSubject().getPrincipal();
        return sysUserService.getUserById(username).get(0);
    }

}