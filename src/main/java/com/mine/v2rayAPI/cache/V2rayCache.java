package com.mine.v2rayAPI.cache;

import com.mine.v2rayAPI.entity.CacheEntity;
import com.mine.v2rayAPI.enums.CacheEnum;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final class V2rayCache {
    private V2rayCache() {
    }

    private static final Map<CacheEnum, CacheEntity<String>> cache = new ConcurrentHashMap<>();

    public static boolean cacheValid(CacheEnum key){
        CacheEntity<String> cacheEntity = cache.get(key);

        return !Objects.isNull(cacheEntity) && LocalDateTime.now().isBefore(cacheEntity.getExpireTime());
    }

    public static String getCacheData(CacheEnum key){
        return cache.get(key).getData();
    }

    public static void putCache(CacheEnum key, String data){
        cache.put(key,new CacheEntity<>(data,LocalDateTime.now().plusHours(1)));
    }

}
