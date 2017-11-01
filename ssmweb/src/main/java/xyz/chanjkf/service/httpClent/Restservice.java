package xyz.chanjkf.service.httpClent;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import xyz.chanjkf.utils.DXPLog;
import xyz.chanjkf.utils.ExceptionType;
import xyz.chanjkf.utils.exception.RestResponseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by yi on 2017/9/20.
 */
public class Restservice {

    private static DXPLog logger = new DXPLog(RestfulService.class);

    private CloseableHttpClient httpClient;

    public Restservice(CloseableHttpClient client) {
        this.httpClient = client;
    }


    public String jsonGet(String url, final NameValuePair[] headerParams) throws IOException, RestResponseException {
        HttpGet get = new HttpGet(url);
        addHeaders(headerParams, get);
        CloseableHttpResponse response = httpClient.execute(get);
        return parseResponse(response, url);
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
