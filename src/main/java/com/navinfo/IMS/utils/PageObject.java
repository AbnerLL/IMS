package com.navinfo.IMS.utils;

/**
 * 用于接收页面的分页信息
 * Created by luozhihui on 2018/1/30.
 */
public class PageObject {
    private Integer pageNum=new Integer(1);
    private Integer pageSize=new Integer(10);

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
