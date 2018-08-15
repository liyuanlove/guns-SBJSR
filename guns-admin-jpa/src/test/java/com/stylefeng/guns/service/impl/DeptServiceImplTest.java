package com.stylefeng.guns.service.impl;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.core.ztree.ZTreeNode;
import com.stylefeng.guns.po.Dept;
import com.stylefeng.guns.service.IDeptService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * DeptServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>08/04/2018</pre>
 */
@Slf4j
public class DeptServiceImplTest extends BaseJunit {

    @Autowired
    private IDeptService deptService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: deleteCascade(Integer deptId)
     */
    @Test
    public void testDeleteCascade() throws Exception {
        //TODO: Test goes here... 
    }

    /**
     * Method: tree()
     */
    @Test
    public void testTree() throws Exception {
        //TODO: Test goes here...
        List<ZTreeNode> list = deptService.tree();
        log.info("{}", list);
//        Hibernate: select id,pid,simplename as name, ( CASE WHEN (pid = 0 OR pid IS NULL) THEN 'true' ELSE 'false' END ) 'open' from sys_dept
//        13:14:21 INFO  - [ZTreeNode(id=24, name=总公司, open=true, checked=null, pid=0),
// ZTreeNode(id=25, name=开发部, open=false, checked=null, pid=24),
// ZTreeNode(id=26, name=运营部, open=false, checked=null, pid=24),
// ZTreeNode(id=27, name=战略部, open=false, checked=null, pid=24),
// ZTreeNode(id=28, name=人力部, open=false, checked=null, pid=24),
// ZTreeNode(id=29, name=卖家商户, open=true, checked=null, pid=0)]
// com.stylefeng.guns.service.impl.DeptServiceImplTest Line:51

    }

    /**
     * Method: list(String condition)
     */
    @Test
    public void testList() throws Exception {
        //TODO: Test goes here...
        List<Dept> list = deptService.list(null);
        log.info("{}", list);
//        Hibernate: select dept0_.id as id1_0_, dept0_.fullname as fullname2_0_, dept0_.num as num3_0_, dept0_.pid as pid4_0_, dept0_.pids as pids5_0_, dept0_.simplename as simplena6_0_, dept0_.tips as tips7_0_, dept0_.version as version8_0_ from sys_dept dept0_ order by dept0_.num ASC
//        13:14:20 INFO  - [com.stylefeng.guns.po.Dept@4960a36e, com.stylefeng.guns.po.Dept@561ed63a, com.stylefeng.guns.po.Dept@ad4f3dda, com.stylefeng.guns.po.Dept@eb3546fa, com.stylefeng.guns.po.Dept@7b6978e4, com.stylefeng.guns.po.Dept@ca7a9cf2] com.stylefeng.guns.service.impl.DeptServiceImplTest Line:61

    }


}
