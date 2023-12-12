package com.example.redistest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
public class TestController {

    RedisService redisService;

    @PostMapping("/keys")
    public Set<String> getKeys(){
        return redisService.getKeys();
    }

    @PostMapping("/set")
    public void setValue(@RequestParam String key, @RequestParam String value){
        redisService.setValue(key, value);
    }

    @PostMapping("/get")
    public String getValue(@RequestParam String key){
        return redisService.getValue(key);
    }
}
