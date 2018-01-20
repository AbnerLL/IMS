package com.navinfo.IMS.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于配置常量
 * Created by luozhihui on 2017/12/3.
 */
public class Constant {
    public static Map<String,String> map=new HashMap<String,String>();
    static {
        map.put("RoadMark","道路图标");
        map.put("ChPOI","中文名称");
        map.put("ChAddress","中文地址");
        map.put("EnPOI","英文名称");
        map.put("EnAddress","英文地址");
        map.put("DepthInfo","深度信息");
        map.put("Agency","代理店");
    }
}
