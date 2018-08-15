package com.stylefeng.guns.service.impl;

import com.stylefeng.guns.core.base.service.impl.BaseServiceImpl;
import com.stylefeng.guns.core.common.constant.AdminConst;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.core.support.StrKit;
import com.stylefeng.guns.core.util.Convert;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.core.ztree.ZTreeNode;
import com.stylefeng.guns.dao.RelationDao;
import com.stylefeng.guns.dao.RoleDao;
import com.stylefeng.guns.dao.UserDao;
import com.stylefeng.guns.po.Relation;
import com.stylefeng.guns.po.Role;
import com.stylefeng.guns.po.User;
import com.stylefeng.guns.service.IRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 角色Service实现
 *
 * @author TC
 * @Date 2018-07-29 16:37:33
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Integer, RoleDao> implements IRoleService {

    @Resource
    private RelationDao relationDao;
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delRoleById(Integer roleId) {
        //不能删除超级管理员角色
        if (roleId.equals(AdminConst.ADMIN_ROLE_ID)) {
            throw new GunsException(BizExceptionEnum.CANT_DELETE_ADMIN);
        }
        //step1:处理用户
        List<User> list = userDao.findByRoleidLike("%" + roleId + "%");
        for (User user : list) {
            String roleid = user.getRoleid();
            if (roleid.contains(roleId + ",")) {
                user.setRoleid(roleid.replace(roleId + ",", ""));
            } else {
                user.setRoleid(roleid.replace(roleId.toString(), ""));
            }
        }
        userDao.saveAll(list);
        //step2:删除该角色所有的权限
        this.relationDao.delByRoleidEq(roleId);
        //step3:删除角色
        this.baseDao.deleteById(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setAuthority(Integer roleId, String ids) {
        // 删除该角色所有的权限
        this.relationDao.delByRoleidEq(roleId);
        // 添加新的权限
        for (Long id : Convert.toLongArray(true, Convert.toStrArray(",", ids))) {
            Relation relation = new Relation();
            relation.setRoleid(roleId);
            relation.setMenuid(id);
            this.relationDao.save(relation);
        }
    }

    @Override
    public List<Role> list(String name) {
        Specification<Role> specification = new Specification<Role>() {
            @Override
            public Predicate toPredicate(Root<Role> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (StringUtils.isNotBlank(name)) {
                    Predicate p1 = cb.like(root.get("name"), "%" + name + "%");
                    list.add(p1);
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
        List<Role> list = baseDao.findAll(specification);
        return list;
    }

    @Override
    public List<ZTreeNode> getRoleTree() {
        List<Map<String, Object>> mapList = this.baseDao.roleTree();
        List<ZTreeNode> list = BeanKit.mapListTobeanList(mapList, ZTreeNode.class);
        return list;
    }

    @Override
    public List<ZTreeNode> getCheckedRoleTree(Integer userId) {
        User user = this.userDao.getOne(userId);
        String roleid = user.getRoleid();
        List<Map<String, Object>> mapList;
        if (ToolUtil.isEmpty(roleid)) {
            mapList = this.baseDao.roleTree();
        } else {
            List<String> split = StrKit.split(roleid, ',');
            mapList = this.baseDao.checkedRoleTree(split);
        }
        List<ZTreeNode> list = BeanKit.mapListTobeanList(mapList, ZTreeNode.class);
        return list;
    }
}
