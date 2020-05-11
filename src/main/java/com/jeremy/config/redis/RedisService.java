package com.jeremy.config.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
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
@Component
public class RedisService{
    @Resource
    private StringRedisTemplate redisTemplate;

    /**
     * 设置永久键值
     * @param key      键
     * @param value    值
     */
    public void set(String key, String value){
        RedisSerializer serializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(serializer);
        ValueOperations<String,String> ov = redisTemplate.opsForValue();
        ov.set(key,value);
    }

    /**
     * 设置键值
     * @param key       键
     * @param value     值
     * @param expire    过期时间，单位：秒
     */
    public void set(String key, String value, long expire) {
        RedisSerializer serializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(serializer);
        ValueOperations<String,String> ov = redisTemplate.opsForValue();
        ov.set(key,value,expire, TimeUnit.SECONDS);
    }

    /**
     * 根据key获取value
     * @param key  键
     * @return
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 通过key移除
     * @param key
     */
    public void remove(String key) {
        redisTemplate.opsForValue().getOperations().delete(key);
    }

    /**
     * 是否存在key
     * @param key
     * @return
     */
    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
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
