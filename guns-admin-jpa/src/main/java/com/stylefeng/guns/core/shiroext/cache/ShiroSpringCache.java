package com.stylefeng.guns.core.shiroext.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.CacheException;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;

import java.util.Collection;
import java.util.Set;

/**
 * <p> 自定义缓存 将数据存入到redis中 </p>
 *
 * @param <K>
 * @param <V>
 */
@SuppressWarnings("unchecked")
@Slf4j
public class ShiroSpringCache<K, V> implements org.apache.shiro.cache.Cache<K, V> {

    private CacheManager cacheManager;
    private Cache cache;

    /**
     * 初始化spring-cache的manager和cache
     *
     * @param name
     * @param cacheManager
     */
    public ShiroSpringCache(String name, CacheManager cacheManager) {
        if (name == null || cacheManager == null) {
            throw new IllegalArgumentException("cacheManager or CacheName cannot be null.");
        }
        this.cacheManager = cacheManager;
        //这里首先是从父类中获取这个cache,如果没有会创建一个redisCache,初始化这个redisCache的时候
        //会设置它的过期时间如果没有配置过这个缓存的，那么默认的缓存时间是为0的，如果配置了，就会把配置的时间赋予给这个RedisCache
        //如果从缓存的过期时间为0，就表示这个RedisCache不存在了，这个redisCache实现了spring中的cache
        this.cache = cacheManager.getCache(name);
    }

    /**
     * 获取值
     *
     * @param key
     * @return
     * @throws CacheException
     */
    @Override
    public V get(K key) throws CacheException {
        log.info("get cache information of key [{}]", key);
        if (key == null) {
            return null;
        }
        ValueWrapper valueWrapper = cache.get(key);
        if (valueWrapper == null) {
            return null;
        }
        return (V) valueWrapper.get();
    }

    /**
     * 存储KV
     *
     * @param key
     * @param value
     * @return
     * @throws CacheException
     */
    @Override
    public V put(K key, V value) throws CacheException {
        log.info("create new cache，information is：{}={}", key, value);
        cache.put(key, value);
        return get(key);
    }

    /**
     * 删除KV
     *
     * @param key
     * @return
     * @throws CacheException
     */
    @Override
    public V remove(K key) throws CacheException {
        log.info("remove cache of key [{}]", key);
        V v = get(key);
        cache.evict(key);
        return v;
    }

    @Override
    public void clear() throws CacheException {
        log.info("clear all cache");
        cache.clear();
    }

    @Override
    public int size() {
        return cacheManager.getCacheNames().size();
    }

    /**
     * 获取缓存中所的key值
     */
    @Override
    public Set<K> keys() {
        return (Set<K>) cacheManager.getCacheNames();
    }

    /**
     * 获取缓存中所有的values值
     */
    @Override
    public Collection<V> values() {
        return (Collection<V>) cache.get(cacheManager.getCacheNames()).get();
    }

    @Override
    public String toString() {
        return "ShiroSpringCache [cache=" + cache + "]";
    }
}