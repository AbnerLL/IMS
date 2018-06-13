package com.navinfo.core.controller;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.core.entity.SysGroup;
import com.navinfo.core.service.SysGroupService;
import com.navinfo.core.so.SysGroupSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by luozhihui on 2018/3/5.
 */
@Controller
@RequestMapping("/sysGroup")
public class SysGroupController {
    @Autowired
    private SysGroupService sysGroupService;
    /**
     * 加载所有对象
     * @param search
     * @return
     */
    @RequestMapping("/loadAll")
    @ResponseBody
    public Msg loadAll(SysGroupSearch search){
        List<SysGroup> sysGroupList = this.sysGroupService.findSysGroup(search);
        return Msg.success().add("entities",sysGroupList);
    }
}
