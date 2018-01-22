package com.navinfo.core.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.core.entity.SysUser;
import com.navinfo.core.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        List<SysUser> sysUsers=sysUserService.getUserById(userId);
        if(sysUsers!=null){
            return Msg.success().add("entities",sysUsers);
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

    /**
     * 获取当前会话用户
     * @return
     */
    @RequestMapping(value="/user/currentUser",method=RequestMethod.GET)
    @ResponseBody
    public Msg getCurrentUser(){
        Subject subject=SecurityUtils.getSubject();
        String username=subject.getPrincipal().toString();
        List<SysUser> sysRoleList=this.sysUserService.getUserById(username);
        return Msg.success().add("entities",sysRoleList);
    }

    /**
     * 修改密码
     * @param oldPwd
     * @param newPwdOne
     * @param newPwdTwo
     * @return
     */
    @RequestMapping(value="/user/changePwd",method = RequestMethod.POST)
    @ResponseBody
    public Msg changePwd(@RequestParam String oldPwd,@RequestParam String newPwdOne,@RequestParam String newPwdTwo){
        try{
            if(oldPwd==null||""==oldPwd){
                return Msg.failure().add("error","原始密码为空！");
            }
            if(newPwdOne==null||newPwdTwo==null){
                return Msg.failure().add("error","新密码为空！");
            }
            if(!newPwdOne.equals(newPwdTwo)){
                return Msg.failure().add("error","新密码不一致！");
            }
            Subject subject=SecurityUtils.getSubject();
            String username=subject.getPrincipal().toString();
            List<SysUser> sysUserList=this.sysUserService.getUserById(username);
            if(sysUserList!=null&&sysUserList.size()>0){
                SysUser sysUser=sysUserList.get(0);
                if(oldPwd.equals(sysUser.getPassword())){
                    sysUser.setPassword(newPwdOne);
                    boolean flag=this.sysUserService.updateUser(sysUser);
                    return flag ? Msg.success():Msg.failure();
                }else{
                    return Msg.failure().add("error","原始密码错误！");
                }
            }
        }catch (Exception e){
            return Msg.failure();
        }
        return Msg.success();
    }
}
