package com.navinfo.IMS.service.impl;

import com.navinfo.IMS.dao.EmpMapper;
import com.navinfo.IMS.entity.Emp;
import com.navinfo.IMS.entity.EmpExample;
import com.navinfo.IMS.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 员工的业务层实现类
 * Created by luozhihui on 2017/9/21.
 */
@Service("empService")
public class EmpServiceImpl implements EmpService{

    @Autowired
    private EmpMapper empMapper;

    public List findAllEmp() {
        return empMapper.selectByExample(null);
    }

    /**
     * 根据一个ID或多个ID获取员工
     * @param empId
     * @return
     */
    public List<Emp> getEmpById(String empId){
        List<Emp> emps=new ArrayList<Emp>();
        if (!empId.contains(",")){
            emps.add(this.empMapper.selectByPrimaryKey(empId));
        }else{
            List<String> ids= Arrays.asList(empId.split(","));
            EmpExample example=new EmpExample();
            example.or().andEmpIdIn(ids);
            this.empMapper.selectByExample(example);
        }

        return emps;
    }
    /**
     * 新增员工
     * @param emp
     * @return
     */
    public boolean insertEmp(Emp emp){
        int num=empMapper.insertSelective(emp);
        return num!=0;
    }

    /**
     * 修改员工
     * @param emp
     * @return
     */
    public boolean updateEmp(Emp emp){
        int num=empMapper.updateByPrimaryKeySelective(emp);
        return num!=0;
    }

    /**
     * 删除员工，支持删除一个或多个
     * @param empId
     * @return
     */
    public boolean deleteEmp(String empId){
        List<String> ids= Arrays.asList(empId.split(","));
        EmpExample empExample=new EmpExample();
        empExample.or().andEmpIdIn(ids);
        int num=empMapper.deleteByExample(empExample);
        return num!=0;
    }
}
