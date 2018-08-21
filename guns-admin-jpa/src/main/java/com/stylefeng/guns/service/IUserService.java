package com.stylefeng.guns.service;

import com.stylefeng.guns.po.User;
import org.tc.jpa.service.IBaseService;

import java.util.Date;
import java.util.List;

/**
 * 用户Service
 *
 * @author TC
 * @Date 2018-07-29 16:36:10
 */
public interface IUserService extends IBaseService<User, Integer> {


    public void add(User user);

    public User edit(User newUser, User oldUser);

    /**
     * 修改密码
     *
     * @param oldPwd
     * @param newPwd
     * @param rePwd
     */
    public void changePwd(String oldPwd, String newPwd, String rePwd);

    public void resetPwd(Integer id);

    /**
     * 修改用户状态
     */
    void setStatus(Integer userId, int status);

    /**
     * 设置用户的角色
     */
    void setRoles(Integer userId, String roleIds);

    /**
     * 根据条件查询用户列表
     */
    List<User> list(String name, Date beginTime, String endTime, Integer deptid);

}
