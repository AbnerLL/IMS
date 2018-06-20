package com.navinfo.IMS.service;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.entity.FixedAsset;
import com.navinfo.IMS.so.FixedAssetSearch;
import com.navinfo.IMS.utils.PageObject;

import java.util.List;
import java.util.Map;

/**
 * Created by luozhihui on 2018/1/20.
 */
public interface FixedAssetService {
    PageInfo findFixedAssetByPage(FixedAssetSearch search, PageObject pageObject);

    boolean saveFixedAsset(FixedAsset fixedAsset);

    List<FixedAsset> findFixedAssetById(String id);

    boolean updateFixedAsset(FixedAsset fixedAsset);

    boolean deleteFixedAsset(String ids);

    List<FixedAsset> findFixedAssetBySearch(FixedAssetSearch search);
}
