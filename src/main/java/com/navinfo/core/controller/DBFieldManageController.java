package com.navinfo.core.controller;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.core.entity.DBFieldManage;
import com.navinfo.core.service.DBFieldManageService;
import com.navinfo.core.so.DBFieldManageSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 数据库字段映射Controller
 * Created by luozhihui on 2018/3/10.
 */
@RestController
@RequestMapping("/DBFieldManage")
public class DBFieldManageController {
    @Autowired
    private DBFieldManageService dbFieldManageService;

    /**
     * 查询
     * @param search
     * @param pageObject
     * @return
     */
    @RequestMapping(value = "/search",method=RequestMethod.GET)
    public Msg search(DBFieldManageSearch search, PageObject pageObject){
        PageInfo pageInfo=this.dbFieldManageService.findDBFieldManageByPage(search,pageObject);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 根据条件查询出所有数据
     * @param search
     * @return
     */
    @RequestMapping("/loadAll")
    public Msg loadAll(DBFieldManageSearch search){
        List<DBFieldManage> dbFieldManageList = this.dbFieldManageService.findDBFieldManage(search);
        return Msg.success().add("entities",dbFieldManageList);
    }
    /**
     * 新增
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Msg add(DBFieldManage dbFieldManage){
        boolean flag = this.dbFieldManageService.saveDBFieldManage(dbFieldManage);
        return flag ? Msg.success() : Msg.failure();
    }

    /**
     * 编辑
     * @param dbFieldManage
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Msg edit(DBFieldManage dbFieldManage){
        boolean flag= this.dbFieldManageService.updateDBFieldManage(dbFieldManage);
        return flag ? Msg.success() : Msg.failure();
    }

    /**
     * 删除一个或多个对象
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public Msg delete(String ids){
        boolean flag = this.dbFieldManageService.deleteDBFieldManages(ids);
        return flag ? Msg.success() : Msg.failure();
    }
}
