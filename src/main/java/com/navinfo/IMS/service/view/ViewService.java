package com.navinfo.IMS.service.view;

import com.navinfo.IMS.entity.KPIReport;
import com.navinfo.IMS.entity.QuestionReport;
import com.navinfo.IMS.entity.WeekReport;
import com.navinfo.IMS.utils.PageObject;

import java.util.List;
import java.util.Map;

/**
 * Created by luozhihui on 2017/12/3.
 */
public interface ViewService {
    List findWorkEfficiency(Map map);
    /**
     * 查询周报数据
     * @param weekReport
     * @return
     */
    List<WeekReport> findWeekReport(WeekReport weekReport );

    /**
     * 查询问题周报
     * @param questionReport
     * @return
     */
    List<QuestionReport> findQuestionReport(QuestionReport questionReport);

    /**
     * kpi周报查询
     * @param kpiReport
     * @return
     */
    List findKPIReport(KPIReport kpiReport);
}
