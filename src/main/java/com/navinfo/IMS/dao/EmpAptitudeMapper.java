package com.navinfo.IMS.dao;

import com.navinfo.IMS.entity.EmpAptitude;
import com.navinfo.IMS.entity.EmpAptitudeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmpAptitudeMapper {
    long countByExample(EmpAptitudeExample example);

    int deleteByExample(EmpAptitudeExample example);

    int deleteByPrimaryKey(String id);

    int insert(EmpAptitude record);

    int insertSelective(EmpAptitude record);

    List<EmpAptitude> selectByExample(EmpAptitudeExample example);

    EmpAptitude selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EmpAptitude record, @Param("example") EmpAptitudeExample example);

    int updateByExample(@Param("record") EmpAptitude record, @Param("example") EmpAptitudeExample example);

    int updateByPrimaryKeySelective(EmpAptitude record);

    int updateByPrimaryKey(EmpAptitude record);
}