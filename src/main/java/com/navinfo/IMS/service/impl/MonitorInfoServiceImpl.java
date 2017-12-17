package com.navinfo.IMS.service.impl;

import com.navinfo.IMS.dao.MonitorInfoMapper;
import com.navinfo.IMS.service.MonitorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luozhihui on 2017/11/26.
 */
@Service("monitorInfoService")
public class MonitorInfoServiceImpl implements MonitorInfoService {
    @Autowired
    private MonitorInfoMapper monitorInfoMapper;

    public List findMonitorInfoAll(){
        return monitorInfoMapper.selectByExample(null);
    }
}
