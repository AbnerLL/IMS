package com.navinfo.IMS.service.impl;

import com.navinfo.IMS.dao.DepInfoReportMapper;
import com.navinfo.IMS.service.DepInfoReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luozhihui on 2017/11/26.
 */
@Service("depInfoReportService")
public class DepInfoReportServiceImpl implements DepInfoReportService {
    @Autowired
    private DepInfoReportMapper depInfoReportMapper;

    public List findDepInfoReportAll(){
        return depInfoReportMapper.selectByExample(null);
    }
}
