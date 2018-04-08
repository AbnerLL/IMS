package com.navinfo.IMS.entity;

import java.math.BigDecimal;

/**
 * KPI月报
 * Created by luozhihui on 2018/4/8.
 */
public class KPIReport {
    private String year;
    private String month;
    private String workType;
    private BigDecimal auditNum;
    private BigDecimal errorNum;
    private String correctRate;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public BigDecimal getAuditNum() {
        return auditNum;
    }

    public void setAuditNum(BigDecimal auditNum) {
        this.auditNum = auditNum;
    }

    public BigDecimal getErrorNum() {
        return errorNum;
    }

    public void setErrorNum(BigDecimal errorNum) {
        this.errorNum = errorNum;
    }

    public String getCorrectRate() {
        return correctRate;
    }

    public void setCorrectRate(String correctRate) {
        this.correctRate = correctRate;
    }
}
