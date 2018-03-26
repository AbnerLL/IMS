package com.navinfo.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.IMS.utils.StringUtil;
import com.navinfo.core.dao.SysDictionaryMapper;
import com.navinfo.core.entity.SysDictionary;
import com.navinfo.core.entity.SysDictionaryExample;
import com.navinfo.core.service.SysDictionaryService;
import com.navinfo.core.so.SysDictionarySearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by luozhihui on 2018/3/25.
 */
@Service("sysDictionaryService")
public class SysDictionaryServiceImpl implements SysDictionaryService {
    @Autowired
    private SysDictionaryMapper sysDictionaryMapper;

    /**
     * 根据查询条件构造查询example
     * @param search
     * @return
     */
    private SysDictionaryExample createSearchExample(SysDictionarySearch search){
        SysDictionaryExample example = new SysDictionaryExample();
        example.createCriteria();
        if (StringUtil.notNull(search.getUuid())){
            example.getOredCriteria().get(0).andUuidEqualTo(search.getUuid());
        }
        if (StringUtil.notNull(search.getDictType())){
            example.getOredCriteria().get(0).andDictTypeEqualTo(search.getDictType());
        }
        example.setOrderByClause("dict_type,to_number(sort_index)");
        return example;
    }

    /**
     * 根据查询条件和分页条件进行分页查询
     * @param search
     * @param pageObject
     * @return
     */
    public PageInfo findSysDictionaryByPage(SysDictionarySearch search, PageObject pageObject){
        SysDictionaryExample example = this.createSearchExample(search);
        PageHelper.startPage(pageObject.getPageNum(),pageObject.getPageSize());
        List<SysDictionary> sysDictionaryList=this.sysDictionaryMapper.selectByExample(example);
        return new PageInfo(sysDictionaryList);
    }

    /**
     * 根据查询条件进行查询
     * @param search
     * @return
     */
    public List<SysDictionary> findSysDictionary(SysDictionarySearch search){
        SysDictionaryExample example = this.createSearchExample(search);
        return this.sysDictionaryMapper.selectByExample(example);
    }

    /**
     * 保存
     * @param sysDictionary
     * @return
     */
    public boolean saveSysDict(SysDictionary sysDictionary){
        sysDictionary.setUuid(UUID.randomUUID().toString());
        return this.sysDictionaryMapper.insertSelective(sysDictionary) !=0;
    }

    /**
     * 根据主键更新
     * @param sysDictionary
     * @return
     */
    public boolean updateSysDict(SysDictionary sysDictionary){
        return this.sysDictionaryMapper.updateByPrimaryKeySelective(sysDictionary) != 0;
    }

    /**
     * 根据主键删除一个或多个
     * @param ids
     * @return
     */
    public boolean deleteSysDict(String ids){
        List idList=Arrays.asList(ids.split(","));
        SysDictionaryExample example = new SysDictionaryExample();
        example.or().andUuidIn(idList);
        return this.sysDictionaryMapper.deleteByExample(example) != 0;
    }
}
