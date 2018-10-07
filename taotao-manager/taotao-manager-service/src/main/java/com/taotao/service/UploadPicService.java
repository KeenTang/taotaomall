package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;
import taotao.common.model.UploadPicResult;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-06
 * Time: 19:07
 */
public interface UploadPicService {
    /**
     * 上传图片
     * @param multipartFile
     * @return
     */
    UploadPicResult uploadPic(MultipartFile multipartFile);
}
