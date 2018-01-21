package com.navinfo.IMS.service;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.entity.EmpResume;

import java.util.List;
import java.util.Map;

/**
 * Created by luozhihui on 2017/12/21.
 */
public interface EmpResumeService {
    PageInfo findEmpResumeByPage(Map map);

    List<EmpResume> findWorkDiaryById(String id);

    boolean saveEmpResume(EmpResume empResume);

    boolean updateEmpResume(EmpResume empResume);

    boolean deleteEmpResume(String ids);
}
