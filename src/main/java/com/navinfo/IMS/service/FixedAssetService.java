package com.navinfo.IMS.service;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.entity.FixedAsset;

import java.util.List;
import java.util.Map;

/**
 * Created by luozhihui on 2018/1/20.
 */
public interface FixedAssetService {
    PageInfo findFixedAssetByPage(Map map);

    boolean saveFixedAsset(FixedAsset fixedAsset);

    List<FixedAsset> findFixedAssetById(String id);

    boolean updateFixedAsset(FixedAsset fixedAsset);

    boolean deleteFixedAsset(String ids);
}
