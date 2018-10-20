package com.taotao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.*;

import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-18
 * Time: 23:29
 */
@Configuration
public class JedisConfig {
    @Bean
    public JedisCluster jedisCluster() {
        HashSet<HostAndPort> hashSet = new HashSet();
        hashSet.add(new HostAndPort("127.0.0.1", 6379));
        hashSet.add(new HostAndPort("127.0.0.1", 6380));
        hashSet.add(new HostAndPort("127.0.0.1", 6381));
        hashSet.add(new HostAndPort("127.0.0.1", 6382));
        hashSet.add(new HostAndPort("127.0.0.1", 6383));
        hashSet.add(new HostAndPort("127.0.0.1", 6384));
        JedisCluster jedisCluster = new JedisCluster(hashSet,new JedisPoolConfig());
        //jedisCluster.set
        return jedisCluster;
    }
}
