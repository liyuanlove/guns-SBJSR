package com.stylefeng.guns.service;

import com.stylefeng.guns.po.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.tan.jpa.service.IBaseService;

import java.util.List;

/**
 * 优惠券Service
 *
 * @author TC
 * @Date 2018-07-29 16:44:13
 */
public interface ICouponService extends IBaseService<Coupon, Integer> {

    /**
     * 生成优惠券
     *
     * @param coupon
     * @param num
     * @return
     */
    public void genCoupon(Coupon coupon, int num);

    /**
     * 删除未领取的数据
     *
     * @param couponId
     * @return
     */
    public void deleteCoupon(Integer couponId);

    /**
     * 领取优惠券
     *
     * @return
     */
    public String recept(String userTel, Integer id);

    /**
     * 使用优惠券
     *
     * @return
     */
    public void use(String code, String userTel);

    /**
     * 获取所有列表
     */
    Page<Coupon> getPage(Pageable pageable, String userTel);

    /**
     * 根据手机号查询
     */
    List<Coupon> getListByTel(String userTel);

}
