package com.navinfo.IMS.dto;

import java.util.List;

/**
 * access文件的实体类
 * Created by luozhihui on 2018/3/28.
 */
public class AccessDB {
    private String dbName;
    private List<AccessTable> accessTableList;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public List<AccessTable> getAccessTableList() {
        return accessTableList;
    }

    public void setAccessTableList(List<AccessTable> accessTableList) {
        this.accessTableList = accessTableList;
    }
}
