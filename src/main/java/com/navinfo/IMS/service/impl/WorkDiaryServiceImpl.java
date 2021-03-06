package com.navinfo.IMS.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dao.WorkDiaryMapper;
import com.navinfo.IMS.entity.WorkDiary;
import com.navinfo.IMS.entity.WorkDiaryExample;
import com.navinfo.IMS.service.WorkDiaryService;
import com.navinfo.IMS.so.WorkDiarySearch;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.IMS.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by luozhihui on 2017/9/21.
 */
@Service("workDiaryService")
public class WorkDiaryServiceImpl implements WorkDiaryService{
    @Autowired
    private WorkDiaryMapper workDiaryMapper;

    /**
     * 根据页面参数生成一个查询的example
     * @return
     */
    private WorkDiaryExample createSearchExample(WorkDiarySearch workDiarySearch){
        WorkDiaryExample example=new WorkDiaryExample();
        example.createCriteria();
        if(StringUtil.notNull(workDiarySearch.getEmpId())){
            example.getOredCriteria().get(0).andEmpIdEqualTo(workDiarySearch.getEmpId());
        }
        if (StringUtil.notNull(workDiarySearch.getEmpName())){
            example.getOredCriteria().get(0).andEmpNameEqualTo(workDiarySearch.getEmpName());
        }
        if (StringUtil.notNull(workDiarySearch.getSection())){
            example.getOredCriteria().get(0).andSectionEqualTo(workDiarySearch.getSection());
        }
        if (StringUtil.notNull(workDiarySearch.getWorkType())){
            example.getOredCriteria().get(0).andWorkTypeEqualTo(workDiarySearch.getWorkType());
        }
        if (StringUtil.notNull(workDiarySearch.getWorkModule())){
            example.getOredCriteria().get(0).andWorkModuleEqualTo(workDiarySearch.getWorkModule());
        }
        if (workDiarySearch.getWorkDateStart()!=null){
            example.getOredCriteria().get(0).andWorkDateGreaterThanOrEqualTo(workDiarySearch.getWorkDateStart());
        }
        if (workDiarySearch.getWorkDateEnd()!=null){
            example.getOredCriteria().get(0).andWorkDateLessThanOrEqualTo(workDiarySearch.getWorkDateEnd());
        }
        if (StringUtil.notNull(workDiarySearch.getWorkTimeStart())){
            example.getOredCriteria().get(0).andWorkTimeStartGreaterThanOrEqualTo(workDiarySearch.getWorkTimeStart());
        }
        if (StringUtil.notNull(workDiarySearch.getWorkTimeEnd())){
            example.getOredCriteria().get(0).andWorkTimeEndLessThanOrEqualTo(workDiarySearch.getWorkTimeEnd());
        }
        if (StringUtil.notNull(workDiarySearch.getKeyword())){
            example.or().andEmpNameLike("%"+workDiarySearch.getKeyword()+"%");
            example.or().andSectionLike("%"+workDiarySearch.getKeyword()+"%");
        }
        example.setOrderByClause("work_date desc");
        return example;
    }
    /**
     * 分页查询
     * @return
     */
    public PageInfo findWorkDiaryByPage(WorkDiarySearch workDiarySearch, PageObject pageObject){
        WorkDiaryExample example=this.createSearchExample(workDiarySearch);
        PageHelper.startPage(pageObject.getPageNum(),pageObject.getPageSize());
        List list=workDiaryMapper.selectByExample(example);
        return new PageInfo(list);
    }

    /**
     * 根据ID获取对象
     * @param id
     * @return
     */
    public List<WorkDiary> findWorkDiaryById(String id){
        List idList= Arrays.asList(id.split(","));
        WorkDiaryExample example=new WorkDiaryExample();
        example.createCriteria().andIdIn(idList);
        return workDiaryMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param workDiary
     * @return
     */
    public boolean saveWorkDiary(WorkDiary workDiary){
        //生成主键
        workDiary.setId(UUID.randomUUID().toString());
        //计算工作时间
        if ( StringUtil.notNull(workDiary.getWorkTimeStart(),workDiary.getWorkTimeEnd())){
            Integer hourEnd = Integer.valueOf(workDiary.getWorkTimeEnd().substring(0,workDiary.getWorkTimeEnd().indexOf(":")));
            Integer hourStart = Integer.valueOf(workDiary.getWorkTimeStart().substring(0,workDiary.getWorkTimeStart().indexOf(":")));
            Integer minutesEnd = Integer.valueOf(workDiary.getWorkTimeEnd().substring(workDiary.getWorkTimeEnd().indexOf(":")+1));
            Integer minutesStart = Integer.valueOf(workDiary.getWorkTimeStart().substring(workDiary.getWorkTimeStart().indexOf(":")+1));
            BigDecimal diffHour = new BigDecimal(hourEnd - hourStart) ;
            //如果工作时间跨越12:00到13:00，则减去一个小时
            if (hourStart <= 12 && hourEnd >= 13){
                diffHour = diffHour.subtract(BigDecimal.ONE);
            }
            BigDecimal diffMinutes = new BigDecimal(minutesEnd).subtract(new BigDecimal(minutesStart)).divide(new BigDecimal(60),2,BigDecimal.ROUND_HALF_UP);
            workDiary.setWorkHours(diffHour.add(diffMinutes).toString());
        }
        //初始化创建人和时间(略)
        return workDiaryMapper.insertSelective(workDiary)!=0;
    }

    /**
     * 更新
     * @param workDiary
     * @return
     */
    public boolean updateWorkDiary(WorkDiary workDiary){
        //计算工作时间
        if ( StringUtil.notNull(workDiary.getWorkTimeStart(),workDiary.getWorkTimeEnd())){
            Integer hourEnd = Integer.valueOf(workDiary.getWorkTimeEnd().substring(0,workDiary.getWorkTimeEnd().indexOf(":")));
            Integer hourStart = Integer.valueOf(workDiary.getWorkTimeStart().substring(0,workDiary.getWorkTimeStart().indexOf(":")));
            Integer minutesEnd = Integer.valueOf(workDiary.getWorkTimeEnd().substring(workDiary.getWorkTimeEnd().indexOf(":")+1));
            Integer minutesStart = Integer.valueOf(workDiary.getWorkTimeStart().substring(workDiary.getWorkTimeStart().indexOf(":")+1));
            BigDecimal diffHour = new BigDecimal(hourEnd - hourStart) ;
            //如果工作时间跨越12:00到13:00，则减去一个小时
            if (hourStart <= 12 && hourEnd >= 13){
                diffHour = diffHour.subtract(BigDecimal.ONE);
            }
            BigDecimal diffMinutes = new BigDecimal(minutesEnd).subtract(new BigDecimal(minutesStart)).divide(new BigDecimal(60),2,BigDecimal.ROUND_HALF_UP);
            workDiary.setWorkHours(diffHour.add(diffMinutes).toString());
        }
        //初始化更新人和时间（略）
        return workDiaryMapper.updateByPrimaryKeySelective(workDiary)!=0;
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    public boolean deleteWorkDiary(String ids){
        List idList=Arrays.asList(ids.split(","));
        WorkDiaryExample example=new WorkDiaryExample();
        example.createCriteria().andIdIn(idList);
        return workDiaryMapper.deleteByExample(example)!=0;
    }

    /**
     * 根据查询条件获取对象
     * @param workDiarySearch
     * @return
     */
    public List<WorkDiary> findWorkDiaryBySearch(WorkDiarySearch workDiarySearch){
        WorkDiaryExample example=this.createSearchExample(workDiarySearch);
        return this.workDiaryMapper.selectByExample(example);
    }
}
