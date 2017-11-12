package com.navinfo.IMS.service.impl;

import com.navinfo.IMS.dao.TestGradeMapper;
import com.navinfo.IMS.entity.TestGrade;
import com.navinfo.IMS.entity.TestGradeExample;
import com.navinfo.IMS.service.TestGradeService;
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
     * 分页查询
     * @return
     */
    public List findTestGrades(){
        return testGradeMapper.selectByExample(null);
    }

    /**
     * 根据主键获取对象
     * @param id
     * @return
     */
    public TestGrade getTestGradeById(String id){
        return testGradeMapper.selectByPrimaryKey(id);
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
