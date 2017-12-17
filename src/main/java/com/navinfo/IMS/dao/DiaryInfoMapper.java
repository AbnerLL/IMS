package com.navinfo.IMS.dao;

import com.navinfo.IMS.entity.DiaryInfo;
import com.navinfo.IMS.entity.DiaryInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DiaryInfoMapper {
    long countByExample(DiaryInfoExample example);

    int deleteByExample(DiaryInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(DiaryInfo record);

    int insertSelective(DiaryInfo record);

    List<DiaryInfo> selectByExample(DiaryInfoExample example);

    DiaryInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DiaryInfo record, @Param("example") DiaryInfoExample example);

    int updateByExample(@Param("record") DiaryInfo record, @Param("example") DiaryInfoExample example);

    int updateByPrimaryKeySelective(DiaryInfo record);

    int updateByPrimaryKey(DiaryInfo record);
}