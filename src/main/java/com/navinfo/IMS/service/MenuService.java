package com.navinfo.IMS.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 菜单的业务处理接口
 * Created by luozhihui on 2017/9/21.
 */
public interface MenuService {

    public List selectMenusByPid();

    PageInfo findMenuByPage(Map map);
}
