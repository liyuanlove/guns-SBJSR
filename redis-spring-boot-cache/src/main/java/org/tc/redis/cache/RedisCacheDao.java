package org.tc.redis.cache;

import org.springframework.cache.Cache;

/**
 * 直接操纵Redis缓存
 */
public interface RedisCacheDao {

    /**
     * 获取缓存对象
     *
     * @param cacheName
     * @return
     */
    public Cache getCache(String cacheName);

    /**
     * 将一个对象放入缓存
     *
     * @param key
     * @param value
     */
    public void put(String cacheName, Object key, Object value);

    /**
     * 从缓存中获得一个对象
     *
     * @param key
     * @return
     */
    public Object get(String cacheName, Object key);

    /**
     * 获取并删除
     *
     * @param cacheName
     * @param key
     * @return
     */
    public Object getAndRemove(String cacheName, Object key);

    /**
     * 移除单个
     *
     * @param cacheName
     * @param key
     */
    public void remove(String cacheName, Object key);

    /**
     * 清空
     *
     * @param cacheName
     */
    public void removeAll(String cacheName);

}
