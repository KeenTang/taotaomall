package com.taotao.service.impl;

import com.taotao.dao.ItemMapper;
import com.taotao.model.Item;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-04
 * Time: 12:19
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public Item getItemById(Long id) {
        return itemMapper.selectByPrimaryKey(id);
    }
}
