package com.stylefeng.guns.warpper;

import com.stylefeng.guns.core.common.constant.enums.DoubleStatus;
import com.stylefeng.guns.core.constant.IsMenu;
import com.stylefeng.guns.po.Menu;
import com.stylefeng.guns.vo.MenuVo;
import org.springframework.beans.BeanUtils;
import org.tc.jpa.warpper.BeanWarpper;

/**
 * 菜单列表的包装类
 *
 * @author fengshuonan
 * @date 2017年2月19日15:07:29
 */
public class MenuWarpper extends BeanWarpper<Menu, MenuVo> {

    @Override
    public MenuVo warpBean(Menu po) {
        MenuVo vo = new MenuVo();
        BeanUtils.copyProperties(po, vo);
        vo.setStatusName(DoubleStatus.valueOf(po.getStatus()));
        vo.setIsMenuName(IsMenu.valueOf(po.getIsmenu()));
        return vo;
    }

}
