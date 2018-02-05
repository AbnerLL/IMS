package com.navinfo.IMS.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dao.TestGradeMapper;
import com.navinfo.IMS.entity.TestGrade;
import com.navinfo.IMS.entity.TestGradeExample;
import com.navinfo.IMS.service.TestGradeService;
import com.navinfo.IMS.so.TestGradeSearch;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.IMS.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by luozhihui on 2017/9/21.
 */
@Service("testGradeService")
public class TestGradeServiceImpl implements TestGradeService{
    @Autowired
    private TestGradeMapper testGradeMapper;

    /**
     * 根据查询构建查询参数example
     * @param search
     * @return
     */
    private TestGradeExample createSearchExample(TestGradeSearch search){
        TestGradeExample example=new TestGradeExample();
        example.createCriteria();
        if (StringUtil.notNull(search.getEmpId())){
            example.getOredCriteria().get(0).andEmpIdEqualTo(search.getEmpId());
        }
        if (StringUtil.notNull(search.getEmpName())){
            example.getOredCriteria().get(0).andEmpNameEqualTo(search.getEmpName());
        }
        if (StringUtil.notNull(search.getSection())){
            example.getOredCriteria().get(0).andSectionEqualTo(search.getSection());
        }
        if (search.getTestDateStart()!=null){
            example.getOredCriteria().get(0).andTestDateGreaterThanOrEqualTo(search.getTestDateStart());
        }
        if (search.getTestDateEnd()!=null){
            example.getOredCriteria().get(0).andTestDateLessThanOrEqualTo(search.getTestDateEnd());
        }
        if (search.getPaperGradeStart()!=null){
            example.getOredCriteria().get(0).andPaperGradeGreaterThanOrEqualTo(search.getPaperGradeStart());
        }
        if (search.getPaperGradeEnd()!=null){
            example.getOredCriteria().get(0).andPaperGradeLessThanOrEqualTo(search.getPaperGradeEnd());
        }
        if (search.getComGradeRoadStart()!=null){
            example.getOredCriteria().get(0).andComGradeRoadGreaterThanOrEqualTo(search.getComGradeRoadStart());
        }
        if (search.getComGradeRoadEnd()!=null){
            example.getOredCriteria().get(0).andComGradeRoadLessThanOrEqualTo(search.getComGradeRoadEnd());
        }
        if (search.getComGradePoiStart()!=null){
            example.getOredCriteria().get(0).andComGradePoiGreaterThanOrEqualTo(search.getComGradePoiStart());
        }
        if (search.getComGradePoiEnd()!=null){
            example.getOredCriteria().get(0).andComGradePoiLessThanOrEqualTo(search.getComGradePoiEnd());
        }
        if (search.getTotalGradeStart()!=null){
            example.getOredCriteria().get(0).andTotalGradeGreaterThanOrEqualTo(search.getTotalGradeStart());
        }
        if (search.getTotalGradeEnd()!=null){
            example.getOredCriteria().get(0).andTotalGradeLessThanOrEqualTo(search.getTotalGradeEnd());
        }
        if (StringUtil.notNull(search.getKeyword())){
            example.or().andEmpIdLike("%"+search.getKeyword()+"%");
            example.or().andEmpNameLike("%"+search.getKeyword()+"%");
        }
        return example;
    }
    /**
     * 根据条件分页查询
     * @param search
     * @param pageObject
     * @return
     */
    public PageInfo findTestGradeByPage(TestGradeSearch search, PageObject pageObject) {
        TestGradeExample example=this.createSearchExample(search);
        PageHelper.startPage(pageObject.getPageNum(),pageObject.getPageSize());
        List<TestGrade> testGradeList=this.testGradeMapper.selectByExample(example);
        return new PageInfo(testGradeList);
    }

    /**
     *不分页查询
     * @param search
     * @return
     */
    public List<TestGrade> findTestGradeBySearch(TestGradeSearch search) {
        TestGradeExample example=this.createSearchExample(search);
        List<TestGrade> testGradeList=this.testGradeMapper.selectByExample(example);
        return testGradeList;
    }

    /**
     * 根据主键获取对象
     * @param id
     * @return
     */
    public List<TestGrade> getTestGradeById(String id){
        List idList= Arrays.asList(id.split(","));
        TestGradeExample example=new TestGradeExample();
        example.or().andIdIn(idList);
        return this.testGradeMapper.selectByExample(example);
    }

    /**
     * 新增对象
     * @param testGrade
     * @return
     */
    public boolean insertTestGrade(TestGrade testGrade){
        testGrade.setId(UUID.randomUUID().toString());
        int num=testGradeMapper.insertSelective(testGrade);
        return num!=0;
    }

    /**
     * 根据主键更新对象
     * @param testGrade
     * @return
     */
    public boolean updateTestGrade(TestGrade testGrade){
        int num=testGradeMapper.updateByPrimaryKeySelective(testGrade);
        return num!=0;
    }

    /**
     * 根据主键删除一个或则多个对象
     * @param ids
     * @return
     */
    public boolean deleteTestGradeById(String ids){
        List idList= Arrays.asList(ids.split(","));
        TestGradeExample example=new TestGradeExample();
        example.or().andIdIn(idList);
        int num=testGradeMapper.deleteByExample(example);
        return num!=0;
    }
}
