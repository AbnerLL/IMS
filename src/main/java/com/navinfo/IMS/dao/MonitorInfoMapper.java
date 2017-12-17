package com.navinfo.IMS.dao;

import com.navinfo.IMS.entity.MonitorInfo;
import com.navinfo.IMS.entity.MonitorInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MonitorInfoMapper {
    long countByExample(MonitorInfoExample example);

    int deleteByExample(MonitorInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(MonitorInfo record);

    int insertSelective(MonitorInfo record);

    List<MonitorInfo> selectByExample(MonitorInfoExample example);

    MonitorInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MonitorInfo record, @Param("example") MonitorInfoExample example);

    int updateByExample(@Param("record") MonitorInfo record, @Param("example") MonitorInfoExample example);

    int updateByPrimaryKeySelective(MonitorInfo record);

    int updateByPrimaryKey(MonitorInfo record);
}