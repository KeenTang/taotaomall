package com.taotao.dao;

import com.taotao.model.Test;

public interface TestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Test record);

    int insertSelective(Test record);

    Test selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);

    Test select(String partNo);

    Integer getMaxNum(String partNo);

    int insert2(Test test);
}