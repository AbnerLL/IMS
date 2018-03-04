package com.navinfo.core.dao;

import com.navinfo.core.entity.FileInfo;
import com.navinfo.core.entity.FileInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FileInfoMapper {
    long countByExample(FileInfoExample example);

    int deleteByExample(FileInfoExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(FileInfo record);

    int insertSelective(FileInfo record);

    List<FileInfo> selectByExample(FileInfoExample example);

    FileInfo selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") FileInfo record, @Param("example") FileInfoExample example);

    int updateByExample(@Param("record") FileInfo record, @Param("example") FileInfoExample example);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);
}