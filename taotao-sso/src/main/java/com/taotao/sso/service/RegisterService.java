package com.taotao.sso.service;

import com.taotao.model.User;
import taotao.common.model.CallbackResult;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-11-07
 * Time: 21:02
 */
public interface RegisterService {
    /**
     * 检查数据是否可用
     * @param param 校验的数据
     * @param type 可选参数1、2、3分别代表username、phone、email
     * @return
     */
    CallbackResult checkData(String param,int type);

    /**
     * 用户注册
     * @param user
     * @return
     */
    CallbackResult register(User user);
}
