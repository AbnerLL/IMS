package com.navinfo.IMS.service.impl;

import com.navinfo.IMS.dao.PreGradeMapper;
import com.navinfo.IMS.entity.PreGrade;
import com.navinfo.IMS.entity.PreGradeExample;
import com.navinfo.IMS.service.PreGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by luozhihui on 2017/9/21.
 */
@Service("preGradeService")
public class PreGradeServiceImpl implements PreGradeService{
    @Autowired
    private PreGradeMapper preGradeMapper;

    public List findPreGrades(){
        List list=preGradeMapper.selectByExample(null);
        return list;
    }

    /**
     * 新增
     * @param preGrade
     * @return
     */
    public boolean insertPreGrade(PreGrade preGrade){
        preGrade.setId(UUID.randomUUID().toString());
        int num=preGradeMapper.insertSelective(preGrade);
        return num!=0;
    }

    /**
     * 根据主键获取对象
     * @param id
     * @return
     */
    public PreGrade getPreGradeById(String id){
        PreGrade preGrade=preGradeMapper.selectByPrimaryKey(id);
        return preGrade;
    }

    /**
     * 根据主键来更新对象
     * @param preGrade
     * @return
     */
    public boolean updatePreGrade(PreGrade preGrade){
        int num=preGradeMapper.updateByPrimaryKeySelective(preGrade);
        return num!=0;
    }

    /**
     * 根据主键删除一条或多条数据
     * @param ids
     * @return
     */
    public boolean deletePreGradeById(String ids){
        List idList= Arrays.asList(ids.split(","));
        PreGradeExample preGradeExample=new PreGradeExample();
        preGradeExample.or().andIdIn(idList);
        int num=preGradeMapper.deleteByExample(preGradeExample);
        return num!=0;
    }
}
