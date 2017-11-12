package com.navinfo.IMS.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.service.ProAbilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 业务能力Controller
 * Created by luozhihui on 2017/9/21.
 */
@Controller
public class ProAbilityController {
    @Autowired
    private ProAbilityService proAbilityService;

    /**
     * 分页查询
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/proAbilities",method = RequestMethod.GET)
    @ResponseBody
    public Msg findWorkAbilities(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum){
        PageHelper.startPage(pageNum,pageSize);
        List workAbilities=proAbilityService.findProAbilities();
        PageInfo pageInfo=new PageInfo(workAbilities);
        return Msg.success().add("pageInfo",pageInfo);
    }
}
