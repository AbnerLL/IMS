package com.navinfo.core.service;


import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.core.entity.SysUser;
import com.navinfo.core.so.SysUserSearch;

import java.util.List;

/**
 * 用户的实现类接口
 * Created by luozhihui on 2017/10/15.
 */
public interface SysUserService {

    boolean getSysUser(String username,String password);

    List getAllUser();

    boolean insertUser(SysUser sysUser);

    List<SysUser> getUserById(String userId);

    boolean updateUser(SysUser user);

    boolean deleteUser(String ids);

    /**
     * 根据查询条件分页查询
     * @return
     */
    PageInfo findSysUserByPage(SysUserSearch sysUserSearch,PageObject pageObject);
}
