package com.navinfo.IMS.dao;

import com.navinfo.IMS.entity.PoiQuestion;
import com.navinfo.IMS.entity.PoiQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PoiQuestionMapper {
    long countByExample(PoiQuestionExample example);

    int deleteByExample(PoiQuestionExample example);

    int deleteByPrimaryKey(String id);

    int insert(PoiQuestion record);

    int insertSelective(PoiQuestion record);

    List<PoiQuestion> selectByExample(PoiQuestionExample example);

    PoiQuestion selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PoiQuestion record, @Param("example") PoiQuestionExample example);

    int updateByExample(@Param("record") PoiQuestion record, @Param("example") PoiQuestionExample example);

    int updateByPrimaryKeySelective(PoiQuestion record);

    int updateByPrimaryKey(PoiQuestion record);
}