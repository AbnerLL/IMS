package com.navinfo.IMS.utils;

import java.util.*;

/**
 * 工具类
 * Created by luozhihui on 2018/1/21.
 */
public class ListUtils {

    /**
     * 将list转化为set
     * 注：需要重写list中对象的equals方法
     * @param list
     * @return
     */
    public static Set ListToSet(List list){
        Set set=new HashSet();
        for(Object listObj:list){
            //默认添加数据
            boolean addFlag=true;
            for(Object setObj:set){
                if(setObj.equals(listObj)){
                    addFlag=false;
                    break;
                }
            }
            if(addFlag){
                set.add(listObj);
            }
        }
        return set;
    }
}
