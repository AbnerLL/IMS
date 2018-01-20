package com.navinfo.IMS.service;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.entity.WorkDiary;

import java.util.List;
import java.util.Map;

/**
 * Created by luozhihui on 2018/1/16.
 */
public interface WorkDiaryService {
    PageInfo findWorkDiaryByPage(Map map);

    List<WorkDiary> findWorkDiaryById(String id);

    boolean saveWorkDiary(WorkDiary workDiary);

    boolean updateWorkDiary(WorkDiary workDiary);

    boolean deleteWorkDiary(String ids);
}
