package com.navinfo.core.dao;

import com.navinfo.core.entity.UserRoleRel;
import com.navinfo.core.entity.UserRoleRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleRelMapper {
    long countByExample(UserRoleRelExample example);

    int deleteByExample(UserRoleRelExample example);

    int deleteByPrimaryKey(String relId);

    int insert(UserRoleRel record);

    int insertSelective(UserRoleRel record);

    List<UserRoleRel> selectByExample(UserRoleRelExample example);

    UserRoleRel selectByPrimaryKey(String relId);

    int updateByExampleSelective(@Param("record") UserRoleRel record, @Param("example") UserRoleRelExample example);

    int updateByExample(@Param("record") UserRoleRel record, @Param("example") UserRoleRelExample example);

    int updateByPrimaryKeySelective(UserRoleRel record);

    int updateByPrimaryKey(UserRoleRel record);
}