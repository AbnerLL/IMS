package com.navinfo.IMS.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dao.PreGradeMapper;
import com.navinfo.IMS.entity.PreGrade;
import com.navinfo.IMS.entity.PreGradeExample;
import com.navinfo.IMS.service.PreGradeService;
import com.navinfo.IMS.so.PreGradeSearch;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.IMS.utils.StringUtil;
import oracle.jdbc.proxy.annotation.Pre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by luozhihui on 2017/9/21.
 */
@Service("preGradeService")
public class PreGradeServiceImpl implements PreGradeService{
    @Autowired
    private PreGradeMapper preGradeMapper;

    /**
     * 根据查询参数构建example
     * @param search
     * @return
     */
    private PreGradeExample createSearchExample(PreGradeSearch search){
        PreGradeExample example=new PreGradeExample();
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
        if (search.getGradeStart()!=null){
            example.getOredCriteria().get(0).andGradeGreaterThanOrEqualTo(search.getGradeStart());
        }
        if (search.getGradeEnd()!=null){
            example.getOredCriteria().get(0).andGradeLessThanOrEqualTo(search.getGradeEnd());
        }
        if (search.getSecGradeStart()!=null){
            example.getOredCriteria().get(0).andSecGradeGreaterThanOrEqualTo(search.getSecGradeStart());
        }
        if (search.getSecGradeEnd()!=null){
            example.getOredCriteria().get(0).andSecGradeLessThanOrEqualTo(search.getSecGradeEnd());
        }
        if (StringUtil.notNull(search.getKeyword())){
            example.or().andEmpIdLike("%"+search.getKeyword()+"%");
            example.or().andEmpNameLike("%"+search.getKeyword()+"%");
            example.or().andVersionLike("%"+search.getKeyword()+"%");
        }
        return example;
    }
    /**
     * 根据参数分页查询
     * @param search
     * @param pageObject
     * @return
     */
    public PageInfo findPreGradeByPage(PreGradeSearch search, PageObject pageObject) {
        PreGradeExample example=this.createSearchExample(search);
        PageHelper.startPage(pageObject.getPageNum(),pageObject.getPageSize());
        List<PreGrade> preGrades=this.preGradeMapper.selectByExample(example);
        return new PageInfo(preGrades);
    }
    /**
     * 根据参数不分页查询
     * @param search
     * @return
     */
    public List<PreGrade> findTestGradeBySearch(PreGradeSearch search) {
        PreGradeExample example=this.createSearchExample(search);
        List<PreGrade> preGrades=this.preGradeMapper.selectByExample(example);
        return preGrades;
    }

    /**
     * 新增
     * @param preGrade
     * @return
     */
    public boolean insertPreGrade(PreGrade preGrade){
        preGrade.setId(UUID.randomUUID().toString());
        int num=preGradeMapper.insertSelective(preGrade);
        return num!=0;
    }

    /**
     * 根据主键获取对象
     * @param id
     * @return
     */
    public PreGrade getPreGradeById(String id){
        PreGrade preGrade=preGradeMapper.selectByPrimaryKey(id);
        return preGrade;
    }

    /**
     * 根据主键来更新对象
     * @param preGrade
     * @return
     */
    public boolean updatePreGrade(PreGrade preGrade){
        int num=preGradeMapper.updateByPrimaryKeySelective(preGrade);
        return num!=0;
    }

    /**
     * 根据主键删除一条或多条数据
     * @param ids
     * @return
     */
    public boolean deletePreGradeById(String ids){
        List idList= Arrays.asList(ids.split(","));
        PreGradeExample preGradeExample=new PreGradeExample();
        preGradeExample.or().andIdIn(idList);
        int num=preGradeMapper.deleteByExample(preGradeExample);
        return num!=0;
    }

}
