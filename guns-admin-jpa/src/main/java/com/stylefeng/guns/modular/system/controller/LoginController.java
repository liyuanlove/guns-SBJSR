package com.stylefeng.guns.modular.system.controller;

import com.baomidou.kaptcha.Kaptcha;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.shiroext.kit.ShiroKit;
import com.stylefeng.guns.core.shiroext.vo.ShiroUser;
import com.stylefeng.guns.core.util.ApiMenuUtils;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.core.ztree.MenuNode;
import com.stylefeng.guns.po.User;
import com.stylefeng.guns.service.IMenuService;
import com.stylefeng.guns.service.IUserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 登录控制器
 *
 * @author fengshuonan
 * @Date 2017年1月10日 下午8:25:24
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private Kaptcha kaptcha;

    /**
     * 登录页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        if (ShiroKit.isAuthenticated() || ShiroKit.getUser() != null) {
            return REDIRECT + "/";
        } else {
            return "/login.html";
        }
    }

    /**
     * 点击登录执行的动作（登录过滤器）
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginVali() {
        return REDIRECT + "/";
    }

    /**
     * 退出（下线过滤器）
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut() {
        deleteAllCookie();
        return REDIRECT + "/login";
    }

    /**
     * 主页
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {

        //未登录
        ShiroUser shiroUser = ShiroKit.getUser();
        if (ToolUtil.isEmpty(shiroUser)) {
            model.addAttribute("tips", "你还没有登录");
            return "/login.html";
        }

        //未分配角色
        List<Integer> roleList = shiroUser.getRoleList();
        if (CollectionUtils.isEmpty(roleList)) {
            ShiroKit.getSubject().logout();
            model.addAttribute("tips", "该用户没有角色，无法登陆");
            return "/login.html";
        }

        //获取菜单列表
        List<MenuNode> menus = menuService.getMenusByRoleIds(roleList);
        List<MenuNode> titles = MenuNode.buildTitle(menus);
        titles = ApiMenuUtils.build(titles);
        model.addAttribute("titles", titles);

        //获取用户头像
        Integer id = shiroUser.getId();
        User user = userService.getOneById(id);
        String avatar = user.getAvatar();
        model.addAttribute("avatar", avatar);

        return "/index.html";
    }

    /**
     * 验证码
     */
    @RequestMapping("/kaptcha")
    public void kaptcha(HttpServletRequest request, HttpServletResponse response) {
        kaptcha.init(request, response);
        kaptcha.render();
    }

}
