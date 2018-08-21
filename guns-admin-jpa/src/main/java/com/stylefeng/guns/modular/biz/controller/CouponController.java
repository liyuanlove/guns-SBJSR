package com.stylefeng.guns.modular.biz.controller;

import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.common.page.PageableFactory;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.po.Coupon;
import com.stylefeng.guns.service.ICouponService;
import com.stylefeng.guns.vo.CouponVo;
import com.stylefeng.guns.warpper.CouponWarpper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tc.jpa.controller.BaseController;
import org.tc.jpa.exception.GunsException;
import org.tc.jpa.tips.SuccessTip;

import javax.validation.Valid;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * 优惠券控制器
 *
 * @author fengshuonan
 * @Date 2018-07-23 18:09:23
 */
@Controller
@RequestMapping("/coupon")
public class CouponController extends BaseController {

    private String PREFIX = "/biz/coupon/";

    @Autowired
    private ICouponService couponService;

    /**
     * 跳转到优惠券首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "coupon.html";
    }

    /**
     * 获取优惠券列表
     */
    @ApiOperation("二维码页面")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "搜索条件", required = true, dataType = "String"),
    })
    @PostMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        //默认的排序依据
        Sort.Order order1 = new Sort.Order(Sort.Direction.ASC, "used");
        Sort.Order order2 = new Sort.Order(Sort.Direction.DESC, "endDay");
        Sort.Order order3 = new Sort.Order(Sort.Direction.DESC, "createtime");
        Sort sort = Sort.by(order1, order2, order3);
        Pageable pageable = new PageableFactory().defaultPage(sort);
        Page<Coupon> page = couponService.getPage(pageable, condition);
        List<Coupon> content = page.getContent();
        List<CouponVo> list = new CouponWarpper().warpList(content);
        return super.warpForBT(list, page.getTotalElements());
    }

    /**
     * 跳转到添加优惠券
     */
    @GetMapping("/coupon_add")
    public String couponAdd() {
        return PREFIX + "coupon_add.html";
    }


    /**
     * 新增优惠券
     */
    @ApiOperation("新增优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "coupon", value = "优惠券信息", required = true, dataType = "Coupon"),
            @ApiImplicitParam(name = "num", value = "数量", required = true, dataType = "int"),
    })
    @PostMapping(value = "/add")
    @ResponseBody
    public Object genCoupon(@Valid Coupon coupon, @RequestParam(required = false, defaultValue = "0") int num,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        if (coupon.getBeginDay().compareTo(coupon.getEndDay()) > 0) {
            throw new GunsException(BizExceptionEnum.COUPON_RANGE_ERROR);
        }
        if (num > 0) {
            couponService.genCoupon(coupon, num);
        } else {
            throw new GunsException(BizExceptionEnum.COUPON_NUM_ERROR);
        }
        return SUCCESS_TIP;
    }

    /**
     * 二维码页面
     *
     * @return
     */
    @ApiOperation("二维码页面")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "couponId", value = "优惠券唯一标识", required = true, dataType = "Integer"),
    })
    @GetMapping("/qrcode/{couponId}")
    public String couponUpdate(@PathVariable Integer couponId, Model model) {
        if (couponId == null) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        boolean exists = couponService.existsById(couponId);
        if (!exists) {
            throw new GunsException(BizExceptionEnum.NO_THIS_RECORD);
        }
        try {
            InetAddress addr = InetAddress.getLocalHost();
            String ip = addr.getHostAddress().toString();
            model.addAttribute("address", "http://" + ip + ":8080/guns/coupon/reception/" + couponId);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            throw new GunsException(BizExceptionEnum.COUPON_QRCODE_ERROR);
        }
        return PREFIX + "qrcode.html";
    }

    /**
     * 删除优惠券
     */
    @ApiOperation("删除优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "couponId", value = "优惠券唯一标识", required = true, dataType = "Integer"),
    })
    @PostMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer couponId) {
        if (couponId == null) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        couponService.deleteCoupon(couponId);
        return SUCCESS_TIP;
    }

    /**
     * 使用优惠券
     *
     * @return
     */
    @ApiOperation("使用优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userTel", value = "手机号码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "code", value = "使用验证码", required = true, dataType = "String"),
    })
    @PostMapping("use")
    @ResponseBody
    public Object use(String userTel, String code) {
        if (ToolUtil.isOneEmpty(code, userTel)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        couponService.use(code, userTel);
        return SUCCESS_TIP;
    }

    /**
     * 消费者领取页面
     *
     * @param model
     * @return
     */
    @GetMapping("/reception/{couponId}")
    public String reception(@PathVariable("couponId") Integer couponId, Model model) {
        model.addAttribute("couponId", couponId);
        return PREFIX + "reception.html";
    }

    /**
     * 领取优惠券
     *
     * @return
     */
    @ApiOperation("领取优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userTel", value = "用户手机号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "couponId", value = "优惠券唯一标识", required = true, dataType = "Integer"),
    })
    @PostMapping("reception")
    @ResponseBody
    public Object recept(String userTel, Integer couponId) {
        if (ToolUtil.isOneEmpty(userTel, couponId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        String code = couponService.recept(userTel, couponId);
        return new SuccessTip(code);
    }

    /**
     * 查询页面
     *
     * @param model
     * @return
     */
    @ApiOperation("查询我的优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userTel", value = "用户手机号", required = true, dataType = "String"),
    })
    @GetMapping("mylist")
    public String mylist(String userTel, Model model) {
        if (StringUtils.isBlank(userTel)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        List<Coupon> list = couponService.getListByTel(userTel);
        model.addAttribute("list", new CouponWarpper().warpList(list));
        model.addAttribute("userTel", userTel);
        return PREFIX + "mylist.html";
    }

}
