package com.stylefeng.guns.service.impl;

import com.stylefeng.guns.core.base.service.impl.BaseServiceImpl;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.core.ztree.ZTreeNode;
import com.stylefeng.guns.dao.DeptDao;
import com.stylefeng.guns.po.Dept;
import com.stylefeng.guns.service.IDeptService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 部门Service实现
 *
 * @author TC
 * @Date 2018-07-30 16:11:11
 */
@Service
public class DeptServiceImpl extends BaseServiceImpl<Dept, Integer, DeptDao> implements IDeptService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCascade(Integer deptId) {
        boolean exists = baseDao.existsById(deptId);
        if (!exists) {
            throw new GunsException(BizExceptionEnum.NO_THIS_RECORD);
        }
        baseDao.delByPidsLike("%[" + deptId + "]%");
        baseDao.deleteById(deptId);
    }

    @Override
    public List<ZTreeNode> tree() {
        List<Map<String, Object>> mapList = baseDao.tree();
        List<ZTreeNode> nodes = BeanKit.mapListTobeanList(mapList, ZTreeNode.class);
        return nodes;
    }

    @Override
    public List<Dept> list(String condition) {
        if (StringUtils.isNotBlank(condition)) {
            return baseDao.listByName(condition);
        } else {
            return baseDao.list();
        }
    }
}
