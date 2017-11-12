package com.navinfo.IMS.service;

import com.navinfo.IMS.entity.Emp;

import java.util.List;

/**
 *
 * Created by luozhihui on 2017/9/21.
 */
public interface EmpService {

    public List findAllEmp();

    public boolean insertEmp(Emp emp);

    public List<Emp> getEmpById(String empId);

    public boolean updateEmp(Emp emp);

    public boolean deleteEmp(String empId);
}
