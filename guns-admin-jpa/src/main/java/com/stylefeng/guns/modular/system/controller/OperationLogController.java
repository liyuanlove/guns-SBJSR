package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.common.annotion.BizLog;
import com.stylefeng.guns.core.common.constant.AdminConst;
import com.stylefeng.guns.core.common.constant.enums.BizLogType;
import com.stylefeng.guns.core.common.page.PageableFactory;
import com.stylefeng.guns.po.OperationLog;
import com.stylefeng.guns.service.IOperationLogService;
import com.stylefeng.guns.vo.OperationLogVo;
import com.stylefeng.guns.warpper.OptLogWarpper;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tan.jpa.controller.BaseController;

import java.util.Date;
import java.util.List;

/**
 * 操作日志管的控制器
 *
 * @author fengshuonan
 * @Date 2017年4月5日 19:45:36
 */
@Controller
@RequestMapping("/log")
public class OperationLogController extends BaseController {

    private static String PREFIX = "/system/log/";

    @Autowired
    private IOperationLogService operationLogService;

    /**
     * 跳转到日志管理的首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "log.html";
    }

    /**
     * 查询操作日志列表
     */
    @RequestMapping("/list")
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @ResponseBody
    public Object list(@RequestParam(required = false) Date beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) String logName, @RequestParam(required = false) Integer logType) {
        //默认的排序依据
        Sort.Order order3 = new Sort.Order(Sort.Direction.DESC, "createtime");
        Sort sort = Sort.by(order3);
        Pageable pageable = new PageableFactory().defaultPage(sort);
        Page<OperationLog> page = operationLogService.getPage(pageable, beginTime, endTime, logName, BizLogType.valueOf(logType));
        List<OperationLog> content = page.getContent();
        List<OperationLogVo> list = new OptLogWarpper().warpList(content);
        return super.warpForBT(list, page.getTotalElements());
    }

    /**
     * 查询操作日志详情
     */
    @RequestMapping("/detail/{id}")
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @ResponseBody
    public Object detail(@PathVariable Integer id) {
        OperationLog operationLog = operationLogService.getOneById(id);
        return new OptLogWarpper().warpBean(operationLog);
    }

    /**
     * 清空日志
     */
    @BizLog(value = "清空业务日志")
    @RequestMapping("/delLog")
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @ResponseBody
    public Object delLog() {
        operationLogService.deleteAllInBatch();
        return SUCCESS_TIP;
    }
}
