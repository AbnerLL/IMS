package com.navinfo.IMS.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TestGrade {
    private String id;

    private String version;

    private String empId;

    private String empName;

    private BigDecimal paperGrade;

    private BigDecimal comGradeRoad;

    private BigDecimal comGradePoi;

    private BigDecimal totalGrade;

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

    public BigDecimal getPaperGrade() {
        return paperGrade;
    }

    public void setPaperGrade(BigDecimal paperGrade) {
        this.paperGrade = paperGrade;
    }

    public BigDecimal getComGradeRoad() {
        return comGradeRoad;
    }

    public void setComGradeRoad(BigDecimal comGradeRoad) {
        this.comGradeRoad = comGradeRoad;
    }

    public BigDecimal getComGradePoi() {
        return comGradePoi;
    }

    public void setComGradePoi(BigDecimal comGradePoi) {
        this.comGradePoi = comGradePoi;
    }

    public BigDecimal getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(BigDecimal totalGrade) {
        this.totalGrade = totalGrade;
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