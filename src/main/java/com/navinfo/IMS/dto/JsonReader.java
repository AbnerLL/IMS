package com.navinfo.IMS.dto;

import java.util.List;

/**
 * 用来向页面的jqGrid控件传递参数
 * Created by luozhihui on 2017/10/1.
 */
public class JsonReader {
    private List invdata;//数据集合

    private long currpage;//当前页数

    private long totalpages;//总页数

    private long totalrecodes;//总记录数

    private String id;//行ID

    public List getInvdata() {
        return invdata;
    }

    public void setInvdata(List invdata) {
        this.invdata = invdata;
    }

    public long getCurrpage() {
        return currpage;
    }

    public void setCurrpage(long currpage) {
        this.currpage = currpage;
    }

    public long getTotalpages() {
        return totalpages;
    }

    public void setTotalpages(long totalpages) {
        this.totalpages = totalpages;
    }

    public long getTotalrecodes() {
        return totalrecodes;
    }

    public void setTotalrecodes(long totalrecodes) {
        this.totalrecodes = totalrecodes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
