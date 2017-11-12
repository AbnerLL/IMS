package com.navinfo.IMS.dao;

import com.navinfo.IMS.entity.WorkDiary;
import com.navinfo.IMS.entity.WorkDiaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkDiaryMapper {
    long countByExample(WorkDiaryExample example);

    int deleteByExample(WorkDiaryExample example);

    int deleteByPrimaryKey(String id);

    int insert(WorkDiary record);

    int insertSelective(WorkDiary record);

    List<WorkDiary> selectByExample(WorkDiaryExample example);

    WorkDiary selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WorkDiary record, @Param("example") WorkDiaryExample example);

    int updateByExample(@Param("record") WorkDiary record, @Param("example") WorkDiaryExample example);

    int updateByPrimaryKeySelective(WorkDiary record);

    int updateByPrimaryKey(WorkDiary record);
}