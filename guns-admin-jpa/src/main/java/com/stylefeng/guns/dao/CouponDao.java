package com.stylefeng.guns.dao;

import com.stylefeng.guns.po.Coupon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tc.jpa.dao.BaseDao;

import java.util.List;

/**
 * 优惠券Dao
 *
 * @author TC
 * @Date 2018-07-29 16:44:14
 */
@Repository
public interface CouponDao extends BaseDao<Coupon, Integer> {

    /**
     * 通过验证码查找
     *
     * @return
     */
    @Query("select o from Coupon o where o.userTel like %?1% and o.useCode = ?2")
    public Coupon loadByTelAndCode(String userTel, String useCode);

    /**
     * 通过手机号码查询
     *
     * @param userTel
     * @return
     */
    public List<Coupon> findByUserTelEquals(String userTel);
}
