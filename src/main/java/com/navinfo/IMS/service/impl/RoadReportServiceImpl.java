package com.navinfo.IMS.service.impl;

import com.navinfo.IMS.dao.RoadReportMapper;
import com.navinfo.IMS.service.RoadReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luozhihui on 2017/11/26.
 */
@Service("roadReportService")
public class RoadReportServiceImpl implements RoadReportService {
    @Autowired
    private RoadReportMapper roadReportMapper;

    public List findRoadReportAll(){
        return roadReportMapper.selectByExample(null);
    }
}
