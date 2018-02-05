package com.navinfo.IMS.so;

import com.navinfo.IMS.entity.MonitorInfo;

import java.util.Date;

/**
 * 用于接收页面查询参数的类
 * Created by luozhihui on 2018/2/4.
 */
public class MonitorInfoSearch extends MonitorInfo {
    private String keyword;
    private Date monitorDateStart;
    private Date monitorDateEnd;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getMonitorDateStart() {
        return monitorDateStart;
    }

    public void setMonitorDateStart(Date monitorDateStart) {
        this.monitorDateStart = monitorDateStart;
    }

    public Date getMonitorDateEnd() {
        return monitorDateEnd;
    }

    public void setMonitorDateEnd(Date monitorDateEnd) {
        this.monitorDateEnd = monitorDateEnd;
    }
}
