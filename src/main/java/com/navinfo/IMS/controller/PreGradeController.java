package com.navinfo.IMS.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.entity.PreGrade;
import com.navinfo.IMS.service.PreGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by luozhihui on 2017/9/21.
 */
@Controller
public class PreGradeController {
    @Autowired
    private PreGradeService preGradeService;

    /**
     * 分页查询
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/preGrades",method = RequestMethod.GET)
    @ResponseBody
    public Msg findPreGrades(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum){
        PageHelper.startPage(pageNum,pageSize);
        List preGrades=preGradeService.findPreGrades();
        PageInfo pageInfo=new PageInfo(preGrades);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 根据主键获得对象
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/preGrade/{id}",method=RequestMethod.GET)
    public Msg findPreGradeById(@PathVariable("id") String id){
        PreGrade preGrade=preGradeService.getPreGradeById(id);
        if(preGrade!=null){
            return Msg.success().add("data",preGrade);
        }else{
            return Msg.failure();
        }
    }
    /**
     * 新增数据
     * @param preGrade
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/preGrade",method = RequestMethod.POST)
    public Msg addPreGrade(PreGrade preGrade){
        boolean flag=preGradeService.insertPreGrade(preGrade);
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
    }

    /**
     * 更新数据
     * @param preGrade
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/preGrade/{id}",method=RequestMethod.PUT)
    public Msg editPreGrade(PreGrade preGrade){
        boolean flag=preGradeService.updatePreGrade(preGrade);
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
    @RequestMapping(value="/preGrade/{id}",method=RequestMethod.DELETE)
    public Msg deletePreGrade(@PathVariable("id") String ids){
        boolean flag=preGradeService.deletePreGradeById(ids);
        if (flag){
            return Msg.success();
        }else {
            return Msg.failure();
        }
    }
}
