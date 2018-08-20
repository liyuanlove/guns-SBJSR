package com.stylefeng.guns.warpper;

import com.stylefeng.guns.core.common.constant.enums.DoubleStatus;
import com.stylefeng.guns.po.Seller;
import com.stylefeng.guns.vo.SellerVo;
import org.springframework.beans.BeanUtils;
import org.tan.jpa.warpper.BeanWarpper;

/**
 * 卖家列表的包装类
 *
 * @author fengshuonan
 * @date 2017年2月19日15:07:29
 */
public class SellerWarpper extends BeanWarpper<Seller, SellerVo> {

    @Override
    public SellerVo warpBean(Seller po) {
        SellerVo vo = new SellerVo();
        BeanUtils.copyProperties(po, vo);
        vo.setStatusName(DoubleStatus.valueOf(po.getStatus()));
        return vo;
    }

}
