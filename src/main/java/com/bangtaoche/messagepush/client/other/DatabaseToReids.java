package com.bangtaoche.messagepush.client.other;

import com.bangtaoche.messagepush.util.RedisUntil;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class DatabaseToReids {

    private Jedis jedis;
    private RedisUntil redisUntil = new RedisUntil();

    /**
     * 添加单条数据到reids
     * @param fixedvalue 键
     * @param url
     * @param price
     */
    public void redisPushByIndex(String fixedvalue,String url,String price){
        jedis=redisUntil.getRedisResource();
        jedis.lpush(fixedvalue,url,price);
        RedisUntil.closeJedis(jedis);
    }

    /**
     * 批量添加数据到redis
     * @param fixedvalue 键
     * @param urlandprice  url：price
     */
    public void redisPushAll(String fixedvalue, Map<String,String> urlandprice){
        for (Map.Entry<String,String> s:
             urlandprice.entrySet()) {
            String url = s.getKey();
            String price = s.getKey();
            this.redisPushByIndex(fixedvalue,url,price);
        }
    }


    public void redisRmByValue(String fixedvalue,String... strs){
        jedis = redisUntil.getRedisResource();
        for (String s:
             strs) {
            jedis.lrem(fixedvalue,0,s);
        }
    }




}
