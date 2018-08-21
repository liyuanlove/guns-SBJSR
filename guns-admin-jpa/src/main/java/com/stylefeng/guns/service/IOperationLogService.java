package com.stylefeng.guns.service;

import com.stylefeng.guns.po.OperationLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.tc.jpa.service.IBaseService;

import java.util.Date;

/**
 * 操作日志Service
 *
 * @author TC
 * @Date 2018-07-29 16:42:18
 */
public interface IOperationLogService extends IBaseService<OperationLog, Integer> {

    /**
     * 获取操作日志列表
     */
    Page<OperationLog> getPage(Pageable pageable, Date beginTime, String endTime, String logname, String logtype);

}
