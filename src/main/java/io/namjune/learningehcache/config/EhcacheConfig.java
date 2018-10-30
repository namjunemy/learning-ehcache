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

        //멤버 찾는 메소드에 대한 캐시
        config.addCache(MemeberCache.findMember());

        return new EhCacheCacheManager(CacheManager.newInstance(config));
    }
}
