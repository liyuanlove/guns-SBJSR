package com.stylefeng.guns.core.shiroext.cache;

import lombok.Data;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

/**
 * <p> 自定义cacheManage 扩张shiro里面的缓存 使用reids作缓存 </p>
 */
@Data
public class ShiroSpringCacheManager implements CacheManager, Destroyable {

    private org.springframework.cache.CacheManager cacheManager;

    public ShiroSpringCacheManager(org.springframework.cache.CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void destroy() throws Exception {
        cacheManager = null;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) {
        if (name == null) {
            return null;
        }
        return new ShiroSpringCache<K, V>(name, getCacheManager());
    }

}
