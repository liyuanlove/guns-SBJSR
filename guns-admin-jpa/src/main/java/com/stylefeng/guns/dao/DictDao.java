package com.stylefeng.guns.dao;

import com.stylefeng.guns.po.Dict;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tan.jpa.dao.BaseDao;

import java.util.List;

/**
 * 字典Dao
 *
 * @author TC
 * @Date 2018-07-29 16:39:45
 */
@Repository
public interface DictDao extends BaseDao<Dict, Integer> {

    /**
     * 删除所有子项
     *
     * @param pid
     */
    @Transactional
    @Modifying
    @Query("delete from Dict where pid = ?1")
    public void delByPidEq(Integer pid);

    /**
     * 是否已经存在
     *
     * @param pid
     * @param code
     * @return
     */
    public boolean existsByPidEqualsAndCodeEquals(Integer pid, String code);

    /**
     * 查找所有子项目
     *
     * @param pid
     * @return
     */
    public List<Dict> findByPidEquals(Integer pid);

    /**
     * 根绝code查询子项
     *
     * @param code
     * @return
     */
    @Query("select o1 from Dict o1 where o1.pid in(select o2.id from Dict o2 where o2.code = ?1) order by o1.num asc")
    public List<Dict> findChildrenByParentCode(String code);

    /**
     * 通过name查找
     *
     * @param name
     * @return
     */
    public Dict findByNameEquals(String name);
}
