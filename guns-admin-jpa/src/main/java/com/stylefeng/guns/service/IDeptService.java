package com.stylefeng.guns.service;

import com.stylefeng.guns.core.ztree.ZTreeNode;
import com.stylefeng.guns.po.Dept;
import org.tc.jpa.service.IBaseService;

import java.util.List;

/**
 * 部门Service
 *
 * @author TC
 * @Date 2018-07-30 16:11:11
 */
public interface IDeptService extends IBaseService<Dept, Integer> {

    /**
     * 删除部门
     */
    void deleteCascade(Integer deptId);

    /**
     * 获取ztree的节点列表
     */
    List<ZTreeNode> tree();

    /**
     * 获取所有部门列表
     */
    List<Dept> list(String condition);
}
