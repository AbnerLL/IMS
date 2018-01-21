package com.navinfo.core.controller;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.core.entity.SysModule;
import com.navinfo.core.service.SysModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统模块Controller
 * Created by luozhihui on 2018/1/21.
 */
@Controller
public class SysModuleController {
    @Autowired
    private SysModuleService sysModuleService;

//    public
    /**
     * 查询
     * @param map
     * @return
     */
    @RequestMapping(value="/sysModules",method = RequestMethod.GET)
    @ResponseBody
    public Msg search(@RequestParam Map map){
        PageInfo pageInfo=this.sysModuleService.findSysModuleByPage(map);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    @RequestMapping(value="/sysModule/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg showSysModule(@PathVariable String id){
        List<SysModule> list=this.sysModuleService.findSysModuleById(id);
        return Msg.success().add("entities",list);
    }

    /**
     *新增对象
     * @param sysModule
     * @return
     */
    @RequestMapping(value="/sysModule",method = RequestMethod.POST)
    @ResponseBody
    public Msg addSysModule(SysModule sysModule){
        boolean flag=this.sysModuleService.saveSysModule(sysModule);
        return flag ? Msg.success() :Msg.failure();
    }

    /**
     * 编辑对象
     * @return
     */
    @RequestMapping(value="/sysModule/{moduleId}",method = RequestMethod.PUT)
    @ResponseBody
    public Msg editSysModule(SysModule sysModule){
        boolean flag=this.sysModuleService.updateSysModule(sysModule);
        return flag ? Msg.success() :Msg.failure();
    }

    /**
     *删除对象
     * @return
     */
    @RequestMapping(value="/sysModule/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteSysModule(@PathVariable String ids){
        boolean flag=this.sysModuleService.deleteSysModule(ids);
        return flag ? Msg.success() :Msg.failure();
    }
}
