package com.marvel.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import net.sf.ehcache.Cache;
import net.sf.ehcache.config.CacheConfiguration;

import java.time.Duration;

@Configuration
@EnableCaching
public class EhCacheConfig {

    @Bean
    public EhCacheManagerFactoryBean cacheManager() {
        return new EhCacheManagerFactoryBean();
    }

    @Bean
    public EhCacheCacheManager marvelEhCacheManager() {
        CacheConfiguration testEhCacheConfig = new CacheConfiguration()
                .eternal(false)
                .timeToIdleSeconds(3600)
                .timeToLiveSeconds(7200)
                .maxEntriesLocalHeap(10)
                .name("marvelCache");
        Cache characterIdCache = new Cache(testEhCacheConfig);

        cacheManager().getObject().addCache(characterIdCache);
        return new EhCacheCacheManager(cacheManager().getObject());
    }

}
