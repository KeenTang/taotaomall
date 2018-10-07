package com.taotao.service;

import com.github.pagehelper.PageInfo;
import com.taotao.model.Item;
import taotao.common.model.EasyUIPagedResult;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-04
 * Time: 12:15
 */
public interface ItemService {
    /**
     * 根据ID查询商品
     * @param id
     * @return
     */
    Item getItemById(Long id);

    /**
     * 商品分页查询
     * @param num
     * @param size
     * @return
     */
    EasyUIPagedResult getItemList(int num, int size);

    void createItem(Item item,String desc);
}
