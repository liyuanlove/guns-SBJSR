package com.stylefeng.guns.dao;

import com.stylefeng.guns.po.Relation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tan.jpa.dao.BaseDao;

import java.util.List;

/**
 * 角色权限配置Dao
 *
 * @author TC
 */
@Repository
public interface RelationDao extends BaseDao<Relation, Integer> {

    /**
     * 通过菜单ID删除
     *
     * @param menuid
     */
    @Transactional
    @Modifying
    @Query("delete from Relation where menuid = ?1")
    public void delMenuidEq(Long menuid);

    /**
     * 通过角色ID删除
     *
     * @param roleid
     */
    @Transactional
    @Modifying
    @Query("delete from Relation where roleid = ?1")
    public void delByRoleidEq(Integer roleid);

    /**
     * 查询角色权限ID集合
     *
     * @param roleid
     * @return
     */
    @Query("select o.menuid from Relation o where o.roleid = ?1")
    public List<Long> menuidListByRoleId(Integer roleid);
}
