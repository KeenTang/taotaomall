package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import taotao.common.model.UploadPicException;
import taotao.common.utils.FastDFSClient;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-04
 * Time: 16:49
 */
@Controller
public class PageController {
    @RequestMapping("/")
    public String index() {
        /*
        try {
            ClientGlobal.initByProperties("properties/fastdfs-client.properties");
            System.out.println("ClientGlobal.configInfo(): " + ClientGlobal.configInfo());
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer = null;
            StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);
            String s = storageClient.upload_file1("C:\\Users\\keen\\Desktop\\TIM图片20180902201421.png", "png", null);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        try {
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:properties/fastdfs-client.properties");
            String path = fastDFSClient.uploadFile("C:\\Users\\keen\\Desktop\\TIM图片20180902201421.png", null);
            System.out.println(path);
        } catch (UploadPicException e) {
            e.printStackTrace();
        }
*/
        return "index";
    }

    @RequestMapping("/{page}")
    public String page(@PathVariable("page") String page) {
        return page;
    }
}
