package com.base;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@SpringBootTest
class ItchunApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //  Redis的三个框架：Jedis,Redisson,Lettuce
    @Test
    void redis_test() {
        ListOperations<String, String> listOperations = redisTemplate.opsForList();
        //我们知道list就是左右都可以pop和push，可以当做栈也可以当消息队列
        listOperations.leftPush("list", "test01");
        listOperations.leftPush("list", "test02");
        listOperations.rightPush("list", "test03");
        listOperations.rightPush("list", "test04");
        //取范围，0到-1就是从0开始起步，0到2就是前三个
        List<String> list = listOperations.range("list", 0, -1);

        for (String s : list) {
            //到这里我们可以这里理解，左就是向上，由就是向下，添加
            System.out.println(s);
        }
    }

}
