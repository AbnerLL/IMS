package com.navinfo.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.IMS.utils.StringUtil;
import com.navinfo.core.dao.DBFieldManageMapper;
import com.navinfo.core.entity.DBFieldManage;
import com.navinfo.core.entity.DBFieldManageExample;
import com.navinfo.core.service.DBFieldManageService;
import com.navinfo.core.so.DBFieldManageSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by luozhihui on 2018/3/10.
 */
@Service("dBFieldManageService")
public class DBFieldManageServiceImpl implements DBFieldManageService {
    @Autowired
    private DBFieldManageMapper dbFieldManageMapper;

    private DBFieldManageExample createSearchExample(DBFieldManageSearch search){
        DBFieldManageExample example=new DBFieldManageExample();
        example.createCriteria();
        if (StringUtil.notNull(search.getTableName())){
            example.getOredCriteria().get(0).andTableNameEqualTo(search.getTableName());
        }
        example.setOrderByClause("sort_index");
        return example;
    }

    /**
     * 根据查询条件分页查询
     * @param search
     * @param pageObject
     * @return
     */
    public PageInfo findDBFieldManageByPage(DBFieldManageSearch search, PageObject pageObject) {
        DBFieldManageExample example = this.createSearchExample(search);
        PageHelper.startPage(pageObject.getPageNum(),pageObject.getPageSize());
        List<DBFieldManage> dbFieldManageList = this.dbFieldManageMapper.selectByExample(example);
        return new PageInfo(dbFieldManageList);
    }

    /**
     * 新增
     * @param dbFieldManage
     * @return
     */
    public boolean saveDBFieldManage(DBFieldManage dbFieldManage) {
        dbFieldManage.setUuid(UUID.randomUUID().toString());
        return this.dbFieldManageMapper.insertSelective(dbFieldManage) != 0;
    }

    /**
     * 更新
     * @param dbFieldManage
     * @return
     */
    public boolean updateDBFieldManage(DBFieldManage dbFieldManage) {
        return this.dbFieldManageMapper.updateByPrimaryKeySelective(dbFieldManage) != 0;

    }

    /**
     * 删除一个或多个对象
     * @param ids
     * @return
     */
    public boolean deleteDBFieldManages(String ids) {
        List idList=Arrays.asList(ids.split(","));
        DBFieldManageExample example = new DBFieldManageExample();
        example.or().andUuidIn(idList);
        return this.dbFieldManageMapper.deleteByExample(example) != 0;
    }

    /**
     * 根据查询条件不分页查询
     * @param search
     * @return
     */
    public List<DBFieldManage> findDBFieldManage(DBFieldManageSearch search) {
        DBFieldManageExample example = this.createSearchExample(search);
        List<DBFieldManage> dbFieldManageList = this.dbFieldManageMapper.selectByExample(example);
        return dbFieldManageList;
    }
}
