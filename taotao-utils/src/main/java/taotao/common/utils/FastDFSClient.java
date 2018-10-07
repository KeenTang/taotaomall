package taotao.common.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import taotao.common.model.UploadPicException;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-06
 * Time: 16:35
 */
public class FastDFSClient {

    private final static Logger log = LoggerFactory.getLogger(FastDFSClient.class);

    private StorageClient1 storageClient1;
    private TrackerServer trackerServer;
    private TrackerClient trackerClient;
    private StorageServer storageServer;

    public FastDFSClient(String configPath) throws UploadPicException {
        this.init(configPath);
    }

    private void init(String configPath) throws UploadPicException {
        if (StringUtils.isEmpty(configPath)) {
            throw new IllegalArgumentException("configPath");
        }
        if (configPath.startsWith("classpath:")) {
            configPath = this.getClass().getResource("/").getPath() + configPath.substring(10);
            System.out.println(configPath);
        }
        try {
            if (configPath.endsWith(".properties")) {
                ClientGlobal.initByProperties(configPath);
            } else {
                ClientGlobal.init(configPath);
            }
            trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
            trackerServer = trackerClient.getConnection();
            storageServer = trackerClient.getStoreStorage(trackerServer);
            storageClient1 = new StorageClient1(trackerServer, storageServer);
        } catch (IOException | MyException e) {
            log.error("初始化FastDFS配置信息失败", e);
            throw new UploadPicException(e.getMessage());
        }
    }

    /**
     * 上传图片
     *
     * @param fileName
     * @param metaList
     * @return
     * @throws UploadPicException
     */
    public String uploadFile(String fileName, NameValuePair[] metaList) throws UploadPicException {
        return this.uploadFile(new File(fileName), metaList);
    }

    /**
     * 上传图片
     *
     * @param file
     * @param metaList
     * @return
     * @throws UploadPicException
     */
    public String uploadFile(File file, NameValuePair[] metaList) throws UploadPicException {
        try {
            byte[] buff = IOUtils.toByteArray(new FileInputStream(file));
            String fileName = file.getName();
            return this.uploadFile(buff, fileName.substring(fileName.lastIndexOf(".") + 1), metaList);
        } catch (IOException e) {
            log.error("上传图片失败", e);
            throw new UploadPicException(e.getMessage());
        }

    }

    /**
     * 上传图片
     *
     * @param fileContent
     * @param extension
     * @return
     * @throws UploadPicException
     */
    public String uploadFile(byte[] fileContent, String extension, NameValuePair[] metaList) throws UploadPicException {
        try {
            return storageClient1.upload_file1(fileContent, extension, metaList);
        } catch (IOException | MyException e) {
            log.error("上传图片失败", e);
            throw new UploadPicException(e.getMessage());
        }
    }
}

