package com.navinfo.IMS.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dao.EmpResumeMapper;
import com.navinfo.IMS.entity.EmpResume;
import com.navinfo.IMS.entity.EmpResumeExample;
import com.navinfo.IMS.service.EmpResumeService;
import com.navinfo.IMS.so.EmpResumeSearch;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.IMS.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by luozhihui on 2017/12/21.
 */
@Service("empResumeService")
public class EmpResumeServiceImpl implements EmpResumeService {

    @Autowired
    private EmpResumeMapper empResumeMapper;

    /**
     * 根据查询条件构建查询的example
     * @param search
     * @return
     */
    private EmpResumeExample createSearchExample(EmpResumeSearch search){
        EmpResumeExample example=new EmpResumeExample();
        example.createCriteria();
        if (StringUtil.notNull(search.getEmpId())){
            example.getOredCriteria().get(0).andEmpIdEqualTo(search.getEmpId());
        }
        if (StringUtil.notNull(search.getEmpName())){
            example.getOredCriteria().get(0).andEmpNameEqualTo(search.getEmpName());
        }
        if (StringUtil.notNull(search.getKeyword())){
            example.or().andEmpIdLike("%"+search.getKeyword()+"%");
            example.or().andEmpNameLike("%"+search.getKeyword()+"%");
        }
        return example;
    }
    /**
     * 根据条件进行分页查询
     * @param pageObject
     * @param resumeSearch
     * @return
     */
    public PageInfo findEmpResumeByPage(EmpResumeSearch resumeSearch, PageObject pageObject){
        EmpResumeExample example=this.createSearchExample(resumeSearch);
        PageHelper.startPage(pageObject.getPageNum(),pageObject.getPageSize());
        List empResumes=empResumeMapper.selectByExample(example);
        return new PageInfo(empResumes);
    }
    /**
     * 根据条件进行不分页查询
     * @param resumeSearch
     * @return
     */
    public List<EmpResume> findEmpResumeBySearch(EmpResumeSearch resumeSearch){
        EmpResumeExample example=this.createSearchExample(resumeSearch);
        List empResumes=empResumeMapper.selectByExample(example);
        return empResumes;
    }
    /**
     * 根据id获取对象
     * @return
     */
    public List<EmpResume> findWorkDiaryById(String id){
        List idList= Arrays.asList(id.split(","));
        EmpResumeExample example=new EmpResumeExample();
        example.createCriteria().andResumeIdIn(idList);
        return empResumeMapper.selectByExample(example);
    }

    /**
     *  保存
     * @param empResume
     * @return
     */
    public boolean saveEmpResume(EmpResume empResume){
        empResume.setResumeId(UUID.randomUUID().toString());
        return empResumeMapper.insertSelective(empResume)!=0;
    }

    /**
     * 更新
     * @param empResume
     * @return
     */
    public boolean updateEmpResume(EmpResume empResume){
        return empResumeMapper.updateByPrimaryKeySelective(empResume)!=0;
    }

    /**
     * 删除一个或多个对象
     * @param ids
     * @return
     */
    public boolean deleteEmpResume(String ids){
        List<String> idList=Arrays.asList(ids.split(","));
        EmpResumeExample example=new EmpResumeExample();
        example.createCriteria().andResumeIdIn(idList);
        return empResumeMapper.deleteByExample(example)!=0;
    }
}
