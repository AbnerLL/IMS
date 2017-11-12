package com.navinfo.IMS.service.impl;

import com.navinfo.IMS.dao.WorkDataMapper;
import com.navinfo.IMS.entity.WorkData;
import com.navinfo.IMS.entity.WorkDataExample;
import com.navinfo.IMS.service.WorkDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 作业统计业务层实现类
 * Created by luozhihui on 2017/10/15.
 */
@Service("workDataService")
public class WorkDataServiceImpl implements WorkDataService {
    @Autowired
    private WorkDataMapper workDataMapper;

    public List findWorkDatas(){
        List list=workDataMapper.selectByExample(null);
        return list;
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public WorkData findWorkDataById(String id){
        WorkData workData=workDataMapper.selectByPrimaryKey(id);
        return workData;
    }
    /**
     * 新增作业数据
     * 后台计算比率
     * @param workData
     * @return
     */
    public boolean insertWorkData(WorkData workData){
        workData.setWorkDataId(UUID.randomUUID().toString());
        if (!workData.getQuality().equals(0)){
            workData.setqRate(workData.getQuality().subtract(workData.getqError()).divide(workData.getQuality(),5,BigDecimal.ROUND_CEILING));
        }
        if(!workData.getMonitor().equals(0)){
            workData.setmRate(workData.getMonitor().subtract(workData.getmError()).divide(workData.getMonitor(),5,BigDecimal.ROUND_CEILING));
        }
        int num=workDataMapper.insertSelective(workData);
        return num!=0;
    }

    /**
     * 编辑作业数据
     * @param workData
     * @return
     */
    public boolean updateWorkData(WorkData workData){
        if (!workData.getQuality().equals(0)){
            workData.setqRate(workData.getQuality().subtract(workData.getqError()).divide(workData.getQuality(),5,BigDecimal.ROUND_CEILING));
        }
        if(!workData.getMonitor().equals(0)){
            workData.setmRate(workData.getMonitor().subtract(workData.getmError()).divide(workData.getMonitor(),5,BigDecimal.ROUND_CEILING));
        }
        int num=workDataMapper.updateByPrimaryKeySelective(workData);
        return num!=0;
    }

    /**
     * 删除作业数据
     * @param id
     * @return
     */
    public boolean deleteWorkData(String id){
        List ids= Arrays.asList(id.split(","));
        WorkDataExample example=new WorkDataExample();
        example.or().andWorkDataIdIn(ids);
        int num=workDataMapper.deleteByExample(example);
        return num!=0;
    }
}
