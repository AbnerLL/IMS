package com.navinfo.IMS.service;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.entity.ProAbility;
import com.navinfo.IMS.so.ProAbilitySearch;
import com.navinfo.IMS.utils.PageObject;

import java.util.List;

/**
 * Created by luozhihui on 2017/9/21.
 */
public interface ProAbilityService {

    PageInfo findProAbilitiesByPage(ProAbilitySearch search, PageObject pageObject);

    boolean saveProAbility(ProAbility proAbility);

    List<ProAbility> findProAbilitiesById(String id);

    boolean updateProAbilityById(ProAbility proAbility);

    boolean deleteProAbilitiesById(String ids);

    /**
     * 根据查询条件不分页查询
     * @param search
     * @return
     */
    List<ProAbility> findProAbilitiesBySearch(ProAbilitySearch search);

}
