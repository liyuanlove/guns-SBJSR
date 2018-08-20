package com.stylefeng.guns.dao;

import com.stylefeng.guns.po.UserSeller;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tan.jpa.dao.BaseDao;

import java.util.List;

/**
 * 卖家范围Dao
 *
 * @author TC
 * @Date 2018-07-29 16:43:03
 */
@Repository
public interface UserSellerDao extends BaseDao<UserSeller, Integer> {

    /**
     * 是否已经给该用户配置范围
     *
     * @param userid
     * @return
     */
    public boolean existsByUseridEquals(Integer userid);

    /**
     * 清空配置数据
     *
     * @param userid
     */
    @Transactional
    @Modifying
    @Query("delete from UserSeller where userid = ?1")
    public void delByUseridEq(Integer userid);

    /**
     * 通过userid查询
     *
     * @param userid
     * @return
     */
    @Query("select o.sellerid from UserSeller o where o.userid = ?1")
    public List<Integer> sellerScope(Integer userid);

}
