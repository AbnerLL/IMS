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
        this.calCHPQPassRate(chPoiQualityList);
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
        this.calDIPassRate(deepInfoQualityList);
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
        this.calDIGPassRate(deepInfoGenQualityList);
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
            if (roadQuality.getAuditTotalNum().compareTo(BigDecimal.ZERO)==0){
                roadQuality.setPassRate("100%");
            }
            BigDecimal passRate=BigDecimal.ONE.subtract(roadQuality.getErrorTotalNum().divide(roadQuality.getAuditTotalNum().multiply(new BigDecimal(3)),3,BigDecimal.ROUND_HALF_UP));
            String passRateStr=BigDecimal.ONE.subtract(passRate).multiply(new BigDecimal(100))+"%";
            roadQuality.setPassRate(passRateStr);
        }
    }
    /**
     * 计算合格率(设施品质)
     * 合格率=1-中文地址错误量/中文地址质检量
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

    /**
     * 计算合格率（中文名称）
     * 合格率=1-错误量/质检量
     * @param chPoiQualityList
     */
    private void calCHPQPassRate(List<ChPoiQuality> chPoiQualityList) {
        for (ChPoiQuality chPoiQuality : chPoiQualityList){
            if (chPoiQuality.getAuditTotalNum().compareTo(BigDecimal.ZERO)==1){
                BigDecimal passRate=chPoiQuality.getErrorTotalNum().divide(chPoiQuality.getAuditTotalNum(),5,BigDecimal.ROUND_HALF_UP);
                String passRateString=BigDecimal.valueOf(100).subtract(passRate.multiply(new BigDecimal(100)))+"%";
                chPoiQuality.setPassRate(passRateString);
            }else {
                chPoiQuality.setPassRate("0.00%");
            }
        }
    }

    /**
     * 计算合格率（深度信息品质）
     * 合格率=1-通用错误量/（通用质检量*4）
     * 合格率=1-停车场错误量/（停车场质检量*6）
     * 合格率=1-汽车租赁错误量/（汽车租赁质检量*3）
     */
    private void calDIPassRate(List<DeepInfoQuality> deepInfoQualityList){
        for (DeepInfoQuality deepInfoQuality : deepInfoQualityList){
            if (deepInfoQuality.getComAuditNum().compareTo(BigDecimal.ZERO)==1){
                BigDecimal comRate=deepInfoQuality.getComErrorNum().divide(deepInfoQuality.getComAuditNum().multiply(new BigDecimal(4)),5,BigDecimal.ROUND_HALF_UP);
                String comRateString=BigDecimal.valueOf(100).subtract(comRate.multiply(new BigDecimal(100)))+"%";
                deepInfoQuality.setComRate(comRateString);
            }else {
                deepInfoQuality.setComRate("0.00%");
            }
            if (deepInfoQuality.getParkAuditNum().compareTo(BigDecimal.ZERO)==1){
                BigDecimal parkRate=deepInfoQuality.getParkErrorNum().divide(deepInfoQuality.getParkAuditNum().multiply(new BigDecimal(6)),5,BigDecimal.ROUND_HALF_UP);
                String parkRateString=BigDecimal.valueOf(100).subtract(parkRate.multiply(new BigDecimal(100)))+"%";
                deepInfoQuality.setParkRate(parkRateString);
            }else{
                deepInfoQuality.setParkRate("0.00%");
            }
            if (deepInfoQuality.getRentAuditNum().compareTo(BigDecimal.ZERO)==1){
                BigDecimal rentRate=deepInfoQuality.getRentErrorNum().divide(deepInfoQuality.getRentAuditNum().multiply(new BigDecimal(3)),5,BigDecimal.ROUND_HALF_UP);
                String rentRateString=BigDecimal.valueOf(100).subtract(rentRate.multiply(new BigDecimal(100)))+"%";
                deepInfoQuality.setRentRate(rentRateString);
            }else{
                deepInfoQuality.setRentRate("0.00%");
            }
        }
    }

    /**
     * 计算合格率（深度信息通用品质）
     */
    private void calDIGPassRate(List<DeepInfoGenQuality> deepInfoGenQualityList){

    }
}
