package com.stylefeng.guns.service.impl;

import com.stylefeng.guns.core.common.constant.enums.DoubleStatus;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.core.ztree.MenuNode;
import com.stylefeng.guns.core.ztree.ZTreeNode;
import com.stylefeng.guns.dao.MenuDao;
import com.stylefeng.guns.dao.RelationDao;
import com.stylefeng.guns.po.Menu;
import com.stylefeng.guns.service.IMenuService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tc.jpa.exception.GunsException;
import org.tc.jpa.service.impl.BaseServiceImpl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 菜单Service实现
 *
 * @author TC
 * @Date 2018-07-29 16:39:05
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu, Long, MenuDao> implements IMenuService {

    @Autowired
    private RelationDao relationDao;

    /**
     * 删除菜单及角色配置
     *
     * @param menuId
     */
    private void delMenuById(Long menuId) {
        //删除关联的relation
        this.relationDao.delMenuidEq(menuId);
        //删除菜单
        this.baseDao.deleteById(menuId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delMenuCascade(Long menuId) {
        Menu menu = baseDao.getOne(menuId);
        //删除所有子菜单
        List<Menu> menus = baseDao.findByPcodesLike("%[" + menu.getCode() + "]%");
        if (CollectionUtils.isNotEmpty(menus)) {
            for (Menu temp : menus) {
                delMenuById(temp.getId());
            }
        }
        //删除当前菜单
        delMenuById(menuId);
    }


    @Override
    public void add(Menu menu) {
        //判断是否存在该编号
        boolean exists = baseDao.existsByCodeIgnoreCase(menu.getCode());
        if (exists) {
            throw new GunsException(BizExceptionEnum.EXISTED_THE_MENU);
        }
        //设置父级菜单编号
        setPcode(menu);
        menu.setStatus(DoubleStatus.ENABLE.getCode());
        this.baseDao.save(menu);
    }

    @Override
    public void setPcode(Menu menu) {
        if (ToolUtil.isEmpty(menu.getPcode()) || menu.getPcode().equals("0")) {
            menu.setPcode("0");
            menu.setPcodes("[0],");
            menu.setLevels(1);
        } else {
            String pid = menu.getPcode();
            Menu pmenu = baseDao.getOne(Long.valueOf(pid));
            Integer pLevels = pmenu.getLevels();

            //如果编号和父编号一致会导致无限递归
            if (menu.getCode().equals(menu.getPcode())) {
                throw new GunsException(BizExceptionEnum.MENU_PCODE_COINCIDENCE);
            }
            menu.setLevels(pLevels + 1);
            String code = pmenu.getCode();
            menu.setPcode(code);
            menu.setPcodes(pmenu.getPcodes() + "[" + code + "],");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void switchStatusCascade(Long menuId) {
        Menu menu = baseDao.getOne(menuId);
        if (menu == null) {
            throw new GunsException(BizExceptionEnum.NO_THIS_MENU);
        }
        int code = 0;
        if (menu.getStatus().equals(DoubleStatus.ENABLE.getCode())) {
            code = DoubleStatus.DISABLE.getCode();
        } else {
            code = DoubleStatus.ENABLE.getCode();
        }

        //所有子菜单
        List<Menu> menus = baseDao.findByPcodesLike("%[" + menu.getCode() + "]%");
        if (CollectionUtils.isNotEmpty(menus)) {
            for (Menu temp : menus) {
                switchStatus(temp, code);
            }
        }
        switchStatus(menu, code);
    }

    private void switchStatus(Menu menu, Integer code) {
        menu.setStatus(code);
        baseDao.save(menu);
    }

    @Override
    public List<Menu> list(String name, String level) {
        Specification<Menu> specification = new Specification<Menu>() {
            @Override
            public Predicate toPredicate(Root<Menu> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (StringUtils.isNotBlank(name)) {
                    Predicate p1 = cb.like(root.get("name"), "%" + name + "%");
                    list.add(p1);
                }
                if (StringUtils.isNotBlank(level)) {
                    Predicate p1 = cb.equal(root.get("levels").as(Integer.class), level);
                    list.add(p1);
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
        Sort.Order order1 = new Sort.Order(Sort.Direction.ASC, "levels");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC, "num");
        Sort sort = Sort.by(order1, order2);
        List<Menu> list = baseDao.findAll(specification, sort);
        return list;
    }

    @Override
    public List<String> getUrlsByRoleId(Integer roleId) {
        return this.baseDao.getUrlsByRoleId(roleId);
    }

    @Override
    public List<ZTreeNode> menuTree() {
        List<Map<String, Object>> mapList = this.baseDao.tree();
        //map to bean
        List<ZTreeNode> list = BeanKit.mapListTobeanList(mapList, ZTreeNode.class);
        return list;
    }

    @Override
    public List<ZTreeNode> getCheckedMenuTree(Integer roleId) {
        List<Long> menuIds = this.relationDao.menuidListByRoleId(roleId);
        List<ZTreeNode> list = null;
        if (ToolUtil.isEmpty(menuIds)) {
            list = this.menuTree();
        } else {
            List<Map<String, Object>> mapList = this.baseDao.treeByMenuIds(menuIds);
            list = BeanKit.mapListTobeanList(mapList, ZTreeNode.class);
        }
        return list;
    }

    @Override
    public List<MenuNode> getMenusByRoleIds(List<Integer> roleIds) {
        List<Map<String, Object>> mapList = this.baseDao.getMenusByRoleIds(roleIds);
        List<MenuNode> list = BeanKit.mapListTobeanList(mapList, MenuNode.class);
        return list;
    }

    @Override
    public Menu getByCode(String code) {
        return baseDao.findByCodeEquals(code);
    }
}
