package com.sun.honor;

import com.sun.HonorWebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author sunjian.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HonorWebApplication.class)
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void connect() throws Exception {

        stringRedisTemplate.opsForValue().set("player", "hehe");
        String player = stringRedisTemplate.opsForValue().get("player");
        System.out.println(player);

    }
}
