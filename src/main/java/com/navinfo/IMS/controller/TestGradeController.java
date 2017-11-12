package com.navinfo.IMS.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.entity.TestGrade;
import com.navinfo.IMS.service.TestGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by luozhihui on 2017/9/21.
 */
@Controller
public class TestGradeController {
    @Autowired
    private TestGradeService testGradeService;
    /**
     * 分页查询
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/testGrades",method = RequestMethod.GET)
    @ResponseBody
    public Msg findTestGrades(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum){
        PageHelper.startPage(pageNum,pageSize);
        List testGrades=testGradeService.findTestGrades();
        PageInfo pageInfo=new PageInfo(testGrades);
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
        TestGrade testGrade=testGradeService.getTestGradeById(id);
        if(testGrade!=null){
            return Msg.success().add("data",testGrade);
        }else{
            return Msg.failure();
        }
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
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
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
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
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
        if (flag){
            return Msg.success();
        }else {
            return Msg.failure();
        }
    }

}
