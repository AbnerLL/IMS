package com.navinfo.core.dao;

import com.navinfo.core.entity.DBFieldManage;
import com.navinfo.core.entity.DBFieldManageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DBFieldManageMapper {
    long countByExample(DBFieldManageExample example);

    int deleteByExample(DBFieldManageExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(DBFieldManage record);

    int insertSelective(DBFieldManage record);

    List<DBFieldManage> selectByExample(DBFieldManageExample example);

    DBFieldManage selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") DBFieldManage record, @Param("example") DBFieldManageExample example);

    int updateByExample(@Param("record") DBFieldManage record, @Param("example") DBFieldManageExample example);

    int updateByPrimaryKeySelective(DBFieldManage record);

    int updateByPrimaryKey(DBFieldManage record);
}