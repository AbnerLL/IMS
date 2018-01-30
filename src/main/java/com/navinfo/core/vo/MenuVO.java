package com.navinfo.core.vo;

import com.navinfo.core.entity.SysModule;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单模块的数据展示类
 * Created by luozhihui on 2018/1/22.
 */
public class MenuVO extends SysModule{

    //子模块对象
    private List<SysModule> children=new ArrayList<SysModule>();

    public List<SysModule> getChildren() {
        return children;
    }

    public void setChildren(List<SysModule> children) {
        this.children = children;
    }
}
