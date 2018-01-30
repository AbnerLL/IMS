package com.navinfo.core.dao;

import com.navinfo.core.entity.SysRole;
import com.navinfo.core.entity.SysRoleExample;

import java.util.Collection;
import java.util.List;

import com.navinfo.core.entity.SysUser;
import com.navinfo.core.vo.PermissionTreeVO;
import org.apache.ibatis.annotations.Param;

public interface SysRoleMapper {
    long countByExample(SysRoleExample example);

    int deleteByExample(SysRoleExample example);

    int deleteByPrimaryKey(String roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectByExample(SysRoleExample example);

    SysRole selectByPrimaryKey(String roleId);

    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    String getMaxIdByParentId(String parentId);

    List<SysRole> findSysRoleByUsername(String username);

    List<PermissionTreeVO> loadModuleTree(String roleId);

    List<PermissionTreeVO> loadAllModuleTree();
}