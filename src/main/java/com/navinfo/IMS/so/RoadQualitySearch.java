package com.navinfo.IMS.so;

import com.navinfo.IMS.dto.view.RoadQuality;

import java.util.Date;

/**
 * Created by luozhihui on 2018/2/27.
 */
public class RoadQualitySearch extends RoadQuality {

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
