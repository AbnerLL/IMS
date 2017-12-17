package com.navinfo.IMS.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.service.PoiQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by luozhihui on 2017/11/26.
 */
@Controller
public class PoiQuestionController {
    @Autowired
    private PoiQuestionService poiQuestionService;

    /**
     * 分页查询对象
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value="/poiQuestions",method= RequestMethod.GET)
    @ResponseBody
    public Msg findPoiQuestionsByPage(@RequestParam(value = "pageSize",defaultValue="10") Integer pageSize,@RequestParam(value = "pageNum",defaultValue="1") Integer pageNum){
        PageHelper.startPage(pageNum,pageSize);
        List poiQuestions=poiQuestionService.findPoiQuestionAll();
        PageInfo pageInfo=new PageInfo(poiQuestions);
        return Msg.success().add("pageInfo",pageInfo);
    }
}
