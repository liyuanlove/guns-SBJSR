package com.stylefeng.guns.service.impl;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.po.User;
import com.stylefeng.guns.service.IUserService;
import com.stylefeng.guns.vo.UserVo;
import com.stylefeng.guns.warpper.UserWarpper;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * UserServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>08/04/2018</pre>
 */
@Slf4j
public class UserServiceImplTest extends BaseJunit {

    @Autowired
    private IUserService userService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: setStatus(Integer userId, int status)
     */
    @Test
    public void testSetStatus() throws Exception {
        //TODO: Test goes here... 
    }

    /**
     * Method: setRoles(Integer userId, String roleIds)
     */
    @Test
    public void testSetRoles() throws Exception {
        //TODO: Test goes here... 
    }

    /**
     * Method: list(String name, String beginTime, String endTime, Integer deptid)
     */
    @Test
    public void testList() throws Exception {
        //TODO: Test goes here...
        List<User> list = userService.list(null, null, null, null);
        log.info("{}", list);
        List<UserVo> voList = new UserWarpper().warpList(list);
        log.info("{}", voList);
//        Hibernate: select user0_.id as id1_8_, user0_.account as account2_8_, user0_.avatar as avatar3_8_, user0_.birthday as birthday4_8_, user0_.createtime as createti5_8_, user0_.deptid as deptid6_8_, user0_.email as email7_8_, user0_.name as name8_8_, user0_.password as password9_8_, user0_.phone as phone10_8_, user0_.roleid as roleid11_8_, user0_.salt as salt12_8_, user0_.sex as sex13_8_, user0_.status as status14_8_, user0_.version as version15_8_ from sys_user user0_ where 1=1 order by user0_.createtime asc
//        10:40:16 INFO  - [User{id=1, avatar='ab563428-dd55-4c17-a6e3-df93f9a0979d.jpg', account='admin', password='6cd3423f077da4d9465408654b7366ef', salt='8pgby', name='TC', birthday=2017-05-05 00:00:00.0, sex=1, email='sn93@qq.com', phone='18200000000', roleid='1', deptid=27, status=1, createtime=2016-01-29 08:49:53.0, version=25}, User{id=2, avatar='null', account='test', password='45abb7879f6a8268f1ef600e6038ac73', salt='ssts3', name='test', birthday=2017-05-01 00:00:00.0, sex=1, email='abc@123.com', phone='', roleid='5', deptid=26, status=3, createtime=2017-05-16 20:33:37.0, version=null}, User{id=3, avatar='null', account='boss', password='71887a5ad666a18f709e1d4e693d5a35', salt='1f7bf', name='老板', birthday=2017-12-04 00:00:00.0, sex=1, email='', phone='', roleid='1', deptid=24, status=1, createtime=2017-12-04 22:24:02.0, version=null}, User{id=4, avatar='null', account='manager', password='b53cac62e7175637d4beb3b16b2f7915', salt='j3cs9', name='经理', birthday=2017-12-04 00:00:00.0, sex=1, email='', phone='', roleid='1', deptid=24, status=1, createtime=2017-12-04 22:24:24.0, version=null}, User{id=5, avatar='', account='tan', password='4772c53d942ff8a27e5a88676d7b7cb1', salt='nfukw', name='me', birthday=2018-07-22 00:00:00.0, sex=1, email='', phone='', roleid='8', deptid=29, status=1, createtime=2018-07-22 17:23:52.0, version=null}] com.stylefeng.guns.service.impl.UserServiceImplTest Line:72
//        Hibernate: select dict0_.id as id1_1_, dict0_.code as code2_1_, dict0_.name as name3_1_, dict0_.num as num4_1_, dict0_.pid as pid5_1_, dict0_.tips as tips6_1_ from sys_dict dict0_ where dict0_.name=?
//        Hibernate: select dict0_.id as id1_1_, dict0_.code as code2_1_, dict0_.name as name3_1_, dict0_.num as num4_1_, dict0_.pid as pid5_1_, dict0_.tips as tips6_1_ from sys_dict dict0_ where dict0_.pid=?
//        Hibernate: select dict0_.id as id1_1_, dict0_.code as code2_1_, dict0_.name as name3_1_, dict0_.num as num4_1_, dict0_.pid as pid5_1_, dict0_.tips as tips6_1_ from sys_dict dict0_ where dict0_.name=?
//        Hibernate: select dict0_.id as id1_1_, dict0_.code as code2_1_, dict0_.name as name3_1_, dict0_.num as num4_1_, dict0_.pid as pid5_1_, dict0_.tips as tips6_1_ from sys_dict dict0_ where dict0_.pid=?
//        Hibernate: select role0_.id as id1_7_0_, role0_.deptid as deptid2_7_0_, role0_.name as name3_7_0_, role0_.num as num4_7_0_, role0_.pid as pid5_7_0_, role0_.tips as tips6_7_0_, role0_.version as version7_7_0_ from sys_role role0_ where role0_.id=?
//        Hibernate: select dict0_.id as id1_1_, dict0_.code as code2_1_, dict0_.name as name3_1_, dict0_.num as num4_1_, dict0_.pid as pid5_1_, dict0_.tips as tips6_1_ from sys_dict dict0_ where dict0_.name=?
//        Hibernate: select dict0_.id as id1_1_, dict0_.code as code2_1_, dict0_.name as name3_1_, dict0_.num as num4_1_, dict0_.pid as pid5_1_, dict0_.tips as tips6_1_ from sys_dict dict0_ where dict0_.pid=?
//        Hibernate: select dict0_.id as id1_1_, dict0_.code as code2_1_, dict0_.name as name3_1_, dict0_.num as num4_1_, dict0_.pid as pid5_1_, dict0_.tips as tips6_1_ from sys_dict dict0_ where dict0_.name=?
//        Hibernate: select dict0_.id as id1_1_, dict0_.code as code2_1_, dict0_.name as name3_1_, dict0_.num as num4_1_, dict0_.pid as pid5_1_, dict0_.tips as tips6_1_ from sys_dict dict0_ where dict0_.pid=?
//        Hibernate: select dict0_.id as id1_1_, dict0_.code as code2_1_, dict0_.name as name3_1_, dict0_.num as num4_1_, dict0_.pid as pid5_1_, dict0_.tips as tips6_1_ from sys_dict dict0_ where dict0_.name=?
//        Hibernate: select dict0_.id as id1_1_, dict0_.code as code2_1_, dict0_.name as name3_1_, dict0_.num as num4_1_, dict0_.pid as pid5_1_, dict0_.tips as tips6_1_ from sys_dict dict0_ where dict0_.pid=?
//        10:40:19 INFO  - [UserVo(id=1, avatar=ab563428-dd55-4c17-a6e3-df93f9a0979d.jpg, account=admin, password=6cd3423f077da4d9465408654b7366ef, salt=8pgby, name=TC, birthday=null, sex=1, email=sn93@qq.com, phone=18200000000, roleid=1, deptid=27, status=1, createtime=2016-01-29 08:49:53.0, version=25, sexName=男, roleName=超级管理员, deptName=战略部, statusName=启用), UserVo(id=2, avatar=null, account=test, password=45abb7879f6a8268f1ef600e6038ac73, salt=ssts3, name=test, birthday=null, sex=1, email=abc@123.com, phone=, roleid=5, deptid=26, status=3, createtime=2017-05-16 20:33:37.0, version=null, sexName=男, roleName=临时, deptName=运营部, statusName=被删除), UserVo(id=3, avatar=null, account=boss, password=71887a5ad666a18f709e1d4e693d5a35, salt=1f7bf, name=老板, birthday=null, sex=1, email=, phone=, roleid=1, deptid=24, status=1, createtime=2017-12-04 22:24:02.0, version=null, sexName=男, roleName=超级管理员, deptName=总公司, statusName=启用), UserVo(id=4, avatar=null, account=manager, password=b53cac62e7175637d4beb3b16b2f7915, salt=j3cs9, name=经理, birthday=null, sex=1, email=, phone=, roleid=1, deptid=24, status=1, createtime=2017-12-04 22:24:24.0, version=null, sexName=男, roleName=超级管理员, deptName=总公司, statusName=启用), UserVo(id=5, avatar=, account=tan, password=4772c53d942ff8a27e5a88676d7b7cb1, salt=nfukw, name=me, birthday=null, sex=1, email=, phone=, roleid=8, deptid=29, status=1, createtime=2018-07-22 17:23:52.0, version=null, sexName=男, roleName=卖家管理员, deptName=优惠活动卖家, statusName=启用)] com.stylefeng.guns.service.impl.UserServiceImplTest Line:74


    }


}
