package com.stylefeng.guns.dao;

import com.stylefeng.guns.po.LoginLog;
import org.springframework.stereotype.Repository;
import org.tc.jpa.dao.BaseDao;

/**
 * 登录日志Dao
 *
 * @author TC
 * @Date 2018-07-29 16:41:25
 */
@Repository
public interface LoginLogDao extends BaseDao<LoginLog, Integer> {

}
