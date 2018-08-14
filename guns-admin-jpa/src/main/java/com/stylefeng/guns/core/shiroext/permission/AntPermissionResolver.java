package com.stylefeng.guns.core.shiroext.permission;


import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

@Slf4j
public class AntPermissionResolver implements PermissionResolver {

    /**
     * subject.isPermitted(url) 中传入的字符串
     * 和自定义 Realm 中传入的权限字符串集合都要经过这个 resolver
     *
     * @param url
     * @return
     */
    @Override
    public Permission resolvePermission(String url) {
        if (url.startsWith("/")) {
            return new AntUrlPermission(url);
        }
        return new WildcardPermission(url);
    }
}
