package com.navinfo.IMS.dao;

import com.navinfo.IMS.entity.RoadReport;
import com.navinfo.IMS.entity.RoadReportExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoadReportMapper {
    long countByExample(RoadReportExample example);

    int deleteByExample(RoadReportExample example);

    int deleteByPrimaryKey(String id);

    int insert(RoadReport record);

    int insertSelective(RoadReport record);

    List<RoadReport> selectByExample(RoadReportExample example);

    RoadReport selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RoadReport record, @Param("example") RoadReportExample example);

    int updateByExample(@Param("record") RoadReport record, @Param("example") RoadReportExample example);

    int updateByPrimaryKeySelective(RoadReport record);

    int updateByPrimaryKey(RoadReport record);
}