package com.taotao.sso.service;

import org.apache.commons.lang3.StringUtils;
import taotao.common.model.CallbackResult;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-11-07
 * Time: 22:57
 */
public interface LoginService {
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    CallbackResult login(String username, String password);
}
