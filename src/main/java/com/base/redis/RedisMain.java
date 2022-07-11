package com.base.redis;

import redis.clients.jedis.Jedis;

public class RedisMain {

    public static void main(String[] args) {
        try {
            Jedis jedis = new Jedis("192.168.1.105", 6379);
            jedis.set("monitor", "value");
            String value = jedis.get("monitor");
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
