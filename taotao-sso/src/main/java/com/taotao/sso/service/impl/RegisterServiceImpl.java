package com.taotao.sso.service.impl;

import com.taotao.dao.UserMapper;
import com.taotao.model.User;
import com.taotao.sso.service.RegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taotao.common.model.CallbackResult;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-11-07
 * Time: 21:04
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 检查数据是否可用
     *
     * @param param 校验的数据
     * @param type  可选参数1、2、3分别代表username、phone、email
     * @return
     */
    @Override
    public CallbackResult checkData(String param, int type) {
        User user = new User();
        if (type == 1) {
            user.setUsername(param);
        } else if (type == 2) {
            user.setPhone(param);
        } else if (type == 3) {
            user.setEmail(param);
        } else {
            return new CallbackResult(400, "错误的参数");
        }
        CallbackResult result = new CallbackResult();
        result.setStatus(200);
        List<User> userList = userMapper.select(user);
        result.setMessage(String.valueOf(userList == null || userList.isEmpty()));
        return result;
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @Override
    public CallbackResult register(User user) {
        CallbackResult result = new CallbackResult();
        if(StringUtils.isEmpty(user.getUsername())){
            result.setStatus(500);
            result.setMessage("用户名不能为空");
        }
        return new CallbackResult(200, String.valueOf(userMapper.insert(user) > 0 ? "注册成功!" : "注册失败!"));
    }
}
