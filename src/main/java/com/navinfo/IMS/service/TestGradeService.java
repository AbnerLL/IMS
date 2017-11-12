package com.navinfo.IMS.service;

import com.navinfo.IMS.entity.TestGrade;

import java.util.List;

/**
 * Created by luozhihui on 2017/9/21.
 */
public interface TestGradeService {

    public List findTestGrades();

    public TestGrade getTestGradeById(String id);

    public boolean insertTestGrade(TestGrade testGrade);

    public boolean updateTestGrade(TestGrade testGrade);

    public boolean deleteTestGradeById(String ids);
}
