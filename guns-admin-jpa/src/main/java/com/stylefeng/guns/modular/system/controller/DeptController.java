package com.stylefeng.guns.modular.system.controller;


import com.stylefeng.guns.core.common.annotion.BizLog;
import com.stylefeng.guns.core.common.annotion.BizNameType;
import com.stylefeng.guns.core.common.constant.cache.Cache;
import com.stylefeng.guns.core.common.constant.cache.CacheKey;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.redis.cache.RedisCacheDao;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.core.ztree.ZTreeNode;
import com.stylefeng.guns.po.Dept;
import com.stylefeng.guns.service.IDeptService;
import com.stylefeng.guns.warpper.DeptWarpper;
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
import java.util.List;

/**
 * 部门控制器
 *
 * @author fengshuonan
 * @Date 2017年2月17日20:27:22
 */
@Controller
@RequestMapping("/dept")
public class DeptController extends BaseController {

    private String PREFIX = "/system/dept/";

    @Autowired
    private IDeptService deptService;
    @Autowired
    private RedisCacheDao redisCacheDao;

    /**
     * 跳转到部门管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "dept.html";
    }

    /**
     * 获取所有部门列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<Dept> list = this.deptService.list(condition);
        return new DeptWarpper().warpList(list);
    }

    /**
     * 部门详情
     */
    @RequestMapping(value = "/detail/{deptId}")
    @ResponseBody
    public Object detail(@PathVariable("deptId") Integer deptId) {
        return deptService.getOneById(deptId);
    }

    /**
     * 获取部门的tree列表
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = this.deptService.tree();
        tree.add(ZTreeNode.createRoot());
        return tree;
    }

    /**
     * 跳转到添加部门
     */
    @RequestMapping("/dept_add")
    public String deptAdd() {
        return PREFIX + "dept_add.html";
    }

    /**
     * 新增部门
     */
    @BizLog(value = "部门", type = BizNameType.ADD)
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Valid Dept dept, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //完善pids,根据pid拿到pid的pids
        setPids(dept);
        dept = this.deptService.insertAllCol(dept);
        return SUCCESS_TIP;
    }

    /**
     * 跳转到修改部门
     */
    @RequestMapping("/dept_update/{deptId}")
    public String deptUpdate(@PathVariable Integer deptId, Model model) {
        Dept dept = deptService.getOneById(deptId);
        model.addAttribute(dept);
        model.addAttribute("pName", ConstantFactory.me().getDeptName(dept.getPid()));
        return PREFIX + "dept_edit.html";
    }

    /**
     * 修改部门
     */
    @BizLog(value = "部门", key = "id", type = BizNameType.UPDATE)
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Dept dept) {
        if (ToolUtil.isEmpty(dept) || dept.getId() == null) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        setPids(dept);
        Integer id = dept.getId();
        Dept oldDept = deptService.getOneById(id);
        redisCacheDao.put(Cache.CRUD, CacheKey.PO_BEFORE + id, oldDept);
        dept = deptService.updateNotNullField(oldDept, dept);
        redisCacheDao.put(Cache.CRUD, CacheKey.PO_AFTER + id, dept);
        return SUCCESS_TIP;
    }

    /**
     * 删除部门
     */
    @BizLog(value = "部门", key = "deptId", type = BizNameType.DELETE)
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer deptId) {
        //缓存被删除的部门名称
        Dept oldDept = deptService.getOneById(deptId);
        redisCacheDao.put(Cache.CRUD, CacheKey.PO_BEFORE + deptId, oldDept);
        deptService.deleteCascade(deptId);
        return SUCCESS_TIP;
    }

    /**
     * 配置PIDS
     *
     * @param dept
     */
    private void setPids(Dept dept) {
        Integer pid = dept.getPid();
        if (ToolUtil.isEmpty(pid) || pid.equals(0)) {
            dept.setPid(0);
            dept.setPids("[0],");
        } else {
            Dept temp = deptService.getOneById(pid);
            String pids = temp.getPids();
            dept.setPids(pids + "[" + pid + "],");
        }
    }
}
