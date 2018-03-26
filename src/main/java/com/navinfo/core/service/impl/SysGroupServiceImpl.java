package com.navinfo.core.service.impl;

import com.navinfo.core.dao.SysGroupMapper;
import com.navinfo.core.service.SysGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by luozhihui on 2018/3/5.
 */
@Service("sysGroupService")
public class SysGroupServiceImpl implements SysGroupService {
    @Autowired
    private SysGroupMapper sysGroupMapper;
}
