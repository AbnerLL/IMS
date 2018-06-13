package com.navinfo.IMS.entity;

import java.math.BigDecimal;

/**
 * 周报实体类
 * Created by luozhihui on 2018/4/6.
 */
public class WeekReport {
    private String workType;
    private String year;
    private String week;
    private String workerId;
    private String worker;
    private String section;
    private BigDecimal auditNum;
    private BigDecimal errorNum;
    private String correctRate;

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
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

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
