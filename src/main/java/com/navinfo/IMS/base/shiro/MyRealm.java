package com.navinfo.IMS.base.shiro;

import com.navinfo.core.entity.SysUser;
import com.navinfo.core.service.SysPermissionService;
import com.navinfo.core.service.SysRoleService;
import com.navinfo.core.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

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

        return null;
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
        SysUser user=sysUserService.getUserById(username);
        if(user!=null){
            return new SimpleAuthenticationInfo(user.getId(),user.getPassword(),this.getName());
        }
        return null;
    }
}
