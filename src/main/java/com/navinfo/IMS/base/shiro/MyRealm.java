package com.navinfo.IMS.base.shiro;

import com.navinfo.core.entity.SysPermission;
import com.navinfo.core.entity.SysRole;
import com.navinfo.core.entity.SysUser;
import com.navinfo.core.service.SysPermissionService;
import com.navinfo.core.service.SysRoleService;
import com.navinfo.core.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 自定义的realm类
 * Created by luozhihui on 2017/11/13.
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysPermissionService sysPermissionService;
    /**
     * 授权（第二步）
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("shrio授权doGetAuthorizationInfo......");
        String userName=(String)principalCollection.getPrimaryPrincipal();
        //角色集合
        Set<String> roleSet=new HashSet<String>();
        //权限集合
        Set<String> permissionSet=new HashSet<String>();
        //获取角色(已经初始化权限)
        Set<SysRole> sysRoleSet=this.sysRoleService.findSysRoleByUsername(userName);
        Iterator<SysRole> it=sysRoleSet.iterator();
        while (it.hasNext()){
            SysRole sysRole=it.next();
            roleSet.add(sysRole.getRoleId());
            for(SysPermission sysPermission:sysRole.getPermissions()){
                permissionSet.add(sysPermission.getPermissionName());
            }
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roleSet);
        simpleAuthorizationInfo.addStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证（第一步）
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("shrio认证doGetAuthenticationInfo......");
        //1.转化token（用来存放页面传来的用户名和密码）
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        //2.根据token来获取用户名
        String username=token.getUsername();
        //3.查询出数据库中的该对象
        List<SysUser> users=sysUserService.getUserById(username);
        if(users!=null&&users.size()>0){
            return new SimpleAuthenticationInfo(users.get(0).getId(),users.get(0).getPassword(),this.getName());
        }
        return null;
    }
}
