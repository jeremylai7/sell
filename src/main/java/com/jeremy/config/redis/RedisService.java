package com.jeremy.config.redis;

/**
 * @Auther: laizc
 * @Date: 2020/5/7 23:02
 * @Description:
 */
public interface RedisService {
    /**
     * 设置redis
     * @param key       键
     * @param value     值
     * @param expire    过期时间 单位:秒  -1:不设置过期时间
     */
    void set(String key,Object value,long expire);

    /**
     * 获取redis
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 移除redis
     * @param key
     */
    void remove(String key);

}
