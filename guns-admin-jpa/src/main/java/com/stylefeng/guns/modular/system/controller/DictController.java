package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.common.annotion.BizLog;
import com.stylefeng.guns.core.common.annotion.BizNameType;
import com.stylefeng.guns.core.common.constant.AdminConst;
import com.stylefeng.guns.core.common.constant.cache.Cache;
import com.stylefeng.guns.core.common.constant.cache.CacheKey;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.po.Dict;
import com.stylefeng.guns.service.IDictService;
import com.stylefeng.guns.warpper.DictWarpper;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tc.jpa.controller.BaseController;
import org.tc.jpa.exception.GunsException;
import org.tc.redis.cache.RedisCacheDao;

import java.util.List;

/**
 * 字典控制器
 *
 * @author fengshuonan
 * @Date 2017年4月26日 12:55:31
 */
@Controller
@RequestMapping("/dict")
public class DictController extends BaseController {

    private String PREFIX = "/system/dict/";

    @Autowired
    private IDictService dictService;
    @Autowired
    private RedisCacheDao redisCacheDao;

    /**
     * 跳转到字典管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "dict.html";
    }

    /**
     * 获取所有字典列表
     */
    @RequestMapping(value = "/list")
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @ResponseBody
    public Object list(String condition) {
        List<Dict> list = this.dictService.list(condition);
        return new DictWarpper().warpList(list);
    }

    /**
     * 字典详情
     */
    @RequestMapping(value = "/detail/{dictId}")
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @ResponseBody
    public Object detail(@PathVariable("dictId") Integer dictId) {
        return dictService.getOneById(dictId);
    }

    /**
     * 跳转到添加字典
     */
    @RequestMapping("/dict_add")
    public String deptAdd() {
        return PREFIX + "dict_add.html";
    }


    /**
     * 新增字典
     *
     * @param dictValues 格式例如   "1:启用;2:禁用;3:冻结"
     */
    @BizLog(value = "字典", type = BizNameType.ADD)
    @RequestMapping(value = "/add")
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @ResponseBody
    public Object add(String dictCode, String dictTips, String dictName, String dictValues) {
        if (ToolUtil.isOneEmpty(dictCode, dictName, dictValues)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        this.dictService.addDict(dictCode, dictName, dictTips, dictValues);
        return SUCCESS_TIP;
    }

    /**
     * 跳转到修改字典
     */
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @RequestMapping("/dict_edit/{dictId}")
    public String deptUpdate(@PathVariable Integer dictId, Model model) {
        if (dictId == null) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        Dict dict = dictService.getOneById(dictId);
        List<Dict> subDicts = dictService.findSubDict(dictId);

        model.addAttribute("dict", dict);
        model.addAttribute("subDicts", subDicts);
        return PREFIX + "dict_edit.html";
    }

    /**
     * 修改字典
     */
    @BizLog(value = "字典", key = "dictId", type = BizNameType.UPDATE)
    @RequestMapping(value = "/update")
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @ResponseBody
    public Object update(Integer dictId, String dictCode, String dictName, String dictTips, String dictValues) {
        if (ToolUtil.isOneEmpty(dictId, dictCode, dictName, dictValues)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        Dict oldDept = dictService.getOneById(dictId);
        redisCacheDao.put(Cache.CRUD, CacheKey.PO_BEFORE + dictId, oldDept);
        Dict dict = dictService.editDict(dictId, dictCode, dictName, dictTips, dictValues);
        redisCacheDao.put(Cache.CRUD, CacheKey.PO_AFTER + dictId, dict);
        return SUCCESS_TIP;
    }

    /**
     * 删除字典记录
     */
    @RequestMapping(value = "/delete")
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @ResponseBody
    public Object delete(@RequestParam Integer dictId) {

        //缓存被删除的名称
        this.dictService.deleteDictCascade(dictId);
        return SUCCESS_TIP;
    }

}
