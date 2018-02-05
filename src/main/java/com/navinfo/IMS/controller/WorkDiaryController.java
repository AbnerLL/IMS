package com.navinfo.IMS.controller;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.entity.WorkDiary;
import com.navinfo.IMS.service.WorkDiaryService;
import com.navinfo.IMS.so.WorkDiarySearch;
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
import java.util.Map;

/**
 * 工作日志的Controller
 * Created by luozhihui on 2017/9/21.
 */
@Controller
public class WorkDiaryController extends BaseController{
    @Autowired
    private WorkDiaryService workDiaryService;

    /**
     * 查询
     * @return
     */
    @RequestMapping(value="/workDiaries",method = RequestMethod.GET)
    @ResponseBody
    public Msg search(WorkDiarySearch workDiarySearch, PageObject pageObject){
        Subject subject= SecurityUtils.getSubject();
        SysUser currentUser=super.getCurrentUser();
        if(!subject.isPermitted("workDiary:view")){
            workDiarySearch.setEmpId(currentUser.getId());
        }
        PageInfo pageInfo=workDiaryService.findWorkDiaryByPage(workDiarySearch,pageObject);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 展示workDiary对象
     * @param id
     * @return
     */
    @RequestMapping(value="/workDiary/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg showWorkDiary(@PathVariable String id){
        List<WorkDiary> workDiaries=workDiaryService.findWorkDiaryById(id);
        return Msg.success().add("workDiaries",workDiaries);
    }

    /**
     * 新增对象
     * @param workDiary
     * @return
     */
    @RequestMapping(value="/workDiary", method = RequestMethod.POST)
    @ResponseBody
    public Msg addWorkDiary(WorkDiary workDiary){
        boolean flag=workDiaryService.saveWorkDiary(workDiary);
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
    }

    /**
     * 更新对象
     * @return
     */
    @RequestMapping(value="/workDiary/{id}",method=RequestMethod.PUT)
    @ResponseBody
    public Msg editWorkDiary(WorkDiary workDiary){
        boolean flag=workDiaryService.updateWorkDiary(workDiary);
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
    }

    /**
     * 根据id删除一个或多个对象
     * @return
     */
    @RequestMapping(value="/workDiary/{ids}",method=RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteWorkDiary(@PathVariable String ids){
        boolean flag=workDiaryService.deleteWorkDiary(ids);
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
    }

    /**
     * 根据查询条件导出excel
     * 不分页
     * @param workDiarySearch
     * @return
     */
    @RequestMapping(value = "/workDiaryExcel")
    public ModelAndView exportExcel(WorkDiarySearch workDiarySearch){
        Subject subject= SecurityUtils.getSubject();
        SysUser currentUser=super.getCurrentUser();
        if(!subject.isPermitted("workDiary:export")){
            workDiarySearch.setEmpId(currentUser.getId());
        }
        ModelAndView modelAndView=new ModelAndView();
        List<WorkDiary> workDiaryList=this.workDiaryService.findWorkDiaryBySearch(workDiarySearch);
        modelAndView.addObject("workDiaryList",workDiaryList);
        modelAndView.setViewName("export/workDiaryExcel");
        return modelAndView;
    }
}
