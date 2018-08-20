package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.common.constant.AdminConst;
import com.stylefeng.guns.core.common.constant.enums.TrebleStatus;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.shiroext.kit.ShiroKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.po.User;
import com.stylefeng.guns.service.ISellerService;
import com.stylefeng.guns.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tan.jpa.controller.BaseController;
import org.tan.jpa.exception.GunsException;
import org.tan.jpa.tips.Tip;

/**
 * 系统管理员控制器
 *
 * @author fengshuonan
 * @Date 2017年1月11日 下午1:08:17
 */
@Controller
@RequestMapping("/mgr")
public class MgrController extends BaseController {

    private static String PREFIX = "/system/user/";

    @Autowired
    private IUserService userService;
    @Autowired
    private ISellerService sellerService;

    /**
     * 删除管理员（逻辑删除）
     */
    @RequestMapping("/delete")
//    @BizLog(value = "删除管理员", key = "userId", type = UserDict.class)
    @ResponseBody
    public Tip delete(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能删除超级管理员
        if (userId.equals(AdminConst.ADMIN_ID)) {
            throw new GunsException(BizExceptionEnum.CANT_DELETE_ADMIN);
        }
        this.userService.setStatus(userId, TrebleStatus.DELETED.getCode());
        return SUCCESS_TIP;
    }

    /**
     * 冻结用户
     */
    @RequestMapping("/freeze")
//    @BizLog(value = "冻结用户", key = "userId", type = UserDict.class)
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @ResponseBody
    public Tip freeze(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能冻结超级管理员
        if (userId.equals(AdminConst.ADMIN_ID)) {
            throw new GunsException(BizExceptionEnum.CANT_FREEZE_ADMIN);
        }
        this.userService.setStatus(userId, TrebleStatus.FREEZED.getCode());
        return SUCCESS_TIP;
    }

    /**
     * 解除冻结
     */
    @RequestMapping("/unfreeze")
//    @BizLog(value = "解除冻结用户", key = "userId", type = UserDict.class)
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @ResponseBody
    public Tip unfreeze(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        this.userService.setStatus(userId, TrebleStatus.ACTIVED.getCode());
        return SUCCESS_TIP;
    }

    /**
     * 查看管理员详情
     */
    @RequestMapping("/view/{userId}")
    @ResponseBody
    public User view(@PathVariable Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        User user = this.userService.getOneById(userId);
        ShiroKit.assertAuth(userId, user);
        return user;
    }

    /**
     * 重置密码
     */
    @RequestMapping("/reset")
//    @BizLog(value = "重置管理员密码", key = "userId", type = UserDict.class)
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @ResponseBody
    public Tip reset(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        userService.resetPwd(userId);
        return SUCCESS_TIP;
    }

    /**
     * 跳转到角色分配页面
     */
    @RequestMapping("/role_assign/{userId}")
    public String roleAssign(@PathVariable Integer userId, Model model) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        User user = this.userService.getOneById(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("userAccount", user.getAccount());
        return PREFIX + "user_roleassign.html";
    }

    /**
     * 分配角色
     */
    @RequestMapping("/setRole")
//    @BizLog(value = "分配角色", key = "userId,roleIds", type = UserDict.class)
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @ResponseBody
    public Tip setRole(@RequestParam("userId") Integer userId, @RequestParam("roleIds") String roleIds) {
        if (ToolUtil.isOneEmpty(userId, roleIds)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能修改超级管理员
        if (userId.equals(AdminConst.ADMIN_ID)) {
            throw new GunsException(BizExceptionEnum.CANT_CHANGE_ADMIN);
        }
        //一般用户只具有几个角色，故不再设计桥接表
        this.userService.setRoles(userId, roleIds);
        return SUCCESS_TIP;
    }

    /**
     * 指定卖家页面
     */
    @RequestMapping("/seller_assign/{userId}")
    public String sellerAssign(@PathVariable Integer userId, Model model) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        User user = userService.getOneById(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("userAccount", user.getAccount());
        return PREFIX + "user_sellerassign.html";
    }

    /**
     * 分配卖家
     */
    @RequestMapping("/setSeller")
//    @BizLog(value = "分配卖家", key = "userId,sellerIds", type = SellerDict.class)
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @ResponseBody
    public Tip setSeller(@RequestParam("userId") Integer userId,
                         @RequestParam("sellerIds") String sellerIds) {
        if (ToolUtil.isOneEmpty(userId, sellerIds)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //不处理超级管理员（默认所有）
        if (userId.equals(AdminConst.ADMIN_ID)) {
            throw new GunsException(BizExceptionEnum.CANT_CHANGE_ADMIN);
        }
        sellerService.setSellerScope(userId, sellerIds);
        return SUCCESS_TIP;
    }

}
