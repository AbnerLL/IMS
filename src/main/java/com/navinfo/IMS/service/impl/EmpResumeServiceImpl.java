package com.navinfo.IMS.service.impl;

import com.navinfo.IMS.dao.EmpResumeMapper;
import com.navinfo.IMS.service.EmpResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luozhihui on 2017/12/21.
 */
@Service("empResumeService")
public class EmpResumeServiceImpl implements EmpResumeService {

    @Autowired
    private EmpResumeMapper empResumeMapper;

    public List findEmpResumeAll(){
        return empResumeMapper.selectByExample(null);
    }
}
