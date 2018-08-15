package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.Tip;
import com.stylefeng.guns.core.common.constant.AdminConst;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.core.ztree.ZTreeNode;
import com.stylefeng.guns.po.Menu;
import com.stylefeng.guns.service.IMenuService;
import com.stylefeng.guns.warpper.MenuWarpper;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 菜单控制器
 *
 * @author fengshuonan
 * @Date 2017年2月12日21:59:14
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {

    private static String PREFIX = "/system/menu/";

    @Autowired
    private IMenuService menuService;

    /**
     * 跳转到菜单列表列表页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "menu.html";
    }

    /**
     * 获取菜单列表
     */
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String menuName, @RequestParam(required = false) String level) {
        List<Menu> list = menuService.list(menuName, level);
        return new MenuWarpper().warpList(list);
    }

    /**
     * 跳转到菜单列表列表页面
     */
    @RequestMapping(value = "/menu_add")
    public String menuAdd() {
        return PREFIX + "menu_add.html";
    }

    /**
     * 新增菜单
     */
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @RequestMapping(value = "/add")
//    @BizLog(value = "菜单新增", key = "name", type = MenuDict.class)
    @ResponseBody
    public Tip add(@Valid Menu menu, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        menuService.add(menu);
        return SUCCESS_TIP;
    }

    /**
     * 菜单修改页
     */
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @RequestMapping(value = "/menu_edit/{menuId}")
    public String menuEdit(@PathVariable Long menuId, Model model) {
        if (ToolUtil.isEmpty(menuId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        Menu menu = this.menuService.getOneById(menuId);
        String pcode = menu.getPcode();
        if (pcode == null || pcode.equals(0)) {
            menu.setPcode("0");
        }
        //获取父级菜单的id
        Menu pmenu = menuService.getByCode(pcode);
        //如果父级是顶级菜单
        if (pmenu == null) {
            menu.setPcode("0");
        }
        Map<String, Object> menuMap = BeanKit.beanToMap(menu);
        menuMap.put("pcodeName", pmenu == null ? "" : pmenu.getName());
        model.addAttribute("menu", menuMap);
        return PREFIX + "menu_edit.html";
    }

    /**
     * 修该菜单
     */
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @RequestMapping(value = "/edit")
//    @BizLog(value = "修改菜单", key = "name", type = MenuDict.class)
    @ResponseBody
    public Tip edit(@Valid Menu menu, BindingResult result) {
        Long id = menu.getId();
        if (result.hasErrors() || id == null) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //设置父级菜单编号
        menuService.setPcode(menu);
        Menu oldMenu = menuService.getOneById(id);
        this.menuService.updateNotNullField(oldMenu, menu);
        return SUCCESS_TIP;
    }

    /**
     * 状态切换
     */
//    @BizLog(value = "菜单状态切换", key = "menuId", type = MenuDict.class)
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @RequestMapping(value = "/switch")
    @ResponseBody
    public Tip switchStatus(@RequestParam Long menuId) {
        if (ToolUtil.isEmpty(menuId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //缓存菜单的名称
        this.menuService.switchStatusCascade(menuId);
        return SUCCESS_TIP;
    }

    /**
     * 删除菜单
     */
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @RequestMapping(value = "/delete")
//    @BizLog(value = "删除菜单", key = "menuId", type = MenuDict.class)
    @ResponseBody
    public Tip remove(@RequestParam Long menuId) {
        if (ToolUtil.isEmpty(menuId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //缓存菜单的名称
        this.menuService.delMenuCascade(menuId);
        return SUCCESS_TIP;
    }

    /**
     * 查看菜单
     */
    @RequestMapping(value = "/view/{menuId}")
    @ResponseBody
    public Tip view(@PathVariable Long menuId) {
        if (ToolUtil.isEmpty(menuId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        this.menuService.getOneById(menuId);
        return SUCCESS_TIP;
    }

    /**
     * 获取菜单列表(首页用，选择父级菜单用)
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> menuTreeList() {
        List<ZTreeNode> list = this.menuService.menuTree();
        list.add(ZTreeNode.createRoot());
        return list;
    }

    /**
     * 获取角色列表
     */
    @RequestMapping(value = "/treeByRoleId/{roleId}")
    @ResponseBody
    public List<ZTreeNode> menuTreeByRoleId(@PathVariable Integer roleId) {
        List<ZTreeNode> list = menuService.getCheckedMenuTree(roleId);
        list.add(ZTreeNode.createRoot());
        return list;
    }

}
