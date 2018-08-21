package com.stylefeng.guns.dao;

import com.stylefeng.guns.po.User;
import org.springframework.stereotype.Repository;
import org.tc.jpa.dao.BaseDao;

import java.util.List;

/**
 * 用户Dao
 *
 * @author TC
 * @Date 2018-07-29 16:36:10
 */
@Repository
public interface UserDao extends BaseDao<User, Integer> {

    /**
     * 是否存在该账户号（忽略大小写）
     *
     * @param account
     * @return
     */
    public boolean existsByAccountIgnoreCase(String account);

    /**
     * 通过账号查找
     *
     * @param account
     * @return
     */
    public User findByAccountEquals(String account);

    /**
     * 通过角色ID查找
     *
     * @param roleid
     * @return
     */
    public List<User> findByRoleidLike(String roleid);
}
