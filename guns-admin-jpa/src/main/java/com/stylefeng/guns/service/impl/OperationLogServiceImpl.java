package com.stylefeng.guns.service.impl;

import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.dao.OperationLogDao;
import com.stylefeng.guns.po.OperationLog;
import com.stylefeng.guns.service.IOperationLogService;
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
 * 操作日志Service实现
 *
 * @author TC
 * @Date 2018-07-29 16:42:18
 */
@Service
public class OperationLogServiceImpl extends BaseServiceImpl<OperationLog, Integer, OperationLogDao> implements IOperationLogService {

    @Override
    public Page<OperationLog> getPage(Pageable pageable, Date beginTime, String endTime, String logname, String logtype) {
        Specification<OperationLog> specification = new Specification<OperationLog>() {
            @Override
            public Predicate toPredicate(Root<OperationLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if (StringUtils.isNotBlank(logname)) {
                    list.add(cb.like(root.get("logname"), "%" + logname + "%"));
                }
                if (StringUtils.isNotBlank(logtype)) {
                    list.add(cb.like(root.get("logtype"), "%" + logtype + "%"));
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
