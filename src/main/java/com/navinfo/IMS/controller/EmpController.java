package com.navinfo.IMS.controller;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.entity.Emp;
import com.navinfo.IMS.service.EmpService;
import com.navinfo.IMS.so.EmpSearch;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.core.controller.BaseController;
import com.navinfo.core.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        //获取当前用户对应的员工信息
        List<Emp> currentEmps=this.empService.getEmpById(getCurrentUser().getId());
        //如果没有对应的用户则不显示数据
        if (!hasPermission("emp:view")){//如果没有emp:view权限则只能看自己的数据
            //只能查看自己所在科室、部门或自己的数据
            if (hasPermission("emp:view:dept")){
                empSearch.setEmpDept(currentEmps.get(0).getEmpDept());
            }else if (hasPermission("emp:view:section")){
                empSearch.setEmpSec(currentEmps.get(0).getEmpSec());
            }else{
                empSearch.setEmpId(currentEmps.get(0).getEmpId());
            }
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
        List<Emp> emps=empService.getEmpById(empId);
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

    /**
     * 根据查询条件导出excel
     * 不分页
     * @return
     */
    @RequestMapping(value = "/empExcel")
    public ModelAndView exportExcel(EmpSearch empSearch){
        List<Emp> currentEmps=this.empService.getEmpById(super.getCurrentUser().getId());
        if (!hasPermission("emp:export")){//如果没有emp:export权限则只能看自己的数据
            if (hasPermission("emp:export:dept")){
                empSearch.setEmpDept(currentEmps.get(0).getEmpDept());
            }else if (hasPermission("emp:export:section")){
                empSearch.setEmpSec(currentEmps.get(0).getEmpSec());
            }else{
                empSearch.setEmpId(currentEmps.get(0).getEmpId());
            }
        }
        ModelAndView modelAndView=new ModelAndView();
        List<Emp> empList=this.empService.findEmpBySearch(empSearch);
        modelAndView.addObject("empList",empList);
        modelAndView.setViewName("export/empExcel");
        return modelAndView;
    }

    /**
     * 获取当前会话用户的员工信息
     * @return
     */
    @RequestMapping(value = "/currentEmp",method = RequestMethod.GET)
    @ResponseBody
    public Msg findCurrentEmp(){
        Subject currentSubject= SecurityUtils.getSubject();
        List<Emp> emps=this.empService.getEmpById((String) currentSubject.getPrincipal());
        return Msg.success().add("entities",emps);
    }
}
