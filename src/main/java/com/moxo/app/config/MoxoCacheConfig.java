package com.moxo.app.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class MoxoCacheConfig {

    public static final String USER = "USER";
    public static final String OTP = "USER";

    @Bean
    public CacheManager cacheManager() {

        CaffeineCache userCache = buildCache(USER, 15, 100);
        CaffeineCache otpCache = buildCache(OTP, 5, 500);
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(Arrays.asList(userCache, otpCache));
//        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
//        cacheManager.setCaffeine(buildCache("users", 10, 100));
        return manager;
    }

    private CaffeineCache buildCache(String name, int minutesToExpire, int size) {
        return new CaffeineCache(name, Caffeine.newBuilder()
                .expireAfterWrite(minutesToExpire, TimeUnit.MINUTES)
                .maximumSize(size)
                .build());
    }
}
