package com.navinfo.IMS.service.impl;

import com.navinfo.IMS.dao.DiaryInfoMapper;
import com.navinfo.IMS.service.DiaryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luozhihui on 2017/11/26.
 */
@Service("diaryInfoService")
public class DiaryInfoServiceImpl implements DiaryInfoService {
    @Autowired
    private DiaryInfoMapper diaryInfoMapper;

    public List findDiaryInfoAll(){
        return diaryInfoMapper.selectByExample(null);
    }
}
