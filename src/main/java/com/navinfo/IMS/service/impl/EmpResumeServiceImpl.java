package com.navinfo.IMS.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dao.EmpResumeMapper;
import com.navinfo.IMS.entity.EmpResume;
import com.navinfo.IMS.entity.EmpResumeExample;
import com.navinfo.IMS.service.EmpResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by luozhihui on 2017/12/21.
 */
@Service("empResumeService")
public class EmpResumeServiceImpl implements EmpResumeService {

    @Autowired
    private EmpResumeMapper empResumeMapper;

    /**
     * 根据条件进行分页查询
     * @param map
     * @return
     */
    public PageInfo findEmpResumeByPage(Map map){
        Integer pageSize=new Integer((String)map.get("pageSize"));
        Integer pageNum=new Integer((String) map.get("pageNum"));
        String keyword=(String) map.get("keyword");
        EmpResumeExample example=new EmpResumeExample();
        if(keyword!=null&&!"".equals(keyword)){
            example.createCriteria().andEmpIdLike("%"+keyword+"%");
            example.or().andEmpNameLike("%"+keyword+"%");
        }
        PageHelper.startPage(pageNum,pageSize);
        List empResumes=empResumeMapper.selectByExample(example);
        return new PageInfo(empResumes);
    }

    /**
     * 根据id获取对象
     * @return
     */
    public List<EmpResume> findWorkDiaryById(String id){
        List idList= Arrays.asList(id.split(","));
        EmpResumeExample example=new EmpResumeExample();
        example.createCriteria().andResumeIdIn(idList);
        return empResumeMapper.selectByExample(example);
    }

    /**
     *  保存
     * @param empResume
     * @return
     */
    public boolean saveEmpResume(EmpResume empResume){
        empResume.setResumeId(UUID.randomUUID().toString());
        return empResumeMapper.insertSelective(empResume)!=0;
    }

    /**
     * 更新
     * @param empResume
     * @return
     */
    public boolean updateEmpResume(EmpResume empResume){
        return empResumeMapper.updateByPrimaryKeySelective(empResume)!=0;
    }

    /**
     * 删除一个或多个对象
     * @param ids
     * @return
     */
    public boolean deleteEmpResume(String ids){
        List<String> idList=Arrays.asList(ids.split(","));
        EmpResumeExample example=new EmpResumeExample();
        example.createCriteria().andResumeIdIn(idList);
        return empResumeMapper.deleteByExample(example)!=0;
    }
}
