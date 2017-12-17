package com.navinfo.IMS.service.impl;

import com.navinfo.IMS.dao.PoiQuestionMapper;
import com.navinfo.IMS.service.PoiQuestionService;
import com.navinfo.IMS.service.PoiReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luozhihui on 2017/11/26.
 */
@Service("poiQuestionService")
public class PoiQuestionServiceImpl implements PoiQuestionService {
    @Autowired
    private PoiQuestionMapper poiQuestionMapper;

    public List findPoiQuestionAll(){
        return poiQuestionMapper.selectByExample(null);
    }
}
