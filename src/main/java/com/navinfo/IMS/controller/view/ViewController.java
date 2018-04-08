package com.navinfo.IMS.controller.view;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.entity.KPIReport;
import com.navinfo.IMS.entity.QuestionReport;
import com.navinfo.IMS.entity.WeekReport;
import com.navinfo.IMS.service.view.ViewService;
import com.navinfo.IMS.utils.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by luozhihui on 2017/12/3.
 */
@RestController
@RequestMapping("/view")
public class ViewController {
    @Autowired
    private ViewService viewService;

    /**
     * 作业功效查询
     * @param map
     * @return
     */
    @RequestMapping(value = "/workEfficiency",method= RequestMethod.GET)
    public Msg search(@RequestParam Map<String,Object> map){
        Integer pageSize=new Integer((String)map.get("pageSize"));
        Integer pageNum=new Integer((String) map.get("pageNum"));
        PageHelper.startPage(pageNum,pageSize);
        List list= viewService.findWorkEfficiency(map);
        PageInfo pageInfo=new PageInfo(list);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 周报查询
     * @param weekReport
     * @return
     */
    @RequestMapping("/weekReports")
    public Msg searchWeekReport(WeekReport weekReport){
        List list = this.viewService.findWeekReport(weekReport);
        PageInfo pageInfo = new PageInfo(list);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 问题报表查询
     * @param questionReport
     * @return
     */
    @RequestMapping("/questionReports")
    public Msg searchQuestionReport(QuestionReport questionReport){
        List list = this.viewService.findQuestionReport(questionReport);
        PageInfo pageInfo = new PageInfo(list);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * kpi周报查询
     * @param kpiReport
     * @return
     */
    @RequestMapping("/kpiReports")
    public Msg searchKPIReport(KPIReport kpiReport){
        List list = this.viewService.findKPIReport(kpiReport);
        PageInfo pageInfo = new PageInfo(list);
        return Msg.success().add("pageInfo",pageInfo);
    }
}
