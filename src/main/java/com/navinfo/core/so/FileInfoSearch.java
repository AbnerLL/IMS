package com.navinfo.core.so;

import com.navinfo.core.entity.FileInfo;

/**
 * 用于封装查询条件的类
 * Created by luozhihui on 2018/2/25.
 */
public class FileInfoSearch extends FileInfo {
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
