package com.navinfo.IMS.controller.view;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.dto.view.*;
import com.navinfo.IMS.service.view.WorkQualityService;
import com.navinfo.IMS.so.*;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 作业品质Controller
 * Created by luozhihui on 2018/2/27.
 */
@Controller
public class WorkQualityController extends BaseController{
    @Autowired
    private WorkQualityService workQualityService;

    /**
     *道路品质
     * @return
     */
    @RequestMapping("/roadQuality")
    @ResponseBody
    public Msg searchRoadQuality(RoadQualitySearch search, PageObject pageObject){
        PageInfo pageInfo=this.workQualityService.findRoadQualityByPage(search,pageObject);
        return Msg.success().add("pageInfo",pageInfo);
    }
    /**
     *设施品质
     * @return
     */
    @RequestMapping("/poiQuality")
    @ResponseBody
    public Msg searchPoiQuality(PoiQualitySearch search, PageObject pageObject){
        PageInfo pageInfo=this.workQualityService.findPoiQualityByPage(search,pageObject);
        return Msg.success().add("pageInfo",pageInfo);
    }
    /**
     *中文名称品质
     * @return
     */
    @RequestMapping("/chPoiQuality")
    @ResponseBody
    public Msg searchChPoiQuality(ChPoiQualitySearch search, PageObject pageObject){
        PageInfo pageInfo=this.workQualityService.findChPoiQualityByPage(search,pageObject);
        return Msg.success().add("pageInfo",pageInfo);
    }
    /**
     *深度信息
     * @return
     */
    @RequestMapping("/deepInfoQuality")
    @ResponseBody
    public Msg searchDeepInfoQuality(DeepInfoQualitySearch search, PageObject pageObject){
        PageInfo pageInfo=this.workQualityService.findDeepInfoQualityByPage(search,pageObject);
        return Msg.success().add("pageInfo",pageInfo);
    }
    /**
     *深度信息通用
     * @return
     */
    @RequestMapping("/deepInfoGenQuality")
    @ResponseBody
    public Msg searchDeepInfoGenQuality(DeepInfoGenQualitySearch search, PageObject pageObject){
        PageInfo pageInfo=this.workQualityService.findDeepInfoGenQualityByPage(search,pageObject);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 导出excel
     * @param search
     * @return
     */
    @RequestMapping("/roadQualityExcel")
    @ResponseBody
    public ModelAndView exportRoadQuality(RoadQualitySearch search){
        ModelAndView modelAndView=new ModelAndView();
        List<RoadQuality> roadQualityList=this.workQualityService.findRoadQuality(search);
        modelAndView.addObject("roadQualityList",roadQualityList);
        modelAndView.setViewName("export/roadQualityExcel");
        return modelAndView;
    }
    /**
     * 导出excel
     * @param search
     * @return
     */
    @RequestMapping("/poiQualityExcel")
    @ResponseBody
    public ModelAndView exportPoiQuality(PoiQualitySearch search){
        ModelAndView modelAndView=new ModelAndView();
        List<PoiQuality> poiQualityList=this.workQualityService.findPoiQuality(search);
        modelAndView.addObject("poiQualityList",poiQualityList);
        modelAndView.setViewName("export/poiQualityExcel");
        return modelAndView;
    }
    /**
     * 导出excel
     * @param search
     * @return
     */
    @RequestMapping("/chPoiQualityExcel")
    @ResponseBody
    public ModelAndView exportChPoiQuality(ChPoiQualitySearch search){
        ModelAndView modelAndView=new ModelAndView();
        List<ChPoiQuality> chPoiQualityList=this.workQualityService.findChPoiQuality(search);
        modelAndView.addObject("chPoiQualityList",chPoiQualityList);
        modelAndView.setViewName("export/chPoiQualityExcel");
        return modelAndView;
    }
    /**
     * 导出excel
     * @param search
     * @return
     */
    @RequestMapping("/deepInfoQualityExcel")
    @ResponseBody
    public ModelAndView exportDeepInfoQuality(DeepInfoQualitySearch search){
        ModelAndView modelAndView=new ModelAndView();
        List<DeepInfoQuality> deepInfoQualityList=this.workQualityService.findDeepInfoQuality(search);
        modelAndView.addObject("deepInfoQualityList",deepInfoQualityList);
        modelAndView.setViewName("export/deepInfoQualityExcel");
        return modelAndView;
    }
    /**
     * 导出excel
     * @param search
     * @return
     */
    @RequestMapping("/deepInfoGenQualityExcel")
    @ResponseBody
    public ModelAndView exportDeepInfoGenQuality(DeepInfoGenQualitySearch search){
        ModelAndView modelAndView=new ModelAndView();
        List<DeepInfoGenQuality> deepInfoGenQualityList=this.workQualityService.findDeepInfoGenQuality(search);
        modelAndView.addObject("deepInfoGenQualityList",deepInfoGenQualityList);
        modelAndView.setViewName("export/deepInfoGenQualityExcel");
        return modelAndView;
    }
}
