package com.navinfo.IMS.controller.view;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.dto.view.PoiQuestionView;
import com.navinfo.IMS.dto.view.RoadQuestionView;
import com.navinfo.IMS.service.view.WorkQuestionService;
import com.navinfo.IMS.so.PoiQuestionSearch;
import com.navinfo.IMS.so.RoadQuestionSearch;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 作业问题Controller
 * Created by luozhihui on 2018/2/28.
 */
@Controller
public class WorkQuestionController extends BaseController{
    @Autowired
    private WorkQuestionService workQuestionService;

    /**
     * 查询道路问题
     * @param search
     * @param pageObject
     * @return
     */
    @RequestMapping("/roadQuestion")
    @ResponseBody
    public Msg searchRoadQuestion(RoadQuestionSearch search, PageObject pageObject){
        PageInfo pageInfo=this.workQuestionService.findRoadQuestionByPage(search,pageObject);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 查询设施问题
     * @param search
     * @param pageObject
     * @return
     */
    @RequestMapping("/poiQuestion")
    @ResponseBody
    public Msg searchPoiQuestion(PoiQuestionSearch search ,PageObject pageObject){
        PageInfo pageInfo=this.workQuestionService.findPoiQuestionByPage(search,pageObject);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 根据查询条件导出
     * @param search
     * @return
     */
    @RequestMapping("/roadQuestionExcel")
    @ResponseBody
    public ModelAndView exportRoadQuestion(RoadQuestionSearch search){
        ModelAndView modelAndView=new ModelAndView();
        List<RoadQuestionView> roadQuestionViewList=this.workQuestionService.findRoadQuestion(search);
        modelAndView.addObject("roadQuestionViewList",roadQuestionViewList);
        modelAndView.setViewName("export/roadQuestionExcel");
        return modelAndView;
    }

    /**
     * 根据条件导出
     * @return
     */
    @RequestMapping("/poiQuestionExcel")
    @ResponseBody
    public ModelAndView exportPoiQuestion(PoiQuestionSearch search){
        ModelAndView modelAndView=new ModelAndView();
        List<PoiQuestionView> poiQuestionViewList=this.workQuestionService.findPoiQuestion(search);
        modelAndView.addObject("poiQuestionViewList",poiQuestionViewList);
        modelAndView.setViewName("export/poiQuestionExcel");
        return modelAndView;
    }
}
