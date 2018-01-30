package com.navinfo.core.dao;

import com.navinfo.core.entity.SysModule;
import com.navinfo.core.entity.SysModuleExample;
import java.util.List;

import com.navinfo.core.vo.MenuVO;
import org.apache.ibatis.annotations.Param;

public interface SysModuleMapper {
    long countByExample(SysModuleExample example);

    int deleteByExample(SysModuleExample example);

    int deleteByPrimaryKey(String moduleId);

    int insert(SysModule record);

    int insertSelective(SysModule record);

    List<SysModule> selectByExample(SysModuleExample example);

    SysModule selectByPrimaryKey(String moduleId);

    int updateByExampleSelective(@Param("record") SysModule record, @Param("example") SysModuleExample example);

    int updateByExample(@Param("record") SysModule record, @Param("example") SysModuleExample example);

    int updateByPrimaryKeySelective(SysModule record);

    int updateByPrimaryKey(SysModule record);

    List<MenuVO> getParentModuleVOByUser(String username);

    List<SysModule> getModuleByUser(String username);
}