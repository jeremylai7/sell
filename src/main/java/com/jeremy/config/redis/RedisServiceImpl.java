package com.jeremy.config.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: laizc
 * @Date: 2020/5/7 23:04
 * @Description:
 */
@Service
public class RedisServiceImpl implements RedisService{
    @Resource
    private RedisTemplate redisTemplate;
   //

    @Override
    public void set(String key, Object value, long expire) {
        RedisSerializer serializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(serializer);
        ValueOperations<String,Object> ov = redisTemplate.opsForValue();
        if (expire == -1){
            ov.set(key,value);
        }else {
            ov.set(key,value,expire, TimeUnit.SECONDS);
        }
    }

    @Override
    public String get(String key) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return (String) operations.get(key);
    }

    @Override
    public void remove(String key) {
        redisTemplate.opsForValue().getOperations().delete(key);
    }

    /*public static void main(String[] args) {
        JedisShardInfo jedisShardInfo = new JedisShardInfo("192.168.31.234",6379,10000);
        //jedisShardInfo.setPassword("1234561");
        Jedis jedis = new Jedis(jedisShardInfo);
        jedis.connect();
        jedis.set("name","jeremy");
        System.out.println(jedis.get("token_c52763c4-8fd5-484c-aabf-92492a550dd3"));
        //jedis.connect();
        jedis.close();
    }*/
}
