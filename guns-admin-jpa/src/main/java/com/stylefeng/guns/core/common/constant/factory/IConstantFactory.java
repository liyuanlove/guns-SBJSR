package com.stylefeng.guns.core.common.constant.factory;

import com.stylefeng.guns.po.Dict;

import java.util.List;

/**
 * 常量生产工厂的接口
 *
 * @author fengshuonan
 * @date 2017-06-14 21:12
 */
public interface IConstantFactory {

    /**
     * 根据用户id获取用户名称
     *
     * @author stylefeng
     * @Date 2017/5/9 23:41
     */
    String getUserNameById(Integer userId);

    /**
     * 根据用户id获取用户账号
     *
     * @author stylefeng
     * @date 2017年5月16日21:55:371
     */
    String getUserAccountById(Integer userId);


    /**
     * 通过角色ids获取角色名称
     */
    String getMultiRoleName(String roleids);

    /**
     * 通过角色id获取角色名称
     */
    String getSingleRoleName(Integer roleid);

    /**
     * 通过角色id获取角色英文名称
     */
    String getRoleEnName(Integer roleId);

    /**
     * 获取部门名称
     */
    String getDeptName(Integer deptId);

    /**
     * 获取子部门id
     */
    List<Integer> getSubDeptId(Integer deptId);

    /**
     * 获取所有父部门id
     */
    List<Integer> getParentDeptIds(Integer deptId);

    /**
     * 获取菜单的名称们(多个)
     */
    String getMenuNames(String menuids);

    /**
     * 获取字典名称
     */
    String getDictName(Integer dictId);


    /**
     * 根据字典名称和字典中的值获取对应的名称
     */
    String getDictsByName(String name, Integer val);

    /**
     * 获取性别名称
     */
    String getSexName(Integer sex);

    /**
     * 获取用户登录状态
     */
    String getStatusName(Integer status);

    /**
     * 查询字典
     */
    List<Dict> findChildrenInDict(Integer id);

    /**
     * 获取被缓存的对象(用户删除业务)
     */
    Object getCrudObject(Object key);


    /**
     * 通过卖家ID获取卖家name
     *
     * @param sellerid
     * @return
     */
    String getSellerName(Integer sellerid);

    /**
     * 获取通知标题
     */
    String getNoticeTitle(Integer dictId);
}
