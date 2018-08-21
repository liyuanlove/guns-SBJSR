package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.common.constant.AdminConst;
import com.stylefeng.guns.core.common.constant.cache.Cache;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.core.ztree.ZTreeNode;
import com.stylefeng.guns.po.Role;
import com.stylefeng.guns.service.IRoleService;
import com.stylefeng.guns.warpper.RoleWarpper;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tc.jpa.controller.BaseController;
import org.tc.jpa.exception.GunsException;
import org.tc.jpa.tips.Tip;
import org.tc.redis.cache.RedisCacheDao;

import javax.validation.Valid;
import java.util.List;

/**
 * 角色控制器
 *
 * @author fengshuonan
 * @Date 2017年2月12日21:59:14
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    private static String PREFIX = "/system/role";

    @Autowired
    private IRoleService roleService;
    @Autowired
    private RedisCacheDao redisCacheDao;

    /**
     * 角色列表页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/role.html";
    }

    /**
     * 获取角色列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String roleName) {
        List<Role> list = roleService.list(roleName);
        return new RoleWarpper().warpList(list);
    }

    /**
     * 角色新增页面
     */
    @RequestMapping(value = "/role_add")
    public String roleAdd() {
        return PREFIX + "/role_add.html";
    }

    /**
     * 角色新增
     */
    @RequestMapping(value = "/add")
//    @BizLog(value = "添加角色", key = "name", type = RoleDict.class)
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @ResponseBody
    public Tip add(@Valid Role role, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        this.roleService.insertAllCol(role);
        return SUCCESS_TIP;
    }

    /**
     * 角色修改页
     */
    @RequestMapping(value = "/role_edit/{roleId}")
    public String roleEdit(@PathVariable Integer roleId, Model model) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        Role role = this.roleService.getOneById(roleId);
        model.addAttribute(role);
        model.addAttribute("pName", ConstantFactory.me().getSingleRoleName(role.getPid()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(role.getDeptid()));
        return PREFIX + "/role_edit.html";
    }

    /**
     * 角色修改
     */
    @RequestMapping(value = "/edit")
//    @BizLog(value = "修改角色", key = "name", type = RoleDict.class)
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @ResponseBody
    public Tip edit(@Valid Role role, BindingResult result) {
        Integer id = role.getId();
        if (result.hasErrors() || id == null) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        Role oldRole = roleService.getOneById(id);
        roleService.updateNotNullField(oldRole, role);
        //删除缓存
        redisCacheDao.removeAll(Cache.CONSTANT);
        return SUCCESS_TIP;
    }

    /**
     * 角色分配权限页
     */
    @RequestMapping(value = "/role_assign/{roleId}")
    public String roleAssign(@PathVariable("roleId") Integer roleId, Model model) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        model.addAttribute("roleId", roleId);
        model.addAttribute("roleName", ConstantFactory.me().getSingleRoleName(roleId));
        return PREFIX + "/role_assign.html";
    }

    /**
     * 配置权限
     */
    @RequestMapping("/setAuthority")
//    @BizLog(value = "配置权限", key = "roleId,ids", type = RoleDict.class)
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @ResponseBody
    public Tip setAuthority(@RequestParam("roleId") Integer roleId, @RequestParam("ids") String ids) {
        if (ToolUtil.isOneEmpty(roleId, ids)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        this.roleService.setAuthority(roleId, ids);
        return SUCCESS_TIP;
    }

    /**
     * 删除角色
     */
    @RequestMapping(value = "/remove")
//    @BizLog(value = "删除角色", key = "roleId", type = RoleDict.class)
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @ResponseBody
    public Tip remove(@RequestParam Integer roleId) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //缓存被删除的角色名称
        this.roleService.delRoleById(roleId);
        //删除缓存
        redisCacheDao.removeAll(Cache.CONSTANT);
        return SUCCESS_TIP;
    }

    /**
     * 查看角色
     */
    @RequestMapping(value = "/view/{roleId}")
    @ResponseBody
    public Tip view(@PathVariable Integer roleId) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        this.roleService.getOneById(roleId);
        return SUCCESS_TIP;
    }

    /**
     * 角色node
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> roleTreeList() {
        List<ZTreeNode> roleTreeList = this.roleService.getRoleTree();
        roleTreeList.add(ZTreeNode.createRoot());
        return roleTreeList;
    }

    /**
     * 角色node(带勾选状态)
     */
    @RequestMapping(value = "/treeByUserId/{userId}")
    @ResponseBody
    public List<ZTreeNode> roleTreeByUserId(@PathVariable Integer userId) {
        List<ZTreeNode> roleTreeList = roleService.getCheckedRoleTree(userId);
        return roleTreeList;
    }

}
