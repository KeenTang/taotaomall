package com.taotao.service.impl;

import com.taotao.service.UploadPicService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import taotao.common.model.UploadPicException;
import taotao.common.model.UploadPicResult;
import taotao.common.utils.FastDFSClient;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-06
 * Time: 19:08
 */
@Service
public class UploadPicServiceImpl implements UploadPicService {
    //private sta

    /**
     * 图片的服务器路径
     */
    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    /**
     * 上传图片
     *
     * @param multipartFile
     * @return
     */
    @Override
    public UploadPicResult uploadPic(MultipartFile multipartFile) {
        UploadPicResult result = new UploadPicResult();
        if (multipartFile.isEmpty()) {
            result.setError(1);
            result.setMessage("图片为空");
        } else {
            String ext = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
            try {
                FastDFSClient fastDFSClient = new FastDFSClient("classpath:properties/fastdfs-client.properties");
                String url = fastDFSClient.uploadFile(multipartFile.getBytes(), ext, null);
                result.setError(0);
                result.setMessage("Success");
                result.setUrl(IMAGE_SERVER_URL + url);
            } catch (IOException | UploadPicException e) {
                result.setError(1);
                result.setMessage(e.getMessage());
            }
        }
        return result;
    }
}
