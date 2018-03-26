package com.navinfo.IMS.dto.view;

import java.math.BigDecimal;

/**
 * 深度信息
 * Created by luozhihui on 2018/2/28.
 */
public class DeepInfoQuality {
    private String version;
    private String section;
    private String workerId;
    private String worker;
    private BigDecimal comAuditNum;
    private BigDecimal comErrorNum;
    private String comRate;
    private BigDecimal parkAuditNum;
    private BigDecimal parkErrorNum;
    private String parkRate;
    private BigDecimal rentAuditNum;
    private BigDecimal rentErrorNum;
    private String rentRate;

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

    public BigDecimal getComAuditNum() {
        return comAuditNum;
    }

    public void setComAuditNum(BigDecimal comAuditNum) {
        this.comAuditNum = comAuditNum;
    }

    public BigDecimal getComErrorNum() {
        return comErrorNum;
    }

    public void setComErrorNum(BigDecimal comErrorNum) {
        this.comErrorNum = comErrorNum;
    }

    public String getComRate() {
        return comRate;
    }

    public void setComRate(String comRate) {
        this.comRate = comRate;
    }

    public BigDecimal getParkAuditNum() {
        return parkAuditNum;
    }

    public void setParkAuditNum(BigDecimal parkAuditNum) {
        this.parkAuditNum = parkAuditNum;
    }

    public BigDecimal getParkErrorNum() {
        return parkErrorNum;
    }

    public void setParkErrorNum(BigDecimal parkErrorNum) {
        this.parkErrorNum = parkErrorNum;
    }

    public String getParkRate() {
        return parkRate;
    }

    public void setParkRate(String parkRate) {
        this.parkRate = parkRate;
    }

    public BigDecimal getRentAuditNum() {
        return rentAuditNum;
    }

    public void setRentAuditNum(BigDecimal rentAuditNum) {
        this.rentAuditNum = rentAuditNum;
    }

    public BigDecimal getRentErrorNum() {
        return rentErrorNum;
    }

    public void setRentErrorNum(BigDecimal rentErrorNum) {
        this.rentErrorNum = rentErrorNum;
    }

    public String getRentRate() {
        return rentRate;
    }

    public void setRentRate(String rentRate) {
        this.rentRate = rentRate;
    }
}
