package com.navinfo.core.dao;

import com.navinfo.core.entity.ModulePermissionRel;
import com.navinfo.core.entity.ModulePermissionRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ModulePermissionRelMapper {
    long countByExample(ModulePermissionRelExample example);

    int deleteByExample(ModulePermissionRelExample example);

    int deleteByPrimaryKey(String relId);

    int insert(ModulePermissionRel record);

    int insertSelective(ModulePermissionRel record);

    List<ModulePermissionRel> selectByExample(ModulePermissionRelExample example);

    ModulePermissionRel selectByPrimaryKey(String relId);

    int updateByExampleSelective(@Param("record") ModulePermissionRel record, @Param("example") ModulePermissionRelExample example);

    int updateByExample(@Param("record") ModulePermissionRel record, @Param("example") ModulePermissionRelExample example);

    int updateByPrimaryKeySelective(ModulePermissionRel record);

    int updateByPrimaryKey(ModulePermissionRel record);
}