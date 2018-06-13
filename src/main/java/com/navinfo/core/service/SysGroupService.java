package com.navinfo.core.service;

import com.navinfo.core.entity.SysGroup;
import com.navinfo.core.so.SysGroupSearch;

import java.util.List;

/**
 * Created by luozhihui on 2018/3/5.
 */
public interface SysGroupService{

    /**
     * 根据条件查询
     * @return
     */
    public List<SysGroup> findSysGroup(SysGroupSearch search);
}
