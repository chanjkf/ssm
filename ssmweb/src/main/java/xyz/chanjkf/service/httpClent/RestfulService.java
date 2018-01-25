package xyz.chanjkf.service.httpClent;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ssl.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import xyz.chanjkf.utils.exception.RestResponseException;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * Created by yi on 2017/9/20.
 */
@Service("RestfulService")
public class RestfulService implements IRestfulService{

    private RestService restService;

    private int maxConnPerHost = 20;

    private int maxTotalConn = 20;
    /* 数据读取超时时间 */
    private int soTimeout = 30000;

    /* http连接超时时间 */
    private int connectionTimeout = 10000;

    /* 连接管理器超时时间 */
    private int connectionManagerTimeout = 10000;

    @PostConstruct
    public void initProperties() {
        CloseableHttpClient client = createHttpClient();
        this.restService = new RestService(client);
    }

    @Override
    public String getIpMsg(String ip) {

        String url = "https://dm-81.data.aliyun.com/rest/160601/ip/getIpInfo.json";
        String appcode = "b3ebe78c03fd46adabe9403ca2bf73ff";
        return get(url+"?ip="+ip, appcode);
    }

    @Override
    public String get(String actionUrl, String appCode) {
        return new RestfulProcesser() {

            @Override
            public String doProcess() throws IOException, RestResponseException {
                String result = restService.jsonGet(actionUrl, getBaseHeaders(appCode));
                return result;
            }
        }.process();
    }

    @Override
    public String post(final String actionUrl, final String jsonBody, final String userName) {
        return new RestfulProcesser() {
            @Override
            public String doProcess() throws IOException, RestResponseException {
                String result = restService.jsonPost(actionUrl, getBaseHeaders(userName), jsonBody);
                return result;
            }

        }.process();
    }

    @Override
    public String put(String actionUrl, String jsonBody, String userName) {
        return new RestfulProcesser() {

            @Override
            public String doProcess() throws IOException,RestResponseException {
                String result = restService.jsonPut(actionUrl, getBaseHeaders(userName), jsonBody);
                return result;
            }

        }.process();
    }

    @Override
    public String delete(String actionUrl, String resourceNo, String userName) {
        return new RestfulProcesser() {

            @Override
            public String doProcess() throws IOException, RestResponseException {
                String requestUrl = StringUtils.isEmpty(resourceNo) ? actionUrl : actionUrl + "/" + resourceNo;
                return restService.jsonDelete(requestUrl, getBaseHeaders(userName));
            }

        }.process();
    }





    private CloseableHttpClient createHttpClient() {
        CloseableHttpClient httpClient = null;
//        try {
//            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            ConnectionConfig cConfig = ConnectionConfig.custom().setCharset(Charset.forName("utf-8")).build();
            SocketConfig config = SocketConfig.custom().setSoTimeout(soTimeout).build();
            RequestConfig defaultRequestConfig = RequestConfig.custom()
                    .setCookieSpec(CookieSpecs.DEFAULT)
                    .setExpectContinueEnabled(true)
                    .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                    .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
                    .setConnectionRequestTimeout(connectionManagerTimeout)
                    .setConnectTimeout(connectionTimeout)
                    .setSocketTimeout(soTimeout).build();
            httpClient = HttpClientBuilder.create().setMaxConnPerRoute(maxConnPerHost)
                    .setSSLHostnameVerifier(new NoopHostnameVerifier())
                    .setDefaultRequestConfig(RequestConfig.copy(defaultRequestConfig).build())
                    .setDefaultConnectionConfig(cConfig).setDefaultSocketConfig(config).build();


//        } catch (KeyStoreException e) {
//            e.printStackTrace();
//        }
        return httpClient;
    }

    private NameValuePair[] getBaseHeaders(String appCode) {
        List<NameValuePair> headerParams = new ArrayList<NameValuePair>();

        /* 获取用户名，server检测使用，暂时打桩 */
        headerParams.add(new BasicNameValuePair("Authorization", "APPCODE " + appCode));
        return headerParams.toArray(new NameValuePair[0]);
    }
}
