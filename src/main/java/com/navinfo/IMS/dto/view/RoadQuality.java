package com.navinfo.IMS.dto.view;

import java.math.BigDecimal;
/**
 * 道路品质
 * Created by luozhihui on 2018/2/27.
 */
public class RoadQuality {
    private String version;
    private String section;
    private String workerId;
    private String worker;
    private String workType;
    private String auditorId;
    private String auditor;
    private BigDecimal workTotalNum;
    private BigDecimal auditTotalNum;
    private BigDecimal errorTotalNum;
    private BigDecimal questionNum;
    private String passRate;
    private BigDecimal bigErrorNum;


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

    public String getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(String auditorId) {
        this.auditorId = auditorId;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public BigDecimal getWorkTotalNum() {
        return workTotalNum;
    }

    public void setWorkTotalNum(BigDecimal workTotalNum) {
        this.workTotalNum = workTotalNum;
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

    public BigDecimal getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(BigDecimal questionNum) {
        this.questionNum = questionNum;
    }

    public String getPassRate() {
        return passRate;
    }

    public void setPassRate(String passRate) {
        this.passRate = passRate;
    }

    public BigDecimal getBigErrorNum() {
        return bigErrorNum;
    }

    public void setBigErrorNum(BigDecimal bigErrorNum) {
        this.bigErrorNum = bigErrorNum;
    }
    public String getWorkType() {
        return workType;
    }
    public void setWorkType(String workType) {
        this.workType = workType;
    }
}
