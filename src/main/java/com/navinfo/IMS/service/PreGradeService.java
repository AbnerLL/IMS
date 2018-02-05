package com.navinfo.IMS.service;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.entity.PreGrade;
import com.navinfo.IMS.so.PreGradeSearch;
import com.navinfo.IMS.utils.PageObject;

import java.util.List;

/**
 * Created by luozhihui on 2017/9/21.
 */
public interface PreGradeService {

    public boolean insertPreGrade(PreGrade preGrade);

    public PreGrade getPreGradeById(String id);

    public boolean updatePreGrade(PreGrade preGrade);

    public boolean deletePreGradeById(String ids);

    PageInfo findPreGradeByPage(PreGradeSearch search, PageObject pageObject);

    List<PreGrade> findTestGradeBySearch(PreGradeSearch search);
}
