package com.stylefeng.guns.warpper;

import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.po.Notice;
import com.stylefeng.guns.vo.NoticeVo;
import org.springframework.beans.BeanUtils;
import org.tan.jpa.warpper.BeanWarpper;

/**
 * 部门列表的包装
 *
 * @author fengshuonan
 * @date 2017年4月25日 18:10:31
 */
public class NoticeWrapper extends BeanWarpper<Notice, NoticeVo> {

    @Override
    public NoticeVo warpBean(Notice po) {
        NoticeVo vo = new NoticeVo();
        BeanUtils.copyProperties(po, vo);
        Integer creater = po.getCreater();
        vo.setCreaterName(ConstantFactory.me().getUserNameById(creater));
        return vo;
    }

}
