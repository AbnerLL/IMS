package com.navinfo.IMS.entity;

import java.math.BigDecimal;
import java.util.Date;

public class PoiReport {
    private String id;

    private String version;

    private String workerId;

    private String worker;

    private String section;

    private Date workDate;

    private BigDecimal cpAuditNum;

    private BigDecimal cpErrorNum;

    private BigDecimal cpPassRate;

    private BigDecimal caAuditNum;

    private BigDecimal caErrorNum;

    private BigDecimal caPassRate;

    private BigDecimal epAuditNum;

    private BigDecimal epErrorNum;

    private BigDecimal epPassRate;

    private BigDecimal eaAuditNum;

    private BigDecimal eaErrorNum;

    private BigDecimal eaPassNum;

    private String remark;

    private String status;

    private String delFlag;

    private String creatorId;

    private String creator;

    private Date createTime;

    private String updaterId;

    private String updater;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId == null ? null : workerId.trim();
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker == null ? null : worker.trim();
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section == null ? null : section.trim();
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
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

    public BigDecimal getCpPassRate() {
        return cpPassRate;
    }

    public void setCpPassRate(BigDecimal cpPassRate) {
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

    public BigDecimal getCaPassRate() {
        return caPassRate;
    }

    public void setCaPassRate(BigDecimal caPassRate) {
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

    public BigDecimal getEpPassRate() {
        return epPassRate;
    }

    public void setEpPassRate(BigDecimal epPassRate) {
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

    public BigDecimal getEaPassNum() {
        return eaPassNum;
    }

    public void setEaPassNum(BigDecimal eaPassNum) {
        this.eaPassNum = eaPassNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId == null ? null : updaterId.trim();
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}