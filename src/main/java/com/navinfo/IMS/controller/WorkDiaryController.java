package com.navinfo.IMS.controller;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.entity.WorkDiary;
import com.navinfo.IMS.service.WorkDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 工作日志的Controller
 * Created by luozhihui on 2017/9/21.
 */
@Controller
public class WorkDiaryController {
    @Autowired
    private WorkDiaryService workDiaryService;

    /**
     * 查询
     * @return
     */
    @RequestMapping(value="/workDiaries",method = RequestMethod.GET)
    @ResponseBody
    public Msg search(@RequestParam Map map){
        PageInfo pageInfo=workDiaryService.findWorkDiaryByPage(map);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 展示workDiary对象
     * @param id
     * @return
     */
    @RequestMapping(value="/workDiary/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg showWorkDiary(@PathVariable String id){
        List<WorkDiary> workDiaries=workDiaryService.findWorkDiaryById(id);
        return Msg.success().add("workDiaries",workDiaries);
    }

    /**
     * 新增对象
     * @param workDiary
     * @return
     */
    @RequestMapping(value="/workDiary", method = RequestMethod.POST)
    @ResponseBody
    public Msg addWorkDiary(WorkDiary workDiary){
        boolean flag=workDiaryService.saveWorkDiary(workDiary);
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
    }

    /**
     * 更新对象
     * @return
     */
    @RequestMapping(value="/workDiary/{id}",method=RequestMethod.PUT)
    @ResponseBody
    public Msg editWorkDiary(WorkDiary workDiary){
        boolean flag=workDiaryService.updateWorkDiary(workDiary);
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
    }

    /**
     * 根据id删除一个或多个对象
     * @return
     */
    @RequestMapping(value="/workDiary/{ids}",method=RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteWorkDiary(@PathVariable String ids){
        boolean flag=workDiaryService.deleteWorkDiary(ids);
        if(flag){
            return Msg.success();
        }else{
            return Msg.failure();
        }
    }
}
