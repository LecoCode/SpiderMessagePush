package com.bangtaoche.messagepush.util;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Properties;

public class RedisUntil {

    private static  String IP;
    private static  int PORT;
    private static JedisPool jedisPool;
    static{
        Properties properties = new Properties();
        try {
            properties.load(RedisUntil.class.getClassLoader().getResourceAsStream("redis.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxWaitMillis(Long.parseLong(properties.getProperty("jedis.pool.maxWait")));
        config.setMaxIdle(Integer.parseInt(properties.getProperty("jedis.pool.maxIdle")));
        config.setTestOnBorrow(Boolean.parseBoolean(properties.getProperty("jedis.pool.testOnBorrow")));
        config.setTestOnReturn(Boolean.parseBoolean(properties.getProperty("jedis.pool.testOnReturn")));
        jedisPool = new JedisPool(config,properties.getProperty("redis.ip"),Integer.parseInt(properties.getProperty("redis.port")));
    }
    /**
     * 获取Redis资源
     * @return
     */
    public synchronized Jedis getRedisResource(){
        if (jedisPool!=null){
            Jedis resource = jedisPool.getResource();
            return resource;
        }
       return null;
    }
    /**
     * 释放资源
     * @param jedis
     */
    public static void closeJedis(Jedis jedis){
        if (jedisPool!=null)
            if (jedis!=null)
                jedisPool.returnResource(jedis);
    }

    @Test
    public void test(){
        Jedis redisResource = getRedisResource();
        redisResource.lpush("SX","xxxx1","1500");
        redisResource.lpush("SX","xxxx2","2500");
        redisResource.lpush("SX","xxxx3","3500");
        redisResource.lpush("SX","xxxx4","4500");
        redisResource.lpush("SX","xxxx5","5500");
        redisResource.lpush("SX","xxxx6","6500");
//
////        redisResource.lrem("SX",0,"xxxx6");
//        List<String> sx = redisResource.lrange("SX", 0, 3);
//        for (String s:
//             sx) {
//
//            System.out.println(s);
//        }
//
//        redisResource.flushAll();
    }
}

