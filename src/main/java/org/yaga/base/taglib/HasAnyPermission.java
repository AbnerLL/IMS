package org.yaga.base.taglib;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.PermissionTag;

import java.util.Arrays;
import java.util.List;

/**
 * 用于判断是否拥有某一权限
 * Created by luozhihui on 2018/2/4.
 */
public class HasAnyPermission extends PermissionTag{

    public HasAnyPermission() {
    }

    protected boolean showTagBody(String s) {
        Subject subject=super.getSubject();
        List<String> permissions= Arrays.asList(s.split(","));
        for (String permission:permissions){
            if (subject.isPermitted(permission)){
                return true;
            }
        }
        return false;
    }
}
