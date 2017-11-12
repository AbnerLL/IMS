package com.navinfo.IMS.service.impl;

import com.navinfo.IMS.dao.ProAbilityMapper;
import com.navinfo.IMS.service.ProAbilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务统计表的业务实现类
 * Created by luozhihui on 2017/9/21.
 */
@Service("proAbilityService")
public class ProAbilityServiceImpl implements ProAbilityService{
    @Autowired
    private ProAbilityMapper proAbilityMapper;

    /**
     * 获取分页数据
     * @return
     */
    public List findProAbilities(){
        List list=proAbilityMapper.selectByExample(null);
        return list;
    }
}
