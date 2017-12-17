package com.navinfo.IMS.dao;

import com.navinfo.IMS.entity.PoiReport;
import com.navinfo.IMS.entity.PoiReportExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PoiReportMapper {
    long countByExample(PoiReportExample example);

    int deleteByExample(PoiReportExample example);

    int deleteByPrimaryKey(String id);

    int insert(PoiReport record);

    int insertSelective(PoiReport record);

    List<PoiReport> selectByExample(PoiReportExample example);

    PoiReport selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PoiReport record, @Param("example") PoiReportExample example);

    int updateByExample(@Param("record") PoiReport record, @Param("example") PoiReportExample example);

    int updateByPrimaryKeySelective(PoiReport record);

    int updateByPrimaryKey(PoiReport record);
}