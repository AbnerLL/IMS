package com.navinfo.IMS.service.impl;

import com.navinfo.IMS.dao.MenuMapper;
import com.navinfo.IMS.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单的业务处理实现类
 * Created by luozhihui on 2017/9/21.
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 用来初始化菜单的逻辑
     * 父级菜单的pid均为0
     * @return List
     */
    public List selectMenusByPid(){
        List menuList=menuMapper.selectByPid("0");
        return menuList;
    }
}
