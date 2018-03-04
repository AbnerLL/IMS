package com.navinfo.IMS.dto.view;

import java.math.BigDecimal;

/**
 * 设施品质
 * Created by luozhihui on 2018/2/28.
 */
public class PoiQuality {
    private String version;
    private String section;
    private String workerId;
    private String worker;
    private BigDecimal cpAuditNum;

    private BigDecimal cpErrorNum;

    private String cpPassRate;

    private BigDecimal caAuditNum;

    private BigDecimal caErrorNum;

    private String caPassRate;

    private BigDecimal epAuditNum;

    private BigDecimal epErrorNum;

    private String epPassRate;

    private BigDecimal eaAuditNum;

    private BigDecimal eaErrorNum;

    private String eaPassRate;

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

    public BigDecimal getCpAuditNum() {
        return cpAuditNum;
    }

    public void setCpAuditNum(BigDecimal cpAuditNum) {
        this.cpAuditNum = cpAuditNum;
    }

    public BigDecimal getCpErrorNum() {
        return cpErrorNum;
    }

    public void setCpErrorNum(BigDecimal cpErrorNum) {
        this.cpErrorNum = cpErrorNum;
    }

    public String getCpPassRate() {
        return cpPassRate;
    }

    public void setCpPassRate(String cpPassRate) {
        this.cpPassRate = cpPassRate;
    }

    public BigDecimal getCaAuditNum() {
        return caAuditNum;
    }

    public void setCaAuditNum(BigDecimal caAuditNum) {
        this.caAuditNum = caAuditNum;
    }

    public BigDecimal getCaErrorNum() {
        return caErrorNum;
    }

    public void setCaErrorNum(BigDecimal caErrorNum) {
        this.caErrorNum = caErrorNum;
    }

    public String getCaPassRate() {
        return caPassRate;
    }

    public void setCaPassRate(String caPassRate) {
        this.caPassRate = caPassRate;
    }

    public BigDecimal getEpAuditNum() {
        return epAuditNum;
    }

    public void setEpAuditNum(BigDecimal epAuditNum) {
        this.epAuditNum = epAuditNum;
    }

    public BigDecimal getEpErrorNum() {
        return epErrorNum;
    }

    public void setEpErrorNum(BigDecimal epErrorNum) {
        this.epErrorNum = epErrorNum;
    }

    public String getEpPassRate() {
        return epPassRate;
    }

    public void setEpPassRate(String epPassRate) {
        this.epPassRate = epPassRate;
    }

    public BigDecimal getEaAuditNum() {
        return eaAuditNum;
    }

    public void setEaAuditNum(BigDecimal eaAuditNum) {
        this.eaAuditNum = eaAuditNum;
    }

    public BigDecimal getEaErrorNum() {
        return eaErrorNum;
    }

    public void setEaErrorNum(BigDecimal eaErrorNum) {
        this.eaErrorNum = eaErrorNum;
    }

    public String getEaPassRate() {
        return eaPassRate;
    }

    public void setEaPassRate(String eaPassRate) {
        this.eaPassRate = eaPassRate;
    }
}
