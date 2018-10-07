package com.taotao.service.impl;

import com.taotao.dao.ItemCatMapper;
import com.taotao.model.ItemCat;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taotao.common.model.EasyUITreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-04
 * Time: 21:24
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public List<EasyUITreeNode> getItemCatList(Long parentId) {
        List<ItemCat> itemCatList = itemCatMapper.getItemCatList(parentId);
        List<EasyUITreeNode> treeNodes = new ArrayList<>(itemCatList.size());
        for (ItemCat itemCat : itemCatList) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(itemCat.getId());
            node.setState(itemCat.getIsParent() ? "closed" : "open");
            node.setText(itemCat.getName());
            treeNodes.add(node);
        }
        return treeNodes;
    }
}
