package com.navinfo.IMS.service;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.entity.WorkDiary;
import com.navinfo.IMS.so.WorkDiarySearch;
import com.navinfo.IMS.utils.PageObject;

import java.util.List;
import java.util.Map;

/**
 * Created by luozhihui on 2018/1/16.
 */
public interface WorkDiaryService {
    PageInfo findWorkDiaryByPage(WorkDiarySearch workDiarySearch, PageObject pageObject);

    List<WorkDiary> findWorkDiaryById(String id);

    boolean saveWorkDiary(WorkDiary workDiary);

    boolean updateWorkDiary(WorkDiary workDiary);

    boolean deleteWorkDiary(String ids);
    /**
     * 根据查询条件获取对象
     * @param workDiarySearch
     * @return
     */
    List<WorkDiary> findWorkDiaryBySearch(WorkDiarySearch workDiarySearch);
}
