package xyz.chanjkf.service.httpClent;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import xyz.chanjkf.utils.Log;
import xyz.chanjkf.utils.ExceptionType;
import xyz.chanjkf.utils.exception.RestResponseException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

/**
 * Created by yi on 2017/9/20.
 */
public class RestService {

    private static Log logger = new Log(RestService.class);

    private CloseableHttpClient httpClient;

    public RestService(CloseableHttpClient client) {
        this.httpClient = client;
    }


    public String jsonGet(String url, final NameValuePair[] headerParams) throws IOException, RestResponseException {
        HttpGet get = new HttpGet(url);
        addHeaders(headerParams, get);
        CloseableHttpResponse response = httpClient.execute(get);
        return parseResponse(response, url);
    }

    private static void sslClient(HttpClient httpClient) {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] xcs, String str) {

                }
                public void checkServerTrusted(X509Certificate[] xcs, String str) {

                }
            };
            ctx.init(null, new TrustManager[] { tm }, null);

            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = httpClient.getConnectionManager();
            SchemeRegistry registry = ccm.getSchemeRegistry();
            registry.register(new Scheme("https", 443, ssf));
        } catch (KeyManagementException ex) {
            throw new RuntimeException(ex);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String jsonPost(String url, final NameValuePair[] headerParams, String requestBody)
            throws IOException, RestResponseException {
        HttpPost post = new HttpPost(url);
        addHeaders(headerParams, post);

        /*detect操作RequestBody为null*/
        if (requestBody != null) {
            StringEntity input = new StringEntity(requestBody, ContentType.APPLICATION_JSON);
            post.setEntity(input);
        }

        CloseableHttpResponse response = httpClient.execute(post);
        return parseResponse(response, url);
    }

    public String jsonPut(String url, final NameValuePair[] headerParams, String requestBody)
            throws IOException, RestResponseException {
        HttpPut put = new HttpPut(url);
        addHeaders(headerParams, put);
        StringEntity input = new StringEntity(requestBody, ContentType.APPLICATION_JSON);
        put.setEntity(input);

        CloseableHttpResponse response = httpClient.execute(put);
        return parseResponse(response, url);
    }

    public String jsonDelete(String url, final NameValuePair[] headerParams) throws IOException, RestResponseException{
        HttpDelete delete = new HttpDelete(url);
        addHeaders(headerParams, delete);
        CloseableHttpResponse response = null;
        response= httpClient.execute(delete);
        return parseResponse(response, url);
    }




    private void addHeaders(final NameValuePair[] headerParams, HttpRequestBase requestBase) {
        if (headerParams != null) {
            for (int i = 0; i < headerParams.length; i++) {
                NameValuePair nameValuePair = headerParams[i];
                requestBase.addHeader(nameValuePair.getName(), nameValuePair.getValue());
            }
        }
        addJsonHeader(requestBase);
    }
    private void addJsonHeader(HttpRequestBase method) {
        if (!method.containsHeader("Content-Type")) {
            method.addHeader("Content-Type", "application/json;charset=utf-8");
        }
        method.addHeader("accept", "application/json,text/plain,text/html");
    }
    private String parseResponse(CloseableHttpResponse response, String url) throws IOException, RestResponseException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            logger.debug("REQUEST-URL: " + url + "; RESPONSE: " +  response.toString() + "; result:" + sb + "}");
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                logger.info("Failed to get rest response, http error code = " + statusCode);
            }
            return sb.toString();
        } catch (Exception e) {
            logger.error("Failed to get rest response", e);
            throw new RestResponseException(ExceptionType.ERROR_HTTP_PARSERESPONSE);
        } finally {
            if(br != null) {
                br.close();
            }
            response.close();
        }
    }

}
