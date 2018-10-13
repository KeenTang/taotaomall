package com.taotao.service.impl;

import com.taotao.dao.TestMapper;
import com.taotao.model.Test;
import com.taotao.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-13
 * Time: 22:13
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testMapper;
    @Override
    public Test getTest(String partNo) {
        return testMapper.select(partNo);
    }

    @Override
    public int save(Test test) {
        return testMapper.insert(test);
    }

    @Override
    public int save2(Test test) {
        return testMapper.insert2(test);
    }

    @Override
    public Integer getMaxNum(String partNo) {
        return testMapper.getMaxNum(partNo);
    }

    @Override
    public int updateNum(Test test) {
        return 0;
    }
}
