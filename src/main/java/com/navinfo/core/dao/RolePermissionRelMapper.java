package com.navinfo.core.dao;

import com.navinfo.core.entity.RolePermissionRel;
import com.navinfo.core.entity.RolePermissionRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolePermissionRelMapper {
    long countByExample(RolePermissionRelExample example);

    int deleteByExample(RolePermissionRelExample example);

    int deleteByPrimaryKey(String relId);

    int insert(RolePermissionRel record);

    int insertSelective(RolePermissionRel record);

    List<RolePermissionRel> selectByExample(RolePermissionRelExample example);

    RolePermissionRel selectByPrimaryKey(String relId);

    int updateByExampleSelective(@Param("record") RolePermissionRel record, @Param("example") RolePermissionRelExample example);

    int updateByExample(@Param("record") RolePermissionRel record, @Param("example") RolePermissionRelExample example);

    int updateByPrimaryKeySelective(RolePermissionRel record);

    int updateByPrimaryKey(RolePermissionRel record);
}