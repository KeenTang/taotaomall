package com.taotao.service;

import com.taotao.model.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-13
 * Time: 22:11
 */
public interface TestService {
    Test getTest(String partNo);
    int save(Test test);
    int save2(Test test);
    Integer getMaxNum(String partNo);
    int updateNum(Test test);
}
