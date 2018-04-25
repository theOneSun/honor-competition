package com.sun.honor.service.impl;

import com.sun.honor.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author sunjian.
 */
@Service
public class RedisServiceImpl implements RedisService {

    private final RedisTemplate<Object,Object> redisTemplate;
    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisServiceImpl(RedisTemplate<Object, Object> redisTemplate, StringRedisTemplate stringRedisTemplate) {
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Override
    public void addTempPlayer(String name) {
//        redisTemplate.expire("maxTime",1, TimeUnit.MINUTES);
//        redisTemplate.opsForValue().set("player", name);
        stringRedisTemplate.expire("maxTime",1, TimeUnit.MINUTES);
        stringRedisTemplate.opsForValue().set("player",name);
        System.out.println("存入redis"+name);
    }
}
