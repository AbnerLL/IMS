package com.navinfo.IMS.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dao.WorkDiaryMapper;
import com.navinfo.IMS.entity.WorkDiary;
import com.navinfo.IMS.entity.WorkDiaryExample;
import com.navinfo.IMS.service.WorkDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by luozhihui on 2017/9/21.
 */
@Service("workDiaryService")
public class WorkDiaryServiceImpl implements WorkDiaryService{
    @Autowired
    private WorkDiaryMapper workDiaryMapper;

    /**
     * 分页查询
     * @return
     */
    public PageInfo findWorkDiaryByPage(Map map){
        Integer pageSize=Integer.valueOf((String) map.get("pageSize")) ;
        Integer pageNum=Integer.valueOf((String) map.get("pageNum"));
        String keyword=(String) map.get("keyword");
        PageHelper.startPage(pageNum,pageSize);
        WorkDiaryExample example=new WorkDiaryExample();
        if (map.get("empId")!=null){
            example.createCriteria().andEmpIdEqualTo((String)map.get("empId"));
        }
        if (map.get("empName")!=null){
            example.createCriteria().andEmpNameEqualTo((String) map.get("empName"));
        }
        if (map.get("workDateStart")!=null){
            example.createCriteria().andWorkDateGreaterThanOrEqualTo((Date) map.get("workDateStart"));
        }
        if (map.get("workDateEnd")!=null){
            example.createCriteria().andWorkDateLessThanOrEqualTo((Date) map.get("workDateEnd"));
        }
        if(keyword!=null&&!"".equals(keyword)){
            example.or().andEmpNameLike("%"+keyword+"%");
            example.or().andSectionLike("%"+keyword+"%");
        }
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
        //初始化创建人和时间(略)
        return workDiaryMapper.insertSelective(workDiary)!=0;
    }

    /**
     * 更新
     * @param workDiary
     * @return
     */
    public boolean updateWorkDiary(WorkDiary workDiary){
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
}
