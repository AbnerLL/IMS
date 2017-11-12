package com.navinfo.IMS.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 通用操作的Controller
 * Created by luozhihui on 2017/9/20.
 */
@Controller
public class CommonController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/setting/{page}")
    public String openPage(@PathVariable String page){
        return page;
    }
}
