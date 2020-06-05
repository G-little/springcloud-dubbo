package com.little.g.springcloud.mall.web.manager;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 简单缓存的数据
 */
public class HomeCacheManager {

    public static final boolean ENABLE = false;

    public static final String INDEX = "index";

    public static final String CATALOG = "catalog";

    public static final String GOODS = "goods";

    private static Cache<String, Object> guavaCache = CacheBuilder.newBuilder()
            .expireAfterWrite(10L, TimeUnit.MINUTES)
            .build();


    /**
     * 缓存首页数据
     *
     * @param data
     */
    public static void loadData(String cacheKey, Object data) {
        Object cacheData = guavaCache.getIfPresent(cacheKey);
        // 有记录，则先丢弃
        if (cacheData != null) {
            guavaCache.invalidate(cacheKey);
        }

        guavaCache.put(cacheKey, data);
    }

    public static <T> T getCacheData(String cacheKey, Class<T> clazz) {
        return (T) guavaCache.getIfPresent(cacheKey);
    }

    /**
     * 判断缓存中是否有数据
     *
     * @return
     */
    public static boolean hasData(String cacheKey) {
        if (!ENABLE) {
            return false;
        }


        Object cacheData = guavaCache.getIfPresent(cacheKey);
        if (cacheData == null) {
            return false;
        }
        return true;
    }

    /**
     * 清除所有缓存
     */
    public static void clearAll() {
        guavaCache.cleanUp();
    }

    /**
     * 清除缓存数据
     */
    public static void clear(String cacheKey) {
        guavaCache.invalidate(cacheKey);
    }

}
