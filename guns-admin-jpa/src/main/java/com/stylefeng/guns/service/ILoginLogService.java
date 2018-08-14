package com.stylefeng.guns.service;

import com.stylefeng.guns.core.base.service.IBaseService;
import com.stylefeng.guns.po.LoginLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

/**
 * 登录日志Service
 *
 * @author TC
 * @Date 2018-07-29 16:41:25
 */
public interface ILoginLogService extends IBaseService<LoginLog, Integer> {
    /**
     * 获取登录日志列表
     */
    Page<LoginLog> getPage(Pageable pageable, Date beginTime, String endTime, String logname);
}
