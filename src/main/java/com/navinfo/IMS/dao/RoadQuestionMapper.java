package com.navinfo.IMS.dao;

import com.navinfo.IMS.entity.RoadQuestion;
import com.navinfo.IMS.entity.RoadQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoadQuestionMapper {
    long countByExample(RoadQuestionExample example);

    int deleteByExample(RoadQuestionExample example);

    int deleteByPrimaryKey(String id);

    int insert(RoadQuestion record);

    int insertSelective(RoadQuestion record);

    List<RoadQuestion> selectByExample(RoadQuestionExample example);

    RoadQuestion selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RoadQuestion record, @Param("example") RoadQuestionExample example);

    int updateByExample(@Param("record") RoadQuestion record, @Param("example") RoadQuestionExample example);

    int updateByPrimaryKeySelective(RoadQuestion record);

    int updateByPrimaryKey(RoadQuestion record);
}