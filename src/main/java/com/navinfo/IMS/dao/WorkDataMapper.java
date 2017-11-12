package com.navinfo.IMS.dao;

import com.navinfo.IMS.entity.WorkData;
import com.navinfo.IMS.entity.WorkDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkDataMapper {
    long countByExample(WorkDataExample example);

    int deleteByExample(WorkDataExample example);

    int deleteByPrimaryKey(String workDataId);

    int insert(WorkData record);

    int insertSelective(WorkData record);

    List<WorkData> selectByExample(WorkDataExample example);

    WorkData selectByPrimaryKey(String workDataId);

    int updateByExampleSelective(@Param("record") WorkData record, @Param("example") WorkDataExample example);

    int updateByExample(@Param("record") WorkData record, @Param("example") WorkDataExample example);

    int updateByPrimaryKeySelective(WorkData record);

    int updateByPrimaryKey(WorkData record);
}