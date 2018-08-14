package com.stylefeng.guns.service;

import com.stylefeng.guns.core.base.service.IBaseService;
import com.stylefeng.guns.core.ztree.ZTreeNode;
import com.stylefeng.guns.po.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商家Service
 *
 * @author TC
 * @Date 2018-07-29 16:43:59
 */
public interface ISellerService extends IBaseService<Seller, Integer> {

    /**
     * 该商家是否已存在
     *
     * @param name
     * @return
     */
    public boolean existsByNameEquals(String name);

    /**
     * 给用户配置卖家范围
     *
     * @param userId
     * @param sellerIds
     * @return
     */
    public void setSellerScope(Integer userId, String sellerIds);

    /**
     * 更新状态
     *
     * @param id
     * @param status
     * @return
     */
    public int setStatus(Integer id, int status);

    /**
     * 分页查询
     */
    Page<Seller> list(Pageable pageable, String condition);

    /**
     * 获取已经配置的商家范围勾选展开
     *
     * @param userId
     * @return
     */
    public List<ZTreeNode> getCheckedSellerTree(Integer userId);

    /**
     * 超级管理员查询所有
     *
     * @return
     */
    public List<ZTreeNode> getSellerTree();

    /**
     * 普通管理员查看范围内数据
     *
     * @param userId
     * @return
     */
    public List<ZTreeNode> getSellerTreeByScope(Integer userId);
}
