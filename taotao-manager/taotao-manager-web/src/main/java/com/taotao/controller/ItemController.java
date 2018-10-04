package com.taotao.controller;

import com.taotao.model.Item;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-04
 * Time: 12:24
 */
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("item/{itemId}")
    @ResponseBody
    public Item getItemById(@PathVariable("itemId") Long id){
        return itemService.getItemById(id);
    }
}
