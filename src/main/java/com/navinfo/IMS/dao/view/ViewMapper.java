package com.navinfo.IMS.dao.view;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by luozhihui on 2017/12/3.
 */
public interface ViewMapper {

    List calculateEfficiency(Map map);

    List selectWeekReports(@Param("workType") String workType, @Param("year") String year);

    List selectQuestionReport(@Param("workType") String workType , @Param("year") String year);

    List selectKPIReport(@Param("workType") String workType , @Param("year") String year);
}
