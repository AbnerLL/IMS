package com.navinfo.IMS.dto.view;

import java.math.BigDecimal;

/**
 * 设施问题
 * Created by luozhihui on 2018/2/28.
 */
public class PoiQuestionView {
    private String version;
    private String section;
    private String workerId;
    private String worker;
    private String workType;
    private String errorType;
    private BigDecimal errorNum;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
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

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
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
}
