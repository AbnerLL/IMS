package com.navinfo.IMS.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dao.EmpAptitudeMapper;
import com.navinfo.IMS.entity.EmpAptitude;
import com.navinfo.IMS.entity.EmpAptitudeExample;
import com.navinfo.IMS.service.EmpAptitudeService;
import com.navinfo.IMS.so.EmpAptitudeSearch;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.IMS.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by luozhihui on 2017/12/5.
 */
@Service("empAptitudeService")
public class EmpAptitudeServiceImpl implements EmpAptitudeService {
    @Autowired
    private EmpAptitudeMapper empAptitudeMapper;

    /**
     * 根据查询条件构造查询example
     * @return
     */
    private EmpAptitudeExample createSearchExample(EmpAptitudeSearch search){
        EmpAptitudeExample example=new EmpAptitudeExample();
        example.createCriteria();
        if (StringUtil.notNull(search.getEmpId())){
            example.getOredCriteria().get(0).andEmpIdEqualTo(search.getEmpId());
        }
        if (StringUtil.notNull(search.getEmpName())){
            example.getOredCriteria().get(0).andEmpNameEqualTo(search.getEmpName());
        }
        if (StringUtil.notNull(search.getVersion())){
            example.getOredCriteria().get(0).andVersionEqualTo(search.getVersion());
        }
        if (StringUtil.notNull(search.getSection())){
            example.getOredCriteria().get(0).andSectionEqualTo(search.getSection());
        }
        if (StringUtil.notNull(search.getProfessionType())){
            example.getOredCriteria().get(0).andProfessionTypeEqualTo(search.getProfessionType());
        }
        if (StringUtil.notNull(search.getWorkAptitude())){
            example.getOredCriteria().get(0).andWorkAptitudeEqualTo(search.getWorkAptitude());
        }
        if (StringUtil.notNull(search.getAuditAptitude())){
            example.getOredCriteria().get(0).andAuditAptitudeEqualTo(search.getAuditAptitude());
        }
        if (StringUtil.notNull(search.getKeyword())){
            example.or().andVersionLike("%"+search.getKeyword()+"%");
            example.or().andEmpNameLike("%"+search.getKeyword()+"%");
        }
        return example;
    }
    /**
     * 根据查询条件分页查询
     * @param search
     * @param pageObject
     * @return
     */
    public PageInfo findEmpAptitudeByPage(EmpAptitudeSearch search, PageObject pageObject) {
        EmpAptitudeExample example=this.createSearchExample(search);
        PageHelper.startPage(pageObject.getPageNum(),pageObject.getPageSize());
        List<EmpAptitude> empAptitudeList=this.empAptitudeMapper.selectByExample(example);
        return new PageInfo(empAptitudeList);
    }
    /**
     * 根据查询条件获取对象
     * 不分页
     * @param search
     * @return
     */
    public List<EmpAptitude> findEmpAptitudeBySearch(EmpAptitudeSearch search) {
        EmpAptitudeExample example=this.createSearchExample(search);
        List<EmpAptitude> empAptitudeList=this.empAptitudeMapper.selectByExample(example);
        return empAptitudeList;
    }
    /**
     * 保存
     * @param empAptitude
     * @return
     */
    public boolean saveEmpAptitude(EmpAptitude empAptitude) {
        empAptitude.setId(UUID.randomUUID().toString());
        return this.empAptitudeMapper.insertSelective(empAptitude)!=0;
    }

    /**
     * 根据一个或多个ID查询对象
     * @param id
     * @return
     */
    public List<EmpAptitude> findEmpAptitudeById(String id) {
        List<String> idList= Arrays.asList(id.split(","));
        EmpAptitudeExample example=new EmpAptitudeExample();
        example.or().andIdIn(idList);
        return this.empAptitudeMapper.selectByExample(example);
    }

    /**
     * 根据主键更新
     * @param empAptitude
     * @return
     */
    public boolean updateEmpAptitudeById(EmpAptitude empAptitude) {
        return this.empAptitudeMapper.updateByPrimaryKeySelective(empAptitude)!=0;
    }

    /**
     * 根据主键删除一个或多个对象
     * @param ids
     * @return
     */
    public boolean deleteEmpAptitudeById(String ids) {
        List<String> idList= Arrays.asList(ids.split(","));
        EmpAptitudeExample example=new EmpAptitudeExample();
        example.or().andIdIn(idList);
        return this.empAptitudeMapper.deleteByExample(example)!=0;
    }
}
