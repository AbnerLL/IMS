package com.navinfo.core.service;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.core.entity.DBFieldManage;
import com.navinfo.core.so.DBFieldManageSearch;

import java.util.List;

/**
 * Created by luozhihui on 2018/3/10.
 */
public interface DBFieldManageService {
    PageInfo findDBFieldManageByPage(DBFieldManageSearch search, PageObject pageObject);

    boolean saveDBFieldManage(DBFieldManage dbFieldManage);

    boolean updateDBFieldManage(DBFieldManage dbFieldManage);

    boolean deleteDBFieldManages(String ids);

    List<DBFieldManage> findDBFieldManage(DBFieldManageSearch search);
}
