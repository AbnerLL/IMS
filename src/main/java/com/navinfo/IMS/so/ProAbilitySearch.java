package com.navinfo.IMS.so;

import com.navinfo.IMS.entity.ProAbility;

/**
 * 该类用于接收页面查询参数
 * Created by luozhihui on 2018/2/7.
 */
public class ProAbilitySearch extends ProAbility {
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
