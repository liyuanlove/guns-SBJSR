package com.stylefeng.guns.core.base.service.impl;

import com.stylefeng.guns.core.base.dao.BaseDao;
import com.stylefeng.guns.core.base.service.IBaseService;
import com.stylefeng.guns.core.exception.GunsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * IBaseService 实现类
 *
 * @author TC
 */
public class BaseServiceImpl<T, ID, D extends BaseDao<T, ID>> implements IBaseService<T, ID> {

    @Autowired
    protected D baseDao;

    @Override
    public long count() {
        return baseDao.count();
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        return baseDao.count(example);
    }

    @Override
    public long count(Specification<T> specification) {
        return baseDao.count(specification);
    }

    @Override
    public boolean existsById(ID pk) {
        return baseDao.existsById(pk);
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        return baseDao.exists(example);
    }

    @Override
    public <S extends T> S save(S entity) {
        return baseDao.save(entity);
    }

    @Override
    public <S extends T> S insertAllCol(S entity) {
        return baseDao.save(entity);
    }

    @Override
    public <S extends T> S updateAllCol(S entity) {
        return baseDao.save(entity);
    }

    @Override
    public <S extends T> S updateNotNullField(S oldEntity, S newEntity) {
        //获取原始对象中的所有public方法
        Method[] methods = oldEntity.getClass().getDeclaredMethods();
        //用于提取不包含指定关键词的方法
        String regExpression = "^(get)(?!Id|Createtime)(\\w+)";
        Pattern pattern = Pattern.compile(regExpression);
        Matcher matcher;
        for (Method method : methods) {
            matcher = pattern.matcher(method.getName());
            //正则匹配以get开头，后面不能匹配Id的方法
            if (matcher.find()) {
                Object o = null;
                try {
                    o = method.invoke(newEntity, null);
                    //忽略值为空的字段
                    if (o == null) {
                        continue;
                    }
                    //取出get方法名后面的字段名
                    String fieldName = matcher.group(2);
                    //找到该字段名的set方法
                    Method rawMethod = oldEntity.getClass().getMethod("set" + fieldName, method.getReturnType());
                    //调用实体对象的set方法更新字段值
                    rawMethod.invoke(oldEntity, o);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new GunsException(400, "更新失败");
                }
            }
        }
        return baseDao.save(oldEntity);
    }

    @Override
    public <S extends T> S saveAndFlush(S entity) {
        return baseDao.saveAndFlush(entity);
    }

    @Override
    public <S extends T> List<S> saveList(List<S> entityList) {
        return baseDao.saveAll(entityList);
    }

    @Override
    public void deleteById(ID id) {
        baseDao.deleteById(id);
    }

    @Override
    public void delete(T entity) {
        baseDao.delete(entity);
    }

    @Override
    public void deleteAll() {
        baseDao.deleteAll();
    }

    @Override
    public void deleteInBatch(Iterable<T> entities) {
        baseDao.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch() {
        baseDao.deleteAllInBatch();
    }

    @Override
    public T getOneById(ID id) {
        return baseDao.getOne(id);
    }

    @Override
    public List<T> findAll() {
        return baseDao.findAll();
    }

    @Override
    public List<T> getList(Specification<T> spec) {
        return baseDao.findAll(spec);
    }

    @Override
    public List<T> getSortedList(Specification<T> spec, Sort sort) {
        return baseDao.findAll(spec, sort);
    }


    @Override
    public <S extends T> Page<S> getPageByExample(Example<S> example, Pageable pageable) {
        return baseDao.findAll(example, pageable);
    }

    @Override
    public Page<T> getPage(Pageable pageable) {
        return baseDao.findAll(pageable);
    }

    @Override
    public Page<T> getFilteredPage(Specification<T> spec, Pageable pageable) {
        return baseDao.findAll(spec, pageable);
    }

}
