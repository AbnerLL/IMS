package com.navinfo.IMS.service.view;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.view.PoiQuestionView;
import com.navinfo.IMS.dto.view.RoadQuestionView;
import com.navinfo.IMS.so.PoiQuestionSearch;
import com.navinfo.IMS.so.RoadQuestionSearch;
import com.navinfo.IMS.utils.PageObject;

import java.util.List;

/**
 * Created by luozhihui on 2018/2/28.
 */
public interface WorkQuestionService {
    PageInfo findRoadQuestionByPage(RoadQuestionSearch search, PageObject pageObject);

    PageInfo findPoiQuestionByPage(PoiQuestionSearch search, PageObject pageObject);

    List<RoadQuestionView> findRoadQuestion(RoadQuestionSearch search);

    List<PoiQuestionView> findPoiQuestion(PoiQuestionSearch search);
}
