package com.navinfo.IMS.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.entity.Emp;
import com.navinfo.IMS.entity.ProAbility;
import com.navinfo.IMS.service.ProAbilityService;
import com.navinfo.IMS.so.ProAbilitySearch;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 业务能力Controller
 * Created by luozhihui on 2017/9/21.
 */
@Controller
public class ProAbilityController extends BaseController{
    @Autowired
    private ProAbilityService proAbilityService;

    /**
     * 分页查询
     * @param search
     * @param pageObject
     * @return
     */
    @RequestMapping(value = "/proAbilities",method = RequestMethod.GET)
    @ResponseBody
    public Msg search(ProAbilitySearch search, PageObject pageObject){
        Emp currentEmp=super.getCurrentEmp();
        if (!hasPermission("proAbility:view")){
            search.setEmpId(currentEmp.getEmpId());
        }
        PageInfo pageInfo = proAbilityService.findProAbilitiesByPage(search,pageObject);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 保存对象
     * @return
     */
    @RequestMapping(value = "/proAbility",method = RequestMethod.POST)
    @ResponseBody
    public Msg save(ProAbility proAbility){
        boolean flag=this.proAbilityService.saveProAbility(proAbility);
        return flag ? Msg.success():Msg.failure();
    }

    /**
     * 查看
     * @return
     */
    @RequestMapping(value = "/proAbility/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg view (@PathVariable String id){
        List<ProAbility> proAbilityList=this.proAbilityService.findProAbilitiesById(id);
        return Msg.success().add("entities",proAbilityList);
    }

    /**
     * 编辑
     * @return
     */
    @RequestMapping(value = "/proAbility/{uuid}",method = RequestMethod.PUT)
    @ResponseBody
    public Msg edit(ProAbility proAbility){
        boolean flag=this.proAbilityService.updateProAbilityById(proAbility);
        return flag ? Msg.success():Msg.failure();
    }
    /**
     * 删除一个或多个对象
     * @return
     */
    @RequestMapping(value = "/proAbility/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg delete(@PathVariable String ids){
        boolean flag=this.proAbilityService.deleteProAbilitiesById(ids);
        return flag ? Msg.success():Msg.failure();
    }

    /**
     * 根据查询条件导出Excel
     * @return
     */
    @RequestMapping(value = "/proAbilityExcel")
    public ModelAndView exportExcel(ProAbilitySearch search){
        Emp currentEmp=super.getCurrentEmp();
        if (!hasPermission("proAbility:export")){
            search.setEmpId(currentEmp.getEmpId());
        }
        ModelAndView modelAndView=new ModelAndView();
        List<ProAbility> proAbilityList=this.proAbilityService.findProAbilitiesBySearch(search);
        modelAndView.addObject("proAbilityList",proAbilityList);
        modelAndView.setViewName("export/proAbilityExcel");
        return modelAndView;
    }
}
