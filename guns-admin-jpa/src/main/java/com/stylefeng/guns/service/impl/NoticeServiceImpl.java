package com.stylefeng.guns.service.impl;

import com.stylefeng.guns.dao.NoticeDao;
import com.stylefeng.guns.po.Notice;
import com.stylefeng.guns.service.INoticeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.tc.jpa.service.impl.BaseServiceImpl;

import java.util.List;

/**
 * 通知Service实现
 *
 * @author TC
 * @Date 2018-07-29 16:40:15
 */
@Service
public class NoticeServiceImpl extends BaseServiceImpl<Notice, Integer, NoticeDao> implements INoticeService {

    @Override
    public List<Notice> list(String condition) {
        if (StringUtils.isNotBlank(condition)) {
            return baseDao.listByName(condition);
        } else {
            return baseDao.list();
        }
    }
}
