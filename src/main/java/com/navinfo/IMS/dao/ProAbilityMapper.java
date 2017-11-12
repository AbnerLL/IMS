package com.navinfo.IMS.dao;

import com.navinfo.IMS.entity.ProAbility;
import com.navinfo.IMS.entity.ProAbilityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProAbilityMapper {
    long countByExample(ProAbilityExample example);

    int deleteByExample(ProAbilityExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProAbility record);

    int insertSelective(ProAbility record);

    List<ProAbility> selectByExample(ProAbilityExample example);

    ProAbility selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProAbility record, @Param("example") ProAbilityExample example);

    int updateByExample(@Param("record") ProAbility record, @Param("example") ProAbilityExample example);

    int updateByPrimaryKeySelective(ProAbility record);

    int updateByPrimaryKey(ProAbility record);
}