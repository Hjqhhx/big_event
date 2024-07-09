package com.itheima;

import cn.hutool.db.nosql.redis.RedisDS;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedisSet() {

        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set("username", "张三");
        operations.set("id", "1", 15, TimeUnit.SECONDS);

    }

    @Test
    public void Get() {

        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        System.out.println(operations.get("username"));
        System.out.println(operations.get("id"));

    }
}
