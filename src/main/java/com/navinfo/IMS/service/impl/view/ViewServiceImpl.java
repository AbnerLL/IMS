package com.navinfo.IMS.service.impl.view;

import com.github.pagehelper.PageHelper;
import com.navinfo.IMS.dao.view.ViewMapper;
import com.navinfo.IMS.dto.view.WorkEfficiency;
import com.navinfo.IMS.entity.KPIReport;
import com.navinfo.IMS.entity.QuestionReport;
import com.navinfo.IMS.entity.WeekReport;
import com.navinfo.IMS.service.view.ViewService;
import com.navinfo.IMS.utils.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作业工效Service
 * Created by luozhihui on 2017/12/3.
 */
@Service("viewService")
public class ViewServiceImpl implements ViewService {
    public static Map<String,String> workTypeMap=new HashMap<String,String>();
    static{
        workTypeMap.put("RoadMark","道路图标");
        workTypeMap.put("ChPOI","中文名称");
        workTypeMap.put("ChAddress","中文地址");
        workTypeMap.put("EnPOI","英文名称");
        workTypeMap.put("EnAddress","英文地址");
        workTypeMap.put("DepthInfo","深度信息");
        workTypeMap.put("Agency","代理店");
    }
    @Autowired
    private ViewMapper viewMapper;

    public List findWorkEfficiency(Map map){
        String workType=(String)map.get("workType");
        //默认查道路图标
        if (workType==null||"".equals(workType)){
            workType="RoadMark";
            map.put("workType","RoadMark");
        }
        //作业类型的名称
//        workType=workTypeMap.get(workType);
        List<WorkEfficiency> list = viewMapper.calculateEfficiency(map);
        //计算工效
        try{
            for(WorkEfficiency workEfficiency:list){
                //设置作业类型
                workEfficiency.setWorkType(workType);
                //作业功效
                if(workEfficiency.getHours().compareTo(new BigDecimal(0))!=0){
                    workEfficiency.setWorkEfficiency(workEfficiency.getWorkNum().divide(workEfficiency.getHours().divide(new BigDecimal(8),3,BigDecimal.ROUND_HALF_UP),3,BigDecimal.ROUND_HALF_UP));
                }
                //质检工效
                if(workEfficiency.getHours2().compareTo(new BigDecimal(0))!=0){
                    workEfficiency.setAuditEfficiency(workEfficiency.getWorkNum2().divide(workEfficiency.getHours2().divide(new BigDecimal(8),3,BigDecimal.ROUND_HALF_UP),3,BigDecimal.ROUND_HALF_UP));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询周报数据
     * @param weekReport
     * @return
     */
    public List<WeekReport> findWeekReport(WeekReport weekReport){
        String workType = weekReport == null ? null : weekReport.getWorkType() != null ? weekReport.getWorkType() : null ;
        String year = weekReport == null ? null : weekReport.getYear() != null ? weekReport.getYear() : null ;
        List<WeekReport> weekReportList = viewMapper.selectWeekReports(workType , year,weekReport.getWorker(),weekReport.getSection());
        //计算合格率
        for (WeekReport wr : weekReportList){
            BigDecimal rate = BigDecimal.ONE.subtract(wr.getErrorNum().divide(wr.getAuditNum(),6,BigDecimal.ROUND_HALF_UP)).multiply(BigDecimal.valueOf(100));
            wr.setCorrectRate(rate.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
        }
        return weekReportList;
    }

    /**
     * 查询问题周报
     * @param questionReport
     * @return
     */
    public List<QuestionReport> findQuestionReport(QuestionReport questionReport){
        String workType = questionReport == null ? null : questionReport.getWorkType() != null ? questionReport.getWorkType() : null;
        String year = questionReport ==null ? null : questionReport.getYear() !=null ?questionReport.getYear() : null ;
        List<QuestionReport> questionReports = viewMapper.selectQuestionReport(workType,year,questionReport.getWorker(),questionReport.getSection());
        BigDecimal totalNum = BigDecimal.ZERO;
        //计算占比
        for (QuestionReport qr : questionReports){
            totalNum = totalNum.add(qr.getErrorNum());
        }
        for (QuestionReport qr : questionReports){
            qr.setErrorRate(qr.getErrorNum().divide(totalNum,5,BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100)).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
        }
        return questionReports;
    }

    /**
     * 查询KPI周报
     * @param kpiReport
     * @return
     */
    public List findKPIReport(KPIReport kpiReport) {
        String workType = kpiReport == null ? null : kpiReport.getWorkType() != null ? kpiReport.getWorkType() : null;
        String year = kpiReport ==null ? null : kpiReport.getYear() !=null ?kpiReport.getYear() : null ;
        List<KPIReport> kpiReportList = this.viewMapper.selectKPIReport(workType,year);
        //计算合格率
        for (KPIReport kr : kpiReportList){
            BigDecimal rate = BigDecimal.ONE.subtract(kr.getErrorNum().divide(kr.getAuditNum(),6,BigDecimal.ROUND_HALF_UP)).multiply(BigDecimal.valueOf(100));
            kr.setCorrectRate(rate.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
        }
        return kpiReportList;
    }
}
