package com.navinfo.core.service.impl;

import com.navinfo.core.dao.SysGroupMapper;
import com.navinfo.core.entity.SysGroup;
import com.navinfo.core.entity.SysGroupExample;
import com.navinfo.core.service.SysGroupService;
import com.navinfo.core.so.SysGroupSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by luozhihui on 2018/3/5.
 */
@Service("sysGroupService")
public class SysGroupServiceImpl implements SysGroupService {
    @Autowired
    private SysGroupMapper sysGroupMapper;

    /**
     * 根据查询条件构建查询对象
     * @param search
     * @return
     */
    private SysGroupExample createSearchExample(SysGroupSearch search){
        SysGroupExample example = new SysGroupExample();
        example.createCriteria();
        if (!StringUtils.isEmpty(search.getGroupName())){
            example.getOredCriteria().get(0).andGroupNameEqualTo(search.getGroupName().trim());
        }
        if (!StringUtils.isEmpty(search.getGroupType())){
            example.getOredCriteria().get(0).andGroupTypeEqualTo(search.getGroupType().trim());
        }
        example.setOrderByClause("group_code,sort_index");
        return example;
    }
    /**
     * 根据条件查询
     * @return
     */
    public List<SysGroup> findSysGroup(SysGroupSearch search){
        SysGroupExample example = this.createSearchExample(search);
        return this.sysGroupMapper.selectByExample(example);
    }
}
