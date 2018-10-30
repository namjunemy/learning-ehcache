package io.namjune.learningehcache.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private static Logger logger = LoggerFactory.getLogger(MemberRepositoryImpl.class);

    @Override
    public Member findByNameNoCache(String name) {
        slowQuery(2000);
        return new Member(0, name + "@gmail.com", name);
    }

    @Override
    @Cacheable(value =
        "findMember", key = "#name")
    public Member findByNameCache(String name) {
        slowQuery(2000);
        return new Member(0, name + "@gmail.com", name);
    }

    @Override
    @CacheEvict(value = "findMember", key = "#name")
    public void refresh(String name) {
        logger.info(name + "의 Cache Clear!");
    }

    private void slowQuery(long seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
