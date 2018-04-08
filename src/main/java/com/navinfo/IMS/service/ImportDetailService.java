package com.navinfo.IMS.service;

import com.alibaba.fastjson.JSONObject;
import com.navinfo.IMS.dto.AccessDB;

import java.io.File;

/**
 * Created by luozhihui on 2018/3/28.
 */
public interface ImportDetailService {

    AccessDB getAccessFile(File file);

    JSONObject getImportOracleDetail();
}
