package com.taotao.dao;

import com.taotao.model.ItemDesc;

import java.util.List;

public interface ItemDescMapper {
    int deleteByPrimaryKey(Long itemId);

    int insert(ItemDesc record);

    int insertSelective(ItemDesc record);

    ItemDesc selectByPrimaryKey(Long itemId);

    int updateByPrimaryKeySelective(ItemDesc record);

    int updateByPrimaryKeyWithBLOBs(ItemDesc record);

    int updateByPrimaryKey(ItemDesc record);

    int batchInsert(List<ItemDesc> descList);
}