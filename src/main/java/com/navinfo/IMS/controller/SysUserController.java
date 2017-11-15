package com.navinfo.IMS.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.entity.SysUser;
import com.navinfo.IMS.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 控制层
 * Created by luozhihui on 2017/10/15.
 */
@Controller
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/toLogin" ,method = RequestMethod.POST)
    public String login(String username,String password){
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        String redirectUrl="redirect:login";
        try{
            subject.login(token);
            redirectUrl= "redirect:index";
        }catch(UnknownAccountException e){
            System.out.println("对用户["+username+"]验证未通过,未知账户");
        }catch(IncorrectCredentialsException e){
            System.out.println("对用户["+username+"]验证未通过,凭证错误");
        }catch (LockedAccountException e){
            System.out.println("对用户["+username+"]验证未通过,账户锁定");
        }
        return redirectUrl;
    }

    /**
     * 根据页码来查询用户
     * @param pageSize
     * @param pageNum
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public Msg getUsers(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        PageHelper.startPage(pageNum,pageSize);
        List list=sysUserService.getAllUser();
        PageInfo info=new PageInfo(list);
        return Msg.success().add("pageInfo",info);
    }

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/user",method = RequestMethod.POST)
    public Msg addUser(SysUser sysUser){
        boolean flag=sysUserService.insertUser(sysUser);
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
    }

    /**
     * 根据Id来获取用户
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/user/{id}",method=RequestMethod.GET )
    public Msg getUser(@PathVariable("id") String userId){
        SysUser user=sysUserService.getUserById(userId);
        if(user!=null){
            return Msg.success().add("users",user);
        }else{
            return Msg.failure();
        }
    }

    /**
     * 根据ID来修改用户
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/user/{id}",method=RequestMethod.PUT)
    public Msg updateUser(SysUser user){
        boolean flag=sysUserService.updateUser(user);
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
    }

    /**
     * 根据id删除员工
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/user/{ids}",method=RequestMethod.DELETE)
    public Msg deleteUser(@PathVariable("ids") String ids){
        boolean flag=sysUserService.deleteUser(ids);
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
    }
}
