package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.common.annotion.BizLog;
import com.stylefeng.guns.core.common.annotion.BizNameType;
import com.stylefeng.guns.core.common.constant.AdminConst;
import com.stylefeng.guns.core.common.constant.cache.Cache;
import com.stylefeng.guns.core.common.constant.cache.CacheKey;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.shiroext.kit.ShiroKit;
import com.stylefeng.guns.core.shiroext.vo.ShiroUser;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.po.User;
import com.stylefeng.guns.service.IUserService;
import com.stylefeng.guns.warpper.UserWarpper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tc.jpa.controller.BaseController;
import org.tc.jpa.exception.GunsException;
import org.tc.jpa.tips.Tip;
import org.tc.jpa.util.FileUtil;
import org.tc.redis.cache.RedisCacheDao;

import javax.naming.NoPermissionException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 系统管理员控制器
 *
 * @author fengshuonan
 * @Date 2017年1月11日 下午1:08:17
 */
@Controller
@RequestMapping("/mgr")
public class UserController extends BaseController {

    private static String PREFIX = "/system/user/";

    @Autowired
    private IUserService userService;
    @Autowired
    private GunsProperties gunsProperties;
    @Autowired
    private RedisCacheDao redisCacheDao;

    /**
     * 用户管理首页
     */
    @GetMapping("")
    public String index() {
        return PREFIX + "user.html";
    }

    /**
     * 获取用户列表
     */
    @ApiOperation("获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名", required = false, dataType = "String"),
            @ApiImplicitParam(name = "beginTime", value = "注册不早于日期", required = false, dataType = "Date"),
            @ApiImplicitParam(name = "endTime", value = "注册不晚于日期", required = false, dataType = "String"),
            @ApiImplicitParam(name = "deptid", value = "所属部门ID", required = false, dataType = "Integer")
    })
    @PostMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String name,
                       @RequestParam(required = false) Date beginTime,
                       @RequestParam(required = false) String endTime,
                       @RequestParam(required = false) Integer deptid) {
        List<User> list = userService.list(name, beginTime, endTime, deptid);
        return new UserWarpper().warpList(list);
    }

    /**
     * 跳转到查看管理员列表的页面
     */
    @GetMapping("/user_add")
    public String addView() {
        return PREFIX + "user_add.html";
    }

    /**
     * 添加管理员
     */
    @PostMapping("/add")
    @BizLog(value = "管理员", type = BizNameType.ADD)
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @ResponseBody
    public Tip add(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        userService.add(user);
        return SUCCESS_TIP;
    }

    /**
     * 跳转到编辑管理员页面
     */
    @GetMapping("/user_edit/{userId}")
    public String userEdit(@PathVariable Integer userId, Model model) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        User user = this.userService.getOneById(userId);
        ShiroKit.assertAuth(userId, user);
        model.addAttribute(user);
        model.addAttribute("roleName", ConstantFactory.me().getMultiRoleName(user.getRoleid()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptid()));
        return PREFIX + "user_edit.html";
    }

    /**
     * 修改（超级管理员操作，或变更自身信息）
     *
     * @throws NoPermissionException
     */
    @PostMapping("/edit")
    @BizLog(value = "管理员", key = "id", type = BizNameType.UPDATE)
    @ResponseBody
    public Tip edit(@Valid User user, BindingResult result) {
        if (result.hasErrors() || user.getId() == null) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        int id = user.getId();
        User oldUser = userService.getOneById(id);
        redisCacheDao.put(Cache.CRUD, CacheKey.PO_BEFORE + id, oldUser);
        //具有超级管理员角色
        if (ShiroKit.hasRole(AdminConst.ADMIN_NAME)) {
            user = userService.edit(user, oldUser);
            redisCacheDao.put(Cache.CRUD, CacheKey.PO_AFTER + id, user);
        } else {
            //部门不可随意扩大
            ShiroKit.assertAuth(id, oldUser);
            //当前用户自身
            ShiroUser shiroUser = ShiroKit.getUser();
            if (shiroUser.getId().equals(id)) {
                user = userService.edit(user, oldUser);
                redisCacheDao.put(Cache.CRUD, CacheKey.PO_AFTER + id, user);
            } else {
                throw new GunsException(BizExceptionEnum.NO_PERMITION);
            }
        }
        return SUCCESS_TIP;
    }

    /**
     * 用户详情页面
     */
    @GetMapping("/user_info")
    public String userInfo(Model model) {
        Integer userId = ShiroKit.getUser().getId();
        if (userId == null) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        User user = this.userService.getOneById(userId);
        model.addAttribute(user);
        model.addAttribute("roleName", ConstantFactory.me().getMultiRoleName(user.getRoleid()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptid()));
        return PREFIX + "user_view.html";
    }

    /**
     * 跳转到修改密码界面
     */
    @GetMapping("/user_chpwd")
    public String chPwd() {
        return PREFIX + "user_chpwd.html";
    }

    /**
     * 当前用户修改密码
     */
    @PostMapping("/changePwd")
    @ResponseBody
    public Object changePwd(@RequestParam String oldPwd, @RequestParam String newPwd, @RequestParam String rePwd) {
        userService.changePwd(oldPwd, newPwd, rePwd);
        return SUCCESS_TIP;
    }

    /**
     * 返回头像
     *
     * @author stylefeng
     * @Date 2017/5/24 23:00
     */
    @GetMapping("/img/{pictureId}")
    public void renderPicture(HttpServletRequest request, HttpServletResponse response) {
        //规避了获取不到文件后缀的问题
        String url = WebUtils.getPathWithinApplication(request);
        int lastIndexOf = url.lastIndexOf("/");
        String filename = url.substring(lastIndexOf + 1);
        String path = gunsProperties.getFileUploadPath() + filename;
        try {
            byte[] bytes = FileUtil.toByteArray(path);
            response.getOutputStream().write(bytes);
        } catch (Exception e) {
            //如果找不到图片就返回一个默认图片
            try {
                response.sendRedirect("/static/img/girl.gif");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 上传头像
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestPart("file") MultipartFile multipartFile) {

        //step1:保存图片
        String pictureName = UUID.randomUUID().toString() + "." + ToolUtil.getFileSuffix(multipartFile.getOriginalFilename());
        //配置的保存地址
        String fileSavePath = gunsProperties.getFileUploadPath();
        try {
            multipartFile.transferTo(new File(fileSavePath + pictureName));
        } catch (Exception e) {
            throw new GunsException(BizExceptionEnum.UPLOAD_ERROR);
        }

        //step2:更新数据
        Integer userId = ShiroKit.getUser().getId();
        User currentUser = userService.getOneById(userId);
        String avatar = currentUser.getAvatar();
        currentUser.setAvatar(pictureName);
        userService.updateAllCol(currentUser);

        //step3:删除旧图
        if (StringUtils.isNotBlank(avatar)) {
            String oldfile = fileSavePath + avatar;
            new File(oldfile).delete();
        }
        return pictureName;
    }

}
