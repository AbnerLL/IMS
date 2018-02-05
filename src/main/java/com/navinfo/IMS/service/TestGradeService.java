package com.navinfo.IMS.service;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.entity.TestGrade;
import com.navinfo.IMS.so.TestGradeSearch;
import com.navinfo.IMS.utils.PageObject;

import java.util.List;

/**
 * Created by luozhihui on 2017/9/21.
 */
public interface TestGradeService {

    List<TestGrade> getTestGradeById(String id);

    public boolean insertTestGrade(TestGrade testGrade);

    public boolean updateTestGrade(TestGrade testGrade);

    public boolean deleteTestGradeById(String ids);

    PageInfo findTestGradeByPage(TestGradeSearch search, PageObject pageObject);

    List<TestGrade> findTestGradeBySearch(TestGradeSearch search);
}
