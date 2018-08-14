package com.stylefeng.guns.modular.biz.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.AdminConst;
import com.stylefeng.guns.core.common.constant.enums.DoubleStatus;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.common.page.PageableFactory;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.shiroext.kit.ShiroKit;
import com.stylefeng.guns.core.ztree.ZTreeNode;
import com.stylefeng.guns.po.Seller;
import com.stylefeng.guns.service.ISellerService;
import com.stylefeng.guns.vo.SellerVo;
import com.stylefeng.guns.warpper.SellerWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

/**
 * 卖家信息控制器
 *
 * @author fengshuonan
 * @Date 2018-07-23 11:21:28
 */
@Controller
@RequestMapping("/seller")
public class SellerController extends BaseController {

    private String PREFIX = "/biz/seller/";

    @Autowired
    private ISellerService sellerService;

    /**
     * 跳转到卖家信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "seller.html";
    }

    /**
     * 获取卖家信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String condition) {
        //默认的排序依据
        Sort.Order order1 = new Sort.Order(Sort.Direction.ASC, "createtime");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC, "id");
        Sort sort = Sort.by(order1, order2);
        Pageable pageable = new PageableFactory().defaultPage(sort);
        Page<Seller> page = sellerService.list(pageable, condition);
        List<Seller> content = page.getContent();
        List<SellerVo> list = new SellerWarpper().warpList(content);
        return super.warpForBT(list, page.getTotalElements());

    }

    /**
     * 跳转到添加卖家信息
     */
    @RequestMapping("/seller_add")
    public String sellerAdd() {
        return PREFIX + "seller_add.html";
    }

    /**
     * 新增卖家信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Valid Seller seller, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        boolean exists = sellerService.existsByNameEquals(seller.getName());
        if (exists) {
            throw new GunsException(BizExceptionEnum.SELLER_ALREADY_EXIST);
        }
        seller.setCreatetime(new Timestamp(System.currentTimeMillis()));
        sellerService.insertAllCol(seller);
        return SUCCESS_TIP;
    }

    /**
     * 跳转到修改卖家信息
     */
    @RequestMapping("/seller_edit/{sellerId}")
    public String sellerUpdate(@PathVariable Integer sellerId, Model model) {
        Seller seller = sellerService.getOneById(sellerId);
        model.addAttribute("item", seller);
        return PREFIX + "seller_edit.html";
    }

    /**
     * 修改卖家信息
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Object update(@Valid Seller seller, BindingResult bindingResult) {
        Integer id = seller.getId();
        if (bindingResult.hasErrors() || id == null) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        Seller oldBean = sellerService.getOneById(id);
        sellerService.updateNotNullField(oldBean, seller);
        return SUCCESS_TIP;
    }

    /**
     * 删除卖家信息(假删)
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer sellerId) {
        if (sellerId == null) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        sellerService.setStatus(sellerId, DoubleStatus.DISABLE.getCode());
        return SUCCESS_TIP;
    }

    /**
     * 卖家树(配置卖家范围使用)
     */
    @RequestMapping(value = "/treeByUserId/{userId}")
    @ResponseBody
    public List<ZTreeNode> roleTreeListByUserId(@PathVariable Integer userId) {
        List<ZTreeNode> checkedSellerTree = sellerService.getCheckedSellerTree(userId);
        checkedSellerTree.add(ZTreeNode.createRoot());
        return checkedSellerTree;
    }

    /**
     * 卖家列表(查看使用)
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> roleTreeListByUserId() {
        //超级管理员不受约束
        if (ShiroKit.isAdmin()) {
            return sellerService.getSellerTree();
        }
        if (ShiroKit.hasRole(AdminConst.SELLER_NAME)) {
            Integer id = ShiroKit.getUser().getId();
            return sellerService.getSellerTreeByScope(id);
        }
        throw new GunsException(BizExceptionEnum.NO_PERMITION);
    }

}
