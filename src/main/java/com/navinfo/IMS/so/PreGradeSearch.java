package com.navinfo.IMS.so;

import com.navinfo.IMS.entity.PreGrade;

import java.math.BigDecimal;

/**
 * 用来获取页面的查询参数
 * Created by luozhihui on 2018/2/4.
 */
public class PreGradeSearch extends PreGrade{
    private String keyword;
    private BigDecimal gradeStart;
    private BigDecimal gradeEnd;
    private BigDecimal secGradeStart;
    private BigDecimal secGradeEnd;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public BigDecimal getGradeStart() {
        return gradeStart;
    }

    public void setGradeStart(BigDecimal gradeStart) {
        this.gradeStart = gradeStart;
    }

    public BigDecimal getGradeEnd() {
        return gradeEnd;
    }

    public void setGradeEnd(BigDecimal gradeEnd) {
        this.gradeEnd = gradeEnd;
    }

    public BigDecimal getSecGradeStart() {
        return secGradeStart;
    }

    public void setSecGradeStart(BigDecimal secGradeStart) {
        this.secGradeStart = secGradeStart;
    }

    public BigDecimal getSecGradeEnd() {
        return secGradeEnd;
    }

    public void setSecGradeEnd(BigDecimal secGradeEnd) {
        this.secGradeEnd = secGradeEnd;
    }
}
