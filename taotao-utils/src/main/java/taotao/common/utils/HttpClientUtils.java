package taotao.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-26
 * Time: 21:37
 */
public final class HttpClientUtils {
    public HttpEntity doPost(String url, Map<String, String> nameValueMap, String charset) throws IOException {
        CloseableHttpClient closeableHttpClient = null;
        CloseableHttpResponse response = null;
        try {
            closeableHttpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost();
            List<NameValuePair> nameValuePairs = new ArrayList<>(nameValueMap.size());
            for (Map.Entry<String, String> entry : nameValueMap.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            StringEntity entity = new UrlEncodedFormEntity(nameValuePairs, charset);
            httpPost.setEntity(entity);
            response = closeableHttpClient.execute(httpPost);
            return response.getEntity();
        } finally {
            if (response != null) {
                response.close();
            }
            if (closeableHttpClient != null) {
                closeableHttpClient.close();
            }
        }
    }
}
