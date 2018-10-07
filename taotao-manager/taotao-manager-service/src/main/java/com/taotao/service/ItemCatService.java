package com.taotao.service;

import taotao.common.model.EasyUITreeNode;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-04
 * Time: 21:19
 */
public interface ItemCatService {
    /**
     * 查询商品类目tree
     * @param parentId
     * @return
     */
    List<EasyUITreeNode> getItemCatList(Long parentId);
}
