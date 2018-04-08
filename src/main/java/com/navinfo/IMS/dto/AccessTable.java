package com.navinfo.IMS.dto;

import java.util.List;

/**
 * Created by luozhihui on 2018/3/28.
 */
public class AccessTable {
    private String tableName;
    private List<AccessColumn> accessColumnList;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<AccessColumn> getAccessColumnList() {
        return accessColumnList;
    }

    public void setAccessColumnList(List<AccessColumn> accessColumnList) {
        this.accessColumnList = accessColumnList;
    }
}
