package com.navinfo.IMS.controller;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.entity.Emp;
import com.navinfo.IMS.entity.FixedAsset;
import com.navinfo.IMS.service.FixedAssetService;
import com.navinfo.IMS.so.FixedAssetSearch;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 设备管理的Controller
 * Created by luozhihui on 2018/1/20.
 */
@Controller
public class FixedAssetController extends BaseController{
    @Autowired
    private FixedAssetService fixedAssetService;

    /**
     * 查询
     * @return
     */
    @RequestMapping(value="/fixedAssets",method= RequestMethod.GET)
    @ResponseBody
    public Msg search(FixedAssetSearch search, PageObject pageObject){
        PageInfo pageInfo=fixedAssetService.findFixedAssetByPage(search,pageObject);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 新增对象
     * @param fixedAsset
     * @return
     */
    @RequestMapping(value = "/fixedAsset",method=RequestMethod.POST)
    @ResponseBody
    public Msg addFixedAsset(FixedAsset fixedAsset){
        boolean flag=fixedAssetService.saveFixedAsset(fixedAsset);
        return flag ? Msg.success() : Msg.failure();
    }

    /**
     * 根据主键获取对象
     * @param id
     * @return
     */
    @RequestMapping(value="/fixedAsset/{id}",method=RequestMethod.GET)
    @ResponseBody
    public Msg showFixedAsset(@PathVariable String id){
        List<FixedAsset> fixedAssetList=fixedAssetService.findFixedAssetById(id);
        return Msg.success().add("entities",fixedAssetList);
    }

    /**
     * 修改对象
     * @param fixedAsset
     * @return
     */
    @RequestMapping(value="/fixedAsset/{uuid}",method=RequestMethod.PUT)
    @ResponseBody
    public Msg editFixedAsset(FixedAsset fixedAsset){
        boolean flag=fixedAssetService.updateFixedAsset(fixedAsset);
        return flag ? Msg.success() : Msg.failure();
    }

    /**
     * 删除一个或多个对象
     * @param ids
     * @return
     */
    @RequestMapping(value="/fixedAsset/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteFixedAsset(@PathVariable String ids){
        boolean flag=fixedAssetService.deleteFixedAsset(ids);
        return flag ? Msg.success():Msg.failure();
    }

    /**
     * 根据条件导出excel
     * @param search
     * @return
     */
    @RequestMapping(value = "fixedAssetExcel")
    public ModelAndView exportExcel(FixedAssetSearch search){
        ModelAndView modelAndView = new ModelAndView();
        Emp currentEmp = super.getCurrentEmp();
        if (!hasPermission("fixedAsset:export")){
            search.setAssetUser(currentEmp.getEmpName());
        }
        List<FixedAsset> fixedAssets = this.fixedAssetService.findFixedAssetBySearch(search);
        modelAndView.addObject("fixedAssetList",fixedAssets);
        modelAndView.setViewName("export/fixedAssetExcel");
        return modelAndView;
    }
}
