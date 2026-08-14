package com.general_Biraj.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Disabled
    @Test
    void testSendEmail() {
//        redisTemplate.opsForValue().set("email", "biraj@gmail.com");
        Object email = redisTemplate.opsForValue().get("email");
        Object salary = redisTemplate.opsForValue().get("salary");
        int a=1;
    }
    }

