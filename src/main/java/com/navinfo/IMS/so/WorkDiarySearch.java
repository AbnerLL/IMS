package com.navinfo.IMS.so;

import com.navinfo.IMS.entity.WorkDiary;

import java.util.Date;

/**
 * 用来接收页面的查询参数
 * Created by luozhihui on 2018/1/30.
 */
public class WorkDiarySearch extends WorkDiary{
    private String keyword;//关键词
    private Date workDateStart;//工作日期（起）
    private Date workDateEnd;//工作日期（止）

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getWorkDateStart() {
        return workDateStart;
    }

    public void setWorkDateStart(Date workDateStart) {
        this.workDateStart = workDateStart;
    }

    public Date getWorkDateEnd() {
        return workDateEnd;
    }

    public void setWorkDateEnd(Date workDateEnd) {
        this.workDateEnd = workDateEnd;
    }
}
