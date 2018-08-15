package com.stylefeng.guns.dao;

import com.stylefeng.guns.core.base.dao.BaseDao;
import com.stylefeng.guns.po.OperationLog;
import org.springframework.stereotype.Repository;

/**
 * 操作日志Dao
 *
 * @author TC
 * @Date 2018-07-29 16:42:18
 */
@Repository
public interface OperationLogDao extends BaseDao<OperationLog, Integer> {

}
