package com.navinfo.IMS.dto.view;

/**
 * 深度信息
 * Created by luozhihui on 2018/2/28.
 */
public class DeepInfoQuality {
    private String version;
    private String section;
    private String workerId;
    private String worker;
    private String comRate;
    private String parkRate;
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

    public String getComRate() {
        return comRate;
    }

    public void setComRate(String comRate) {
        this.comRate = comRate;
    }

    public String getParkRate() {
        return parkRate;
    }

    public void setParkRate(String parkRate) {
        this.parkRate = parkRate;
    }

    public String getRentRate() {
        return rentRate;
    }

    public void setRentRate(String rentRate) {
        this.rentRate = rentRate;
    }
}
