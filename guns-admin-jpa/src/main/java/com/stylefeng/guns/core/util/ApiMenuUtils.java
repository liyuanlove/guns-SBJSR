package com.stylefeng.guns.core.util;

import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.common.constant.AdminConst;
import com.stylefeng.guns.core.ztree.MenuNode;

import java.util.List;

/**
 * api接口文档显示过滤
 *
 * @author fengshuonan
 * @date 2017-08-17 16:55
 */
public class ApiMenuUtils extends MenuNode {

    public static List<MenuNode> build(List<MenuNode> nodes) {

        //如果关闭了接口文档,则不显示接口文档菜单
        GunsProperties gunsProperties = SpringContextHolder.getBean(GunsProperties.class);
        if (!gunsProperties.getSwaggerOpen()) {
            MenuNode target = null;
            for (MenuNode menuNode : nodes) {
                if (AdminConst.API_MENU_NAME.equals(menuNode.getName())) {
                    target = menuNode;
                    break;
                }
            }
            if (target != null) {
                nodes.remove(target);
            }
        }

        return nodes;
    }
}
