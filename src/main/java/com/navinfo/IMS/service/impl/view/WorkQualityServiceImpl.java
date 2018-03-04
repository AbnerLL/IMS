package com.navinfo.IMS.service.impl.view;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dao.view.WorkQualityMapper;
import com.navinfo.IMS.dto.view.*;
import com.navinfo.IMS.service.view.WorkQualityService;
import com.navinfo.IMS.so.*;
import com.navinfo.IMS.utils.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by luozhihui on 2018/2/27.
 */
@Service("workQualityService")
public class WorkQualityServiceImpl implements WorkQualityService {
    @Autowired
    private WorkQualityMapper workQualityMapper;

    /**
     * 分页查询（道路品质）
     * @param search
     * @param pageObject
     * @return
     */
    public PageInfo findRoadQualityByPage(RoadQualitySearch search, PageObject pageObject) {
        PageHelper.startPage(pageObject.getPageNum(),pageObject.getPageSize());
        List<RoadQuality> roadQualityList=this.workQualityMapper.selectRoadQuality(search);
        //计算合格率（1-错误量/（质检量*3））*100%
        this.calRQPassRate(roadQualityList);
        return new PageInfo(roadQualityList);
    }

    /**
     * 分页查询（设施品质）
     * @param search
     * @param pageObject
     * @return
     */
    public PageInfo findPoiQualityByPage(PoiQualitySearch search, PageObject pageObject) {
        PageHelper.startPage(pageObject.getPageNum(),pageObject.getPageSize());
        List<PoiQuality> poiQualityList=this.workQualityMapper.selectPoiQuality(search);
        //计算合格率
        this.calPQPassRate(poiQualityList);
        return new PageInfo(poiQualityList);
    }

    /**
     * 分页查询（中文名称品质）
     * @param search
     * @param pageObject
     * @return
     */
    public PageInfo findChPoiQualityByPage(ChPoiQualitySearch search, PageObject pageObject) {
        PageHelper.startPage(pageObject.getPageNum(),pageObject.getPageSize());
        List<ChPoiQuality> chPoiQualityList=this.workQualityMapper.selectChPoiQuality(search);
        //计算率
        return new PageInfo(chPoiQualityList);
    }

    /**
     * 分页查询（深度信息品质）
     * @param search
     * @param pageObject
     * @return
     */
    public PageInfo findDeepInfoQualityByPage(DeepInfoQualitySearch search, PageObject pageObject) {
        PageHelper.startPage(pageObject.getPageNum(),pageObject.getPageSize());
        List<DeepInfoQuality> deepInfoQualityList=this.workQualityMapper.selectDeepInfoQuality(search);
        //计算率
        return new PageInfo(deepInfoQualityList);
    }

    /**
     * 分页查询（深度信息-通用品质）
     * @param search
     * @param pageObject
     * @return
     */
    public PageInfo findDeepInfoGenQualityByPage(DeepInfoGenQualitySearch search, PageObject pageObject) {
        PageHelper.startPage(pageObject.getPageNum(),pageObject.getPageSize());
        List<DeepInfoGenQuality> deepInfoGenQualityList=this.workQualityMapper.selectDeepInfoGenQuality(search);
        //计算率

        return new PageInfo(deepInfoGenQualityList);
    }

    public List<RoadQuality> findRoadQuality(RoadQualitySearch search) {
        return this.workQualityMapper.selectRoadQuality(search);
    }

    public List<PoiQuality> findPoiQuality(PoiQualitySearch search) {
        return this.workQualityMapper.selectPoiQuality(search);
    }

    public List<ChPoiQuality> findChPoiQuality(ChPoiQualitySearch search) {
        return this.workQualityMapper.selectChPoiQuality(search);
    }

    public List<DeepInfoQuality> findDeepInfoQuality(DeepInfoQualitySearch search) {
        return this.workQualityMapper.selectDeepInfoQuality(search);
    }

    public List<DeepInfoGenQuality> findDeepInfoGenQuality(DeepInfoGenQualitySearch search) {
        return this.workQualityMapper.selectDeepInfoGenQuality(search);
    }

    /**
     * 计算合格率(道路品质)
     * @return
     */
    private void calRQPassRate(List<RoadQuality> roadQualityList){
        for (RoadQuality roadQuality:roadQualityList){
            if (roadQuality.getAuditTotalNum().compareTo(new BigDecimal(0))==0){
                roadQuality.setPassRate("100%");
            }
            BigDecimal passRate=new BigDecimal(1).subtract(roadQuality.getErrorTotalNum().divide(roadQuality.getAuditTotalNum().multiply(new BigDecimal(3)),3,BigDecimal.ROUND_HALF_UP));
            String passRateStr=new BigDecimal(1).subtract(passRate).multiply(new BigDecimal(100))+"%";
            roadQuality.setPassRate(passRateStr);
        }
    }
    /**
     * 计算合格率(道路品质)
     * @return
     */
    private void calPQPassRate(List<PoiQuality> poiQualityList){
        for (PoiQuality poiQuality:poiQualityList){
            //计算中文名称合格率
            if (poiQuality.getCpAuditNum().compareTo(BigDecimal.ZERO)==1){
                poiQuality.setCpPassRate(BigDecimal.ONE.subtract(poiQuality.getCpErrorNum().divide(poiQuality.getCpAuditNum(),5,BigDecimal.ROUND_HALF_UP)).multiply(BigDecimal.valueOf(100)).setScale(2,BigDecimal.ROUND_HALF_UP)+"%");
            }else{
                poiQuality.setCpPassRate("0.00%");
            }
            //计算中文地址合格率
            if (poiQuality.getCaAuditNum().compareTo(BigDecimal.ZERO)==1){
                poiQuality.setCaPassRate(BigDecimal.ONE.subtract(poiQuality.getCaErrorNum().divide(poiQuality.getCaAuditNum(),5,BigDecimal.ROUND_HALF_UP)).multiply(BigDecimal.valueOf(100)).setScale(2,BigDecimal.ROUND_HALF_UP)+"%");
            }else{
                poiQuality.setCaPassRate("0.00%");
            }
            //计算英文名称合格率
            if (poiQuality.getEpAuditNum().compareTo(BigDecimal.ZERO)==1){
                poiQuality.setEpPassRate(BigDecimal.ONE.subtract(poiQuality.getEpErrorNum().divide(poiQuality.getEpAuditNum(),5,BigDecimal.ROUND_HALF_UP)).multiply(BigDecimal.valueOf(100)).setScale(2,BigDecimal.ROUND_HALF_UP)+"%");
            }else{
                poiQuality.setEpPassRate("0.00%");
            }
            //计算英文地址合格率
            if (poiQuality.getEaAuditNum().compareTo(BigDecimal.ZERO)==1){
                poiQuality.setEaPassRate(BigDecimal.ONE.subtract(poiQuality.getEaErrorNum().divide(poiQuality.getEaAuditNum(),5,BigDecimal.ROUND_HALF_UP)).multiply(BigDecimal.valueOf(100)).setScale(2,BigDecimal.ROUND_HALF_UP)+"%");
            }else{
                poiQuality.setEaPassRate("0.00%");
            }
        }
    }
}
