package com.stylefeng.guns.service.impl;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.core.ztree.ZTreeNode;
import com.stylefeng.guns.po.Role;
import com.stylefeng.guns.service.IRoleService;
import com.stylefeng.guns.vo.RoleVo;
import com.stylefeng.guns.warpper.RoleWarpper;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * RoleServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>08/03/2018</pre>
 */
@Slf4j
public class RoleServiceImplTest extends BaseJunit {

    @Autowired
    private IRoleService roleService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: setAuthority(Integer roleId, String ids)
     */
    @Test
    public void testSetAuthority() throws Exception {
        //TODO: Test goes here... 
    }

    /**
     * Method: list(String name)
     */
    @Test
    public void testList() throws Exception {
        //TODO: Test goes here...
        List<Role> list = roleService.list("");
        log.info("{}", list);
        List<RoleVo> voList = new RoleWarpper().warpList(list);
        log.info("{}", voList);
//        Hibernate: select role0_.id as id1_7_, role0_.deptid as deptid2_7_, role0_.name as name3_7_, role0_.num as num4_7_, role0_.pid as pid5_7_, role0_.tips as tips6_7_, role0_.version as version7_7_ from sys_role role0_ where 1=1
//        18:01:29 INFO  - [com.stylefeng.guns.po.Role@38d0effb, com.stylefeng.guns.po.Role@fa33c9e5, com.stylefeng.guns.po.Role@29c57457, com.stylefeng.guns.po.Role@5f1e9573] com.stylefeng.guns.service.impl.RoleServiceImplTest Line:53
//        18:01:29 INFO  - Starting without optional epoll library io.lettuce.core.EpollProvider Line:101
//        18:01:29 INFO  - Starting without optional kqueue library io.lettuce.core.KqueueProvider Line:101
//        18:01:31 INFO  - [RoleVo(id=1, num=0, pid=0, name=超级管理员, deptid=24, tips=administrator, version=1, pName=--, deptName=总公司), RoleVo(id=5, num=4, pid=1, name=临时, deptid=26, tips=temp, version=null, pName=超级管理员, deptName=运营部), RoleVo(id=6, num=2, pid=1, name=开发工程师, deptid=25, tips=dev, version=null, pName=超级管理员, deptName=开发部), RoleVo(id=8, num=null, pid=1, name=卖家管理员, deptid=29, tips=seller, version=null, pName=超级管理员, deptName=优惠活动卖家)] com.stylefeng.guns.service.impl.RoleServiceImplTest Line:55

    }

    /**
     * Method: delRoleById(Integer roleId)
     */
    @Test
    public void testDelRoleById() throws Exception {
        //TODO: Test goes here... 
    }

    /**
     * Method: roleTree()
     */
    @Test
    public void testGetRoleTree() throws Exception {
        //TODO: Test goes here...
        List<ZTreeNode> roleTreeList = this.roleService.getRoleTree();
        roleTreeList.add(ZTreeNode.createRoot());
        log.info("{}", roleTreeList);

    }

    /**
     * Method: checkedRoleTree(Integer userId)
     */
    @Test
    public void testGetCheckedRoleTree() throws Exception {
        //TODO: Test goes here...
        List<ZTreeNode> roleTreeList = roleService.getCheckedRoleTree(1);
        log.info("{}", roleTreeList);
//        Hibernate: select user0_.id as id1_8_0_, user0_.account as account2_8_0_, user0_.avatar as avatar3_8_0_, user0_.birthday as birthday4_8_0_, user0_.createtime as createti5_8_0_, user0_.deptid as deptid6_8_0_, user0_.email as email7_8_0_, user0_.name as name8_8_0_, user0_.password as password9_8_0_, user0_.phone as phone10_8_0_, user0_.roleid as roleid11_8_0_, user0_.salt as salt12_8_0_, user0_.sex as sex13_8_0_, user0_.status as status14_8_0_, user0_.version as version15_8_0_ from sys_user user0_ where user0_.id=?
//        Hibernate: SELECT r0.id,pid,name, (CASE WHEN (pid = 0 OR pid IS NULL) THEN 'true' ELSE 'false' END) 'open', (CASE WHEN (r1.ID = 0 OR r1.ID IS NULL) THEN 'false' ELSE 'true' END ) checked from sys_role r0 LEFT JOIN (SELECT ID FROM sys_role WHERE ID IN(?)) r1 ON r0.ID = r1.ID ORDER BY r0.pid, num ASC
//        18:01:29 INFO  - [ZTreeNode(id=1, name=超级管理员, open=true, checked=true, pid=0), ZTreeNode(id=8, name=卖家管理员, open=false, checked=false, pid=1), ZTreeNode(id=6, name=开发工程师, open=false, checked=false, pid=1), ZTreeNode(id=5, name=临时, open=false, checked=false, pid=1)] com.stylefeng.guns.service.impl.RoleServiceImplTest Line:85

    }

}
