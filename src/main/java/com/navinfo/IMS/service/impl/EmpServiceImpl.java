package com.navinfo.IMS.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dao.EmpMapper;
import com.navinfo.IMS.entity.Emp;
import com.navinfo.IMS.entity.EmpExample;
import com.navinfo.IMS.service.EmpService;
import com.navinfo.IMS.so.EmpSearch;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.IMS.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * 员工的业务层实现类
 * Created by luozhihui on 2017/9/21.
 */
@Service("empService")
public class EmpServiceImpl implements EmpService{

    @Autowired
    private EmpMapper empMapper;

    /**
     * 根据页面的查询参数生成查询Example
     * @return
     */
    private EmpExample createSearchExample(EmpSearch empSearch){
        EmpExample empExample=new EmpExample();
        empExample.createCriteria();
        if(StringUtil.notNull(empSearch.getEmpId())){
            empExample.getOredCriteria().get(0).andEmpIdEqualTo(empSearch.getEmpId());
        }
        if(StringUtil.notNull(empSearch.getEmpName())){
            empExample.getOredCriteria().get(0).andEmpNameEqualTo(empSearch.getEmpName());
        }
        if(empSearch.getEmpEntryAge()!=null){
            empExample.getOredCriteria().get(0).andEmpEntryAgeEqualTo(empSearch.getEmpEntryAge());
        }
        if (StringUtil.notNull(empSearch.getEmpDept())){
            empExample.getOredCriteria().get(0).andEmpDeptEqualTo(empSearch.getEmpDept());
        }
        if (StringUtil.notNull(empSearch.getEmpSec())){
            empExample.getOredCriteria().get(0).andEmpSecEqualTo(empSearch.getEmpSec());
        }
        if (StringUtil.notNull(empSearch.getEmpPost())) {
            empExample.getOredCriteria().get(0).andEmpPostEqualTo(empSearch.getEmpPost());
        }
        if (empSearch.getEmpHiredateStart()!=null){
            empExample.getOredCriteria().get(0).andEmpHiredateGreaterThanOrEqualTo(empSearch.getEmpHiredateStart());
        }
        if (empSearch.getEmpHiredateEnd()!=null){
            empExample.getOredCriteria().get(0).andEmpHiredateLessThanOrEqualTo(empSearch.getEmpHiredateEnd());
        }
        //关键词部分
        if (StringUtil.notNull(empSearch.getKeyword())){
            empExample.or().andEmpNameLike("%"+empSearch.getKeyword()+"%");
            empExample.or().andEmpSecLike("%"+empSearch.getKeyword()+"%");
        }
        return empExample;
    }

    /**
     * 分页查询
     * @param empSearch 表单的查询参数
     * @param pageObject 页面的分页参数
     * @return PageInfo 分页信息类，包含数据和详细的分页信息
     */
    public PageInfo findEmpByPage(EmpSearch empSearch, PageObject pageObject){
        EmpExample empExample=this.createSearchExample(empSearch);
        PageHelper.startPage(pageObject.getPageNum(),pageObject.getPageSize());
        List<Emp> empList=this.empMapper.selectByExample(empExample);
        return new PageInfo(empList);
    }

    /**
     * 根据查询条件查出对应的对象
     * @param empSearch
     * @return
     */
    public List<Emp> findEmpBySearch(EmpSearch empSearch) {
        EmpExample empExample=this.createSearchExample(empSearch);
        return empMapper.selectByExample(empExample);
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
