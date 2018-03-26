package com.navinfo.core.controller;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.core.entity.SysDictionary;
import com.navinfo.core.service.SysDictionaryService;
import com.navinfo.core.so.SysDictionarySearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 字典对象的Controller
 * Created by luozhihui on 2018/3/25.
 */
@RestController
@RequestMapping("/dictionary")
public class SysDictionaryController {
    @Autowired
    private SysDictionaryService sysDictionaryService;

    /**
     * 分页查询
     * @param search
     * @param pageObject
     * @return
     */
    @RequestMapping("/search")
    public Msg search(SysDictionarySearch search, PageObject pageObject){
        PageInfo pageInfo = this.sysDictionaryService.findSysDictionaryByPage(search,pageObject);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 根据条件加载所有对象
     * @param search
     * @return
     */
    @RequestMapping("/loadAll")
    public Msg loadAll(SysDictionarySearch search){
        List<SysDictionary> sysDictionaryList = this.sysDictionaryService.findSysDictionary(search);
        return Msg.success().add("entities",sysDictionaryList);
    }
    /**
     * 新增
     * @param sysDictionary
     * @return
     */
    @RequestMapping("/add")
    public Msg add(SysDictionary sysDictionary){
        boolean flag = this.sysDictionaryService.saveSysDict(sysDictionary);
        return flag ? Msg.success() : Msg.failure();
    }

    /**
     * 编辑
     * @param sysDictionary
     * @return
     */
    @RequestMapping("/update")
    public Msg edit(SysDictionary sysDictionary){
        boolean flag = this.sysDictionaryService.updateSysDict(sysDictionary);
        return flag ? Msg.success() : Msg.failure();
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Msg delete(String ids){
        boolean flag = this.sysDictionaryService.deleteSysDict(ids);
        return flag ? Msg.success() : Msg.failure();
    }
}
