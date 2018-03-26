package com.navinfo.IMS.dto.view;

import java.math.BigDecimal;

/**
 * 中文名称
 * Created by luozhihui on 2018/2/28.
 */
public class ChPoiQuality {
    private String version;
    private String section;
    private String workerId;
    private String worker;
    private String workType;
    private BigDecimal auditTotalNum;
    private BigDecimal errorTotalNum;
    private String passRate;

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

    public BigDecimal getAuditTotalNum() {
        return auditTotalNum;
    }

    public void setAuditTotalNum(BigDecimal auditTotalNum) {
        this.auditTotalNum = auditTotalNum;
    }

    public BigDecimal getErrorTotalNum() {
        return errorTotalNum;
    }

    public void setErrorTotalNum(BigDecimal errorTotalNum) {
        this.errorTotalNum = errorTotalNum;
    }

    public String getPassRate() {
        return passRate;
    }

    public void setPassRate(String passRate) {
        this.passRate = passRate;
    }
}
