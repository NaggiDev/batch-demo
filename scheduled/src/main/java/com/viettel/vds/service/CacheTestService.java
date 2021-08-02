package com.viettel.vds.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

@CacheConfig(cacheNames = {"CacheTestService"}, cacheManager = "localCacheManager")
public interface CacheTestService {
    @Cacheable(value = "getNamePram",sync = true,key ="#name")
    public String getName(String name);
    @Cacheable(value = "getName")
    public String getName();
}
