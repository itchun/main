package com.base.redis;

import redis.clients.jedis.Jedis;

import java.util.List;

public class RedisMain {

    public static void main(String[] args) {
        try {
            Jedis jedis = new Jedis("192.168.1.160", 6379);
            jedis.auth("datahome123");
            jedis.set("monitor", "value");
            String value = jedis.get("monitor");
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
