package com.navinfo.IMS.service.impl;

import com.navinfo.IMS.dao.ChartsMapper;
import com.navinfo.IMS.service.ChartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by luozhihui on 2017/12/17.
 */
@Service("chartsService")
public class ChartsServiceImpl implements ChartsService {
    @Autowired
    private ChartsMapper chartsMapper;

    public List findEmpAddressGroup(){
        return chartsMapper.findEmpAddressGroup();
    }

    public List findEmpGenderGroup(){
        return chartsMapper.findEmpGenderGroup();
    }

    public List findEmpSLingStat(){
        return chartsMapper.findEmpSLingStat();
    }

    public List findPoiQualityRate(){
        return chartsMapper.findPoiVersionTotalNum();
    }

    public List findRoadQualityRate(){
        return chartsMapper.findRoadVersionTotalNum();
    }
}
