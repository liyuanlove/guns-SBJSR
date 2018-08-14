package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.annotion.BizLog;
import com.stylefeng.guns.core.common.constant.AdminConst;
import com.stylefeng.guns.core.common.page.PageableFactory;
import com.stylefeng.guns.po.LoginLog;
import com.stylefeng.guns.service.ILoginLogService;
import com.stylefeng.guns.vo.LoginLogVo;
import com.stylefeng.guns.warpper.LoginLogWarpper;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 登录日志的控制器
 *
 * @author fengshuonan
 * @Date 2017年4月5日 19:45:36
 */
@Controller
@RequestMapping("/loginLog")
public class LoginLogController extends BaseController {

    private static String PREFIX = "/system/log/";

    @Autowired
    private ILoginLogService loginLogService;

    /**
     * 跳转到日志管理的首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "login_log.html";
    }

    /**
     * 查询登录日志列表
     */
    @RequestMapping("/list")
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @ResponseBody
    public Object list(@RequestParam(required = false) Date beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) String logName) {
        //默认的排序依据
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "createtime");
        Sort sort = Sort.by(order);
        Pageable pageable = new PageableFactory().defaultPage(sort);
        Page<LoginLog> page = loginLogService.getPage(pageable, beginTime, endTime, logName);
        List<LoginLog> content = page.getContent();
        List<LoginLogVo> list = new LoginLogWarpper().warpList(content);
        return super.warpForBT(list, page.getTotalElements());
    }

    /**
     * 清空日志
     */
    @BizLog("清空登录日志")
    @RequestMapping("/delLoginLog")
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @ResponseBody
    public Object delLog() {
        loginLogService.deleteAllInBatch();
        return SUCCESS_TIP;
    }
}
