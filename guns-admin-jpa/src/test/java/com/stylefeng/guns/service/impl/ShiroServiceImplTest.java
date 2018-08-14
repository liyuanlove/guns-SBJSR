package com.stylefeng.guns.service.impl;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.core.shiroext.vo.ShiroUser;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.po.User;
import com.stylefeng.guns.service.IShiroService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * ShiroServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>08/02/2018</pre>
 */
@Slf4j
public class ShiroServiceImplTest extends BaseJunit {

    @Autowired
    private IShiroService shiroService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getUserByAccount(String account)
     */
    @Test
    public void testGetUserByAccount() throws Exception {
        //TODO: Test goes here...
        User user = shiroService.getUserByAccount("admin");
        log.info("{}", user);
        SimpleAuthenticationInfo simpleInfo = shiroService.getSimpleInfo(user, "");
        log.info("{}", simpleInfo);

        ShiroUser shiroUser = (ShiroUser) simpleInfo.getPrincipals().getPrimaryPrincipal();
        log.info("{}", shiroUser);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        List<Integer> roleList = shiroUser.getRoleList();
        log.info("{}", roleList);

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
        log.info("{}", info);

    }

    /**
     * Method: getShiroUser(User user)
     */
    @Test
    public void testGetShiroUser() throws Exception {
        //TODO: Test goes here... 
    }

    /**
     * Method: findPermissionsByRoleId(Integer roleId)
     */
    @Test
    public void testFindPermissionsByRoleId() throws Exception {
        //TODO: Test goes here... 
    }

    /**
     * Method: findRoleNameByRoleId(Integer roleId)
     */
    @Test
    public void testFindRoleNameByRoleId() throws Exception {
        //TODO: Test goes here... 
    }

    /**
     * Method: getSimpleInfo(ShiroUser shiroUser, User user, String realmName)
     */
    @Test
    public void testInfo() throws Exception {
        //TODO: Test goes here... 
    }


}
