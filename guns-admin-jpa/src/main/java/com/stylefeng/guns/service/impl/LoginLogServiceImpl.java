package com.stylefeng.guns.service.impl;

import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.dao.LoginLogDao;
import com.stylefeng.guns.po.LoginLog;
import com.stylefeng.guns.service.ILoginLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.tan.jpa.service.impl.BaseServiceImpl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 登录日志Service实现
 *
 * @author TC
 * @Date 2018-07-29 16:41:25
 */
@Service
public class LoginLogServiceImpl extends BaseServiceImpl<LoginLog, Integer, LoginLogDao> implements ILoginLogService {

    @Override
    public Page<LoginLog> getPage(Pageable pageable, Date beginTime, String endTime, String logname) {
        Specification<LoginLog> specification = new Specification<LoginLog>() {
            @Override
            public Predicate toPredicate(Root<LoginLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (StringUtils.isNotBlank(logname)) {
                    list.add(cb.like(root.get("logname"), "%" + logname + "%"));
                }
                if (beginTime != null) {
                    list.add(cb.greaterThanOrEqualTo(root.get("createtime"), beginTime));
                }
                if (StringUtils.isNotBlank(endTime)) {
                    list.add(cb.lessThanOrEqualTo(root.get("createtime"), DateUtil.parseTime(endTime + " 23:59:59")));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
        return baseDao.findAll(specification, pageable);
    }
}
