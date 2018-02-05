package com.navinfo.IMS.service;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.entity.EmpAptitude;
import com.navinfo.IMS.so.EmpAptitudeSearch;
import com.navinfo.IMS.utils.PageObject;

import java.util.List;

/**
 * Created by luozhihui on 2017/12/5.
 */
public interface EmpAptitudeService {

    PageInfo findEmpAptitudeByPage(EmpAptitudeSearch search, PageObject pageObject);

    boolean saveEmpAptitude(EmpAptitude empAptitude);

    List<EmpAptitude> findEmpAptitudeById(String id);

    boolean updateEmpAptitudeById(EmpAptitude empAptitude);

    boolean deleteEmpAptitudeById(String ids);

    List<EmpAptitude> findEmpAptitudeBySearch(EmpAptitudeSearch search);
}
