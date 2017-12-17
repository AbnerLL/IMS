package com.navinfo.IMS.service.impl;

import com.navinfo.IMS.dao.SysPermissionMapper;
import com.navinfo.IMS.entity.SysPermission;
import com.navinfo.IMS.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限业务层接口实现类
 * Created by luozhihui on 2017/10/15.
 */
@Service("sysPermissionService")
public class SysPermissionImpl implements SysPermissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    /**
     * 查询所有权限列表所有数据
     * @return
     */
    public List findPermissionAll() {
        return sysPermissionMapper.selectByExample(null);
    }
}
