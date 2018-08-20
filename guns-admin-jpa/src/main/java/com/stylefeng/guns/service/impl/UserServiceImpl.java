package com.stylefeng.guns.service.impl;

import com.stylefeng.guns.core.common.constant.AdminConst;
import com.stylefeng.guns.core.common.constant.enums.TrebleStatus;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.shiroext.kit.ShiroKit;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.dao.DeptDao;
import com.stylefeng.guns.dao.UserDao;
import com.stylefeng.guns.po.User;
import com.stylefeng.guns.service.IUserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.tan.jpa.exception.GunsException;
import org.tan.jpa.service.impl.BaseServiceImpl;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户Service实现
 *
 * @author TC
 * @Date 2018-07-29 16:36:10
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer, UserDao> implements IUserService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public void add(User user) {
        boolean exists = baseDao.existsByAccountIgnoreCase(user.getAccount());
        if (exists) {
            throw new GunsException(BizExceptionEnum.USER_ALREADY_REG);
        }
        user = setPwd(user, user.getPassword());
        user.setStatus(TrebleStatus.ACTIVED.getCode());
        user.setCreatetime(new Date());

        this.baseDao.save(user);
    }

    @Override
    public User edit(User newUser, User oldUser) {
        User user = editUser(newUser, oldUser);
        return baseDao.save(user);
    }

    @Override
    public void changePwd(String oldPwd, String newPwd, String rePwd) {
        if (!newPwd.equals(rePwd)) {
            throw new GunsException(BizExceptionEnum.TWO_PWD_NOT_MATCH);
        }
        Integer userId = ShiroKit.getUser().getId();
        User currentUser = baseDao.getOne(userId);
        String prevSalt = currentUser.getSalt();

        String oldMd5 = ShiroKit.md5(oldPwd, prevSalt);
        if (currentUser.getPassword().equals(oldMd5)) {
            baseDao.save(setPwd(currentUser, newPwd));
        } else {
            throw new GunsException(BizExceptionEnum.OLD_PWD_NOT_RIGHT);
        }
    }

    /**
     * 给用户配置新的密码和盐
     *
     * @param user
     * @param password 新密码
     * @return
     */
    private User setPwd(User user, String password) {
        String randomSalt = ShiroKit.getRandomSalt(5);
        String newMd5 = ShiroKit.md5(password, randomSalt);
        user.setSalt(randomSalt);
        user.setPassword(newMd5);
        return user;
    }

    @Override
    public void resetPwd(Integer id) {
        User user = this.baseDao.getOne(id);
        ShiroKit.assertAuth(id, user);
        baseDao.save(setPwd(user, AdminConst.DEFAULT_PWD));
    }

    /**
     * 将变更的数据复制给旧数据
     *
     * @param newUser
     * @param oldUser
     * @return
     */
    private User editUser(User newUser, User oldUser) {
        if (newUser == null || oldUser == null) {
            return oldUser;
        } else {
            if (ToolUtil.isNotEmpty(newUser.getAvatar())) {
                oldUser.setAvatar(newUser.getAvatar());
            }
            if (ToolUtil.isNotEmpty(newUser.getName())) {
                oldUser.setName(newUser.getName());
            }
            if (ToolUtil.isNotEmpty(newUser.getBirthday())) {
                oldUser.setBirthday(newUser.getBirthday());
            }
            if (ToolUtil.isNotEmpty(newUser.getDeptid())) {
                oldUser.setDeptid(newUser.getDeptid());
            }
            if (ToolUtil.isNotEmpty(newUser.getSex())) {
                oldUser.setSex(newUser.getSex());
            }
            if (ToolUtil.isNotEmpty(newUser.getEmail())) {
                oldUser.setEmail(newUser.getEmail());
            }
            if (ToolUtil.isNotEmpty(newUser.getPhone())) {
                oldUser.setPhone(newUser.getPhone());
            }
            return oldUser;
        }
    }

    @Override
    public void setStatus(Integer userId, int status) {
        User user = baseDao.getOne(userId);
        ShiroKit.assertAuth(userId, user);
        user.setStatus(status);
        baseDao.save(user);
    }

    @Override
    public void setRoles(Integer userId, String roleIds) {
        User user = baseDao.getOne(userId);
        ShiroKit.assertAuth(userId, user);
        user.setRoleid(roleIds);
        baseDao.save(user);
    }

    @Override
    public List<User> list(String name, Date beginTime, String endTime, Integer deptid) {
        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if (StringUtils.isNotBlank(name)) {
                    Predicate p1 = cb.like(root.get("name"), "%" + name + "%");
                    Predicate p2 = cb.like(root.get("account"), "%" + name + "%");
                    Predicate p3 = cb.like(root.get("phone"), "%" + name + "%");
                    list.add(cb.or(p1, p2, p3));
                }
                if (beginTime != null) {
                    list.add(cb.greaterThanOrEqualTo(root.get("createtime"), beginTime));
                }
                if (StringUtils.isNotBlank(endTime)) {
                    list.add(cb.lessThanOrEqualTo(root.get("createtime"), DateUtil.parseTime(endTime + " 23:59:59")));
                }
                Expression<Integer> did = root.get("deptid");
                if (!ShiroKit.isAdmin()) {
                    List<Integer> deptDataScope = ShiroKit.getDeptDataScope();
                    if (CollectionUtils.isNotEmpty(deptDataScope)) {
                        list.add(did.in(deptDataScope));
                    }
                }
                if (deptid != null) {
                    List<Integer> ids = deptDao.deptAndSubDept(deptid, "%[" + deptid + "]%");
                    list.add(did.in(ids));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };

        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "createtime");
        Sort sort = Sort.by(order);
        List<User> list = baseDao.findAll(specification, sort);
        return list;
    }

}
