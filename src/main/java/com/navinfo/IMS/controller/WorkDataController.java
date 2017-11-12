package com.navinfo.IMS.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.entity.WorkData;
import com.navinfo.IMS.service.WorkDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 作业信息的Controller
 * Created by luozhihui on 2017/9/21.
 */
@Controller
public class WorkDataController {
    @Autowired
    private WorkDataService workDataService;

    /**
     * 分页查询
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/workDatas",method = RequestMethod.GET)
    @ResponseBody
    public Msg findWorkDatas(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum){
        PageHelper.startPage(pageNum,pageSize);
        List workDatas=workDataService.findWorkDatas();
        PageInfo pageInfo=new PageInfo(workDatas);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 根据主键查询作业数据
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/workData/{workDataId}",method=RequestMethod.GET)
    public Msg findWorkDataById(@PathVariable("workDataId") String id){
        WorkData workData=workDataService.findWorkDataById(id);
        if(workData!=null){
            return Msg.success().add("workDatas",workData);
        }else{
            return Msg.failure();
        }
    }
    /**
     * 添加作业数据
     * @param workData
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/workData",method=RequestMethod.POST)
    public Msg addWorkData(WorkData workData){
        boolean flag=workDataService.insertWorkData(workData);
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
    }

    /**
     * 编辑作业数据
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/workData/{workDataId}",method=RequestMethod.PUT)
    public Msg editWorkData(WorkData workData){
        boolean flag=workDataService.updateWorkData(workData);
        if(flag){
            return Msg.success();
        }else{
            return  Msg.failure();
        }
    }

    /**
     * 删除作业数据
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/workData/{workDataId}",method=RequestMethod.DELETE)
    public Msg deleteWorkData(@PathVariable("workDataId") String id){
        boolean flag=workDataService.deleteWorkData(id);
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
    }
}
