package com.navinfo.IMS.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dao.ProAbilityMapper;
import com.navinfo.IMS.entity.ProAbility;
import com.navinfo.IMS.entity.ProAbilityExample;
import com.navinfo.IMS.service.ProAbilityService;
import com.navinfo.IMS.so.ProAbilitySearch;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.IMS.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 业务统计表的业务实现类
 * Created by luozhihui on 2017/9/21.
 */
@Service("proAbilityService")
public class ProAbilityServiceImpl implements ProAbilityService{
    @Autowired
    private ProAbilityMapper proAbilityMapper;

    /**
     * 更具查询参数构建查询example
     * @param search
     * @return
     */
    private ProAbilityExample createSearchExample(ProAbilitySearch search){
        ProAbilityExample example=new ProAbilityExample();
        example.createCriteria();
        if (StringUtil.notNull(search.getVersion())){
            example.getOredCriteria().get(0).andVersionEqualTo(search.getVersion());
        }
        if (StringUtil.notNull(search.getEmpId())){
            example.getOredCriteria().get(0).andEmpIdEqualTo(search.getEmpId());
        }
        if (StringUtil.notNull(search.getEmpName())){
            example.getOredCriteria().get(0).andEmpNameEqualTo(search.getEmpName());
        }
        if (StringUtil.notNull(search.getSection())){
            example.getOredCriteria().get(0).andSectionEqualTo(search.getSection());
        }
        if (StringUtil.notNull(search.getProStage())){
            example.getOredCriteria().get(0).andProStageEqualTo(search.getProStage());
        }
        if (StringUtil.notNull(search.getProType())){
            example.getOredCriteria().get(0).andProTypeEqualTo(search.getProType());
        }
        if (StringUtil.notNull(search.getLogAbility())){
            example.getOredCriteria().get(0).andLogAbilityEqualTo(search.getLogAbility());
        }
        if (StringUtil.notNull(search.getTestAbility())){
            example.getOredCriteria().get(0).andTestAbilityEqualTo(search.getTestAbility());
        }
        if (StringUtil.notNull(search.getKeyword())){
            example.or().andEmpNameLike("%"+search.getKeyword()+"%");
            example.or().andSectionLike("%"+search.getKeyword()+"%");
        }
        return example;
    }
    /**
     * 根据查询参数分页查询
     * @param search
     * @param pageObject
     * @return
     */
    public PageInfo findProAbilitiesByPage(ProAbilitySearch search, PageObject pageObject) {
        ProAbilityExample example=this.createSearchExample(search);
        PageHelper.startPage(pageObject.getPageNum(),pageObject.getPageSize());
        List<ProAbility> proAbilityList=this.proAbilityMapper.selectByExample(example);
        return new PageInfo(proAbilityList);
    }

    /**
     * 根据查询条件不分页查询
     * @param search
     * @return
     */
    public List<ProAbility> findProAbilitiesBySearch(ProAbilitySearch search){
        ProAbilityExample example=this.createSearchExample(search);
        return this.proAbilityMapper.selectByExample(example);
    }

    /**
     * 保存用户对象
     * @param proAbility
     * @return
     */
    public boolean saveProAbility(ProAbility proAbility) {
        proAbility.setUuid(UUID.randomUUID().toString());
        return this.proAbilityMapper.insertSelective(proAbility)!=0;
    }

    /**
     * 根据ID查询一个或多个对象
     * @param id
     * @return
     */
    public List<ProAbility> findProAbilitiesById(String id) {
        List<String> idList= Arrays.asList(id.split(","));
        ProAbilityExample example=new ProAbilityExample();
        example.or().andUuidIn(idList);
        return this.proAbilityMapper.selectByExample(example);
    }

    /**
     * 根据主键更新
     * @param proAbility
     * @return
     */
    public boolean updateProAbilityById(ProAbility proAbility) {
        return this.proAbilityMapper.updateByPrimaryKeySelective(proAbility)!=0;
    }

    /**
     * 根据主键删除一个或多个对象
     * @param ids
     * @return
     */
    public boolean deleteProAbilitiesById(String ids) {
        List<String> idList= Arrays.asList(ids.split(","));
        ProAbilityExample example=new ProAbilityExample();
        example.or().andUuidIn(idList);
        return this.proAbilityMapper.deleteByExample(example)!=0;
    }
}
