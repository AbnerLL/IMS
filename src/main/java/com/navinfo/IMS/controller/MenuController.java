package com.navinfo.IMS.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.service.MenuService;
import com.navinfo.IMS.utils.MenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by luozhihui on 2017/9/21.
 */
@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/index")
    public String init(){
        return "index";
    }

    /**
     * 初始化菜单
     * @return
     */
    @RequestMapping(value="/initMenus",method = RequestMethod.GET)
    @ResponseBody
    public Object InitMenus(){
        List<MenuUtil> menuUtilList=menuService.selectMenusByPid();
        return menuUtilList;
    }

    /**
     * 查询
     * @param map
     * @return
     */
    @RequestMapping(value="/menus",method=RequestMethod.GET)
    @ResponseBody
    public Msg search(@RequestParam Map map){
        PageInfo pageInfo=this.menuService.findMenuByPage(map);
        return Msg.success().add("pageInfo",pageInfo);
    }
}
