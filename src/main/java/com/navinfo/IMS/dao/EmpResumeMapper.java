package com.navinfo.IMS.dao;

import com.navinfo.IMS.entity.EmpResume;
import com.navinfo.IMS.entity.EmpResumeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmpResumeMapper {
    long countByExample(EmpResumeExample example);

    int deleteByExample(EmpResumeExample example);

    int deleteByPrimaryKey(String resumeId);

    int insert(EmpResume record);

    int insertSelective(EmpResume record);

    List<EmpResume> selectByExample(EmpResumeExample example);

    EmpResume selectByPrimaryKey(String resumeId);

    int updateByExampleSelective(@Param("record") EmpResume record, @Param("example") EmpResumeExample example);

    int updateByExample(@Param("record") EmpResume record, @Param("example") EmpResumeExample example);

    int updateByPrimaryKeySelective(EmpResume record);

    int updateByPrimaryKey(EmpResume record);
}