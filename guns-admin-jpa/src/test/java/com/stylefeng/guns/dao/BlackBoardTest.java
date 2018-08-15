package com.stylefeng.guns.dao;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.po.Notice;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * 首页通知展示测试
 *
 * @author fengshuonan
 * @date 2017-05-21 15:02
 */
public class BlackBoardTest extends BaseJunit {

    @Autowired
    NoticeDao noticeDao;

    @Test
    public void blackBoardTest() {
        List<Notice> notices = noticeDao.listByName(null);
        assertTrue(notices.size() > 0);
    }
}
