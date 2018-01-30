package com.navinfo.IMS.service;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.entity.Emp;
import com.navinfo.IMS.so.EmpSearch;
import com.navinfo.IMS.utils.PageObject;

import java.util.List;
import java.util.Map;

/**
 *
 * Created by luozhihui on 2017/9/21.
 */
public interface EmpService {

    List findAllEmp();

    boolean insertEmp(Emp emp);

    List<Emp> getEmpById(String empId);

    boolean updateEmp(Emp emp);

    boolean deleteEmp(String empId);

    PageInfo findEmpByPage(EmpSearch empSearch, PageObject pageObject);
}
