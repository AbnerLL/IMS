package com.navinfo.core.so;

import com.navinfo.core.entity.SysLog;

/**
 * 系统日志的查询类
 * Created by luozhihui on 2018/2/24.
 */
public class SysLogSearch extends SysLog{
    private String keyword;//关键词

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
