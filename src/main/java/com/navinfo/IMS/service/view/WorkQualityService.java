package com.navinfo.IMS.service.view;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.view.*;
import com.navinfo.IMS.so.*;
import com.navinfo.IMS.utils.PageObject;

import java.util.List;

/**
 * Created by luozhihui on 2018/2/27.
 */
public interface WorkQualityService {
    PageInfo findRoadQualityByPage(RoadQualitySearch search, PageObject pageObject);

    PageInfo findPoiQualityByPage(PoiQualitySearch search, PageObject pageObject);

    PageInfo findChPoiQualityByPage(ChPoiQualitySearch search, PageObject pageObject);

    PageInfo findDeepInfoQualityByPage(DeepInfoQualitySearch search, PageObject pageObject);

    PageInfo findDeepInfoGenQualityByPage(DeepInfoGenQualitySearch search, PageObject pageObject);

    List<RoadQuality> findRoadQuality(RoadQualitySearch search);

    List<PoiQuality> findPoiQuality(PoiQualitySearch search);

    List<ChPoiQuality> findChPoiQuality(ChPoiQualitySearch search);

    List<DeepInfoQuality> findDeepInfoQuality(DeepInfoQualitySearch search);

    List<DeepInfoGenQuality> findDeepInfoGenQuality(DeepInfoGenQualitySearch search);
}
