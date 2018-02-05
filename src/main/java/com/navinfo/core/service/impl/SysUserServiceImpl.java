package com.navinfo.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.IMS.utils.StringUtil;
import com.navinfo.core.dao.SysUserMapper;
import com.navinfo.core.entity.SysUser;
import com.navinfo.core.entity.SysUserExample;
import com.navinfo.core.service.SysUserService;
import com.navinfo.core.so.SysUserSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 用户的业务实现类
 * Created by luozhihui on 2017/10/15.
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService{

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     *
     * @param sysUserSearch
     * @return
     */
    private SysUserExample createSearchExample(SysUserSearch sysUserSearch){
        SysUserExample example=new SysUserExample();
        example.createCriteria();
        if (StringUtil.notNull(sysUserSearch.getId())){
            example.getOredCriteria().get(0).andIdEqualTo(sysUserSearch.getId());
        }
        if (StringUtil.notNull(sysUserSearch.getNickname())){
            example.getOredCriteria().get(0).andNicknameEqualTo(sysUserSearch.getNickname());
        }
        if (StringUtil.notNull(sysUserSearch.getStatus())){
            example.getOredCriteria().get(0).andStatusEqualTo(sysUserSearch.getStatus());
        }
        if (sysUserSearch.getCreateTime()!=null){
            example.getOredCriteria().get(0).andCreateTimeGreaterThanOrEqualTo(sysUserSearch.getCreateTime());
        }
        if (StringUtil.notNull(sysUserSearch.getKeyword())){
            example.or().andIdLike("%"+sysUserSearch.getKeyword()+"%");
            example.or().andNicknameLike("%"+sysUserSearch.getKeyword()+"%");
        }
        return example;
    }
    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return boolean
     */
    public boolean getSysUser(String username,String password){
        SysUserExample example=new SysUserExample();
        example.or().andIdEqualTo(username).andPasswordEqualTo(password);
        List<SysUser> users=sysUserMapper.selectByExample(example);
        return users.size()!=0;
    }
    /**
     * 根据查询条件分页查询
     * @return
     */
    public PageInfo findSysUserByPage(SysUserSearch sysUserSearch, PageObject pageObject){
        SysUserExample sysUserExample=this.createSearchExample(sysUserSearch);
        sysUserExample.setOrderByClause("create_time asc");
        PageHelper.startPage(pageObject.getPageNum(),pageObject.getPageSize());
        return new PageInfo(this.sysUserMapper.selectByExample(sysUserExample));
    }
    /**
     * 查询所有数据
     * @return list
     */
    public List getAllUser(){
        SysUserExample sysUserExample=new SysUserExample();
        sysUserExample.setOrderByClause("create_time asc");
        return sysUserMapper.selectByExample(sysUserExample);
    }

    /**
     * 新增用户
     * @param sysUser 用户对象
     */
    public boolean insertUser(SysUser sysUser){
        //设置创建时间
        sysUser.setCreateTime(new Date());
        int count=sysUserMapper.insertSelective(sysUser);
        return count!=0;
    }

    /**
     * 根据主键获取用户
     * 可以根据分隔符来获取多位用户
     * @return SysUser
     */
    public List<SysUser> getUserById(String userId){
        List<String> idList=Arrays.asList(userId.split(","));
        SysUserExample example=new SysUserExample();
        example.createCriteria().andIdIn(idList);
        return this.sysUserMapper.selectByExample(example);
    }

    /**
     * 根据ID动态更新用户数据
     * @param user 用户对象
     * @return boolean
     */
    public boolean updateUser(SysUser user){
        int num=sysUserMapper.updateByPrimaryKeySelective(user);
        return num==1;
    }

    /**
     * 删除用户
     * @param ids 用户的id 一个或多个
     * @return boolean
     */
    public boolean deleteUser(String ids){
        List<String> list=Arrays.asList(ids.split(","));
        SysUserExample example=new SysUserExample();
        example.or().andIdIn(list);
        int num=sysUserMapper.deleteByExample(example);
        return num!=0;
    }
}
