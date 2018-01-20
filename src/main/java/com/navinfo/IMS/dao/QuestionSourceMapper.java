package com.navinfo.IMS.dao;

import com.navinfo.IMS.entity.QuestionSource;
import com.navinfo.IMS.entity.QuestionSourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuestionSourceMapper {
    long countByExample(QuestionSourceExample example);

    int deleteByExample(QuestionSourceExample example);

    int deleteByPrimaryKey(String id);

    int insert(QuestionSource record);

    int insertSelective(QuestionSource record);

    List<QuestionSource> selectByExample(QuestionSourceExample example);

    QuestionSource selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") QuestionSource record, @Param("example") QuestionSourceExample example);

    int updateByExample(@Param("record") QuestionSource record, @Param("example") QuestionSourceExample example);

    int updateByPrimaryKeySelective(QuestionSource record);

    int updateByPrimaryKey(QuestionSource record);
}