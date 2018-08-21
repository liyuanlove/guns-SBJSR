package com.stylefeng.guns.warpper;

import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.po.Coupon;
import com.stylefeng.guns.vo.CouponVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.tc.jpa.warpper.BeanWarpper;

import java.util.Calendar;
import java.util.Date;

/**
 * 卖家列表的包装类
 *
 * @author fengshuonan
 * @date 2017年2月19日15:07:29
 */
public class CouponWarpper extends BeanWarpper<Coupon, CouponVo> {


    @Override
    public CouponVo warpBean(Coupon coupon) {
        CouponVo vo = new CouponVo();
        BeanUtils.copyProperties(coupon, vo);

        String userTel = coupon.getUserTel();
        Integer used = coupon.getUsed();

        Date endDay = coupon.getEndDay();
        Date today = new Date();
        today = DateUtils.truncate(today, Calendar.DATE);

        String status = null;
        //过期了
        boolean istoolate = today.compareTo(endDay) > 0;
        boolean actived = StringUtils.isNotBlank(userTel);
        boolean useflag = 1 == used.intValue();
        int bg = 3;
        if (istoolate) {
            if (useflag) {
                status = "已使用";
                bg = 2;
            } else {
                status = "已过期";
            }
        } else {
            if (actived) {
                if (useflag) {
                    status = "已使用";
                    bg = 2;
                } else {
                    status = "未使用";
                    bg = 1;
                }
            } else {
                status = "未领取";
                bg = 0;
            }
        }
        vo.setStatus(status);
        vo.setBg(bg);
        vo.setSellername(ConstantFactory.me().getSellerName(coupon.getSellerid()));
        return vo;
    }

}
