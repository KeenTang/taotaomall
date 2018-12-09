package com.taotao.sso.controller;

import com.taotao.sso.service.LoginService;
import com.taotao.sso.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import taotao.common.model.CallbackResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-11-07
 * Time: 23:46
 */
@Controller
@CrossOrigin(origins = "*", allowCredentials = "true")
public class LoginController {
    @Value("${SESSION_EXPIRE}")
    private int SESSION_EXPIRE;
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CallbackResult login(@RequestBody String username, @RequestBody String password,
                                HttpServletResponse response, HttpServletRequest request) {
        CallbackResult callbackResult = loginService.login(username, password);
        if (callbackResult.getStatus() == 200) {
            CookieUtils.setCookie(request, response, "TT_TOKEN", (String) callbackResult.getData(), SESSION_EXPIRE);
        }
        return callbackResult;
    }
}
