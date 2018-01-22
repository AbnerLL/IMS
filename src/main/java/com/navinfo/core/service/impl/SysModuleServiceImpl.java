package com.navinfo.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.core.dao.SysModuleMapper;
import com.navinfo.core.entity.SysModule;
import com.navinfo.core.entity.SysModuleExample;
import com.navinfo.core.service.SysModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by luozhihui on 2018/1/21.
 */
@Service("sysModuleService")
public class SysModuleServiceImpl implements SysModuleService {

    @Autowired
    private SysModuleMapper sysModuleMapper;

    /**
     * 分页查询
     * @param map
     * @return
     */
    public PageInfo findSysModuleByPage(Map map){
        Integer pageNum=Integer.valueOf((String) map.get("pageNum"));
        Integer pageSize=Integer.valueOf((String) map.get("pageSize"));
//        String sort=(String) map.get("sort");
//        String order=(String) map.get("order");
        String keyword=(String) map.get("keyword");
        SysModuleExample example=new SysModuleExample();
        if(keyword!=null&&!"".equals(keyword)){
            example.createCriteria().andModuleNameLike("%"+keyword+"%");
            example.or().andModulePidLike("%"+keyword+"%");
        }
//        if(sort!=null&&!"".equals(sort)){
//            example.setOrderByClause(sort+" "+order);
//        }
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo(this.sysModuleMapper.selectByExample(example));
    }

    /**
     * 根据主键查询一个或多个对象
     * @param id
     * @return
     */
    public List<SysModule> findSysModuleById(String id){
        List idList= Arrays.asList(id.split(","));
        SysModuleExample example=new SysModuleExample();
        example.createCriteria().andModuleIdIn(idList);
        return this.sysModuleMapper.selectByExample(example);
    }

    /**
     * 保存
     * @param sysModule
     * @return
     */
    public boolean saveSysModule(SysModule sysModule){
        return this.sysModuleMapper.insertSelective(sysModule)!=0;
    }

    /**
     * 根据主键更新对象
     * @param sysModule
     * @return
     */
    public boolean updateSysModule(SysModule sysModule){
        return this.sysModuleMapper.updateByPrimaryKeySelective(sysModule)!=0;
    }

    /**
     * 删除一个或多个对象
     * @param ids
     * @return
     */
    public boolean deleteSysModule(String ids){
        List idList= Arrays.asList(ids.split(","));
        SysModuleExample example=new SysModuleExample();
        example.createCriteria().andModuleIdIn(idList);
        return this.sysModuleMapper.deleteByExample(example)!=0;
    }
}
