package com.navinfo.IMS.service;

import com.navinfo.IMS.entity.PreGrade;

import java.util.List;

/**
 * Created by luozhihui on 2017/9/21.
 */
public interface PreGradeService {

    public List findPreGrades();

    public boolean insertPreGrade(PreGrade preGrade);

    public PreGrade getPreGradeById(String id);

    public boolean updatePreGrade(PreGrade preGrade);

    public boolean deletePreGradeById(String ids);
}
