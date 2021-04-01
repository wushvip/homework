package com.chinamobile.cmss.bcse.sdk.util;
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.chinamobile.cmss.bcse.sdk.constant.Constant;
import com.chinamobile.cmss.bcse.sdk.tools.HMACSHA1;
import com.chinamobile.cmss.bcse.sdk.tools.MD5;
import com.chinamobile.cmss.bcse.sdk.tools.Md5Util;



/** 
 * @ClassName: HttpClientFactory 
 * @Description: TODO
 * @author: chenmin
 * @date: 2016年4月13日 下午1:54:13  
 */
public class HttpClientFactory {
	
    private static final Logger LOG = LoggerFactory
            .getLogger(HttpClientFactory.class);
    
    private static final int INIT_DELAY = 5 * 1000;
    private static final int CHECK_INTERVAL = 5 * 60 * 1000;
    
    private int lastConnections = 50;
    //默认请求超时时间：10秒
    private int timeout = (30 * 1000);
    //默认连接超时时间：5秒
    private int connectTimeout = (5 * 1000);
    private boolean gzip = false;

    //java sdk version
    private String version = "v2.1.3";

    private HttpClient httpClient;
    private ScheduledExecutorService scheduledExeService;
    public PoolingHttpClientConnectionManager connectionManager;

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public HttpClientFactory(int timeout, int connectTimeout, int connections) {
        if (timeout <= 0) {
            timeout = this.timeout;
        }

        if (connectTimeout <= 0) {
            connectTimeout = this.connectTimeout;
        }

        if(connections > 0 && lastConnections != connections ){
            lastConnections = connections;
        }
        
        connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(lastConnections);
        connectionManager.setDefaultMaxPerRoute(lastConnections);
        scheduledExeService = Executors.newScheduledThreadPool(1,
                new DaemonThreadFactory("Http-client-ConenctionPool-Monitor"));
        scheduledExeService.scheduleAtFixedRate(new IdleConnectionMonitor(connectionManager),
                INIT_DELAY, CHECK_INTERVAL,
                TimeUnit.MILLISECONDS);
        
        SocketConfig socketConfig = SocketConfig.custom()
                .setSoKeepAlive(true)
                .build();
        connectionManager.setDefaultSocketConfig(socketConfig);
        this.httpClient=HttpClients.custom().setConnectionManager(connectionManager).build();
    }

    public int getMaxConnections() {
        return this.lastConnections;
    }

    /**
     * 设置gzip传输方式
     */
    public void setGzip() {
        this.gzip = true;
    }
   
    /**
     * 用户认证
     * @return String
     */
    public String getAuthorization (String url,String type,Map<String, Object> opts,String Date){
    	String Authorization  = "";
    	String AccessKeyId = (String)opts.get("userId");
    	String SecretAccessKey = Constant.secretAccessKey;
    	String HTTP_Verb = type;
    	String Content_MD5 = "";
    	String Content_Type = "application/json;charset=UTF-8";
    	String CanonicalizedResource = url;
    	String CanonicalizedHeaders ="";
    	//CanonicalizedResource
    	/*if("GET".equals(type)||"DELETE".equals(type)){
    		if(url.contains("?")){
    			CanonicalizedResource = url.substring(0, url.indexOf("?"));
    			
    		}else {
    			CanonicalizedResource = url;
    		}
    	} else if("POST".equals(type)||"PUT".equals(type)){
    		CanonicalizedResource = url;
    	}*/
    	//Content-MD5：
    	Content_MD5 = getContent_MD5(opts);
    	//System.out.println("Content_MD5:"+Content_MD5);
    	//String StringToSign = HMACSHA1.getStringToSign(HTTP_Verb, "", Content_Type, Date, "", "user");
    	//String StringToSign = HMACSHA1.getStringToSign(HTTP_Verb, Content_MD5, Content_Type, Date, "", "user");
    	String StringToSign = HMACSHA1.getStringToSign(HTTP_Verb, Content_MD5, Content_Type, Date, "", "");
    	String Signature=HMACSHA1.getSignature(SecretAccessKey,StringToSign);
    	Authorization = "SE" + " " + AccessKeyId + ":" + Signature;
    	return Authorization;
    }
    /**
     * 对map排序
     */
    public List<Map.Entry<String, Object>> sortForMap(Map map){
    	List<Map.Entry<String, Object>> list = new ArrayList<Map.Entry<String, Object>>(map.entrySet());
    		Collections.sort(list, new Comparator<Map.Entry<String, Object>>()  
  	      {   
  	          public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2)  
  	          {  
  	           if(o1.getKey()!=null&&o2.getKey()!=null&&o1.getKey().compareTo(o2.getKey())>0){  
  	            return 1;  
  	           }else{  
  	            return -1;  
  	           }       
  	          }  
  	      }); 

    	return list;
    	
    }
    
    /**
     * 获取Content-MD5
     * @param opts
     * @return Content-MD5
     */
    public String getContent_MD5(Map<String, Object> opts){
    	if(opts==null || opts.isEmpty()){
    		return "";
    	}
    	JSONObject paramJson = new JSONObject();
    	LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
    	//StringBuilder sb = new StringBuilder();
		List<Map.Entry<String, Object>> list = sortForMap(opts);
		for(Map.Entry<String, Object> entry:list){
			/*String key = null;
    		String value = null;
			key = MD5.getMD5(entry.getKey());
			value = MD5.getMD5(String.valueOf(entry.getValue())); 
			sb.append("&").append(key).append("=").append(value);*/
			map.put(entry.getKey(), entry.getValue());
		}
		paramJson = JSONObject.parseObject(JSON.toJSONString(map),Feature.OrderedField);
    	System.out.println("paramJson:"+String.valueOf(paramJson).replaceAll("\"", ""));
    	
		return Md5Util.encrypt(String.valueOf(paramJson).replaceAll("\"", ""));
    }
    
    
    /**
     * 文件上传
     * @Title: doUpload 
     * @Description: TODO
     * @param reqURL
     * @param opts
     * @param encoding
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     * @return: String
     */
    public String doUpload(String reqURL, Map<String, Object> opts,
            String encoding,MultipartFile file) throws ClientProtocolException, IOException {
        String responseContent = "";
        //String Date=String.valueOf(System.currentTimeMillis());
        //String authorization = getAuthorization(reqURL,"POST",opts,Date);
        HttpPost httpPost = buildHttpPostRequest(reqURL, opts, encoding);
        //httpPost.setHeader("Content-Type", "multipart/form-data");
        httpPost.setHeader("accept", "application/json");
        //httpPost.setHeader("SE-Date", Date);
        //httpPost.setHeader("Authorization", authorization);
        if (this.gzip == true) {
            httpPost.setHeader("Accept-Encoding", "gzip");
        }
        System.out.println("POSTURL:"+reqURL);
        HttpEntity reqEntity = MultipartEntityBuilder.create()
        		.addBinaryBody("file",file.getBytes())
        		.addTextBody("appId", "f1067d44a8524419bca57f9ba1ec46e0") 
        		.addTextBody("userId","admin")
        		.build();
        String fileName="D:/cmcc_project/BCSE产品/1.3.0开发/备份/stop.txt";
        FileEntity fileEntity=new FileEntity(new File(fileName));
        
        httpPost.setEntity(reqEntity);
        httpPost.setEntity(fileEntity);
        HttpResponse response = httpClient.execute(httpPost);

        validateResponse(response, httpPost);
        
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            Header contentEncoding = entity.getContentEncoding();
            if (contentEncoding != null
                    && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
                GzipDecompressingEntity gzipEntity = new GzipDecompressingEntity(
                    entity);
                responseContent = EntityUtils.toString(gzipEntity, encoding);
            } else {
                responseContent = EntityUtils.toString(entity, encoding);
            }
            EntityUtils.consume(entity);
        } else {
            LOG.warn("Http entity is null! request url is {},response status is {}", reqURL, response.getStatusLine());
        }
        return responseContent;

    }

    
    /**
     * 通过POST的方法向服务器发送请求。
     *
     * @param reqURL 请求的url地址。
     * @param opts 请求的参数列表。
     * @param encoding 请求的编码方式。
     * @return 返回response结果。
     * @throws IOException
     * @throws ClientProtocolException
     * 
     */
    public String doPost(String reqURL, Map<String, Object> opts,
            String encoding) throws ClientProtocolException, IOException {
        String responseContent = "";
        String Date=String.valueOf(System.currentTimeMillis());
        String authorization = getAuthorization(reqURL,"POST",opts,Date);
        HttpPost httpPost = buildHttpPostRequest(reqURL, opts, encoding);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("accept", "application/json");
        httpPost.setHeader("SE-Date", Date);
        httpPost.setHeader("Authorization", authorization);
        if (this.gzip == true) {
            httpPost.setHeader("Accept-Encoding", "gzip");
        }
        System.out.println("POSTURL:"+reqURL);
        HttpResponse response = httpClient.execute(httpPost);

        validateResponse(response, httpPost);
        
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            Header contentEncoding = entity.getContentEncoding();
            if (contentEncoding != null
                    && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
                GzipDecompressingEntity gzipEntity = new GzipDecompressingEntity(
                    entity);
                responseContent = EntityUtils.toString(gzipEntity, encoding);
            } else {
                responseContent = EntityUtils.toString(entity, encoding);
            }
            EntityUtils.consume(entity);
        } else {
            LOG.warn("Http entity is null! request url is {},response status is {}", reqURL, response.getStatusLine());
        }
        return responseContent;

    }

    
    /**
     * 通过PUT的方法向服务器发送请求。
     *
     * @param reqURL 请求的url地址。
     * @param opts 请求的参数列表。
     * @param encoding 请求的编码方式。
     * @return 返回response结果。
     * @throws IOException
     * @throws ClientProtocolException
     * 
     */
    public String doPut(String reqURL, Map<String, Object> opts,
            String encoding) throws ClientProtocolException, IOException {
        String responseContent = "";
        String Date=String.valueOf(System.currentTimeMillis());
        String authorization = getAuthorization(reqURL,"PUT",opts,Date);
        HttpPut httpPut = buildHttpPutRequest(reqURL, opts, encoding);
        httpPut.setHeader("Content-Type", "application/json");
        httpPut.setHeader("accept", "application/json");
        httpPut.setHeader("SE-Date", Date);
        httpPut.setHeader("Authorization", authorization);
        if (this.gzip == true) {
        	httpPut.setHeader("Accept-Encoding", "gzip");
        }
        System.out.println("PUTURL:"+reqURL);
        HttpResponse response = httpClient.execute(httpPut);

        validateResponse(response, httpPut);
        
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            Header contentEncoding = entity.getContentEncoding();
            if (contentEncoding != null
                    && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
                GzipDecompressingEntity gzipEntity = new GzipDecompressingEntity(
                    entity);
                responseContent = EntityUtils.toString(gzipEntity, encoding);
            } else {
                responseContent = EntityUtils.toString(entity, encoding);
            }
            EntityUtils.consume(entity);
        } else {
            LOG.warn("Http entity is null! request url is {},response status is {}", reqURL, response.getStatusLine());
        }
        return responseContent;

    }
    /**
     * 通过GET的方式向服务器发出请求。
     *
     * @param url 要请求的url。
     * @param encoding 指定的编码格式。
     * @return 获取response返回的结果。
     * @throws IOException
     * @throws ClientProtocolException
     */
    public String doGet(String url, String encoding, boolean isPB,Map<String, Object> opts) throws ClientProtocolException, IOException {
        String result = "";
        String Date=String.valueOf(System.currentTimeMillis());
        String authorization = getAuthorization(url,"GET",opts,Date);
        //System.out.println("Authorization"+authorization);
        HttpGet httpget = new HttpGet(url);
        httpget.setHeader("User-Agent", "opensearch/java sdk " + version);
        httpget.setHeader("SE-Date", Date);
        httpget.setHeader("Authorization", authorization);
        httpget.setHeader("Content-Type", "application/json");
        httpget.setHeader("accept", "application/json");
        if (this.gzip == true) {
            httpget.setHeader("Accept-Encoding", "gzip");
        }
        //System.out.println("GETURL:"+url);
        HttpResponse response = httpClient.execute(httpget);
        validateResponse(response, httpget);
        HttpEntity entity = response.getEntity();
        InputStream is;
        if (null != entity) {

            Header header = entity.getContentEncoding();

            if (header != null && header.getValue().equalsIgnoreCase("gzip")) {
                GzipDecompressingEntity gzipEntity = new GzipDecompressingEntity(
                        entity);
                is = gzipEntity.getContent();
            } else {
                is = entity.getContent();
            }

            if(isPB){
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bytes = new byte[1024];
                int len = 0;
                while(( len= is.read(bytes)) != -1){
                    byteArrayOutputStream.write(bytes, 0, len);
                }
                byte[] temp = byteArrayOutputStream.toByteArray();
                result = new String(temp,"ISO8859-1");
            }else{
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] data = new byte[4096];
                int count = -1;
                while ((count = is.read(data, 0, 4096)) != -1)
                    outStream.write(data, 0, count);
                data = null;
                return new String(outStream.toByteArray(), encoding);
            }

        }

        return result;
    }

    /**
     * 通过DELETE的方式向服务器发出请求。
     *
     * @param url 要请求的url。
     * @param encoding 指定的编码格式。
     * @return 获取response返回的结果。
     * @throws IOException
     * @throws ClientProtocolException
     */
    public String doDelete(String url, String encoding, boolean isPB,Map<String, Object> opts) throws ClientProtocolException, IOException {
        String result = "";
        String Date=String.valueOf(System.currentTimeMillis());
        String authorization = getAuthorization(url,"DELETE",opts,Date);
        HttpDelete httpDelete = new HttpDelete(url);
        httpDelete.setHeader("SE-Date", Date);
        httpDelete.setHeader("Authorization", authorization);
        httpDelete.setHeader("Content-Type", "application/json");
        httpDelete.setHeader("accept", "application/json");
        httpDelete.setHeader("User-Agent", "opensearch/java sdk " + version);
        if (this.gzip == true) {
        	httpDelete.setHeader("Accept-Encoding", "gzip");
        }
        //System.out.println("DELETEURL"+url);
        HttpResponse response = httpClient.execute(httpDelete);
        validateResponse(response, httpDelete);
        HttpEntity entity = response.getEntity();
        InputStream is;
        if (null != entity) {

            Header header = entity.getContentEncoding();

            if (header != null && header.getValue().equalsIgnoreCase("gzip")) {
                GzipDecompressingEntity gzipEntity = new GzipDecompressingEntity(
                        entity);
                is = gzipEntity.getContent();
            } else {
                is = entity.getContent();
            }

            if(isPB){
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bytes = new byte[1024];
                int len = 0;
                while(( len= is.read(bytes)) != -1){
                    byteArrayOutputStream.write(bytes, 0, len);
                }
                byte[] temp = byteArrayOutputStream.toByteArray();
                result = new String(temp,"ISO8859-1");
            }else{
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] data = new byte[4096];
                int count = -1;
                while ((count = is.read(data, 0, 4096)) != -1)
                    outStream.write(data, 0, count);
                data = null;
                return new String(outStream.toByteArray(), encoding);
            }

        }

        return result;
    }
    
    
    public void shutdownIdleConnectionMonitor() {
        if (scheduledExeService != null) {
            scheduledExeService.shutdown();
        }
    }

    /** 
     * @Title: buildHttpPostRequest 
     * @Description: TODO 构造查询参数
     * @param url
     * @param opts
     * @param encoding
     * @return
     * @throws UnsupportedEncodingException
     * @return: HttpPost
     */
    private HttpPost buildHttpPostRequest(String url,
            Map<String, Object> opts, String encoding)
            throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        
        if (opts != null) {
        	String str = JSON.toJSONString(opts);
        	JSONObject json = JSON.parseObject(str,Feature.OrderedField); 
        	 StringEntity s = new StringEntity(json.toString(),"utf-8");    
             s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            httpPost.setEntity(s);
        }
        return httpPost;
    }

    private HttpPut buildHttpPutRequest(String url,
            Map<String, Object> opts, String encoding)
            throws UnsupportedEncodingException {
    	HttpPut httpPut = new HttpPut(url);
        
        if (opts != null) {
        	String str = JSON.toJSONString(opts);
        	JSONObject json = JSON.parseObject(str); 
        	 StringEntity s = new StringEntity(json.toString(),"utf-8");    
             s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
             httpPut.setEntity(s);
        }
        return httpPut;
    }
    
    
    private void validateResponse(HttpResponse response, HttpGet get)
            throws IOException {
        StatusLine status = response.getStatusLine();
        if (status.getStatusCode() >= HttpStatus.SC_MULTIPLE_CHOICES) {
            LOG.warn(
                    "Did not receive successful HTTP response: status code = {}, status message = {}",
                    status.getStatusCode(), status.getReasonPhrase());
            get.abort();
            return;
        }
    }

    private void validateResponse(HttpResponse response, HttpPost post)
            throws IOException {
        StatusLine status = response.getStatusLine();
        if (status.getStatusCode() >= HttpStatus.SC_MULTIPLE_CHOICES) {
            LOG.warn(
                    "Did not receive successful HTTP response: status code = {}, status message = {}",
                    status.getStatusCode(), status.getReasonPhrase());
            post.abort();
            return;
        }
    }
    
    private void validateResponse(HttpResponse response, HttpPut put)
            throws IOException {
        StatusLine status = response.getStatusLine();
        if (status.getStatusCode() >= HttpStatus.SC_MULTIPLE_CHOICES) {
            LOG.warn(
                    "Did not receive successful HTTP response: status code = {}, status message = {}",
                    status.getStatusCode(), status.getReasonPhrase());
            put.abort();
            return;
        }
    }
    
    private void validateResponse(HttpResponse response, HttpDelete httpDelete)
            throws IOException {
        StatusLine status = response.getStatusLine();
        if (status.getStatusCode() >= HttpStatus.SC_MULTIPLE_CHOICES) {
            LOG.warn(
                    "Did not receive successful HTTP response: status code = {}, status message = {}",
                    status.getStatusCode(), status.getReasonPhrase());
            httpDelete.abort();
            return;
        }
    }

}

