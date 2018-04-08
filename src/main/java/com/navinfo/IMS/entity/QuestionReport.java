package com.navinfo.IMS.entity;

import java.math.BigDecimal;

/**
 * 问题报表
 * Created by luozhihui on 2018/4/8.
 */
public class QuestionReport {
    private String workType;
    private String year;
    private String errorType;
    private BigDecimal errorNum;
    private String errorRate;

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

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public BigDecimal getErrorNum() {
        return errorNum;
    }

    public void setErrorNum(BigDecimal errorNum) {
        this.errorNum = errorNum;
    }

    public String getErrorRate() {
        return errorRate;
    }

    public void setErrorRate(String errorRate) {
        this.errorRate = errorRate;
    }
}
