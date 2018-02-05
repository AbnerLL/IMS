package com.navinfo.IMS.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.entity.QuestionSource;
import com.navinfo.IMS.service.QuestionSourceService;
import com.navinfo.IMS.so.QuestionSourceSearch;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.core.controller.BaseController;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 问题溯源Controller
 * Created by luozhihui on 2018/1/15.
 */
@Controller
public class QuestionSourceController extends BaseController{
    @Autowired
    private QuestionSourceService questionSourceService;

    /**
     *查询
     * @return
     */
    @RequestMapping("/questionSources")
    @ResponseBody
    public Msg search(QuestionSourceSearch search, PageObject pageObject){
        //判断是否有查看所有数据的权限，否则只能看自己的数据
        if (!hasPermission("questionSource:view")){
            search.setEmpId(this.getCurrentUser().getId());
        }
        PageInfo pageInfo=this.questionSourceService.findQuestionSourceByPage(search,pageObject);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 新增
     * @param questionSource
     * @return
     */
    @RequestMapping(value = "/questionSource",method= RequestMethod.POST)
    @ResponseBody
    public Msg add(QuestionSource questionSource){
        boolean flag=this.questionSourceService.saveQuestionSource(questionSource);
        return flag ? Msg.success():Msg.failure();
    }
    /**
     * 修改
     * @param questionSource
     * @return
     */
    @RequestMapping(value = "/questionSource/{id}",method= RequestMethod.PUT)
    @ResponseBody
    public Msg edit(QuestionSource questionSource){
        boolean flag=this.questionSourceService.updateQuestionSource(questionSource);
        return flag ? Msg.success():Msg.failure();
    }
    /**
     * 查看
     * @param id 主键
     * @return
     */
    @RequestMapping(value = "/questionSource/{id}",method= RequestMethod.GET)
    @ResponseBody
    public Msg view(@PathVariable String id){
        QuestionSource questionSource=this.questionSourceService.findQuestionSourceById(id);
        return Msg.success().add("modal",questionSource);
    }
    /**
     * 删除,可以删除一个或多个对象
     * @param ids 主键
     * @return
     */
    @RequestMapping(value = "/questionSource/{ids}",method= RequestMethod.DELETE)
    @ResponseBody
    public Msg delete(@PathVariable String ids){
        boolean flag=this.questionSourceService.deleteQuestionSourceByIds(ids);
        return flag ?Msg.success():Msg.failure();
    }

    /**
     * 根据查询条件导出excel
     * @param search
     * @return
     */
    @RequestMapping(value = "/questionSourceExcel")
    public ModelAndView exportExcel(QuestionSourceSearch search){
        ModelAndView modelAndView=new ModelAndView();
        if (!hasPermission("questionSource:export")){
            search.setEmpId(this.getCurrentUser().getId());
        }
        List<QuestionSource> questionSourceList=this.questionSourceService.findQuestionSourceBySearch(search);
        modelAndView.addObject("questionSourceList",questionSourceList);
        modelAndView.setViewName("export/questionSourceExcel");
        return modelAndView;
    }
}
