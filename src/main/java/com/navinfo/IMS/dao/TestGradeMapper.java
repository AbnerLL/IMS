package com.navinfo.IMS.dao;

import com.navinfo.IMS.entity.TestGrade;
import com.navinfo.IMS.entity.TestGradeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestGradeMapper {
    long countByExample(TestGradeExample example);

    int deleteByExample(TestGradeExample example);

    int deleteByPrimaryKey(String id);

    int insert(TestGrade record);

    int insertSelective(TestGrade record);

    List<TestGrade> selectByExample(TestGradeExample example);

    TestGrade selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TestGrade record, @Param("example") TestGradeExample example);

    int updateByExample(@Param("record") TestGrade record, @Param("example") TestGradeExample example);

    int updateByPrimaryKeySelective(TestGrade record);

    int updateByPrimaryKey(TestGrade record);
}