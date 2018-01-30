package com.navinfo.IMS.controller;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.entity.Emp;
import com.navinfo.IMS.service.EmpService;
import com.navinfo.IMS.so.EmpSearch;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.core.controller.BaseController;
import com.navinfo.core.entity.SysRole;
import com.navinfo.core.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 员工信息的Controller
 * Created by luozhihui on 2017/9/21.
 */
@Controller
public class EmpController extends BaseController{
    @Autowired
    private EmpService empService;

    /**
     * 根据分页信息和关键词来获取员工信息
     * @param empSearch 页面的表单查询参数
     * @param pageObject 页面的分页信息
     * @return Msg 通用的响应json对象
     */
    @RequestMapping(value="/emps",method = RequestMethod.GET)
    @ResponseBody
    public Msg search(EmpSearch empSearch, PageObject pageObject){
        Subject currentSubject= SecurityUtils.getSubject();
        SysUser currentUser=this.getCurrentUser();
        if (!currentSubject.isPermitted("emp:view")){//如果没有emp:view权限则只能看自己的数据
            empSearch.setEmpId(currentUser.getId());
        }
        PageInfo pageInfo=this.empService.findEmpByPage(empSearch,pageObject);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 根据ID获取员工信息
     * @param empId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/emp/{empId}",method=RequestMethod.GET)
    public Msg findEmpById(@PathVariable("empId")  String empId){
        List emps=empService.getEmpById(empId);
        if(emps!=null&&emps.size()!=0){
            return Msg.success().add("emps",emps);
        }else {
            return Msg.failure();
        }
    }
    /**
     * 新增员工
     * @param emp
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/emp",method=RequestMethod.POST)
    public Msg addEmp(Emp emp){
        boolean flag=empService.insertEmp(emp);
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
    }

    /**
     * 编辑员工
     * @param emp
     * @return
     */
    @ResponseBody
    @RequestMapping(value="emp/{empId}",method=RequestMethod.PUT)
    public Msg editEmp(Emp emp){
        boolean flag=empService.updateEmp(emp);
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
    }

    /**
     * 删除员工
     * @param empId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/emp/{empId}",method=RequestMethod.DELETE)
    public Msg deleteEmp(@PathVariable("empId") String empId){
        boolean flag=empService.deleteEmp(empId);
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
    }
}
