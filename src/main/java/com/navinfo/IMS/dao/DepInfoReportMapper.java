package com.navinfo.IMS.dao;

import com.navinfo.IMS.entity.DepInfoReport;
import com.navinfo.IMS.entity.DepInfoReportExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepInfoReportMapper {
    long countByExample(DepInfoReportExample example);

    int deleteByExample(DepInfoReportExample example);

    int deleteByPrimaryKey(String id);

    int insert(DepInfoReport record);

    int insertSelective(DepInfoReport record);

    List<DepInfoReport> selectByExample(DepInfoReportExample example);

    DepInfoReport selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DepInfoReport record, @Param("example") DepInfoReportExample example);

    int updateByExample(@Param("record") DepInfoReport record, @Param("example") DepInfoReportExample example);

    int updateByPrimaryKeySelective(DepInfoReport record);

    int updateByPrimaryKey(DepInfoReport record);
}