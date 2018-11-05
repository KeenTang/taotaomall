package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.dao.ItemDescMapper;
import com.taotao.dao.ItemMapper;
import com.taotao.model.Item;
import com.taotao.model.ItemDesc;
import com.taotao.service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taotao.common.model.EasyUIPagedResult;
import taotao.common.utils.IDUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private ItemDescMapper itemDescMapper;

    @Override
    public Item getItemById(Long id) {
        return itemMapper.selectByPrimaryKey(id);
    }

    /**
     * 商品分页查询
     *
     * @param num
     * @param size
     * @return
     */
    @Override
    public EasyUIPagedResult getItemList(int num, int size) {
        //设置分页
        PageHelper.startPage(num, size);
        //执行查询获取数据
        List<Item> items = itemMapper.selectAll();
        //获取分页结果
        PageInfo<Item> pageInfo = new PageInfo<>(items);
        EasyUIPagedResult pagedResult = new EasyUIPagedResult();
        pagedResult.setTotal(pageInfo.getTotal());
        pagedResult.setRows(pageInfo.getList());
        return pagedResult;
    }

    @Override
    public void createItem(Item item, String desc) {
        List<Item> list = new ArrayList(500);
        List<ItemDesc> descList = new ArrayList<>(list.size());
        for (int i = 0; i < 500; i++) {
            item.setId(IDUtils.getItemID());
            item.setStatus(1);
            item.setCreated(new Date());
            item.setUpdated(item.getCreated());
            list.add(item);
        }
        long st = System.currentTimeMillis();
        int count = itemMapper.batchInsert(list);
        long et = System.currentTimeMillis();
        long t1 = (et - st);
        ItemDesc itemDesc = new ItemDesc();
        for (int i = 0; i < 500; i++) {
            itemDesc.setItemId(item.getId());
            itemDesc.setItemDesc(desc);
            BeanUtils.copyProperties(item, itemDesc);
            descList.add(itemDesc);
        }
        st = System.currentTimeMillis();
        int count2 = itemDescMapper.batchInsert(descList);
        et = System.currentTimeMillis();
        System.out.println("time1==" + t1 + "count==" + count);
        System.out.println("time2==" + (et - st) + "count==" + count2);
    }

}
