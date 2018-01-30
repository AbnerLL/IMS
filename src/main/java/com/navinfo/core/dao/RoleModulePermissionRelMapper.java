package com.navinfo.core.dao;

import com.navinfo.core.entity.RoleModulePermissionRel;
import com.navinfo.core.entity.RoleModulePermissionRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleModulePermissionRelMapper {
    long countByExample(RoleModulePermissionRelExample example);

    int deleteByExample(RoleModulePermissionRelExample example);

    int deleteByPrimaryKey(String relId);

    int insert(RoleModulePermissionRel record);

    int insertSelective(RoleModulePermissionRel record);

    List<RoleModulePermissionRel> selectByExample(RoleModulePermissionRelExample example);

    RoleModulePermissionRel selectByPrimaryKey(String relId);

    int updateByExampleSelective(@Param("record") RoleModulePermissionRel record, @Param("example") RoleModulePermissionRelExample example);

    int updateByExample(@Param("record") RoleModulePermissionRel record, @Param("example") RoleModulePermissionRelExample example);

    int updateByPrimaryKeySelective(RoleModulePermissionRel record);

    int updateByPrimaryKey(RoleModulePermissionRel record);
}