package com.navinfo.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.IMS.utils.StringUtil;
import com.navinfo.core.dao.SysLogMapper;
import com.navinfo.core.entity.SysLog;
import com.navinfo.core.entity.SysLogExample;
import com.navinfo.core.service.SysLogService;
import com.navinfo.core.so.SysLogSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by luozhihui on 2018/2/24.
 */
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    /**
     * 构建查询对象
     * @return
     */
    private SysLogExample createSearchExample(SysLogSearch search){
        SysLogExample example=new SysLogExample();
        example.createCriteria();
        if (StringUtil.notNull(search.getUsername())){
            example.createCriteria().andUsernameEqualTo(search.getUsername());
        }
        if (StringUtil.notNull(search.getKeyword())){
            example.or().andUsernameLike("%"+search.getKeyword()+"%");
        }
        example.setOrderByClause("create_time desc");
        return example;
    }

    /**
     * 分页查询日志
     * @param search
     * @param pageObject
     * @return
     */
    public PageInfo findSysLogByPage(SysLogSearch search, PageObject pageObject) {
        SysLogExample example=this.createSearchExample(search);
        PageHelper.startPage(pageObject.getPageNum(),pageObject.getPageSize());
        List<SysLog> sysLogList=this.sysLogMapper.selectByExample(example);
        return new PageInfo(sysLogList);
    }

    /**
     * 插入日志
     * @param sysLog
     */
    public void insertSysLog(SysLog sysLog) {
        sysLog.setUuid(UUID.randomUUID().toString());
        sysLog.setCreateTime(new Date());
        this.sysLogMapper.insertSelective(sysLog);
    }
}
