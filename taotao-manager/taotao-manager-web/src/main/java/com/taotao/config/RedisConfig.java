package com.taotao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-17
 * Time: 22:46
 */
@Configuration
//@EnableCaching
public class RedisConfig {

    @Value("${redis_hosts}")
    private String redisHosts;

    @Bean
    public JedisConnectionFactory getJedisConnectionFactory() {
        String[] hosts = redisHosts.split(",");
        if (hosts.length <= 0) {
            throw new IllegalArgumentException("redis配置错误!");
        }
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        for (String hostAndPort : hosts) {
            redisClusterConfiguration.addClusterNode(this.createRedisNode(hostAndPort));
        }
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration);
        return jedisConnectionFactory;
    }

    private RedisNode createRedisNode(String hostAndPort) {
        int separatorIndex = hostAndPort.lastIndexOf(":");
        String host = hostAndPort.substring(0, separatorIndex);
        int port = Integer.valueOf(hostAndPort.substring(separatorIndex + 1));
        return new RedisNode(host, port);
    }

}
