package org.tc.redis.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class RedisCacheDaoImpl implements RedisCacheDao {

    /**
     * Spring 的 Cache（这里采用Redis）
     */
    @Autowired
    private CacheManager cacheManager;

    @Override
    public Cache getCache(String cacheName) {
        return cacheManager.getCache(cacheName);
    }

    @Override
    public void put(String cacheName, Object key, Object value) {
        getCache(cacheName).put(key, value);
    }

    @Override
    public Object get(String cacheName, Object key) {
        Cache.ValueWrapper valueWrapper = getCache(cacheName).get(key);
        if (valueWrapper != null) {
            return valueWrapper.get();
        }
        return null;
    }

    @Override
    public Object getAndRemove(String cacheName, Object key) {
        Cache cache = getCache(cacheName);
        Cache.ValueWrapper valueWrapper = cache.get(key);
        if (valueWrapper != null) {
            cache.evict(key);
            return valueWrapper.get();
        }
        return null;
    }

    @Override
    public void remove(String cacheName, Object key) {
        getCache(cacheName).evict(key);
    }

    @Override
    public void removeAll(String cacheName) {
        getCache(cacheName).clear();
    }

}
