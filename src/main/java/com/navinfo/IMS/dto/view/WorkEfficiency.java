package com.navinfo.IMS.dto.view;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 作业功效模型
 * Created by luozhihui on 2017/12/3.
 */
public class WorkEfficiency {
    private String empId;
    private String empName;
    /**
     * 作业类型：共7项：道路图标（RoadMark），中文名称（ChPOI），
     * 中文地址（ChAddress），英文名称（EnPOI），英文地址（EnAddress），
     * 深度信息（DepthInfo），代理店（Agency）
     */
    private String workType;
    private BigDecimal workNum=new BigDecimal(0);//作业数据1
    private BigDecimal workNum2=new BigDecimal(0);//作业数量2
    private BigDecimal hours=new BigDecimal(0);//作业时间
    private BigDecimal hours2=new BigDecimal(0);//作业时间2
    private BigDecimal workEfficiency=new BigDecimal(0);//作业工效
    private BigDecimal auditEfficiency=new BigDecimal(0);//质检工效
    private Date searchDateStart;//搜索时间（起）
    private Date searchDateEnd;//搜索时间（止）

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public BigDecimal getWorkNum() {
        return workNum;
    }

    public void setWorkNum(BigDecimal workNum) {
        this.workNum = workNum;
    }

    public BigDecimal getWorkNum2() {
        return workNum2;
    }

    public void setWorkNum2(BigDecimal workNum2) {
        this.workNum2 = workNum2;
    }

    public BigDecimal getHours() {
        return hours;
    }

    public BigDecimal getHours2() {
        return hours2;
    }

    public void setHours2(BigDecimal hours2) {
        this.hours2 = hours2;
    }

    public void setHours(BigDecimal hours) {
        this.hours = hours;
    }

    public BigDecimal getWorkEfficiency() {
        return workEfficiency;
    }

    public void setWorkEfficiency(BigDecimal workEfficiency) {
        this.workEfficiency = workEfficiency;
    }

    public BigDecimal getAuditEfficiency() {
        return auditEfficiency;
    }

    public void setAuditEfficiency(BigDecimal auditEfficiency) {
        this.auditEfficiency = auditEfficiency;
    }

    public Date getSearchDateStart() {
        return searchDateStart;
    }

    public void setSearchDateStart(Date searchDateStart) {
        this.searchDateStart = searchDateStart;
    }

    public Date getSearchDateEnd() {
        return searchDateEnd;
    }

    public void setSearchDateEnd(Date searchDateEnd) {
        this.searchDateEnd = searchDateEnd;
    }
}
