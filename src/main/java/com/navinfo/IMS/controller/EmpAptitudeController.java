package com.navinfo.IMS.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.service.EmpAptitudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by luozhihui on 2017/12/5.
 */
@Controller
public class EmpAptitudeController {
    @Autowired
    private EmpAptitudeService empAptitudeService;

    /**
     * 查询
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/empAptitudes",method= RequestMethod.GET)
    @ResponseBody
    public Msg search(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        PageHelper.startPage(pageNum,pageSize);
        List list=empAptitudeService.findAll();
        PageInfo pageInfo=new PageInfo(list);
        return Msg.success().add("pageInfo",pageInfo);
    }
}
