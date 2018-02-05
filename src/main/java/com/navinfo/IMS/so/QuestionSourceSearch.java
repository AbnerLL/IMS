package com.navinfo.IMS.so;

import com.navinfo.IMS.entity.QuestionSource;

/**
 * 用于接收页面查询参数的类
 * Created by luozhihui on 2018/2/1.
 */
public class QuestionSourceSearch extends QuestionSource {
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
