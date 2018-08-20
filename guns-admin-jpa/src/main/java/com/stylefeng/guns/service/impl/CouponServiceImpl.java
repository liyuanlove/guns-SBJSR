package com.stylefeng.guns.service.impl;

import com.stylefeng.guns.core.common.constant.AdminConst;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.shiroext.kit.ShiroKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.dao.CouponDao;
import com.stylefeng.guns.dao.UserSellerDao;
import com.stylefeng.guns.po.Coupon;
import com.stylefeng.guns.service.ICouponService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tan.jpa.exception.GunsException;
import org.tan.jpa.service.impl.BaseServiceImpl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 优惠券Service实现
 *
 * @author TC
 * @Date 2018-07-29 16:44:14
 */
@Service
public class CouponServiceImpl extends BaseServiceImpl<Coupon, Integer, CouponDao> implements ICouponService {

    @Autowired
    private UserSellerDao userSellerDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void genCoupon(Coupon coupon, int num) {
        List<Coupon> list = Coupon.createList(coupon, num);
        baseDao.saveAll(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCoupon(Integer couponId) {
        Coupon coupon = baseDao.getOne(couponId);
        if (coupon.getUserTel() != null) {
            throw new GunsException(BizExceptionEnum.COUPON_CANT_DELETE);
        }
        baseDao.deleteById(couponId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String recept(String userTel, Integer id) {
        Coupon coupon = baseDao.getOne(id);
        if (coupon.getUserTel() != null) {
            if (userTel.equals(coupon.getUserTel())) {
                throw new GunsException(BizExceptionEnum.COUPON_RECEPTED);
            }
            throw new GunsException(BizExceptionEnum.OTHER_RECEPTED);
        } else {
            Date date = new Date();
            date = DateUtils.truncate(date, Calendar.DATE);
            int result = date.compareTo(coupon.getEndDay());
            //过期了
            if (result > 0) {
                throw new GunsException(BizExceptionEnum.COUPON_EXPIRED);
            }
            coupon.setUserTel(userTel);
            coupon.setReceptDay(new Date());
            String code = ToolUtil.getRandomString(5).toUpperCase();
            coupon.setUseCode(code);
            baseDao.save(coupon);
            return code;
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void use(String code, String userTel) {
        Coupon coupon = baseDao.loadByTelAndCode(userTel, code);
        String useCode = coupon.getUseCode();
        if (useCode != null) {
            Date date = new Date();
            date = DateUtils.truncate(date, Calendar.DATE);
            int result = date.compareTo(coupon.getEndDay());
            //过期了
            if (result > 0) {
                throw new GunsException(BizExceptionEnum.COUPON_EXPIRED);
            }
            if (useCode.equals(code.trim())) {
                coupon.setUsed(Integer.valueOf(1));
                baseDao.save(coupon);
            } else {
                throw new GunsException(BizExceptionEnum.COUPON_CODE_ERROR);
            }
        } else {
            throw new GunsException(BizExceptionEnum.COUPON_NOT_ACTIVED);
        }
    }

    @Override
    public Page<Coupon> getPage(Pageable pageable, String userTel) {
        if (ShiroKit.isAdmin()) {
            return baseDao.findAll(pageable);
        } else if (ShiroKit.hasRole(AdminConst.SELLER_NAME)) {
            Integer id = ShiroKit.getUser().getId();
            List<Integer> sellers = userSellerDao.sellerScope(id);
            Specification<Coupon> specification = new Specification<Coupon>() {
                @Override
                public Predicate toPredicate(Root<Coupon> root, CriteriaQuery<?> query,
                                             CriteriaBuilder cb) {
                    List<Predicate> list = new ArrayList<Predicate>();
                    if (StringUtils.isNotBlank(userTel)) {
                        list.add(cb.like(root.get("userTel").as(String.class), "%" + userTel + "%"));
                    }
                    if (CollectionUtils.isNotEmpty(sellers)) {
                        list.add(root.get("sellerid").as(Integer.class).in(sellers));
                    }
                    Predicate[] p = new Predicate[list.size()];
                    return cb.and(list.toArray(p));
                }
            };
            return baseDao.findAll(specification, pageable);
        } else {
            throw new GunsException(BizExceptionEnum.NO_PERMITION);
        }
    }

    @Override
    public List<Coupon> getListByTel(String userTel) {
        return this.baseDao.findByUserTelEquals(userTel);
    }
}
