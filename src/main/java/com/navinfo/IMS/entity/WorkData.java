package com.navinfo.IMS.entity;

import java.math.BigDecimal;
import java.util.Date;

public class WorkData {
    private String workDataId;

    private String version;

    private String empId;

    private String empName;

    private String workType;

    private BigDecimal quantity;

    private BigDecimal quality;

    private BigDecimal qError;

    private BigDecimal qRate;

    private BigDecimal monitor;

    private BigDecimal mError;

    private BigDecimal mRate;

    private BigDecimal majorError;

    private BigDecimal efficiency;

    private BigDecimal qEfficiency;

    private Date createTime;

    private String creatorId;

    private String creator;

    private Date updateTime;

    private String updaterId;

    private String updater;

    private String status;

    private String attr1;

    private String attr2;

    private String attr3;

    private String attr4;

    private String attr5;

    public String getWorkDataId() {
        return workDataId;
    }

    public void setWorkDataId(String workDataId) {
        this.workDataId = workDataId == null ? null : workDataId.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId == null ? null : empId.trim();
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType == null ? null : workType.trim();
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getQuality() {
        return quality;
    }

    public void setQuality(BigDecimal quality) {
        this.quality = quality;
    }

    public BigDecimal getqError() {
        return qError;
    }

    public void setqError(BigDecimal qError) {
        this.qError = qError;
    }

    public BigDecimal getqRate() {
        return qRate;
    }

    public void setqRate(BigDecimal qRate) {
        this.qRate = qRate;
    }

    public BigDecimal getMonitor() {
        return monitor;
    }

    public void setMonitor(BigDecimal monitor) {
        this.monitor = monitor;
    }

    public BigDecimal getmError() {
        return mError;
    }

    public void setmError(BigDecimal mError) {
        this.mError = mError;
    }

    public BigDecimal getmRate() {
        return mRate;
    }

    public void setmRate(BigDecimal mRate) {
        this.mRate = mRate;
    }

    public BigDecimal getMajorError() {
        return majorError;
    }

    public void setMajorError(BigDecimal majorError) {
        this.majorError = majorError;
    }

    public BigDecimal getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(BigDecimal efficiency) {
        this.efficiency = efficiency;
    }

    public BigDecimal getqEfficiency() {
        return qEfficiency;
    }

    public void setqEfficiency(BigDecimal qEfficiency) {
        this.qEfficiency = qEfficiency;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1 == null ? null : attr1.trim();
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2 == null ? null : attr2.trim();
    }

    public String getAttr3() {
        return attr3;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3 == null ? null : attr3.trim();
    }

    public String getAttr4() {
        return attr4;
    }

    public void setAttr4(String attr4) {
        this.attr4 = attr4 == null ? null : attr4.trim();
    }

    public String getAttr5() {
        return attr5;
    }

    public void setAttr5(String attr5) {
        this.attr5 = attr5 == null ? null : attr5.trim();
    }
}