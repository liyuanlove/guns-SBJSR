package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.po.Notice;
import com.stylefeng.guns.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tan.jpa.controller.BaseController;

import java.util.List;

/**
 * 总览通知信息
 *
 * @author fengshuonan
 * @Date 2017年3月4日23:05:54
 */
@Controller
@RequestMapping("/blackboard")
public class BlackboardController extends BaseController {

    @Autowired
    private INoticeService noticeService;

    /**
     * 跳转到黑板
     */
    @RequestMapping("")
    public String blackboard(Model model) {
        List<Notice> notices = noticeService.list(null);
        model.addAttribute("noticeList", notices);
        return "/blackboard.html";
    }
}
