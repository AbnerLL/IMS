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
 * �Զ����realm��
 * Created by luozhihui on 2017/11/13.
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    /**
     * ��Ȩ���ڶ�����
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("shrio��ȨdoGetAuthorizationInfo......");
        String userName=(String)principalCollection.getPrimaryPrincipal();
        //��ɫ����
        Set<String> roleSet=new HashSet<String>();
        //Ȩ�޼���
        Set<String> permissionSet=new HashSet<String>();
        //��ȡ��ɫ(�Ѿ���ʼ��Ȩ��)
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
     * ��֤����һ����
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("shrio��֤doGetAuthenticationInfo......");
        //1.ת��token���������ҳ�洫�����û��������룩
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        //2.����token����ȡ�û���
        String username=token.getUsername();
        //3.��ѯ�����ݿ��еĸö���
        List<SysUser> users=sysUserService.getUserById(username);
        if(users!=null&&users.size()>0){
            return new SimpleAuthenticationInfo(users.get(0).getId(),users.get(0).getPassword(),this.getName());
        }
        return null;
    }
}
