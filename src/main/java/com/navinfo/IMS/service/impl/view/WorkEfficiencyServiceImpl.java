package com.navinfo.IMS.service.impl.view;

import com.navinfo.IMS.dao.view.WorkEfficiencyMapper;
import com.navinfo.IMS.dto.view.WorkEfficiency;
import com.navinfo.IMS.service.view.WorkEfficiencyService;
import com.navinfo.IMS.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 作业工效Service
 * Created by luozhihui on 2017/12/3.
 */
@Service("workEfficiencyService")
public class WorkEfficiencyServiceImpl implements WorkEfficiencyService{
    @Autowired
    private WorkEfficiencyMapper workEfficiencyMapper;

    public List findWorkEfficiency(Map map){
        String workType=(String)map.get("workType");
        //默认查道路图标
        if (workType==null||"".equals(workType)){
            workType="RoadMark";
            map.put("workType","RoadMark");
        }
        //作业类型的名称
        workType=Constant.map.get(workType);
        List<WorkEfficiency> list =workEfficiencyMapper.calculateEfficiency(map);
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
}
