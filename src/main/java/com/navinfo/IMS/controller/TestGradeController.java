package com.navinfo.IMS.controller;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.entity.Emp;
import com.navinfo.IMS.entity.TestGrade;
import com.navinfo.IMS.service.TestGradeService;
import com.navinfo.IMS.so.TestGradeSearch;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 全要素考核的Controller
 * Created by luozhihui on 2017/9/21.
 */
@Controller
public class TestGradeController extends BaseController{
    @Autowired
    private TestGradeService testGradeService;
    /**
     * 分页查询
     * @param search
     * @param pageObject
     * @return
     */
    @RequestMapping(value = "/testGrades",method = RequestMethod.GET)
    @ResponseBody
    public Msg search(TestGradeSearch search, PageObject pageObject){
        Emp currentEmp=super.getCurrentEmp();
        if (!hasPermission("testGrade:view")){
            search.setEmpId(currentEmp.getEmpId());
        }
        PageInfo pageInfo=this.testGradeService.findTestGradeByPage(search,pageObject);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 根据主键获得对象
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/testGrade/{id}",method=RequestMethod.GET)
    public Msg findTestGradeById(@PathVariable("id") String id){
        List<TestGrade> testGradeList=testGradeService.getTestGradeById(id);
        return Msg.success().add("entities",testGradeList);
    }
    /**
     * 新增数据
     * @param testGrade
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/testGrade",method = RequestMethod.POST)
    public Msg addTestGrade(TestGrade testGrade){
        boolean flag=testGradeService.insertTestGrade(testGrade);
        return flag ?Msg.success():Msg.failure();
    }

    /**
     * 更新数据
     * @param testGrade
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/testGrade/{id}",method=RequestMethod.PUT)
    public Msg editTestGrade(TestGrade testGrade){
        boolean flag=testGradeService.updateTestGrade(testGrade);
        return flag ?Msg.success():Msg.failure();
    }

    /**
     * 删除数据
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/testGrade/{id}",method=RequestMethod.DELETE)
    public Msg deleteTestGrade(@PathVariable("id") String ids){
        boolean flag=testGradeService.deleteTestGradeById(ids);
        return flag ?Msg.success():Msg.failure();
    }
    /**
     * 根据查询条件导出数据
     * @param search
     * @return
     */
    @RequestMapping(value = "/testGradeExcel")
    public ModelAndView exportExcel(TestGradeSearch search){
        ModelAndView modelAndView=new ModelAndView();
        Emp currentEmp=super.getCurrentEmp();
        if (!hasPermission("monitorInfo:export")){
            search.setEmpId(currentEmp.getEmpId());
        }
        List<TestGrade> monitorInfoList=this.testGradeService.findTestGradeBySearch(search);
        modelAndView.addObject("monitorInfoList",monitorInfoList);
        modelAndView.setViewName("export/testGradeExcel");
        return modelAndView;
    }
}
