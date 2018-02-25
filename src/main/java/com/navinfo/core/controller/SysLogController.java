package com.navinfo.core.controller;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.core.service.SysLogService;
import com.navinfo.core.so.SysLogSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统日志的Controller
 * Created by luozhihui on 2018/2/24.
 */
@Controller
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 查询
     * @return
     */
    @RequestMapping(value = "/sysLogs",method= RequestMethod.GET)
    @ResponseBody
    public Msg search(SysLogSearch search, PageObject pageObject){
        PageInfo pageInfo=this.sysLogService.findSysLogByPage(search,pageObject);
        return Msg.success().add("pageInfo",pageInfo);
    }
}
