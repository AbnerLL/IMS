package com.navinfo.core.service;

import com.github.pagehelper.PageInfo;
import com.navinfo.core.entity.SysModule;

import java.util.List;
import java.util.Map;

/**
 * Created by luozhihui on 2018/1/21.
 */
public interface SysModuleService {
    PageInfo findSysModuleByPage(Map map);

    boolean deleteSysModule(String ids);

    List<SysModule> findSysModuleById(String id);

    boolean saveSysModule(SysModule sysModule);

    boolean updateSysModule(SysModule sysModule);
}
