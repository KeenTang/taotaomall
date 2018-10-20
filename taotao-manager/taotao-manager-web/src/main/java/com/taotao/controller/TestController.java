package com.taotao.controller;

import com.taotao.model.Test;
import com.taotao.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.JedisCluster;
import taotao.common.utils.RedisLockUtils;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-13
 * Time: 22:23
 */
@Controller
public class TestController {
    private final static LoggerF
    @Autowired
    private TestService testService;

    private AtomicInteger atomicInteger = new AtomicInteger(1);

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Autowired
    private JedisCluster jedisCluster;

    @RequestMapping("/test")
    public ResponseEntity test() {
        for (int i = 0; i < 3; i++) {
            if (save()) {
                break;
            } else {
                System.out.println("第" + i + "次执行失败");
            }
        }
        return new ResponseEntity("Success", HttpStatus.OK);
    }

    @RequestMapping("/test2")
    public ResponseEntity test2() {
        String name = "线程" + atomicInteger.getAndDecrement();
        String key = "partno1";
        String requestId = UUID.randomUUID().toString();
        if (RedisLockUtils.tryGetLock(jedisCluster, key, requestId, 5000)) {
            System.out.println(name + "获取锁成功");
            try {
                this.save();
            } finally {
                boolean result = RedisLockUtils.releaseDistributedLock(jedisCluster, key, requestId);
                System.out.println(name + "解锁结果:" + result);
            }

        } else {
            System.out.println(name + "获取锁失败");
        }
        return new ResponseEntity("Success", HttpStatus.OK);
    }

    @RequestMapping("/testRedis")
    public ResponseEntity testRedis(){
        jedisCluster.set("TEst","TEst");
        return new ResponseEntity("Success",HttpStatus.OK);
    }



    /*
    @RequestMapping("/test3")
    public ResponseEntity test4() {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        String name = "线程" + atomicInteger.getAndDecrement();
        String key = "part4";
        String value = UUID.randomUUID().toString();
        RedisConnection connection = jedisConnectionFactory.getConnection();
        //connection.set
        boolean result = connection.set(key.getBytes(), value.getBytes(), Expiration.seconds(5), RedisStringCommands.SetOption.SET_IF_ABSENT);
        if (result) {
            System.out.println(name + "获取锁成功");
            try {
                this.save();
            } finally {
                boolean result2 = connection.eval(script.getBytes(), ReturnType.BOOLEAN, 1, key.getBytes(), value.getBytes());
                System.out.println(name + "解锁结果:" + result2);
            }
        } else {
            System.out.println(name + "获取锁失败");
        }
        return new ResponseEntity("Success", HttpStatus.OK);
    }*/

    private boolean save() {
        System.out.println("test");
        String partNo = "partno1";
        Test test;
        int i;
        //synchronized (TestController.class) {
        Integer num = testService.getMaxNum(partNo);
        System.out.println("num==" + String.valueOf(num));
        if (num == null || num.intValue() == 0) {
            test = new Test();
            test.setNum(1);
            test.setVersion(1);
            test.setPartNo(partNo);
            i = testService.save(test);
        } else {
            //test = testService.getTest(partNo);
            test = new Test();
            test.setNum(num);
            test.setVersion(1);
            test.setPartNo(partNo);
            i = testService.save2(test);
            if (i == 0) {
                System.out.println("执行失败");
            } else {
                System.out.println("执行成功");
            }
            //test.setVersion();
        }
        return i > 0;
        //}
    }
}
