package com.navinfo.core.service;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.core.entity.SysLog;
import com.navinfo.core.so.SysLogSearch;

/**
 * Created by luozhihui on 2018/2/24.
 */
public interface SysLogService {
    PageInfo findSysLogByPage(SysLogSearch search, PageObject pageObject);

    void insertSysLog(SysLog sysLog);
}
