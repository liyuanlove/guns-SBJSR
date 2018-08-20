package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.shiroext.kit.ShiroKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.po.Notice;
import com.stylefeng.guns.service.INoticeService;
import com.stylefeng.guns.warpper.NoticeWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tan.jpa.controller.BaseController;
import org.tan.jpa.exception.GunsException;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

/**
 * 通知控制器
 *
 * @author fengshuonan
 * @Date 2017-05-09 23:02:21
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {

    private String PREFIX = "/system/notice/";

    @Autowired
    private INoticeService noticeService;

    /**
     * 跳转到通知列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "notice.html";
    }

    /**
     * 跳转到首页通知
     */
    @RequestMapping("/hello")
    public String hello() {
        List<Notice> notices = noticeService.list(null);
        super.setAttr("noticeList", notices);
        return "/blackboard.html";
    }

    /**
     * 获取通知列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<Notice> list = this.noticeService.list(condition);
        return new NoticeWrapper().warpList(list);
    }

    /**
     * 跳转到添加通知
     */
    @RequestMapping("/notice_add")
    public String noticeAdd() {
        return PREFIX + "notice_add.html";
    }

    /**
     * 新增通知
     */
    @RequestMapping(value = "/add")
    @ResponseBody
//    @BizLog(value = "新增通知", key = "title", type = NoticeMap.class)
    public Object add(@Valid Notice notice, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        notice.setCreater(ShiroKit.getUser().getId());
        notice.setCreatetime(new Timestamp(System.currentTimeMillis()));
        noticeService.insertAllCol(notice);
        return SUCCESS_TIP;
    }

    /**
     * 跳转到修改通知
     */
    @RequestMapping("/notice_update/{noticeId}")
    public String noticeUpdate(@PathVariable Integer noticeId, Model model) {
        Notice notice = this.noticeService.getOneById(noticeId);
        model.addAttribute("notice", notice);
        return PREFIX + "notice_edit.html";
    }

    /**
     * 修改通知
     */
    @RequestMapping(value = "/update")
    @ResponseBody
//    @BizLog(value = "修改通知", key = "title", type = NoticeMap.class)
    public Object update(@Valid Notice notice, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        Integer id = notice.getId();
        if (ToolUtil.isEmpty(id)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        Notice old = this.noticeService.getOneById(id);
        old.setTitle(notice.getTitle());
        old.setContent(notice.getContent());
        noticeService.updateAllCol(old);
        return SUCCESS_TIP;
    }

    /**
     * 删除通知
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
//    @BizLog(value = "删除通知", key = "noticeId", type = NoticeMap.class)
    public Object delete(@RequestParam Integer noticeId) {
        //缓存通知名称（日志使用）
        this.noticeService.deleteById(noticeId);
        return SUCCESS_TIP;
    }
}
