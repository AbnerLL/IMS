package com.navinfo.IMS.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.entity.SysUser;
import com.navinfo.IMS.service.SysUserService;
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

    @RequestMapping(value = "/toLogin" ,method = RequestMethod.POST)
    public String login(String username,String password){
        if(!StringUtils.isEmpty(username)){
            boolean flag=sysUserService.getSysUser(username,password);
            if (flag){
                return "redirect:index";
            }
        }
        return "login";
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
        List<SysUser> users=sysUserService.getUserById(userId);
        if(users!=null&&users.size()!=0){
            return Msg.success().add("users",users);
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
