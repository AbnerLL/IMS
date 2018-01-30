package com.navinfo.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.core.dao.ModulePermissionRelMapper;
import com.navinfo.core.dao.SysModuleMapper;
import com.navinfo.core.dao.SysPermissionMapper;
import com.navinfo.core.entity.*;
import com.navinfo.core.service.SysModuleService;
import com.navinfo.core.service.SysPermissionService;
import com.navinfo.core.vo.MenuVO;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by luozhihui on 2018/1/21.
 */
@Service("sysModuleService")
public class SysModuleServiceImpl implements SysModuleService {

    @Autowired
    private SysModuleMapper sysModuleMapper;
    @Autowired
    private ModulePermissionRelMapper modulePermissionRelMapper;
    @Autowired
    private SysPermissionMapper sysPermissionMapper;
    /**
     * 获取菜单模块
     * @return
     */
    public List<MenuVO> getModuleVOS(){
        //获取当前用户名
        String username=(String) SecurityUtils.getSubject().getPrincipal();
        //根据用户名获取父模块
        List<MenuVO> parentModuleVO=this.sysModuleMapper.getParentModuleVOByUser(username);
        //根据用户名获取子模块
        List<SysModule> childModule=this.sysModuleMapper.getModuleByUser(username);
        //将子模块装填进父模块的子属性中
        for(MenuVO moduleVO:parentModuleVO){
            for (SysModule sysModule:childModule){
                if(moduleVO.getModuleId().equals(sysModule.getModulePid())){
                    moduleVO.getChildren().add(sysModule);
                }
            }
        }
        return parentModuleVO;
    }
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
     * 注:如果新增模块为一级模块，则提前初始化模块权限表
     * @param sysModule
     * @return
     */
    public boolean saveSysModule(SysModule sysModule){
        //一级模块保存时,初始化其模块权限关联表
        if("0".equals(sysModule.getModulePid())){
            ModulePermissionRel modulePermissionRel=new ModulePermissionRel();
            modulePermissionRel.setRelId(UUID.randomUUID().toString());
            modulePermissionRel.setModuleId(sysModule.getModuleId());
            modulePermissionRel.setPermissionId("0");
            this.modulePermissionRelMapper.insertSelective(modulePermissionRel);
        }
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
     * 注：删除模块时，应该同时删掉其关联数据和关联的权限
     * @param ids
     * @return
     */
    public boolean deleteSysModule(String ids){
        List idList= Arrays.asList(ids.split(","));
        SysModuleExample example=new SysModuleExample();
        example.createCriteria().andModuleIdIn(idList);
        this.sysModuleMapper.deleteByExample(example);
        //删除关联表数据
        ModulePermissionRelExample modulePermissionRelExample=new ModulePermissionRelExample();
        modulePermissionRelExample.createCriteria().andModuleIdIn(idList);
        this.modulePermissionRelMapper.deleteByExample(modulePermissionRelExample);
        //删除关联的权限
        SysPermissionExample sysPermissionExample=new SysPermissionExample();
        sysPermissionExample.createCriteria().andModuleIdIn(idList);
        this.sysPermissionMapper.deleteByExample(sysPermissionExample);
        return true;
    }
}
