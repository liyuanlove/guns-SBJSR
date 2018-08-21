package com.stylefeng.guns.service.impl;

import com.stylefeng.guns.core.common.constant.enums.DoubleStatus;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.core.util.Convert;
import com.stylefeng.guns.core.ztree.ZTreeNode;
import com.stylefeng.guns.dao.SellerDao;
import com.stylefeng.guns.dao.UserSellerDao;
import com.stylefeng.guns.po.Seller;
import com.stylefeng.guns.po.UserSeller;
import com.stylefeng.guns.service.ISellerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
 * 商家Service实现
 *
 * @author TC
 * @Date 2018-07-29 16:43:59
 */
@Service
public class SellerServiceImpl extends BaseServiceImpl<Seller, Integer, SellerDao> implements ISellerService {

    @Autowired
    private UserSellerDao userSellerDao;

    @Override
    public boolean existsByNameEquals(String name) {
        return baseDao.existsByNameEquals(name);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setSellerScope(Integer userId, String sellerIds) {
        this.userSellerDao.delByUseridEq(userId);
        for (Integer id : Convert.toIntArray(true, Convert.toStrArray(",", sellerIds))) {
            UserSeller relation = new UserSeller();
            relation.setUserid(userId);
            relation.setSellerid(id);
            this.userSellerDao.save(relation);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int setStatus(Integer id, int status) {
        return baseDao.updateStatus(id, status);
    }

    @Override
    public Page<Seller> list(Pageable pageable, String condition) {
        Specification<Seller> specification = new Specification<Seller>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query,
                                         CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(cb.equal(root.get("status").as(Integer.class), DoubleStatus.ENABLE.getCode()));

                if (StringUtils.isNotBlank(condition)) {
                    Predicate name = cb.like(root.get("name").as(String.class), "%" + condition + "%");
                    Predicate addr = cb.like(root.get("addr").as(String.class), "%" + condition + "%");
                    list.add(cb.or(name, addr));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
        Page<Seller> sellerPage = baseDao.findAll(specification, pageable);
        return sellerPage;
    }

    @Override
    public List<ZTreeNode> getCheckedSellerTree(Integer userId) {
        List<Map<String, Object>> mapList = baseDao.getCheckedSellerTree(userId);
        List<ZTreeNode> list = BeanKit.mapListTobeanList(mapList, ZTreeNode.class);
        return list;
    }

    @Override
    public List<ZTreeNode> getSellerTree() {
        List<Map<String, Object>> mapList = baseDao.getSellerTree();
        List<ZTreeNode> list = BeanKit.mapListTobeanList(mapList, ZTreeNode.class);
        return list;
    }

    @Override
    public List<ZTreeNode> getSellerTreeByScope(Integer userId) {
        boolean exists = userSellerDao.existsByUseridEquals(userId);
        if (!exists) {
            throw new GunsException(BizExceptionEnum.NO_SELLER_DATASCOPE);
        }
        List<Map<String, Object>> mapList = baseDao.getSellerTreeByScope(userId);
        List<ZTreeNode> list = BeanKit.mapListTobeanList(mapList, ZTreeNode.class);
        return list;
    }
}
