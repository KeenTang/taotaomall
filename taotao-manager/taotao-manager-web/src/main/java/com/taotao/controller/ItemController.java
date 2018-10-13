package com.taotao.controller;

import com.taotao.model.Item;
import com.taotao.model.Test;
import com.taotao.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import taotao.common.model.CallbackResult;
import taotao.common.model.EasyUIPagedResult;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-04
 * Time: 12:24
 */
@Controller
@RequestMapping("/item")
public class ItemController {
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
    @Autowired
    private ItemService itemService;



    @RequestMapping("/{itemId}")
    @ResponseBody
    public Item getItemById(@PathVariable("itemId") Long id) {
        return itemService.getItemById(id);
    }

    @RequestMapping("/list")
    @ResponseBody
    public EasyUIPagedResult list(int page, int rows) {
        logger.info("日志打印");
        EasyUIPagedResult pagedResult = itemService.getItemList(page, rows);
        System.out.println("total==" + pagedResult.getRows());
        System.out.println("rows==" + pagedResult.getRows());
        return pagedResult;
    }

    @RequestMapping("/save")
    @ResponseBody
    public CallbackResult createItem(Item item,String desc){
        CallbackResult result=new CallbackResult();
        itemService.createItem(item,desc);
        result.setStatus(200);
        return result;
    }

}
