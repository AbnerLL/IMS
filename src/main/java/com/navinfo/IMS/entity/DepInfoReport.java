package com.navinfo.IMS.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DepInfoReport {
    private String id;

    private String version;

    private String section;

    private String workerId;

    private String worker;

    private Date workDate;

    private String depInfo;

    private BigDecimal dcomAuditNum;

    private BigDecimal dcomErrorNum;

    private BigDecimal dcomPassRate;

    private BigDecimal dpAuditNum;

    private BigDecimal dpErrorNum;

    private BigDecimal dpPassRate;

    private BigDecimal dcarAuditNum;

    private BigDecimal dcarErrorNum;

    private BigDecimal dcarPassRate;

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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section == null ? null : section.trim();
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

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public String getDepInfo() {
        return depInfo;
    }

    public void setDepInfo(String depInfo) {
        this.depInfo = depInfo == null ? null : depInfo.trim();
    }

    public BigDecimal getDcomAuditNum() {
        return dcomAuditNum;
    }

    public void setDcomAuditNum(BigDecimal dcomAuditNum) {
        this.dcomAuditNum = dcomAuditNum;
    }

    public BigDecimal getDcomErrorNum() {
        return dcomErrorNum;
    }

    public void setDcomErrorNum(BigDecimal dcomErrorNum) {
        this.dcomErrorNum = dcomErrorNum;
    }

    public BigDecimal getDcomPassRate() {
        return dcomPassRate;
    }

    public void setDcomPassRate(BigDecimal dcomPassRate) {
        this.dcomPassRate = dcomPassRate;
    }

    public BigDecimal getDpAuditNum() {
        return dpAuditNum;
    }

    public void setDpAuditNum(BigDecimal dpAuditNum) {
        this.dpAuditNum = dpAuditNum;
    }

    public BigDecimal getDpErrorNum() {
        return dpErrorNum;
    }

    public void setDpErrorNum(BigDecimal dpErrorNum) {
        this.dpErrorNum = dpErrorNum;
    }

    public BigDecimal getDpPassRate() {
        return dpPassRate;
    }

    public void setDpPassRate(BigDecimal dpPassRate) {
        this.dpPassRate = dpPassRate;
    }

    public BigDecimal getDcarAuditNum() {
        return dcarAuditNum;
    }

    public void setDcarAuditNum(BigDecimal dcarAuditNum) {
        this.dcarAuditNum = dcarAuditNum;
    }

    public BigDecimal getDcarErrorNum() {
        return dcarErrorNum;
    }

    public void setDcarErrorNum(BigDecimal dcarErrorNum) {
        this.dcarErrorNum = dcarErrorNum;
    }

    public BigDecimal getDcarPassRate() {
        return dcarPassRate;
    }

    public void setDcarPassRate(BigDecimal dcarPassRate) {
        this.dcarPassRate = dcarPassRate;
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