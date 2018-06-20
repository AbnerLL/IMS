package com.navinfo.IMS.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dao.FixedAssetMapper;
import com.navinfo.IMS.entity.FixedAsset;
import com.navinfo.IMS.entity.FixedAssetExample;
import com.navinfo.IMS.service.FixedAssetService;
import com.navinfo.IMS.so.FixedAssetSearch;
import com.navinfo.IMS.utils.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by luozhihui on 2018/1/20.
 */
@Service("fixedAssetService")
public class FixedAssetServiceImpl implements FixedAssetService {
    @Autowired
    private FixedAssetMapper fixedAssetMapper;

    /**
     * 根据search构建example对象
     * @param search
     * @return
     */
    private FixedAssetExample createSearchExample(FixedAssetSearch search){
        FixedAssetExample example = new FixedAssetExample();
        example.createCriteria();
        if (!StringUtils.isEmpty(search.getSection())){
            example.getOredCriteria().get(0).andSectionEqualTo(search.getSection().trim());
        }
        if (!StringUtils.isEmpty(search.getAssetUser())){
            example.getOredCriteria().get(0).andAssetUserEqualTo(search.getAssetUser());
        }
        if (!StringUtils.isEmpty(search.getKeyword())){
            example.or().andSectionLike("%"+search.getKeyword()+"%");
        }
        return example;
    }
    /**
     * 分页查询
     * @param search
     * @param pageObject
     * @return
     */
    public PageInfo findFixedAssetByPage(FixedAssetSearch search, PageObject pageObject){
        FixedAssetExample example = this.createSearchExample(search);
        PageHelper.startPage(pageObject.getPageNum(),pageObject.getPageSize());
        List<FixedAsset> list=fixedAssetMapper.selectByExample(example);
        return new PageInfo(list);
    }

    /**
     * 新增
     * @param fixedAsset
     * @return
     */
    public boolean saveFixedAsset(FixedAsset fixedAsset){
        fixedAsset.setUuid(UUID.randomUUID().toString());
        return fixedAssetMapper.insertSelective(fixedAsset)!=0;
    }

    /**
     * 根据主键ID来获取对像
     * @param id
     * @return
     */
    public List<FixedAsset> findFixedAssetById(String id){
        List<String> idList= Arrays.asList(id.split(","));
        FixedAssetExample example=new FixedAssetExample();
        example.createCriteria().andUuidIn(idList);
        return fixedAssetMapper.selectByExample(example);
    }

    /**
     * 更新对象
     * @param fixedAsset
     * @return
     */
    public boolean updateFixedAsset(FixedAsset fixedAsset){
        return fixedAssetMapper.updateByPrimaryKeySelective(fixedAsset)!=0;
    }

    /**
     * 删除一个或多个对象
     * @param ids
     * @return
     */
    public boolean deleteFixedAsset(String ids){
        List<String> idList=Arrays.asList(ids.split(","));
        FixedAssetExample example=new FixedAssetExample();
        example.createCriteria().andUuidIn(idList);
        return fixedAssetMapper.deleteByExample(example)!=0;
    }

    /**
     * 根据条件查询所有数据
     * @param search
     * @return
     */
    public List<FixedAsset> findFixedAssetBySearch(FixedAssetSearch search) {
        FixedAssetExample example = this.createSearchExample(search);
        List<FixedAsset> fixedAssets = this.fixedAssetMapper.selectByExample(example);
        return fixedAssets;
    }

}
