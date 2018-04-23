package com.tingfeng.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;

import java.io.IOException;

public class HttpProxy {

    /**
     * Http 请求(Json格式参数)
     *
     * @param requestUrl
     * @param requestJson
     * @param httpMethod
     * @return
     * @throws Exception
     */
    public static String httpRequest(String requestUrl, String requestJson, HttpMethod httpMethod) {

        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpResponse response;

            if (null == httpMethod) {
                throw new RuntimeException("Http Method should be Get, Post, Put");
            }

            if (HttpMethod.GET == httpMethod) {
                HttpGet httpGet = new HttpGet(requestUrl);
                response = httpClient.execute(httpGet);
            } else {
                HttpEntityEnclosingRequestBase requestBase = null;

                switch (httpMethod) {
                    case POST:
                        requestBase = new HttpPost(requestUrl);
                        break;
                    case PUT:
                        requestBase = new HttpPut(requestUrl);
                        break;
                }

                if (null != requestJson && !requestJson.trim().equals("")) {
                    StringEntity requestEntity = new StringEntity(requestJson, ContentType.APPLICATION_JSON);
                    if (requestBase != null) {
                        requestBase.setEntity(requestEntity);
                    }
                }

                response = httpClient.execute(requestBase);
            }

            HttpEntity httpEntity = response.getEntity();
            return EntityUtils.toString(httpEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
