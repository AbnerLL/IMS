package com.navinfo.IMS.dao;

import com.navinfo.IMS.entity.PreGrade;
import com.navinfo.IMS.entity.PreGradeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PreGradeMapper {
    long countByExample(PreGradeExample example);

    int deleteByExample(PreGradeExample example);

    int deleteByPrimaryKey(String id);

    int insert(PreGrade record);

    int insertSelective(PreGrade record);

    List<PreGrade> selectByExample(PreGradeExample example);

    PreGrade selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PreGrade record, @Param("example") PreGradeExample example);

    int updateByExample(@Param("record") PreGrade record, @Param("example") PreGradeExample example);

    int updateByPrimaryKeySelective(PreGrade record);

    int updateByPrimaryKey(PreGrade record);
}