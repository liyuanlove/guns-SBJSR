package com.stylefeng.guns.dao;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.po.Dept;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * 字典服务测试
 *
 * @author fengshuonan
 * @date 2017-04-27 17:05
 */
public class DeptTest extends BaseJunit {

    @Resource
    DeptDao deptDao;

    @Test
    public void addDeptTest() {
        Dept dept = new Dept();
        dept.setFullname("测试fullname");
        dept.setNum(5);
        dept.setPid(1);
        dept.setSimplename("测试");
        dept.setTips("测试tips");
        dept.setVersion(1);
//        Integer insertAllCol = deptDao.save(dept);
//        assertEquals(insertAllCol, new Integer(1));
    }
//
//    @Test
//    public void updateTest() {
//        Dept dept = this.deptDao.selectById(24);
//        dept.setTips("哈哈");
//        boolean flag = dept.updateById();
//        assertTrue(flag);
//    }
//
//    @Test
//    public void deleteTest() {
//        Dept dept = this.deptDao.selectById(24);
//        Integer integer = deptDao.deleteById(dept);
//        assertTrue(integer > 0);
//    }
//
//    @Test
//    public void listTest() {
//        List<Map<String, Object>> list = this.deptDao.list("总公司");
//        assertTrue(list.size() > 0);
//    }
}
