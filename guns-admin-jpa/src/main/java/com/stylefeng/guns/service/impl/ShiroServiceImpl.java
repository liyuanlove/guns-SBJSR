package com.stylefeng.guns.service.impl;

import com.stylefeng.guns.core.common.constant.enums.TrebleStatus;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.shiroext.vo.ShiroUser;
import com.stylefeng.guns.core.util.Convert;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.dao.MenuDao;
import com.stylefeng.guns.dao.UserDao;
import com.stylefeng.guns.po.User;
import com.stylefeng.guns.service.IShiroService;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShiroServiceImpl implements IShiroService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private MenuDao menuDao;

    @Override
    public User getUserByAccount(String account) {
        User user = userDao.findByAccountEquals(account);
        // 账号不存在
        if (ToolUtil.isEmpty(user)) {
            throw new UnknownAccountException("用户名或密码的错误");
        }
        // 账号被冻结
        if (user.getStatus() != TrebleStatus.ACTIVED.getCode()) {
            throw new LockedAccountException("用户已经被禁用，请联系管理员启用该账号");
        }
        return user;
    }

    @Override
    public List<String> findPermissionsByRoleId(Integer roleid) {
        return menuDao.getUrlsByRoleId(roleid);
    }

    @Override
    public String findRoleNameByRoleId(Integer roleid) {
        return ConstantFactory.me().getRoleEnName(roleid);
    }

    @Override
    public SimpleAuthenticationInfo getSimpleInfo(User user, String realmName) {
        ShiroUser shiroUser = getShiroUser(user);
        String credentials = user.getPassword();
        String source = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(source);
        //均为用户的正确信息
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(shiroUser, credentials, realmName);
        info.setCredentialsSalt(credentialsSalt);
        return info;
    }

    /**
     * 包装user
     *
     * @param user
     * @return
     */
    private ShiroUser getShiroUser(User user) {
        ShiroUser shiroUser = new ShiroUser();
        BeanUtils.copyProperties(user, shiroUser);
        shiroUser.setDeptName(ConstantFactory.me().getDeptName(user.getDeptid()));

        Integer[] roleArray = Convert.toIntArray(user.getRoleid());
        List<Integer> roleList = new ArrayList<Integer>();
        List<String> roleNameList = new ArrayList<String>();
        for (int roleId : roleArray) {
            roleList.add(roleId);
            roleNameList.add(ConstantFactory.me().getSingleRoleName(roleId));
        }
        shiroUser.setRoleList(roleList);
        shiroUser.setRoleNames(roleNameList);

        return shiroUser;
    }
}
