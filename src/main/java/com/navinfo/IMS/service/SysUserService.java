package com.navinfo.IMS.service;


import com.navinfo.IMS.entity.SysUser;

import java.util.List;

/**
 * 用户的实现类接口
 * Created by luozhihui on 2017/10/15.
 */
public interface SysUserService {

    public boolean getSysUser(String username,String password);

    public List getAllUser();

    public boolean insertUser(SysUser sysUser);

    public List<SysUser> getUserById(String userId);

    public boolean updateUser(SysUser user);

    public boolean deleteUser(String ids);
}
