package com.navinfo.IMS.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dao.FixedAssetMapper;
import com.navinfo.IMS.entity.FixedAsset;
import com.navinfo.IMS.entity.FixedAssetExample;
import com.navinfo.IMS.service.FixedAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * 分页查询
     * @param map
     * @return
     */
    public PageInfo findFixedAssetByPage(Map map){
        Integer pageNum=Integer.valueOf((String) map.get("pageNum"));
        Integer pageSize=Integer.valueOf((String) map.get("pageSize"));
        String keyword=(String) map.get("keyword");
        PageHelper.startPage(pageNum,pageSize);
        FixedAssetExample example=new FixedAssetExample();
        if(keyword!=null&&!"".equals(keyword)){
            example.createCriteria().andAssetUserLike("%"+keyword+"%");
            example.or().andSectionLike("%"+keyword+"%");
        }
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
}
