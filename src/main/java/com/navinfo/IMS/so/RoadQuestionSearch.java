package com.navinfo.IMS.so;

import com.navinfo.IMS.dto.view.RoadQuestionView;
import com.navinfo.IMS.entity.RoadReport;

import java.util.Date;

/**
 * 用于接收页面的查询参数
 * Created by luozhihui on 2018/2/27.
 */
public class RoadQuestionSearch extends RoadQuestionView {
    private String keyword;
    private Date auditDateStart;
    private Date auditDateEnd;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getAuditDateStart() {
        return auditDateStart;
    }

    public void setAuditDateStart(Date auditDateStart) {
        this.auditDateStart = auditDateStart;
    }

    public Date getAuditDateEnd() {
        return auditDateEnd;
    }

    public void setAuditDateEnd(Date auditDateEnd) {
        this.auditDateEnd = auditDateEnd;
    }
}
