package com.taotao.rest.service.impl;

import com.taotao.model.Test;
import com.taotao.rest.service.TestService;

import javax.inject.Named;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-21
 * Time: 17:56
 */

@Named
public class TestServiceImpl implements TestService {
    @Override
    public Test test(String id) {
        System.out.println("id===" + id);
        Test test=new Test();
        test.setPartNo("partno一");
        return test;
    }
}
