package com.navinfo.IMS.service;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.entity.EmpResume;
import com.navinfo.IMS.so.EmpResumeSearch;
import com.navinfo.IMS.utils.PageObject;

import java.util.List;
import java.util.Map;

/**
 * Created by luozhihui on 2017/12/21.
 */
public interface EmpResumeService {
    PageInfo findEmpResumeByPage(EmpResumeSearch search, PageObject pageObject);
    /**
     * 根据条件进行不分页查询
     * @param resumeSearch
     * @return
     */
    List<EmpResume> findEmpResumeBySearch(EmpResumeSearch resumeSearch);

    List<EmpResume> findWorkDiaryById(String id);

    boolean saveEmpResume(EmpResume empResume);

    boolean updateEmpResume(EmpResume empResume);

    boolean deleteEmpResume(String ids);

}
