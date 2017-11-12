package com.navinfo.IMS.dao;

import com.navinfo.IMS.entity.Menu;
import com.navinfo.IMS.entity.MenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuMapper {
    long countByExample(MenuExample example);

    int deleteByExample(MenuExample example);

    int insert(Menu record);

    int insertSelective(Menu record);

    List<Menu> selectByExample(MenuExample example);

    int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);

    /**
     * 根据父id查询出子对象
     * @param pid
     * @return
     */
    List selectSimpleByPid(String pid);

    /**
     * 根据父ID查询出父对象并且包含子对象
     * @param pid
     * @return
     */
    List selectByPid(String pid);
}
