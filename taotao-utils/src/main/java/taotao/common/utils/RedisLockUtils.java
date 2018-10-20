package taotao.common.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-14
 * Time: 16:50
 */
public class RedisLockUtils {
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;
    //private  Jedis jedis = new Jedis("127.0.0.1", 6379);


    /**
     * 尝试获取分布式锁
     *
     * @param key        锁
     * @param requestId  请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public static boolean tryGetLock(JedisCluster jedisCluster, String key, String requestId, long expireTime) {
        String result = jedisCluster.set(key, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        return LOCK_SUCCESS.equals(result);
    }

    /**
     * 释放分布式锁
     *
     * @param key       锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(JedisCluster jedisCluster,String key, String requestId) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedisCluster.eval(script, Collections.singletonList(key), Collections.singletonList(requestId));
        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }


    /*
    private static Redisson redisson;
    private static Config config = new Config();

    static {
        config.useSingleServer().setAddress("127.0.0.1:6379");
        redisson = (Redisson) Redisson.create(config);
    }

    public static boolean tryGetLock(String key) {
        RLock redissonLock = redisson.getLock(key);
        try {
            return redissonLock.tryLock(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            return false;
        }
    }

    public static void unlock(String key) {
        RLock redissonLock = redisson.getLock(key);
        redissonLock.unlock();
    }
*/
}
