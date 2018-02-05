package com.navinfo.IMS.so;

import com.navinfo.IMS.entity.TestGrade;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用来接收页面查询参数
 * Created by luozhihui on 2018/2/4.
 */
public class TestGradeSearch extends TestGrade {
    private String keyword;
    private Date testDateStart;
    private Date testDateEnd;
    private BigDecimal paperGradeStart;
    private BigDecimal paperGradeEnd;
    private BigDecimal comGradeRoadStart;
    private BigDecimal comGradeRoadEnd;
    private BigDecimal comGradePoiStart;
    private BigDecimal comGradePoiEnd;
    private BigDecimal totalGradeStart;
    private BigDecimal totalGradeEnd;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getTestDateStart() {
        return testDateStart;
    }

    public void setTestDateStart(Date testDateStart) {
        this.testDateStart = testDateStart;
    }

    public Date getTestDateEnd() {
        return testDateEnd;
    }

    public void setTestDateEnd(Date testDateEnd) {
        this.testDateEnd = testDateEnd;
    }

    public BigDecimal getPaperGradeStart() {
        return paperGradeStart;
    }

    public void setPaperGradeStart(BigDecimal paperGradeStart) {
        this.paperGradeStart = paperGradeStart;
    }

    public BigDecimal getPaperGradeEnd() {
        return paperGradeEnd;
    }

    public void setPaperGradeEnd(BigDecimal paperGradeEnd) {
        this.paperGradeEnd = paperGradeEnd;
    }

    public BigDecimal getComGradeRoadStart() {
        return comGradeRoadStart;
    }

    public void setComGradeRoadStart(BigDecimal comGradeRoadStart) {
        this.comGradeRoadStart = comGradeRoadStart;
    }

    public BigDecimal getComGradeRoadEnd() {
        return comGradeRoadEnd;
    }

    public void setComGradeRoadEnd(BigDecimal comGradeRoadEnd) {
        this.comGradeRoadEnd = comGradeRoadEnd;
    }

    public BigDecimal getComGradePoiStart() {
        return comGradePoiStart;
    }

    public void setComGradePoiStart(BigDecimal comGradePoiStart) {
        this.comGradePoiStart = comGradePoiStart;
    }

    public BigDecimal getComGradePoiEnd() {
        return comGradePoiEnd;
    }

    public void setComGradePoiEnd(BigDecimal comGradePoiEnd) {
        this.comGradePoiEnd = comGradePoiEnd;
    }

    public BigDecimal getTotalGradeStart() {
        return totalGradeStart;
    }

    public void setTotalGradeStart(BigDecimal totalGradeStart) {
        this.totalGradeStart = totalGradeStart;
    }

    public BigDecimal getTotalGradeEnd() {
        return totalGradeEnd;
    }

    public void setTotalGradeEnd(BigDecimal totalGradeEnd) {
        this.totalGradeEnd = totalGradeEnd;
    }
}
