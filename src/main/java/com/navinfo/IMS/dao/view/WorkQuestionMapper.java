package com.navinfo.IMS.dao.view;

import com.navinfo.IMS.dto.view.PoiQuestionView;
import com.navinfo.IMS.dto.view.RoadQuestionView;
import com.navinfo.IMS.so.PoiQuestionSearch;
import com.navinfo.IMS.so.RoadQuestionSearch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by luozhihui on 2018/2/27.
 */
public interface WorkQuestionMapper {

    List<RoadQuestionView> selectRoadQuestion(@Param("search") RoadQuestionSearch search);

    List<PoiQuestionView> selectPoiQuestion(@Param("search") PoiQuestionSearch search);
}
