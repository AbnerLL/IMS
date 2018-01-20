package com.navinfo.IMS.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.service.QuestionSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 问题溯源Controller
 * Created by luozhihui on 2018/1/15.
 */
@Controller
public class QuestionSourceController {
    @Autowired
    private QuestionSourceService questionSourceService;

    /**
     *查询
     * @return
     */
    @GetMapping("/questionSources")
    @ResponseBody
    public Msg search(@RequestParam(value="pageSize",defaultValue="10") Integer pageSize, @RequestParam(value="pageNum",defaultValue="1")Integer pageNum){
        PageHelper.startPage(pageNum,pageSize);
        List list=questionSourceService.findQuestionSourceAll();
        PageInfo pageInfo=new PageInfo(list);
        return Msg.success().add("pageInfo",pageInfo);
    }
}
