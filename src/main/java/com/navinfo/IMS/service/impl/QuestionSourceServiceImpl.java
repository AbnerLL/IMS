package com.navinfo.IMS.service.impl;

import com.navinfo.IMS.dao.QuestionSourceMapper;
import com.navinfo.IMS.service.QuestionSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luozhihui on 2018/1/15.
 */
@Service("questionSourceService")
public class QuestionSourceServiceImpl implements QuestionSourceService {
    @Autowired
    private QuestionSourceMapper questionSourceMapper;

    public List findQuestionSourceAll(){
        return questionSourceMapper.selectByExample(null);
    }
}
