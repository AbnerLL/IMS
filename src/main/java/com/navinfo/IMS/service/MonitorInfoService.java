package com.navinfo.IMS.service;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.entity.MonitorInfo;
import com.navinfo.IMS.so.MonitorInfoSearch;
import com.navinfo.IMS.utils.PageObject;

import java.util.List;

/**
 * Created by luozhihui on 2017/11/26.
 */
public interface MonitorInfoService {
    PageInfo findMonitorInfosByPage(MonitorInfoSearch search, PageObject pageObject);

    boolean saveMonitorInfo(MonitorInfo monitorInfo);

    List<MonitorInfo> findMonitorInfoById(String id);

    boolean updateMonitorInfoById(MonitorInfo monitorInfo);

    boolean deleteMonitorInfoById(String id);

    List<MonitorInfo> findMonitorInfosBySearch(MonitorInfoSearch search);
}
