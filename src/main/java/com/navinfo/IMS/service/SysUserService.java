package com.navinfo.IMS.service;


import com.navinfo.IMS.entity.SysUser;

import java.util.List;

/**
 * 用户的实现类接口
 * Created by luozhihui on 2017/10/15.
 */
public interface SysUserService {

    boolean getSysUser(String username,String password);

    List getAllUser();

    boolean insertUser(SysUser sysUser);

    SysUser getUserById(String userId);

    boolean updateUser(SysUser user);

    boolean deleteUser(String ids);
}
