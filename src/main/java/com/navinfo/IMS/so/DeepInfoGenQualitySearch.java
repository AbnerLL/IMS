package com.navinfo.IMS.so;

import com.navinfo.IMS.dto.view.DeepInfoGenQuality;

import java.util.Date;

/**
 * Created by luozhihui on 2018/2/28.
 */
public class DeepInfoGenQualitySearch extends DeepInfoGenQuality {
    private String keyword;
    private Date statDateStart;
    private Date statDateEnd;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getStatDateStart() {
        return statDateStart;
    }

    public void setStatDateStart(Date statDateStart) {
        this.statDateStart = statDateStart;
    }

    public Date getStatDateEnd() {
        return statDateEnd;
    }

    public void setStatDateEnd(Date statDateEnd) {
        this.statDateEnd = statDateEnd;
    }
}
