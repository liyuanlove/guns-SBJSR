package com.stylefeng.guns.service.impl;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.po.Notice;
import com.stylefeng.guns.service.INoticeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * NoticeServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>08/04/2018</pre>
 */
@Slf4j
public class NoticeServiceImplTest extends BaseJunit {

    @Autowired
    private INoticeService noticeService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: list(String condition)
     */
    @Test
    public void testList() throws Exception {
        //TODO: Test goes here...
        List<Notice> list = noticeService.list(null);
        log.info("[{}]", list);
        list = noticeService.list("dis");
        log.info("[{}]", list);
//        Hibernate: select notice0_.id as id1_4_, notice0_.content as content2_4_, notice0_.creater as creater3_4_, notice0_.createtime as createti4_4_, notice0_.title as title5_4_, notice0_.type as type6_4_ from sys_notice notice0_ order by notice0_.createtime DESC
//        15:31:29 INFO  - [[com.stylefeng.guns.po.Notice@302e118b, com.stylefeng.guns.po.Notice@383572a4, com.stylefeng.guns.po.Notice@8c2527e4, com.stylefeng.guns.po.Notice@ffc64f76]] com.stylefeng.guns.service.impl.NoticeServiceImplTest Line:42
//        Hibernate: select notice0_.id as id1_4_, notice0_.content as content2_4_, notice0_.creater as creater3_4_, notice0_.createtime as createti4_4_, notice0_.title as title5_4_, notice0_.type as type6_4_ from sys_notice notice0_ where notice0_.title like ? or notice0_.content like ? order by notice0_.createtime DESC
//        15:31:29 INFO  - [[com.stylefeng.guns.po.Notice@ffc64f76]] com.stylefeng.guns.service.impl.NoticeServiceImplTest Line:44

    }


}
