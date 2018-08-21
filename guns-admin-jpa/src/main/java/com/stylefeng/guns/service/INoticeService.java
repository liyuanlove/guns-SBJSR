package com.stylefeng.guns.service;

import com.stylefeng.guns.po.Notice;
import org.tc.jpa.service.IBaseService;

import java.util.List;

/**
 * 通知Service
 *
 * @author TC
 * @Date 2018-07-29 16:40:15
 */
public interface INoticeService extends IBaseService<Notice, Integer> {

    /**
     * 搜索
     *
     * @param condition
     * @return
     */
    public List<Notice> list(String condition);
}
