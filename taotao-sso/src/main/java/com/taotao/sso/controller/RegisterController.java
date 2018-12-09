package com.taotao.sso.controller;

import com.taotao.model.User;
import com.taotao.sso.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import taotao.common.model.CallbackResult;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-11-07
 * Time: 21:31
 */
@Controller
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowCredentials = "true",
        methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.PUT})
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public CallbackResult checkData(@PathVariable String param, @PathVariable int type) {
        return registerService.checkData(param, type);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CallbackResult register(@RequestBody User user) {
       return registerService.register(user);
    }
}
