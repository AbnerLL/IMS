package com.navinfo.IMS.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.entity.EmpResume;
import com.navinfo.IMS.service.EmpResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 员工履历的Controller
 * Created by luozhihui on 2017/12/21.
 */
@Controller
public class EmpResumeController {

    @Autowired
    private EmpResumeService empResumeService;

    /**
     * 查询
     * @param map 存放页面传来的参数
     * @return
     */
    @RequestMapping(value = "/empResumes",method = RequestMethod.GET)
    @ResponseBody
    public Msg search(@RequestParam Map<String,Object> map ){
        PageInfo pageInfo=empResumeService.findEmpResumeByPage(map);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 展示empResume对象
     * @return Msg
     */
    @RequestMapping(value = "/empResume/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg showEmpResume(@PathVariable String id){
        List<EmpResume> list=empResumeService.findWorkDiaryById(id);
        return Msg.success().add("entities",list);
    }

    /**
     * 新增对象
     * @return
     */
    @RequestMapping(value = "/empResume",method = RequestMethod.POST)
    @ResponseBody
    public Msg addEmpResume(EmpResume empResume){
        boolean flag=empResumeService.saveEmpResume(empResume);
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
    }

    /**
     * 编辑对象
     * @param empResume
     * @return
     */
    @RequestMapping(value = "/empResume/{resumeId}",method = RequestMethod.PUT)
    @ResponseBody
    public Msg editEmpResume(EmpResume empResume){
        boolean flag=empResumeService.updateEmpResume(empResume);
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
    }

    /**
     * 删除一个或多个对象
     * @param ids
     * @return
     */
    @RequestMapping(value = "/empResume/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteEmpResume(@PathVariable String ids){
        boolean flag=empResumeService.deleteEmpResume(ids);
        return flag ? Msg.success():Msg.failure();
    }
}
