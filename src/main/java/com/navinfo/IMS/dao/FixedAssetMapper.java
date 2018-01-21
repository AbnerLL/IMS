package com.navinfo.IMS.dao;

import com.navinfo.IMS.entity.FixedAsset;
import com.navinfo.IMS.entity.FixedAssetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FixedAssetMapper {
    long countByExample(FixedAssetExample example);

    int deleteByExample(FixedAssetExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(FixedAsset record);

    int insertSelective(FixedAsset record);

    List<FixedAsset> selectByExample(FixedAssetExample example);

    FixedAsset selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") FixedAsset record, @Param("example") FixedAssetExample example);

    int updateByExample(@Param("record") FixedAsset record, @Param("example") FixedAssetExample example);

    int updateByPrimaryKeySelective(FixedAsset record);

    int updateByPrimaryKey(FixedAsset record);
}