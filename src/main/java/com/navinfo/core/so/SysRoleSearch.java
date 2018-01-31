package com.navinfo.core.so;

import com.navinfo.core.entity.SysRole;

/**
 * 用来接收页面参数的查询类
 * Created by luozhihui on 2018/1/31.
 */
public class SysRoleSearch extends SysRole {
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
