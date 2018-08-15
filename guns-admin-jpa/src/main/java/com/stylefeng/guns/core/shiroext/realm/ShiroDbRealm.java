package com.stylefeng.guns.core.shiroext.realm;

import com.stylefeng.guns.core.shiroext.vo.ShiroUser;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.po.User;
import com.stylefeng.guns.service.IShiroService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 自定义realm的实现  该realm类提供了两个方法
 */
public class ShiroDbRealm extends AuthorizingRealm {

    @Autowired
    private IShiroService shiroService;

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = shiroService.getUserByAccount(token.getUsername());
        SimpleAuthenticationInfo info = shiroService.getSimpleInfo(user, super.getName());
        return info;
    }

    /**
     * 权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //即登录认证是存放的第一个参数
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        List<Integer> roleList = shiroUser.getRoleList();

        for (Integer roleId : roleList) {
            List<String> permissions = shiroService.findPermissionsByRoleId(roleId);
            if (!ToolUtil.isEmpty(permissions)) {
                for (String permission : permissions) {
                    if (StringUtils.isNotBlank(permission)) {
                        info.addStringPermission(permission);
                    }
                }
            }
            String roleName = shiroService.findRoleNameByRoleId(roleId);
            info.addRole(roleName);
        }

        return info;
    }

    /**
     * 清楚认证缓存
     *
     * @param principals
     */
    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
        ShiroUser user = (ShiroUser) principals.getPrimaryPrincipal();
        SimplePrincipalCollection simplePrincipalCollection = new SimplePrincipalCollection(
                user.getAccount(), getName());
        super.clearCachedAuthenticationInfo(simplePrincipalCollection);
    }

    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
        ShiroUser user = (ShiroUser) principals.getPrimaryPrincipal();
        SimplePrincipalCollection simplePrincipalCollection = new SimplePrincipalCollection(
                user.getAccount(), getName());
        super.clearCachedAuthorizationInfo(simplePrincipalCollection);
    }
}
