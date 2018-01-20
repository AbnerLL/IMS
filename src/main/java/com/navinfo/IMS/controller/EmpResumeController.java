package com.navinfo.IMS.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.service.EmpResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        Integer pageSize=new Integer((String)map.get("pageSize"));
        Integer pageNum=new Integer((String) map.get("pageNum"));
        PageHelper.startPage(pageNum,pageSize);
        List empResumes=empResumeService.findEmpResumeAll();
        PageInfo pageInfo=new PageInfo(empResumes);
        return Msg.success().add("pageInfo",pageInfo);
    }

}
