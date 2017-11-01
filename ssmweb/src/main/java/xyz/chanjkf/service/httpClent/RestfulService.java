package xyz.chanjkf.service.httpClent;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;
import xyz.chanjkf.utils.exception.RestResponseException;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yi on 2017/9/20.
 */
@Service("RestfulService")
public class RestfulService implements IRestfulService{

    private Restservice restService;

    private final String  manage_server = "http://";

    private final String  manage_base_path = "10.84.1.235:8080/dmall";

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
        this.restService = new Restservice(client);
    }

    @Override
    public String get(String actionUrl, String userName) {
        return new RestfulProcesser() {

            @Override
            public String doProcess() throws IOException, RestResponseException {
                String result = restService.jsonGet(actionUrl, getBaseHeaders(HttpGet.METHOD_NAME, userName));
                return result;
            }
        }.process();
    }

    @Override
    public String post(final String actionUrl, final String jsonBody, final String userName) {
        return new RestfulProcesser() {
            @Override
            public String doProcess() throws IOException, RestResponseException {
                String result = restService.jsonPost(actionUrl, getBaseHeaders(HttpPost.METHOD_NAME,userName), jsonBody);
                return result;
            }

        }.process();
    }

    @Override
    public String put(String actionUrl, String jsonBody, String userName) {
        return new RestfulProcesser() {

            @Override
            public String doProcess() throws IOException,RestResponseException {
                String result = restService.jsonPut(actionUrl, getBaseHeaders(HttpPut.METHOD_NAME, userName), jsonBody);
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
                return restService.jsonDelete(requestUrl, getBaseHeaders(HttpDelete.METHOD_NAME, userName));
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

    @Override
    public String getRequestUrl(String actionUrl) {
        /* https://10.80.0.101:8443/api/v1/connections/ */
        return manage_server + manage_base_path + "/" + actionUrl;
    }
    private NameValuePair[] getBaseHeaders(String httpMethod, String userName) {
        List<NameValuePair> headerParams = new ArrayList<NameValuePair>();

        /* 获取用户名，server检测使用，暂时打桩 */
        String date = getUTMDateStr();
        headerParams.add(new BasicNameValuePair("X-Auth-token", userName));
        headerParams.add(new BasicNameValuePair(HttpHeaders.DATE, date));
        headerParams.add(new BasicNameValuePair(HttpHeaders.CONTENT_TYPE, "application/json"));
//        String signature = getAuthorization(httpMethod, userName, "application/json", date);
//        if (null != signature) {
//            headerParams.add(new BasicNameValuePair(HttpHeaders.AUTHORIZATION, signature));
//        }

        return headerParams.toArray(new NameValuePair[0]);
    }
    /**
     * 获取当前的UTM时间
     * @return
     */
    private String getUTMDateStr(){
        Calendar cd = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = sdf.format(cd.getTime());

        return date;
    }
}
