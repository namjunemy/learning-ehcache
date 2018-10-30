package io.namjune.learningehcache.controller;

import io.namjune.learningehcache.domain.Member;
import io.namjune.learningehcache.domain.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    private static Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/member/nocache/{name}")
    @ResponseBody
    public Member getNoCacheMember(@PathVariable String name) {
        long start = System.currentTimeMillis();
        Member member = memberRepository.findByNameNoCache(name);
        long end = System.currentTimeMillis();

        logger.info(name + "의 Nocache 수행시간 : " + Long.toString(end - start));

        return member;
    }

    @GetMapping("/member/cache/{name}")
    @ResponseBody
    public Member getCacheMember(@PathVariable String name) {
        long start = System.currentTimeMillis();
        Member member = memberRepository.findByNameCache(name);
        long end = System.currentTimeMillis();

        logger.info(name + "의 Cache 수행시간 : " + Long.toString(end - start));

        return member;
    }

    @GetMapping("/member/refresh/{name}")
    @ResponseBody
    public String refresh(@PathVariable String name) {
        memberRepository.refresh(name);
        return "cache clear!";
    }
}
