package com.tingfeng.utils;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpClientProxyUtil {

    private static Logger logger = Logger.getLogger(HttpClientProxyUtil.class);

    /**
     * 使用HTTPClient进行POST请求
     * @param api_url 请求路径
     * @param param 请求格式有name1=value1&name2=value2、json、xml、map或其他形式，具体要看接收方的取值
     * @return
     */
    public static String sendGet(String api_url, String param) {
        logger.info("请求URL：" + api_url +"，参数：" + param);

        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.createDefault();   // 生成一个httpclient对象
            HttpGet httpGet = new HttpGet(api_url + "?" + param);
            // 连接超时时间，10秒, 传输超时时间，30秒，不设置超时的话，一旦服务器没有响应，等待时间N久(>24小时)。
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(30000).build();
            httpGet.setConfig(requestConfig);   // 设置请求器的配置

            HttpResponse response = httpClient.execute(httpGet);    //执行请求Http
            HttpEntity entity = response.getEntity();

            // 判断响应实体是否为空
            if (entity != null) {
                String content = EntityUtils.toString(entity, "UTF-8");
                logger.info("响应内容：" + content);
                return content;
            }
        } catch (ConnectionPoolTimeoutException e) {
            e.printStackTrace();
            logger.error("http get throw ConnectionPoolTimeoutException(等待超时)");
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
            logger.error("http get throw ConnectTimeoutException(联接超时)");
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            logger.error("http get throw SocketTimeoutException(通讯超时)");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("http get throw Exception");
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    /**
     * 使用HTTPClient进行POST请求
     * @param api_url 请求路径
     * @param param 请求格式有name1=value1&name2=value2、json、xml、map或其他形式，具体要看接收方的取值
     * @return
     */
    public static String sendPost(String api_url, String param) {
        logger.info("请求URL：" + api_url +"，参数：" + param);

        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.createDefault();   // 生成一个httpclient对象
            HttpPost httpPost = new HttpPost(api_url);
            // 得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
            StringEntity postEntity = new StringEntity(param, "UTF-8");
            httpPost.setEntity(postEntity);

            // 连接超时时间，10秒, 传输超时时间，30秒，4.3版本不设置超时的话，一旦服务器没有响应，等待时间N久(>24小时)。
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(30000).build();
            httpPost.setConfig(requestConfig);// 设置请求器的配置

            HttpResponse response = httpClient.execute(httpPost);   //执行请求Http
            HttpEntity entity = response.getEntity();

//          logger.info("执行请求：" + httpPost.getRequestLine());
//          logger.info("响应的所有HEADER内容：" + Arrays.toString(response.getAllHeaders()));

            // 判断响应实体是否为空
            if (entity != null) {
                String content = EntityUtils.toString(entity, "UTF-8");
                logger.info("响应内容：" + content);
                return content;
            }
        } catch (ConnectionPoolTimeoutException e) {
            e.printStackTrace();
            logger.error("http get throw ConnectionPoolTimeoutException(等待超时)");
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
            logger.error("http get throw ConnectTimeoutException(联接超时)");
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            logger.error("http get throw SocketTimeoutException(通讯超时)");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("http get throw Exception");
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

}
