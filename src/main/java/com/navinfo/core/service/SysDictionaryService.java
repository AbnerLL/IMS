package com.navinfo.core.service;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.core.entity.SysDictionary;
import com.navinfo.core.so.SysDictionarySearch;

import java.util.List;

/**
 * Created by luozhihui on 2018/3/25.
 */
public interface SysDictionaryService {

    PageInfo findSysDictionaryByPage(SysDictionarySearch search,PageObject pageObject);

    List<SysDictionary> findSysDictionary(SysDictionarySearch search);

    boolean saveSysDict(SysDictionary sysDictionary);

    boolean updateSysDict(SysDictionary sysDictionary);

    boolean deleteSysDict(String ids);
}
