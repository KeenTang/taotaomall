package com.taotao.sso.service.impl;

import com.taotao.dao.UserMapper;
import com.taotao.model.User;
import com.taotao.sso.service.LoginService;
import com.taotao.sso.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;
import taotao.common.model.CallbackResult;
import taotao.common.utils.JsonUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-11-07
 * Time: 23:00
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Value("${REDIS_SESSION_KEY}")
    private String REDIS_SESSION_KEY;
    @Value("${SESSION_EXPIRE}")
    private int SESSION_EXPIRE;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JedisCluster jedisCluster;

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public CallbackResult login(String username, String password) {
        CallbackResult result = new CallbackResult();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        List<User> userList = userMapper.select(user);
        if (userList != null && !userList.isEmpty()) {
            Map<String, Object> map = new HashMap<>();
            map.put("userId", userList.get(0).getId());
            result.setStatus(200);
            String token = JwtUtils.createJwt(map, SESSION_EXPIRE);
            result.setData(token);
            user.setPassword(null);
            jedisCluster.set(REDIS_SESSION_KEY + ":" + token, JsonUtils.objToString(user));
            jedisCluster.expire(REDIS_SESSION_KEY + ":" + token, SESSION_EXPIRE);
        } else {
            result.setStatus(500);
            result.setMessage("用户名或密码错误");
        }
        return result;
    }
}
