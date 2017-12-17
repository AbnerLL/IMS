package com.navinfo.IMS.service.impl;

import com.navinfo.IMS.dao.RoadQuestionMapper;
import com.navinfo.IMS.service.RoadQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luozhihui on 2017/11/26.
 */
@Service("roadQuestionService")
public class RoadQuestionServiceImpl implements RoadQuestionService{
    @Autowired
    private RoadQuestionMapper roadQuestionMapper;

    public List findRoadQuestionAll() {
        return roadQuestionMapper.selectByExample(null);
    }
}
