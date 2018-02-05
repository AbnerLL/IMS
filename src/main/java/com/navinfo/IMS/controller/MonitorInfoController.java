package com.navinfo.IMS.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.entity.Emp;
import com.navinfo.IMS.entity.MonitorInfo;
import com.navinfo.IMS.service.MonitorInfoService;
import com.navinfo.IMS.so.MonitorInfoSearch;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 监察品质Controller
 * Created by luozhihui on 2017/11/26.
 */
@Controller
public class MonitorInfoController extends BaseController{
    @Autowired
    private MonitorInfoService monitorInfoService;

    /**
     * 分页查询对象
     * @param search
     * @param pageObject
     * @return
     */
    @RequestMapping(value="/monitorInfos",method= RequestMethod.GET)
    @ResponseBody
    public Msg search(MonitorInfoSearch search,PageObject pageObject){
        Emp currentEmp=super.getCurrentEmp();
        if (!hasPermission("monitorInfo:view")){
            search.setWorkerId(currentEmp.getEmpId());
        }
        PageInfo pageInfo=monitorInfoService.findMonitorInfosByPage(search,pageObject);
        return Msg.success().add("pageInfo",pageInfo);
    }
    /**
     * 保存对象
     * @param monitorInfo
     * @return
     */
    @RequestMapping(value = "/monitorInfo",method = RequestMethod.POST)
    @ResponseBody
    public Msg save(MonitorInfo monitorInfo){
        boolean flag=this.monitorInfoService.saveMonitorInfo(monitorInfo);
        return flag ? Msg.success():Msg.failure();
    }
    /**
     * 根据主键获取一个或多个对象
     * @param id
     * @return
     */
    @RequestMapping(value = "/monitorInfo/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg view(@PathVariable String id){
        List<MonitorInfo> monitorInfoList=this.monitorInfoService.findMonitorInfoById(id);
        return Msg.success().add("entities",monitorInfoList);
    }

    /**
     * 根据主键ID编辑对象
     * @param monitorInfo
     * @return
     */
    @RequestMapping(value = "/monitorInfo/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Msg edit(MonitorInfo monitorInfo){
        boolean flag=this.monitorInfoService.updateMonitorInfoById(monitorInfo);
        return flag ? Msg.success():Msg.failure();
    }

    /**
     * 根据主键删除一个或多个对象
     * @param id
     * @return
     */
    @RequestMapping(value = "/monitorInfo/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg delete(@PathVariable String id){
        boolean flag=this.monitorInfoService.deleteMonitorInfoById(id);
        return flag ? Msg.success():Msg.failure();
    }

    /**
     * 根据查询条件导出数据
     * @param search
     * @return
     */
    @RequestMapping(value = "/monitorInfoExcel")
    public ModelAndView exportExcel(MonitorInfoSearch search){
        ModelAndView modelAndView=new ModelAndView();
        Emp currentEmp=super.getCurrentEmp();
        if (!hasPermission("monitorInfo:export")){
            search.setWorkerId(currentEmp.getEmpId());
        }
        List<MonitorInfo> monitorInfoList=this.monitorInfoService.findMonitorInfosBySearch(search);
        modelAndView.addObject("monitorInfoList",monitorInfoList);
        modelAndView.setViewName("export/monitorInfoExcel");
        return modelAndView;
    }
}
