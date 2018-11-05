package com.taotao.rest.service.impl;

import com.taotao.model.Test;
import com.taotao.rest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

import javax.inject.Named;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-21
 * Time: 17:56
 */

@Named
public class TestServiceImpl implements TestService {
    @Autowired
    private JedisCluster jedisCluster;
    @Override
    public Test test(String id) {
        jedisCluster.set("Test","Hello Reids");
        System.out.println("id===" + id);
        Test test = new Test();
        test.setPartNo("partno一");
        System.out.println(jedisCluster.get("Test"));
        return test;
    }

    @Override
    public String testPost(Map<String,Object> map) {
        return "name==" + map.get("name")+";pass==="+map.get("pass");
    }
}
