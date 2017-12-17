package com.navinfo.IMS.service.impl;

import com.navinfo.IMS.dao.PoiQuestionMapper;
import com.navinfo.IMS.dao.PoiReportMapper;
import com.navinfo.IMS.service.PoiQuestionService;
import com.navinfo.IMS.service.PoiReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luozhihui on 2017/11/26.
 */
@Service("poiReportService")
public class PoiReportServiceImpl implements PoiReportService{
    @Autowired
    private PoiReportMapper poiReportMapper;

    public List findPoiReportAll(){
        return poiReportMapper.selectByExample(null);
    }
}
