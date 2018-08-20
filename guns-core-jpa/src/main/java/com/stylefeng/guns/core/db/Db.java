package com.stylefeng.guns.core.db;

import com.stylefeng.guns.core.util.SpringContextHolder;
import org.tan.jpa.dao.BaseDao;

/**
 * 便捷数据库操作类
 * 本类的使用情景：
 * 1.单纯想创建现有的Mapper
 * Db.getMapper(UserLoginMapper.class).getOneById("14779707158513204");<br>
 *
 * @author fengshuonan
 * @date 2017年2月22日 下午8:07:17
 */
@SuppressWarnings("all")
public class Db<T> {

    /**
     * 每个Db类，包装一个Mapper接口,这个clazz就是接口的类类型，例如UserMapper.class
     */
    private Class<T> clazz;

    private BaseDao<?, ?> baseDao;

    /**
     * 私有构造方法，不允许自己创建
     */
    private Db(Class clazz) {
        this.clazz = clazz;
        this.baseDao = (BaseDao<?, ?>) SpringContextHolder.getBean(clazz);
    }

    /**
     * <p>
     * 创建包含指定mapper的Db工具类,使用本类的第一种用法
     *
     * @param clazz mapper的类类型
     * @date 2017年2月22日 下午10:09:31
     */
    public static <T> Db<T> create(Class<T> clazz) {
        return new Db<T>(clazz);
    }

    /**
     * <p>
     * 获取一个mapper的快捷方法
     *
     * @param clazz mapper类的类对象
     * @date 2017年2月22日 下午10:31:35
     */
    public BaseDao<?, ?> getDao() {
        return this.baseDao;
    }

    /**
     * <p>
     * 获取一个mapper的快捷方法
     *
     * @param clazz mapper类的类对象
     * @date 2017年2月22日 下午10:31:35
     */
    public static <T> T getDao(Class<T> clazz) {
        return SpringContextHolder.getBean(clazz);
    }

}
