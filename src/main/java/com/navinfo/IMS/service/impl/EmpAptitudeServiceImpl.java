package com.navinfo.IMS.service.impl;

import com.navinfo.IMS.dao.EmpAptitudeMapper;
import com.navinfo.IMS.service.EmpAptitudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luozhihui on 2017/12/5.
 */
@Service("empAptitudeService")
public class EmpAptitudeServiceImpl implements EmpAptitudeService {
    @Autowired
    private EmpAptitudeMapper empAptitudeMapper;

    public List findAll(){
        return empAptitudeMapper.selectByExample(null);
    }
}
