package com.taotao.rest.service.impl;

import com.taotao.dao.ItemCatMapper;
import com.taotao.model.ItemCat;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.ItemCatResult;
import com.taotao.rest.service.ItemCatService;
import org.apache.cxf.rs.security.cors.CorsHeaderConstants;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-23
 * Time: 22:47
 */
@Named
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public ItemCatResult getItemCat() {
        ItemCatResult result = new ItemCatResult();
        result.setData(this.getCatNodeList(0L));
        return result;
    }

    private List<CatNode> getCatNodeList(Long pid) {
        List resultList = new ArrayList<>();
        List<ItemCat> itemCatList = itemCatMapper.getItemCatList(pid);
        for (ItemCat itemCat : itemCatList) {
            CatNode node = new CatNode();
            if (itemCat.getIsParent()) {
                node.setUrl("/products/" + itemCat.getId() + ".html");
                if (pid == 0) {
                    node.setName("<a href='/products/'" + itemCat.getId() + ".html>" +
                            itemCat.getName() + "</a>");
                } else {
                    node.setName(itemCat.getName());
                }
                node.setList(this.getCatNodeList(itemCat.getId()));
                resultList.add(node);
            } else {
                String itemName = "/products/" + itemCat.getId() + ".html|" + itemCat.getName();
                resultList.add(itemName);
            }
        }
        return resultList;
    }
}
