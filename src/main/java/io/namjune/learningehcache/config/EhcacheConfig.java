package io.namjune.learningehcache.config;

import net.sf.ehcache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EhcacheConfig extends CacheConfig {

    @Bean
    public EhCacheCacheManager ehCacheCacheManager() {
        net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();

        //Ehcache 전역 설정 파일중 멤버에 대한 캐시 사용
        config.addCache(DomainCache.member());

        return new EhCacheCacheManager(CacheManager.newInstance(config));
    }
}
