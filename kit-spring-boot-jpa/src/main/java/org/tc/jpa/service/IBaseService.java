package org.tc.jpa.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 顶级 Service
 *
 * @author TC
 */
public interface IBaseService<T, ID> {

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities
     */
    long count();

    /**
     * Returns the number of instances matching the given {@link Example}.
     *
     * @param example the {@link Example} to count instances for. Must not be {@literal null}.
     * @return the number of instances matching the {@link Example}.
     */
    <S extends T> long count(Example<S> example);

    long count(Specification<T> var1);

    /**
     * 根据主键判定是否存在
     *
     * @return
     */
    public boolean existsById(ID pk);

    /**
     * Checks whether the data store contains elements that match the given {@link Example}.
     *
     * @param example the {@link Example} to use for the existence check. Must not be {@literal null}.
     * @return {@literal true} if the data store contains elements that match the given {@link Example}.
     */
    <S extends T> boolean exists(Example<S> example);

    /**
     * 未配置ID使直接除ID外全字段插入
     * Hibernate: select next_val as id_val from hibernate_sequence for updateAllCol
     * Hibernate: updateAllCol hibernate_sequence set next_val= ? where next_val=?
     * Hibernate: insertAllCol into seller (addr, createtime, name, status, id) values (?, ?, ?, ?, ?)
     * 若有ID则查询是否存在，存在则除主键更新
     * Hibernate: select seller0_.id as id1_11_0_, seller0_.addr as addr2_11_0_, seller0_.createtime as createti3_11_0_, seller0_.name as name4_11_0_, seller0_.status as status5_11_0_ from seller seller0_ where seller0_.id=?
     * Hibernate: updateAllCol seller set addr=?, createtime=?, name=?, status=? where id=?
     *
     * @param entity
     */
    public <S extends T> S save(S entity);

    public <S extends T> S insertAllCol(S entity);

    public <S extends T> S updateAllCol(S entity);

    /**
     * 值更新非空字段
     *
     * @param oldEntity DB中的值
     * @param newEntity 前台值
     * @param <S>
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    public <S extends T> S updateNotNullField(S oldEntity, S newEntity);

    /**
     * Saves an entity and flushes changes instantly.
     *
     * @param entity
     * @return the saved entity
     */
    <S extends T> S saveAndFlush(S entity);

    /**
     * 插入（批量），该方法不适合 Oracle
     *
     * @param entityList 实体对象列表
     * @return boolean
     */
    <S extends T> List<S> saveList(List<S> entityList);


    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    void deleteById(ID id);

    /**
     * Deletes a given entity.
     *
     * @param entity
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    void delete(T entity);

    /**
     * Deletes all entities managed by the repository.
     */
    void deleteAll();

    /**
     * Deletes the given entities in a batch which means it will create a single {@link Query}. Assume that we will clear
     * the {@link javax.persistence.EntityManager} after the call.
     *
     * @param entities
     */
    void deleteInBatch(Iterable<T> entities);

    /**
     * Deletes all entities in a batch call.
     */
    void deleteAllInBatch();

    /**
     * <p>
     * 根据 ID 查询
     * </p>
     *
     * @param pk 主键ID
     * @return T
     */
    T getOneById(ID pk);


    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    List<T> findAll();

    /**
     * Returns all entities matching the given {@link Specification}.
     *
     * @param spec can be {@literal null}.
     * @return never {@literal null}.
     */
    List<T> getList(Specification<T> spec);

    /**
     * Returns all entities matching the given {@link Specification} and {@link Sort}.
     *
     * @param spec can be {@literal null}.
     * @param sort must not be {@literal null}.
     * @return never {@literal null}.
     */
    List<T> getSortedList(Specification<T> spec, Sort sort);

    /**
     * Returns a {@link Page} of entities meeting the paging restriction provided in the {@code Pageable} object.
     *
     * @param pageable
     * @return a page of entities
     */
    Page<T> getPage(Pageable pageable);

    /**
     * Returns a {@link Page} of entities matching the given {@link Example}. In case no match could be found, an empty
     * {@link Page} is returned.
     *
     * @param example  must not be {@literal null}.
     * @param pageable can be {@literal null}.
     * @return a {@link Page} of entities matching the given {@link Example}.
     */
    <S extends T> Page<S> getPageByExample(Example<S> example, Pageable pageable);

    /**
     * Returns a {@link Page} of entities matching the given {@link Specification}.
     *
     * @param spec     can be {@literal null}.
     * @param pageable must not be {@literal null}.
     * @return never {@literal null}.
     */
    Page<T> getFilteredPage(Specification<T> spec, Pageable pageable);
}
