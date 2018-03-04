package com.navinfo.IMS.dao.view;

import com.navinfo.IMS.dto.view.*;
import com.navinfo.IMS.so.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 作业品质mapper接口
 * Created by luozhihui on 2018/2/27.
 */
public interface WorkQualityMapper {

    List<RoadQuality> selectRoadQuality(@Param("search") RoadQualitySearch search);

    List<PoiQuality> selectPoiQuality(@Param("search") PoiQualitySearch search);

    List<ChPoiQuality> selectChPoiQuality(@Param("search") ChPoiQualitySearch search);

    List<DeepInfoQuality> selectDeepInfoQuality(@Param("search") DeepInfoQualitySearch search);

    List<DeepInfoGenQuality> selectDeepInfoGenQuality(@Param("search") DeepInfoGenQualitySearch search);
}
