package com.navinfo.IMS.so;

import com.navinfo.IMS.entity.Emp;

import java.util.Date;

/**
 * 用来接收页面的查询参数
 * Created by luozhihui on 2018/1/30.
 */
public class EmpSearch extends Emp {
    private String keyword;
    private Date empHiredateStart;
    private Date empHiredateEnd;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getEmpHiredateStart() {
        return empHiredateStart;
    }

    public void setEmpHiredateStart(Date empHiredateStart) {
        this.empHiredateStart = empHiredateStart;
    }

    public Date getEmpHiredateEnd() {
        return empHiredateEnd;
    }

    public void setEmpHiredateEnd(Date empHiredateEnd) {
        this.empHiredateEnd = empHiredateEnd;
    }
}
