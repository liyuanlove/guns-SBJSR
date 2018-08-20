package com.stylefeng.guns.service.impl;

import com.stylefeng.guns.core.common.constant.factory.MutiStrFactory;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.dao.DictDao;
import com.stylefeng.guns.po.Dict;
import com.stylefeng.guns.service.IDictService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tan.jpa.exception.GunsException;
import org.tan.jpa.service.impl.BaseServiceImpl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 字典Service实现
 *
 * @author TC
 * @Date 2018-07-29 16:39:45
 */
@Service
public class DictServiceImpl extends BaseServiceImpl<Dict, Integer, DictDao> implements IDictService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Dict addDict(String dictCode, String dictName, String dictTips, String dictValues) {
        return this.saveDict(dictCode, dictName, dictTips, dictValues);
    }

    private Dict saveDict(String dictCode, String dictName, String dictTips, String dictValues) {
        boolean exists = baseDao.existsByPidEqualsAndCodeEquals(0, dictCode);
        //判断有没有该字典
        if (exists) {
            throw new GunsException(BizExceptionEnum.DICT_EXISTED);
        }
        //解析dictValues
        List<Map<String, String>> items = MutiStrFactory.parseKeyValue(dictValues);

        //添加字典
        Dict dict = new Dict();
        dict.setName(dictName);
        dict.setCode(dictCode);
        dict.setTips(dictTips);
        dict.setNum(0);
        dict.setPid(0);
        dict = this.baseDao.saveAndFlush(dict);

        //添加字典条目
        for (Map<String, String> item : items) {
            String code = item.get(MutiStrFactory.MUTI_STR_CODE);
            String name = item.get(MutiStrFactory.MUTI_STR_NAME);
            String num = item.get(MutiStrFactory.MUTI_STR_NUM);
            Dict itemDict = new Dict();
            itemDict.setPid(dict.getId());
            itemDict.setCode(code);
            itemDict.setName(name);

            try {
                itemDict.setNum(Integer.valueOf(num));
            } catch (NumberFormatException e) {
                throw new GunsException(BizExceptionEnum.DICT_MUST_BE_NUMBER);
            }
            this.baseDao.saveAndFlush(itemDict);
        }
        return dict;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Dict editDict(Integer dictId, String dictCode, String dictName, String dictTips, String dicts) {
        //删除之前的字典
        this.deleteDictCascade(dictId);
        //重新添加新的字典
        return this.saveDict(dictCode, dictName, dictTips, dicts);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDictCascade(Integer dictId) {
        //删除这个字典的子词典
        baseDao.delByPidEq(dictId);
        //删除这个词典
        baseDao.deleteById(dictId);
    }

    @Override
    public List<Dict> selectByParentCode(String code) {
        return this.baseDao.findChildrenByParentCode(code);
    }


    @Override
    public List<Dict> list(String conditiion) {
        Specification<Dict> specification = new Specification<Dict>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query,
                                         CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(cb.equal(root.get("pid").as(Integer.class), 0));
                if (StringUtils.isNotBlank(conditiion)) {
                    list.add(cb.like(root.get("name").as(String.class), "%" + conditiion + "%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "id");
        Sort sort = Sort.by(order);
        return this.baseDao.findAll(specification, sort);
    }

    @Override
    public List<Dict> findSubDict(Integer pid) {
        return baseDao.findByPidEquals(pid);
    }
}
