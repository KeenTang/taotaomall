package com.taotao.controller;

import com.taotao.model.Test;
import com.taotao.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-13
 * Time: 22:23
 */
@Controller
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping("/test")
    public ResponseEntity test() {
        for (int i = 0; i < 3; i++) {
            if (save()) {
                break;
            } else {
                System.out.println("第" + i + "次执行失败");
            }
        }
        return new ResponseEntity("Success", HttpStatus.OK);
    }

    private boolean save() {
        System.out.println("test");
        String partNo = "partno1";
        Test test;
        int i;
        //synchronized (TestController.class) {
        Integer num = testService.getMaxNum(partNo);
        System.out.println("num==" + String.valueOf(num));
        if (num == null || num.intValue() == 0) {
            test = new Test();
            test.setNum(1);
            test.setVersion(1);
            test.setPartNo(partNo);
            i = testService.save(test);
        } else {
            //test = testService.getTest(partNo);
            test = new Test();
            test.setNum(num);
            test.setVersion(1);
            test.setPartNo(partNo);
            i = testService.save2(test);
            if (i == 0) {
                System.out.println("执行失败");
            } else {
                System.out.println("执行成功");
            }
            //test.setVersion();
        }
        return i > 0;
        //}
    }
}
