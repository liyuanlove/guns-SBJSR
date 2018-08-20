package com.stylefeng.guns.dao;

import com.stylefeng.guns.po.Seller;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tan.jpa.dao.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * 商家Dao
 *
 * @author TC
 * @Date 2018-07-29 16:43:59
 */
@Repository
public interface SellerDao extends BaseDao<Seller, Integer> {

    /**
     * 该商家是否已存在
     *
     * @param name
     * @return
     */
    public boolean existsByNameEquals(String name);

    /**
     * 更新状态
     *
     * @param id
     * @param status
     * @return
     */
    @Transactional
    @Modifying
    @Query("update Seller set status = ?2 where id = ?1")
    public int updateStatus(Integer id, Integer status);

    /**
     * 获取已经配置的商家范围勾选展开
     *
     * @param userId
     * @return
     */
    @Query(nativeQuery = true,
            value = "SELECT t1.id, name," +
                    "( CASE WHEN (t2.sellerid IS NULL) THEN 'false' ELSE 'true' END) checked " +
                    "FROM seller t1 " +
                    "LEFT JOIN ( SELECT sellerid FROM sys_user_seller WHERE userid =:userId) t2" +
                    " ON t1.id = t2.sellerid WHERE t1.status = 1 ORDER BY  t1.createtime,t1.id")
    public List<Map<String, Object>> getCheckedSellerTree(@Param("userId") Integer userId);

    /**
     * 获取所有商家
     *
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT id, name FROM seller WHERE status = 1 ORDER BY createtime,id")
    public List<Map<String, Object>> getSellerTree();

    /**
     * 获取范围内商家
     *
     * @param userid
     * @return
     */
    @Query(nativeQuery = true,
            value = "SELECT id, name  FROM seller" +
                    " WHERE status = 1 and id in(selset sellerid from sys_user_seller where userid = :userid)" +
                    " ORDER BY createtime,id")
    public List<Map<String, Object>> getSellerTreeByScope(@Param("userid") Integer userid);

}
