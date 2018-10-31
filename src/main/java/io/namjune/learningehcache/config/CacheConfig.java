package io.namjune.learningehcache.config;

import net.sf.ehcache.config.CacheConfiguration;

public class CacheConfig {

    public static class DomainCache {

        public static final int MAX_ENTRIES_LOCAL_HEAP = 1000;
        public static final int MAX_ENTRIES_LOCAL_DISK = 100;
        public static final long TIME_TO_LIVE_SECONDS = 60L * 5L;
        public static final String MEMORY_STORE_EVICTION_POLICY = "LFU";

        public static final String MEMBER = "Member";

        static CacheConfiguration member() {
            CacheConfiguration cacheConfig = new CacheConfiguration();
            cacheConfig.setName(MEMBER);
            cacheConfig.overflowToOffHeap(false);
            cacheConfig.setEternal(false);
            cacheConfig.setMemoryStoreEvictionPolicy(MEMORY_STORE_EVICTION_POLICY);
            cacheConfig.setMaxEntriesLocalHeap(MAX_ENTRIES_LOCAL_HEAP);
            cacheConfig.setTimeToLiveSeconds(TIME_TO_LIVE_SECONDS);
            return cacheConfig;
        }
    }
}
