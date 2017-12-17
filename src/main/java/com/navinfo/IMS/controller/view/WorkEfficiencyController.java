package com.navinfo.IMS.controller.view;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.service.view.WorkEfficiencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luozhihui on 2017/12/3.
 */
@Controller
public class WorkEfficiencyController {
    @Autowired
    private WorkEfficiencyService workEfficiencyService;

    @RequestMapping(value = "/workEfficiency",method= RequestMethod.GET)
    @ResponseBody
    public Msg search(@RequestParam Map<String,Object> map){
        Integer pageSize=new Integer((String)map.get("pageSize"));
        Integer pageNum=new Integer((String) map.get("pageNum"));
        PageHelper.startPage(pageNum,pageSize);
        List list=workEfficiencyService.findWorkEfficiency(map);
        PageInfo pageInfo=new PageInfo(list);
        return Msg.success().add("pageInfo",pageInfo);
    }
}
