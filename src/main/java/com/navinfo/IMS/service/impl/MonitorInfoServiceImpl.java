package com.navinfo.IMS.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dao.MonitorInfoMapper;
import com.navinfo.IMS.entity.MonitorInfo;
import com.navinfo.IMS.entity.MonitorInfoExample;
import com.navinfo.IMS.service.MonitorInfoService;
import com.navinfo.IMS.so.MonitorInfoSearch;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.IMS.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by luozhihui on 2017/11/26.
 */
@Service("monitorInfoService")
public class MonitorInfoServiceImpl implements MonitorInfoService {
    @Autowired
    private MonitorInfoMapper monitorInfoMapper;

    private MonitorInfoExample createSearchExample(MonitorInfoSearch search) {
        MonitorInfoExample example = new MonitorInfoExample();
        example.createCriteria();
        if (StringUtil.notNull(search.getVersion())) {
            example.getOredCriteria().get(0).andVersionEqualTo(search.getVersion());
        }
        if (StringUtil.notNull(search.getWorkType())) {
            example.getOredCriteria().get(0).andWorkTypeEqualTo(search.getWorkType());
        }
        if (StringUtil.notNull(search.getSection())) {
            example.getOredCriteria().get(0).andSectionEqualTo(search.getSection());
        }
        if (StringUtil.notNull(search.getWorkerId())) {
            example.getOredCriteria().get(0).andWorkerIdEqualTo(search.getWorkerId());
        }
        if (StringUtil.notNull(search.getWorker())) {
            example.getOredCriteria().get(0).andWorkerEqualTo(search.getWorker());
        }
        if (search.getMonitorDateStart() != null) {
            example.getOredCriteria().get(0).andMonitorDateGreaterThanOrEqualTo(search.getMonitorDateStart());
        }
        if (search.getMonitorDateEnd() != null) {
            example.getOredCriteria().get(0).andMonitorDateLessThanOrEqualTo(search.getMonitorDateEnd());
        }
        if (StringUtil.notNull(search.getKeyword())) {
            example.or().andVersionLike("%" + search.getKeyword() + "%");
            example.or().andWorkerLike("%" + search.getKeyword() + "%");
            example.or().andWorkTypeLike("%" + search.getKeyword() + "%");
        }
        return example;
    }
    /**
     * 根据查询条件分页查询
     * @param search
     * @param pageObject
     * @return
     */
    public PageInfo findMonitorInfosByPage(MonitorInfoSearch search, PageObject pageObject){
        MonitorInfoExample example=this.createSearchExample(search);
        PageHelper.startPage(pageObject.getPageNum(),pageObject.getPageSize());
        List<MonitorInfo> monitorInfoList=this.monitorInfoMapper.selectByExample(example);
        return new PageInfo(monitorInfoList);
    }

    /**
     * 计算监察合格率
     */
    private void calculateRate(MonitorInfo monitorInfo){
        //监察合格率（1-错误量/（监察量*3））*100%
        if (monitorInfo.getMonitorNum().compareTo(new BigDecimal(0))==1){
            monitorInfo.setmPassRate(new BigDecimal(1).subtract
                    (monitorInfo.getmErrorNum().divide
                            (monitorInfo.getMonitorNum().multiply
                                    (new BigDecimal(3)),4,BigDecimal.ROUND_HALF_UP)));
            monitorInfo.setmPassRate(monitorInfo.getmPassRate().multiply(new BigDecimal(100)).setScale(2));
        }
    }
    /**
     * 保存
     * @param monitorInfo
     * @return
     */
    public boolean saveMonitorInfo(MonitorInfo monitorInfo) {
        monitorInfo.setId(UUID.randomUUID().toString());
        this.calculateRate(monitorInfo);
        return this.monitorInfoMapper.insertSelective(monitorInfo)!=0;
    }

    /**
     * 根据主键查询一个或多个对象
     * @param id
     * @return
     */
    public List<MonitorInfo> findMonitorInfoById(String id) {
        List<String> ids= Arrays.asList(id.split(","));
        MonitorInfoExample example=new MonitorInfoExample();
        example.or().andIdIn(ids);
        return this.monitorInfoMapper.selectByExample(example);
    }

    /**
     * 根据主键更新数据
     * @param monitorInfo
     * @return
     */
    public boolean updateMonitorInfoById(MonitorInfo monitorInfo) {
        this.calculateRate(monitorInfo);
        return this.monitorInfoMapper.updateByPrimaryKeySelective(monitorInfo)!=0;
    }

    /**
     * 根据主键删除一个或多个对象
     * @param id
     * @return
     */
    public boolean deleteMonitorInfoById(String id) {
        List<String> ids= Arrays.asList(id.split(","));
        MonitorInfoExample example=new MonitorInfoExample();
        example.or().andIdIn(ids);
        return this.monitorInfoMapper.deleteByExample(example)!=0;
    }

    /**
     * 根据查询条件进行查询不分页
     * @param search
     * @return
     */
    public List<MonitorInfo> findMonitorInfosBySearch(MonitorInfoSearch search) {
        MonitorInfoExample example=this.createSearchExample(search);
        List<MonitorInfo> monitorInfoList=this.monitorInfoMapper.selectByExample(example);
        return monitorInfoList;
    }
}
