package com.navinfo.IMS.controller;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.entity.Emp;
import com.navinfo.IMS.entity.EmpAptitude;
import com.navinfo.IMS.service.EmpAptitudeService;
import com.navinfo.IMS.so.EmpAptitudeSearch;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 人员资质Controller
 * Created by luozhihui on 2017/12/5.
 */
@Controller
public class EmpAptitudeController extends BaseController{
    @Autowired
    private EmpAptitudeService empAptitudeService;

    /**
     * 查询
     * @param search
     * @param pageObject
     * @return
     */
    @RequestMapping(value = "/empAptitudes",method= RequestMethod.GET)
    @ResponseBody
    public Msg search(EmpAptitudeSearch search, PageObject pageObject){
        Emp currentEmp=super.getCurrentEmp();
        if (!hasPermission("empAptitude:view")){
            search.setEmpId(currentEmp.getEmpId());
        }
        PageInfo pageInfo=this.empAptitudeService.findEmpAptitudeByPage(search,pageObject);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 新增数据
     * @param empAptitude
     * @return
     */
    @RequestMapping(value = "/empAptitude",method = RequestMethod.POST)
    @ResponseBody
    public Msg save(EmpAptitude empAptitude){
        boolean flag=this.empAptitudeService.saveEmpAptitude(empAptitude);
        return flag ? Msg.success() : Msg.failure();
    }

    /**
     * 查看
     * @param id
     * @return
     */
    @RequestMapping(value = "/empAptitude/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg view(@PathVariable String id){
        List<EmpAptitude> empAptitudeList=this.empAptitudeService.findEmpAptitudeById(id);
        return Msg.success().add("entities",empAptitudeList);
    }

    /**
     * 根据主键编辑
     * @param empAptitude
     * @return
     */
    @RequestMapping(value = "/empAptitude/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Msg edit(EmpAptitude empAptitude){
        boolean flag=this.empAptitudeService.updateEmpAptitudeById(empAptitude);
        return flag ? Msg.success() :Msg.failure();
    }

    /**
     *根据主键删除一个或多个对象
     * @return
     */
    @RequestMapping(value = "/empAptitude/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg delete(@PathVariable String ids){
        boolean flag=this.empAptitudeService.deleteEmpAptitudeById(ids);
        return flag ? Msg.success() : Msg.failure();
    }

    /**
     * 根据查询条件导出excel
     * @return
     */
    @RequestMapping(value = "/empAptitudeExcel")
    public ModelAndView exportExcel(EmpAptitudeSearch search){
        ModelAndView modelAndView=new ModelAndView();
        Emp currentEmp=super.getCurrentEmp();
        if (!hasPermission("empAptitude:export")){
            search.setEmpId(currentEmp.getEmpId());
        }
        List<EmpAptitude> empAptitudeList=this.empAptitudeService.findEmpAptitudeBySearch(search);
        modelAndView.addObject("empAptitudeList",empAptitudeList);
        modelAndView.setViewName("export/empAptitudeExcel");
        return modelAndView;
    }
}
