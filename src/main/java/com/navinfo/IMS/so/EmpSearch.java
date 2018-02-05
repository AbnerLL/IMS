package com.navinfo.IMS.so;

import com.navinfo.IMS.entity.Emp;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用来接收页面的查询参数
 * Created by luozhihui on 2018/1/30.
 */
public class EmpSearch extends Emp {
    private String keyword;
    private BigDecimal empEntryAgeStart;
    private BigDecimal empEntryAgeEnd;
    private Date empHiredateStart;
    private Date empHiredateEnd;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public BigDecimal getEmpEntryAgeStart() {
        return empEntryAgeStart;
    }

    public void setEmpEntryAgeStart(BigDecimal empEntryAgeStart) {
        this.empEntryAgeStart = empEntryAgeStart;
    }

    public BigDecimal getEmpEntryAgeEnd() {
        return empEntryAgeEnd;
    }

    public void setEmpEntryAgeEnd(BigDecimal empEntryAgeEnd) {
        this.empEntryAgeEnd = empEntryAgeEnd;
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
