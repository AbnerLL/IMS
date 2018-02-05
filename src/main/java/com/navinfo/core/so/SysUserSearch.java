package com.navinfo.core.so;

import com.navinfo.core.entity.SysUser;

/**
 * 用来接收页面参数的查询类
 * Created by luozhihui on 2018/2/1.
 */
public class SysUserSearch extends SysUser{
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
