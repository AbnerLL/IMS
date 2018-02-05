package com.navinfo.IMS.controller;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.entity.Emp;
import com.navinfo.IMS.entity.EmpResume;
import com.navinfo.IMS.service.EmpResumeService;
import com.navinfo.IMS.so.EmpResumeSearch;
import com.navinfo.IMS.so.EmpSearch;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.IMS.utils.StringUtil;
import com.navinfo.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 员工履历的Controller
 * Created by luozhihui on 2017/12/21.
 */
@Controller
public class EmpResumeController extends BaseController{

    @Autowired
    private EmpResumeService empResumeService;

    /**
     * 查询
     * @param search 存放页面传来的参数
     * @param pageObject 用于接收页面表格分页的参数
     * @return
     */
    @RequestMapping(value = "/empResumes",method = RequestMethod.GET)
    @ResponseBody
    public Msg search(EmpResumeSearch search, PageObject pageObject){
        Emp currentEmp=super.getCurrentEmp();
        if (!hasPermission("empResume:view")){
            search.setEmpId(currentEmp.getEmpId());
        }
        PageInfo pageInfo=empResumeService.findEmpResumeByPage(search,pageObject);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 展示empResume对象
     * @return Msg
     */
    @RequestMapping(value = "/empResume/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg showEmpResume(@PathVariable String id){
        List<EmpResume> list=empResumeService.findWorkDiaryById(id);
        return Msg.success().add("entities",list);
    }

    /**
     * 新增对象
     * @return
     */
    @RequestMapping(value = "/empResume",method = RequestMethod.POST)
    @ResponseBody
    public Msg addEmpResume(EmpResume empResume){
        boolean flag=empResumeService.saveEmpResume(empResume);
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
    }

    /**
     * 编辑对象
     * @param empResume
     * @return
     */
    @RequestMapping(value = "/empResume/{resumeId}",method = RequestMethod.PUT)
    @ResponseBody
    public Msg editEmpResume(EmpResume empResume){
        boolean flag=empResumeService.updateEmpResume(empResume);
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
    }

    /**
     * 删除一个或多个对象
     * @param ids
     * @return
     */
    @RequestMapping(value = "/empResume/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteEmpResume(@PathVariable String ids){
        boolean flag=empResumeService.deleteEmpResume(ids);
        return flag ? Msg.success():Msg.failure();
    }

    /**
     * 根据查询条件导出excel
     * @param empResumeSearch
     * @return
     */
    @RequestMapping(value ="/empResumeExcel")
    public ModelAndView exportExcel(EmpResumeSearch empResumeSearch){
        ModelAndView modelAndView=new ModelAndView();
        Emp currentEmp=super.getCurrentEmp();
        if (!hasPermission("empResume:export")){
            empResumeSearch.setEmpId(currentEmp.getEmpId());
        }
        List<EmpResume> empResumeList=this.empResumeService.findEmpResumeBySearch(empResumeSearch);
        modelAndView.addObject("empResumeList",empResumeList);
        modelAndView.setViewName("export/empResumeExcel.jsp");
        return modelAndView;
    }
}
