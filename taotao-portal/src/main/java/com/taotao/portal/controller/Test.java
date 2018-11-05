package com.taotao.portal.controller;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-24
 * Time: 21:51
 */
public class Test {
    public static void main(String[] args) throws IOException {
        System.setProperty("http.proxyHost", "localhost");
        System.setProperty("http.proxyPort", "8888");
        System.setProperty("https.proxyHost", "localhost");
        System.setProperty("https.proxyPort", "8888");
        //testHttpGet();
        testHttpPost();
    }

    public static void testHttpGet() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8081/rest/services/itemCat/getItemCat");
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            System.out.println(EntityUtils.toString(entity));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void testHttpPost() throws IOException {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8081/rest/services/testService/testPost");
        /*
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        NameValuePair namePair = new BasicNameValuePair("name", "张三");
        NameValuePair passwordPair = new BasicNameValuePair("password", "123");
        nameValuePairs.add(namePair);
        nameValuePairs.add(passwordPair);*/
        StringEntity stringEntity;
        try {
            stringEntity = new StringEntity("{\"name\":\"abc\"}");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }
        httpPost.setEntity(stringEntity);
        httpPost.addHeader("content-type", "application/json;charset=utf-8");
        CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        System.out.println(EntityUtils.toString(entity));
    }
}
