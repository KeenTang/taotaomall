package com.taotao.controller;

import com.taotao.service.UploadPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import taotao.common.model.UploadPicResult;
import taotao.common.utils.JsonUtils;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-06
 * Time: 19:53
 */
@Controller
public class UploadPicController {
    @Autowired
    private UploadPicService uploadPicService;


    @RequestMapping("/pic/upload")
    @ResponseBody
    public String upload( MultipartFile uploadFile){
        UploadPicResult result = uploadPicService.uploadPic(uploadFile);
        String s = JsonUtils.objToString(result);
        UploadPicResult result1 = JsonUtils.stringToObj(s, UploadPicResult.class);
        System.out.println(result1);
        return s;
    }
}
