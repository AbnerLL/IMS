package com.navinfo.IMS.controller;

import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.service.ChartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 用于处理页面图表请求的Controller
 * Created by luozhihui on 2017/12/17.
 */
@Controller
public class ChartsController {
    @Autowired
    private ChartsService chartsService;

    /**
     * 获取员工分布数据
     * @return
     */
    @GetMapping("/charts/empAddressGroup")
    @ResponseBody
    public Msg searchEmpAddressGroup(){
        List list=chartsService.findEmpAddressGroup();
        return Msg.success().add("data",list);
    }
    /**
     * 获取员工性别分布数据
     * @return
     */
    @GetMapping("/charts/empGenderGroup")
    @ResponseBody
    public Msg searchEmpGenderGroup(){
        List list=chartsService.findEmpGenderGroup();
        return Msg.success().add("data",list);
    }

    /**
     * 获取司龄的分组统计
     * @return
     */
    @RequestMapping(value="/charts/empSLingStat",method= RequestMethod.GET)
    @ResponseBody
    public Msg searchEmpSlingStat(){
        List list=chartsService.findEmpSLingStat();
        return Msg.success().add("data",list);
    }

    /**
     *查询道路品质率
     * @return
     */
    @RequestMapping(value="/charts/roadQualityRate",method= RequestMethod.GET)
    @ResponseBody
    public Msg searchRoadQualityRate(){
        List list=chartsService.findRoadQualityRate();
        return Msg.success().add("data",list);
    }

    /**
     *查询设置品质率
     * @return
     */
    @RequestMapping(value="/charts/poiQualityRate",method= RequestMethod.GET)
    @ResponseBody
    public Msg searchPoiQualityRate(){
        List list=chartsService.findPoiQualityRate();
        return Msg.success().add("data",list);
    }
}
