package com.stylefeng.guns.service.impl;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.core.ztree.ZTreeNode;
import com.stylefeng.guns.po.Menu;
import com.stylefeng.guns.service.IMenuService;
import com.stylefeng.guns.vo.MenuVo;
import com.stylefeng.guns.warpper.MenuWarpper;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * MenuServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>08/03/2018</pre>
 */
@Slf4j
public class MenuServiceImplTest extends BaseJunit {

    @Autowired
    private IMenuService menuService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

//    Hibernate: select menu0_.id as col_0_0_ from sys_menu menu0_ where upper(menu0_.code)=upper(?) limit ?
//            16:17:57 INFO  - true com.stylefeng.guns.service.impl.MenuServiceImplTest Line:44
//    Hibernate: select menu0_.id as col_0_0_ from sys_menu menu0_ where upper(menu0_.code)=upper(?) limit ?
//            16:17:57 INFO  - false com.stylefeng.guns.service.impl.MenuServiceImplTest Line:45

    /**
     * Method: delMenuCascade(Long menuId)
     */
    @Test
    public void testDelMenuCascade() throws Exception {
        //TODO: Test goes here... 
    }


//    Hibernate: select menu0_.id as id1_3_, menu0_.code as code2_3_, menu0_.icon as icon3_3_, menu0_.ismenu as ismenu4_3_, menu0_.isopen as isopen5_3_, menu0_.levels as levels6_3_, menu0_.name as name7_3_, menu0_.num as num8_3_, menu0_.pcode as pcode9_3_, menu0_.pcodes as pcodes10_3_, menu0_.status as status11_3_, menu0_.tips as tips12_3_, menu0_.url as url13_3_ from sys_menu menu0_ where menu0_.status=1 and menu0_.levels=2 order by menu0_.levels asc, menu0_.num asc
//16:17:56 INFO  - [Menu{id=106, code='mgr', name='用户管理', pcode='system', pcodes='[0],[system],', icon='', url='/mgr', levels=2, num=1, ismenu=1, isopen=0, status=1, tips='null'}, Menu{id=169, code='seller', name='卖家管理', pcode='biz', pcodes='[0],[biz],', icon='', url='/seller', levels=2, num=1, ismenu=1, isopen=null, status=1, tips='null'}, Menu{id=114, code='role', name='角色管理', pcode='system', pcodes='[0],[system],', icon='null', url='/role', levels=2, num=2, ismenu=1, isopen=0, status=1, tips='null'}, Menu{id=176, code='coupon', name='优惠券', pcode='biz', pcodes='[0],[biz],', icon='', url='/coupon', levels=2, num=2, ismenu=1, isopen=null, status=1, tips='null'}, Menu{id=131, code='dept', name='部门管理', pcode='system', pcodes='[0],[system],', icon='null', url='/dept', levels=2, num=3, ismenu=1, isopen=null, status=1, tips='null'}, Menu{id=119, code='menu', name='菜单管理', pcode='system', pcodes='[0],[system],', icon='null', url='/menu', levels=2, num=4, ismenu=1, isopen=0, status=1, tips='null'}, Menu{id=132, code='type', name='字典管理', pcode='system', pcodes='[0],[system],', icon='null', url='/type', levels=2, num=4, ismenu=1, isopen=null, status=1, tips='null'}, Menu{id=128, code='log', name='业务日志', pcode='system', pcodes='[0],[system],', icon='null', url='/log', levels=2, num=6, ismenu=1, isopen=0, status=1, tips='null'}, Menu{id=133, code='loginLog', name='登录日志', pcode='system', pcodes='[0],[system],', icon='null', url='/loginLog', levels=2, num=6, ismenu=1, isopen=null, status=1, tips='null'}, Menu{id=130, code='druid', name='监控管理', pcode='system', pcodes='[0],[system],', icon='null', url='/druid', levels=2, num=7, ismenu=1, isopen=null, status=1, tips='null'}, Menu{id=141, code='notice', name='通知管理', pcode='system', pcodes='[0],[system],', icon='null', url='/notice', levels=2, num=9, ismenu=1, isopen=null, status=1, tips='null'}] com.stylefeng.guns.service.impl.MenuServiceImplTest Line:63
//            16:17:56 INFO  - [MenuVo(id=106, code=mgr, name=用户管理, pcode=system, pcodes=[0],[system],, icon=, url=/mgr, levels=2, num=1, ismenu=1, isopen=0, status=1, tips=null, statusName=null, isMenuName=是), MenuVo(id=169, code=seller, name=卖家管理, pcode=biz, pcodes=[0],[biz],, icon=, url=/seller, levels=2, num=1, ismenu=1, isopen=null, status=1, tips=null, statusName=null, isMenuName=是), MenuVo(id=114, code=role, name=角色管理, pcode=system, pcodes=[0],[system],, icon=null, url=/role, levels=2, num=2, ismenu=1, isopen=0, status=1, tips=null, statusName=null, isMenuName=是), MenuVo(id=176, code=coupon, name=优惠券, pcode=biz, pcodes=[0],[biz],, icon=, url=/coupon, levels=2, num=2, ismenu=1, isopen=null, status=1, tips=null, statusName=null, isMenuName=是), MenuVo(id=131, code=dept, name=部门管理, pcode=system, pcodes=[0],[system],, icon=null, url=/dept, levels=2, num=3, ismenu=1, isopen=null, status=1, tips=null, statusName=null, isMenuName=是), MenuVo(id=119, code=menu, name=菜单管理, pcode=system, pcodes=[0],[system],, icon=null, url=/menu, levels=2, num=4, ismenu=1, isopen=0, status=1, tips=null, statusName=null, isMenuName=是), MenuVo(id=132, code=type, name=字典管理, pcode=system, pcodes=[0],[system],, icon=null, url=/type, levels=2, num=4, ismenu=1, isopen=null, status=1, tips=null, statusName=null, isMenuName=是), MenuVo(id=128, code=log, name=业务日志, pcode=system, pcodes=[0],[system],, icon=null, url=/log, levels=2, num=6, ismenu=1, isopen=0, status=1, tips=null, statusName=null, isMenuName=是), MenuVo(id=133, code=loginLog, name=登录日志, pcode=system, pcodes=[0],[system],, icon=null, url=/loginLog, levels=2, num=6, ismenu=1, isopen=null, status=1, tips=null, statusName=null, isMenuName=是), MenuVo(id=130, code=druid, name=监控管理, pcode=system, pcodes=[0],[system],, icon=null, url=/druid, levels=2, num=7, ismenu=1, isopen=null, status=1, tips=null, statusName=null, isMenuName=是), MenuVo(id=141, code=notice, name=通知管理, pcode=system, pcodes=[0],[system],, icon=null, url=/notice, levels=2, num=9, ismenu=1, isopen=null, status=1, tips=null, statusName=null, isMenuName=是)] com.stylefeng.guns.service.impl.MenuServiceImplTest Line:65

    /**
     * Method: list(String name, String level)
     */
    @Test
    public void testList() throws Exception {
        //TODO: Test goes here...
        List<Menu> list = menuService.list("", "2");
        log.info("{}", list);
        List<MenuVo> voList = new MenuWarpper().warpList(list);
        log.info("{}", voList);

    }

//    Hibernate: select menu0_.id as id1_3_0_, menu0_.code as code2_3_0_, menu0_.icon as icon3_3_0_, menu0_.ismenu as ismenu4_3_0_, menu0_.isopen as isopen5_3_0_, menu0_.levels as levels6_3_0_, menu0_.name as name7_3_0_, menu0_.num as num8_3_0_, menu0_.pcode as pcode9_3_0_, menu0_.pcodes as pcodes10_3_0_, menu0_.status as status11_3_0_, menu0_.tips as tips12_3_0_, menu0_.url as url13_3_0_ from sys_menu menu0_ where menu0_.id=?
//    Hibernate: select menu0_.id as id1_3_, menu0_.code as code2_3_, menu0_.icon as icon3_3_, menu0_.ismenu as ismenu4_3_, menu0_.isopen as isopen5_3_, menu0_.levels as levels6_3_, menu0_.name as name7_3_, menu0_.num as num8_3_, menu0_.pcode as pcode9_3_, menu0_.pcodes as pcodes10_3_, menu0_.status as status11_3_, menu0_.tips as tips12_3_, menu0_.url as url13_3_ from sys_menu menu0_ where menu0_.pcodes like ?
//    Hibernate: update sys_menu set code=?, icon=?, ismenu=?, isopen=?, levels=?, name=?, num=?, pcode=?, pcodes=?, status=?, tips=?, url=? where id=?

    /**
     * Method: switchStatusCascade(Long menuId)
     */
    @Test
    public void testSwitchStatusCascade() throws Exception {
        //TODO: Test goes here...
        menuService.switchStatusCascade(172L);
    }

//    Hibernate: select menuid from sys_relation where roleid = ?
//            16:41:35 INFO  - [105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 128, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 181, 182, 183, 184, 185, 186, 187, 188, 189, 0] com.stylefeng.guns.service.impl.MenuServiceImplTest Line:100
//    Hibernate: SELECT m1.id AS id,m1. NAME,  ( CASE WHEN (m2.id = 0 OR m2.id IS NULL) THEN 0 ELSE m2.id END ) AS pid,  ( CASE WHEN (m2.id = 0 OR m2.id IS  NULL) THEN 'true' ELSE 'false' END  ) as open,  ( CASE WHEN (m3.ID = 0 OR m3.ID IS NULL) THEN 'false' ELSE 'true'  END ) checked  FROM sys_menu m1 LEFT join sys_menu m2 ON m2.status = 1 and m1.pcode = m2.CODE  left join (SELECT ID FROM sys_menu WHERE status = 1 and ID IN(?) ) m3 on m1.id = m3.id  ORDER BY pid
//16:41:35 INFO  - [ZTreeNode(id=168, name=业务管理, open=true, checked=false, pid=0), ZTreeNode(id=105, name=系统管理, open=true, checked=true, pid=0), ZTreeNode(id=186, name=首页, open=true, checked=false, pid=0), ZTreeNode(id=145, name=通知, open=true, checked=false, pid=0), ZTreeNode(id=148, name=代码生成, open=true, checked=false, pid=0), ZTreeNode(id=149, name=接口文档, open=true, checked=false, pid=0), ZTreeNode(id=131, name=部门管理, open=false, checked=false, pid=105), ZTreeNode(id=114, name=角色管理, open=false, checked=false, pid=105), ZTreeNode(id=132, name=字典管理, open=false, checked=false, pid=105), ZTreeNode(id=119, name=菜单管理, open=false, checked=false, pid=105), ZTreeNode(id=133, name=登录日志, open=false, checked=false, pid=105), ZTreeNode(id=128, name=业务日志, open=false, checked=false, pid=105), ZTreeNode(id=141, name=通知管理, open=false, checked=false, pid=105), ZTreeNode(id=130, name=监控管理, open=false, checked=false, pid=105), ZTreeNode(id=106, name=用户管理, open=false, checked=false, pid=105), ZTreeNode(id=112, name=解除冻结用户, open=false, checked=false, pid=106), ZTreeNode(id=183, name=分配卖家, open=false, checked=false, pid=106), ZTreeNode(id=108, name=修改用户, open=false, checked=false, pid=106), ZTreeNode(id=113, name=分配角色, open=false, checked=false, pid=106), ZTreeNode(id=184, name=卖家分配页面, open=false, checked=false, pid=106), ZTreeNode(id=109, name=删除用户, open=false, checked=false, pid=106), ZTreeNode(id=165, name=分配角色页面, open=false, checked=false, pid=106), ZTreeNode(id=187, name=新增用户页面, open=false, checked=false, pid=106), ZTreeNode(id=110, name=重置密码, open=false, checked=false, pid=106), ZTreeNode(id=166, name=修改用户页面, open=false, checked=false, pid=106), ZTreeNode(id=192, name=用户获取头像, open=false, checked=false, pid=106), ZTreeNode(id=111, name=冻结用户, open=false, checked=false, pid=106), ZTreeNode(id=167, name=用户列表, open=false, checked=false, pid=106), ZTreeNode(id=107, name=添加用户, open=false, checked=false, pid=106), ZTreeNode(id=193, name=用户上传头像, open=false, checked=false, pid=106), ZTreeNode(id=162, name=修改角色跳转, open=false, checked=false, pid=114), ZTreeNode(id=115, name=添加角色, open=false, checked=false, pid=114), ZTreeNode(id=163, name=角色分配权限, open=false, checked=false, pid=114), ZTreeNode(id=116, name=修改角色, open=false, checked=false, pid=114), ZTreeNode(id=164, name=角色列表, open=false, checked=false, pid=114), ZTreeNode(id=117, name=删除角色, open=false, checked=false, pid=114), ZTreeNode(id=118, name=角色配置权限, open=false, checked=false, pid=114), ZTreeNode(id=189, name=菜单状态切换, open=false, checked=false, pid=119), ZTreeNode(id=122, name=删除菜单, open=false, checked=false, pid=119), ZTreeNode(id=190, name=首页菜单, open=false, checked=false, pid=119), ZTreeNode(id=150, name=编辑菜单页, open=false, checked=false, pid=119), ZTreeNode(id=191, name=角色配置权限, open=false, checked=false, pid=119), ZTreeNode(id=151, name=菜单列表, open=false, checked=false, pid=119), ZTreeNode(id=120, name=添加菜单, open=false, checked=false, pid=119), ZTreeNode(id=188, name=新增菜单页, open=false, checked=false, pid=119), ZTreeNode(id=121, name=修改菜单, open=false, checked=false, pid=119), ZTreeNode(id=159, name=日志详情, open=false, checked=false, pid=128), ZTreeNode(id=134, name=清空日志, open=false, checked=false, pid=128), ZTreeNode(id=158, name=日志列表, open=false, checked=false, pid=128), ZTreeNode(id=152, name=修改部门跳转, open=false, checked=false, pid=131), ZTreeNode(id=153, name=部门列表, open=false, checked=false, pid=131), ZTreeNode(id=135, name=添加部门, open=false, checked=false, pid=131), ZTreeNode(id=154, name=部门详情, open=false, checked=false, pid=131), ZTreeNode(id=136, name=修改部门, open=false, checked=false, pid=131), ZTreeNode(id=137, name=删除部门, open=false, checked=false, pid=131), ZTreeNode(id=140, name=删除字典, open=false, checked=false, pid=132), ZTreeNode(id=155, name=修改菜单跳转, open=false, checked=false, pid=132), ZTreeNode(id=156, name=字典列表, open=false, checked=false, pid=132), ZTreeNode(id=138, name=添加字典, open=false, checked=false, pid=132), ZTreeNode(id=157, name=字典详情, open=false, checked=false, pid=132), ZTreeNode(id=139, name=修改字典, open=false, checked=false, pid=132), ZTreeNode(id=161, name=登录日志列表, open=false, checked=false, pid=133), ZTreeNode(id=160, name=清空登录日志, open=false, checked=false, pid=133), ZTreeNode(id=142, name=添加通知, open=false, checked=false, pid=141), ZTreeNode(id=143, name=修改通知, open=false, checked=false, pid=141), ZTreeNode(id=144, name=删除通知, open=false, checked=false, pid=141), ZTreeNode(id=169, name=卖家管理, open=false, checked=false, pid=168), ZTreeNode(id=176, name=优惠券, open=false, checked=false, pid=168), ZTreeNode(id=173, name=修改卖家跳转, open=false, checked=false, pid=169), ZTreeNode(id=174, name=添加卖家跳转, open=false, checked=false, pid=169), ZTreeNode(id=170, name=添加卖家, open=false, checked=false, pid=169), ZTreeNode(id=175, name=卖家列表, open=false, checked=false, pid=169), ZTreeNode(id=171, name=修改卖家, open=false, checked=false, pid=169), ZTreeNode(id=172, name=删除卖家, open=false, checked=false, pid=169), ZTreeNode(id=178, name=二维码, open=false, checked=false, pid=176), ZTreeNode(id=179, name=删除优惠券, open=false, checked=false, pid=176), ZTreeNode(id=181, name=添加优惠券跳转, open=false, checked=false, pid=176), ZTreeNode(id=182, name=优惠券列表, open=false, checked=false, pid=176), ZTreeNode(id=177, name=添加优惠券, open=false, checked=false, pid=176), ZTreeNode(id=185, name=使用优惠券, open=false, checked=false, pid=176)] com.stylefeng.guns.service.impl.MenuServiceImplTest Line:103

    /**
     * Method: menuidListByRoleId(Integer roleId)
     */
    @Test
    public void testGetMenuIdsByRoleId() throws Exception {
        //TODO: Test goes here...
        List<ZTreeNode> list = menuService.getCheckedMenuTree(1);
        log.info("{}", list);
    }

//    Hibernate: select url from sys_relation rel inner join sys_menu m on rel.menuid = m.id and m.status=1 where rel.roleid = ?
//            16:17:57 INFO  - [#, /mgr, /mgr/add, /mgr/edit, /mgr/delete, /mgr/reset, /mgr/freeze, /mgr/unfreeze, /mgr/setRole, /role, /role/add, /role/edit, /role/remove, /role/setAuthority, /menu, /menu/add, /menu/edit, /menu/delete, /log, /druid, /dept, /type, /loginLog, /log/delLog, /dept/add, /dept/update, /dept/delete, /type/add, /type/update, /type/delete, /notice, /notice/add, /notice/update, /notice/delete, /notice/hello, /code, /swagger-ui.html, /menu/menu_edit, /menu/list, /dept/dept_update, /dept/list, /dept/detail, /type/dict_edit, /type/list, /type/detail, /log/list, /log/detail, /loginLog/delLoginLog, /loginLog/list, /role/role_edit, /role/role_assign, /role/list, /mgr/role_assign, /mgr/user_edit, /mgr/list, #, /seller, /seller/add, /seller/edit, /seller/seller_edit, /seller/seller_add, /seller/list, /coupon, /coupon/add, /coupon/qrcode, /coupon/delete, /coupon/coupon_add, /coupon/list, /mgr/setSeller, /mgr/seller_assign, /coupon/use, /, /mgr/user_add, /menu/menu_add, /menu/switch] com.stylefeng.guns.service.impl.MenuServiceImplTest Line:98

    /**
     * Method: getUrlsByRoleId(Integer roleId)
     */
    @Test
    public void testGetUrlsByRoleId() throws Exception {
        //TODO: Test goes here...
        List<String> list = menuService.getUrlsByRoleId(1);
        log.info("{}", list);
    }

//    Hibernate: SELECT m1.id AS id,m1.NAME AS name,  (CASE WHEN (m2.id = 0 OR m2.id IS NULL) THEN 0 ELSE m2.id END ) AS pid,  (CASE WHEN (m2.id = 0 OR m2.id IS NULL) THEN 'true' ELSE 'false' END ) as open  FROM sys_menu m1  LEFT join sys_menu m2 ON m2.status = 1 and m1.pcode = m2.CODE  WHERE m1.status = 1  order by m1.levels, m1.num
//16:17:57 INFO  - [ZTreeNode(id=186, name=首页, open=true, checked=null, pid=0), ZTreeNode(id=145, name=通知, open=true, checked=null, pid=0), ZTreeNode(id=149, name=接口文档, open=true, checked=null, pid=0), ZTreeNode(id=148, name=代码生成, open=true, checked=null, pid=0), ZTreeNode(id=105, name=系统管理, open=true, checked=null, pid=0), ZTreeNode(id=168, name=业务管理, open=true, checked=null, pid=0), ZTreeNode(id=106, name=用户管理, open=false, checked=null, pid=105), ZTreeNode(id=169, name=卖家管理, open=false, checked=null, pid=168), ZTreeNode(id=114, name=角色管理, open=false, checked=null, pid=105), ZTreeNode(id=176, name=优惠券, open=false, checked=null, pid=168), ZTreeNode(id=131, name=部门管理, open=false, checked=null, pid=105), ZTreeNode(id=132, name=字典管理, open=false, checked=null, pid=105), ZTreeNode(id=119, name=菜单管理, open=false, checked=null, pid=105), ZTreeNode(id=133, name=登录日志, open=false, checked=null, pid=105), ZTreeNode(id=128, name=业务日志, open=false, checked=null, pid=105), ZTreeNode(id=130, name=监控管理, open=false, checked=null, pid=105), ZTreeNode(id=141, name=通知管理, open=false, checked=null, pid=105), ZTreeNode(id=142, name=添加通知, open=false, checked=null, pid=141), ZTreeNode(id=135, name=添加部门, open=false, checked=null, pid=131), ZTreeNode(id=138, name=添加字典, open=false, checked=null, pid=132), ZTreeNode(id=136, name=修改部门, open=false, checked=null, pid=131), ZTreeNode(id=160, name=清空登录日志, open=false, checked=null, pid=133), ZTreeNode(id=167, name=用户列表, open=false, checked=null, pid=106), ZTreeNode(id=139, name=修改字典, open=false, checked=null, pid=132), ZTreeNode(id=137, name=删除部门, open=false, checked=null, pid=131), ZTreeNode(id=182, name=优惠券列表, open=false, checked=null, pid=176), ZTreeNode(id=151, name=菜单列表, open=false, checked=null, pid=119), ZTreeNode(id=140, name=删除字典, open=false, checked=null, pid=132), ZTreeNode(id=175, name=卖家列表, open=false, checked=null, pid=169), ZTreeNode(id=164, name=角色列表, open=false, checked=null, pid=114), ZTreeNode(id=158, name=日志列表, open=false, checked=null, pid=128), ZTreeNode(id=188, name=新增菜单页, open=false, checked=null, pid=119), ZTreeNode(id=143, name=修改通知, open=false, checked=null, pid=141), ZTreeNode(id=187, name=新增用户页面, open=false, checked=null, pid=106), ZTreeNode(id=181, name=添加优惠券跳转, open=false, checked=null, pid=176), ZTreeNode(id=174, name=添加卖家跳转, open=false, checked=null, pid=169), ZTreeNode(id=161, name=登录日志列表, open=false, checked=null, pid=133), ZTreeNode(id=107, name=添加用户, open=false, checked=null, pid=106), ZTreeNode(id=177, name=添加优惠券, open=false, checked=null, pid=176), ZTreeNode(id=120, name=添加菜单, open=false, checked=null, pid=119), ZTreeNode(id=159, name=日志详情, open=false, checked=null, pid=128), ZTreeNode(id=144, name=删除通知, open=false, checked=null, pid=141), ZTreeNode(id=115, name=添加角色, open=false, checked=null, pid=114), ZTreeNode(id=134, name=清空日志, open=false, checked=null, pid=128), ZTreeNode(id=170, name=添加卖家, open=false, checked=null, pid=169), ZTreeNode(id=155, name=修改菜单跳转, open=false, checked=null, pid=132), ZTreeNode(id=178, name=二维码, open=false, checked=null, pid=176), ZTreeNode(id=166, name=修改用户页面, open=false, checked=null, pid=106), ZTreeNode(id=173, name=修改卖家跳转, open=false, checked=null, pid=169), ZTreeNode(id=162, name=修改角色跳转, open=false, checked=null, pid=114), ZTreeNode(id=150, name=编辑菜单页, open=false, checked=null, pid=119), ZTreeNode(id=152, name=修改部门跳转, open=false, checked=null, pid=131), ZTreeNode(id=116, name=修改角色, open=false, checked=null, pid=114), ZTreeNode(id=153, name=部门列表, open=false, checked=null, pid=131), ZTreeNode(id=171, name=修改卖家, open=false, checked=null, pid=169), ZTreeNode(id=108, name=修改用户, open=false, checked=null, pid=106), ZTreeNode(id=156, name=字典列表, open=false, checked=null, pid=132), ZTreeNode(id=121, name=修改菜单, open=false, checked=null, pid=119), ZTreeNode(id=179, name=删除优惠券, open=false, checked=null, pid=176), ZTreeNode(id=117, name=删除角色, open=false, checked=null, pid=114), ZTreeNode(id=154, name=部门详情, open=false, checked=null, pid=131), ZTreeNode(id=172, name=删除卖家, open=false, checked=null, pid=169), ZTreeNode(id=109, name=删除用户, open=false, checked=null, pid=106), ZTreeNode(id=157, name=字典详情, open=false, checked=null, pid=132), ZTreeNode(id=122, name=删除菜单, open=false, checked=null, pid=119), ZTreeNode(id=185, name=使用优惠券, open=false, checked=null, pid=176), ZTreeNode(id=189, name=菜单状态切换, open=false, checked=null, pid=119), ZTreeNode(id=110, name=重置密码, open=false, checked=null, pid=106), ZTreeNode(id=163, name=角色分配权限, open=false, checked=null, pid=114), ZTreeNode(id=118, name=角色配置权限, open=false, checked=null, pid=114), ZTreeNode(id=111, name=冻结用户, open=false, checked=null, pid=106), ZTreeNode(id=112, name=解除冻结用户, open=false, checked=null, pid=106), ZTreeNode(id=165, name=分配角色页面, open=false, checked=null, pid=106), ZTreeNode(id=113, name=分配角色, open=false, checked=null, pid=106), ZTreeNode(id=184, name=卖家分配页面, open=false, checked=null, pid=106), ZTreeNode(id=183, name=分配卖家, open=false, checked=null, pid=106), ZTreeNode(id=192, name=用户获取头像, open=false, checked=null, pid=106), ZTreeNode(id=190, name=首页菜单, open=false, checked=null, pid=119), ZTreeNode(id=193, name=用户上传头像, open=false, checked=null, pid=106), ZTreeNode(id=191, name=角色配置权限, open=false, checked=null, pid=119)] com.stylefeng.guns.service.impl.MenuServiceImplTest Line:108

    /**
     * Method: tree()
     */
    @Test
    public void testMenuTree() throws Exception {
        //TODO: Test goes here...
        List<ZTreeNode> list = menuService.menuTree();
        log.info("{}", list);
    }

    /**
     * Method: getCheckedMenuTree(List<Long> menuIds)
     */
    @Test
    public void testMenuTreeByMenuIds() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: getMenusByRoleIds(List<Integer> roleIds)
     */
    @Test
    public void testGetMenusByRoleIds() throws Exception {
        //TODO: Test goes here...
        List<String> list = menuService.getUrlsByRoleId(1);
        log.info("{}", list);
    }

//    Hibernate: select menu0_.id as id1_3_, menu0_.code as code2_3_, menu0_.icon as icon3_3_, menu0_.ismenu as ismenu4_3_, menu0_.isopen as isopen5_3_, menu0_.levels as levels6_3_, menu0_.name as name7_3_, menu0_.num as num8_3_, menu0_.pcode as pcode9_3_, menu0_.pcodes as pcodes10_3_, menu0_.status as status11_3_, menu0_.tips as tips12_3_, menu0_.url as url13_3_ from sys_menu menu0_ where menu0_.status=1 and menu0_.code=?
//            16:17:57 INFO  - Menu{id=106, code='mgr', name='用户管理', pcode='system', pcodes='[0],[system],', icon='', url='/mgr', levels=2, num=1, ismenu=1, isopen=0, status=1, tips='null'} com.stylefeng.guns.service.impl.MenuServiceImplTest Line:136

    /**
     * Method: getByCode(String code)
     */
    @Test
    public void testGetByCode() throws Exception {
        //TODO: Test goes here...
        Menu menu = menuService.getByCode("mgr");
        log.info("{}", menu);
    }


    /**
     * Method: delMenuById(Long menuId)
     */
    @Test
    public void testDelMenuById() throws Exception {
        //TODO: Test goes here... 
        /* 
        try { 
           Method method = MenuServiceImpl.getClass().getMethod("delMenuById", Long.class); 
           method.setAccessible(true); 
           method.invoke(<Object>, <Parameters>); 
        } catch(NoSuchMethodException e) { 
        } catch(IllegalAccessException e) { 
        } catch(InvocationTargetException e) { 
        } 
        */
    }

    /**
     * Method: switchStatus(Long menuId, Integer code)
     */
    @Test
    public void testSwitchStatus() throws Exception {
        //TODO: Test goes here... 
        /* 
        try { 
           Method method = MenuServiceImpl.getClass().getMethod("switchStatus", Long.class, Integer.class); 
           method.setAccessible(true); 
           method.invoke(<Object>, <Parameters>); 
        } catch(NoSuchMethodException e) { 
        } catch(IllegalAccessException e) { 
        } catch(InvocationTargetException e) { 
        } 
        */
    }
}
