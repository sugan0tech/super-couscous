package com.example.redistest;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class RedisService {

    private final RedisTemplate<String ,String> redisTemplate;

    public void setValue(String key, String value){
        redisTemplate.opsForValue().set(key, value);
    }

    public String getValue(String key){
        return (String) redisTemplate.opsForValue().get(key);
    }

    public Set<String> getKeys(){
        return redisTemplate.keys("*");
    }
}
