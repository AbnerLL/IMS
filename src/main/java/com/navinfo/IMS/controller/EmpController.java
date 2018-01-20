package com.navinfo.IMS.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.entity.Emp;
import com.navinfo.IMS.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * 员工信息的Controller
 * Created by luozhihui on 2017/9/21.
 */
@Controller
public class EmpController {
    @Autowired
    private EmpService empService;

    /**
     * 根据分页信息和关键词来获取员工信息
     * @param map 用来存放页面传来的参数
     * @return
     */
    @RequestMapping(value="/emps",method = RequestMethod.GET)
    @ResponseBody
    public Msg findEmps(@RequestParam Map<String,Object> map){
        Integer pageSize=new Integer((String)map.get("pageSize"));
        Integer pageNum=new Integer((String) map.get("pageNum"));
        PageHelper.startPage(pageNum,pageSize);
        List emps=empService.findAllEmp();
        PageInfo pageInfo=new PageInfo(emps);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 根据ID获取员工信息
     * @param empId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/emp/{empId}",method=RequestMethod.GET)
    public Msg findEmpById(@PathVariable("empId")  String empId){
        List emps=empService.getEmpById(empId);
        if(emps!=null&&emps.size()!=0){
            return Msg.success().add("emps",emps);
        }else {
            return Msg.failure();
        }
    }
    /**
     * 新增员工
     * @param emp
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/emp",method=RequestMethod.POST)
    public Msg addEmp(Emp emp){
        boolean flag=empService.insertEmp(emp);
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
    }

    /**
     * 编辑员工
     * @param emp
     * @return
     */
    @ResponseBody
    @RequestMapping(value="emp/{empId}",method=RequestMethod.PUT)
    public Msg editEmp(Emp emp){
        boolean flag=empService.updateEmp(emp);
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
    }

    /**
     * 删除员工
     * @param empId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/emp/{empId}",method=RequestMethod.DELETE)
    public Msg deleteEmp(@PathVariable("empId") String empId){
        boolean flag=empService.deleteEmp(empId);
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
    }
}
