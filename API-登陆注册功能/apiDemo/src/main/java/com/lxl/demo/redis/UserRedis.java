package com.lxl.demo.redis;

import java.util.concurrent.TimeUnit;
import com.google.gson.Gson;
import com.lxl.demo.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRedis extends BaseRedis<User> {

    private static final String REDIS_KEY = "com.dayup.seckil.redis.UserRedis";
    @Override
    protected String getRedisKey() {
        return REDIS_KEY;
    }
    public void add(String key, Long time, User user){
        Gson gson = new Gson();
        redisTemplate.opsForValue().set(key, gson.toJson(user), time, TimeUnit.SECONDS);
    }
}
