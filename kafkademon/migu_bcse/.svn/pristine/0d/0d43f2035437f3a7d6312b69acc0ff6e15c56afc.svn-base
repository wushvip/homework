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

package com.chinamobile.cmss.bcse.sdk.util;
import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.web.multipart.MultipartFile;

public class HttpClientManager {

    private static HttpClientFactory httpClientFactory = new HttpClientFactory(0, 0, 0);

    public static PoolingHttpClientConnectionManager getConnectionManager () {
        return httpClientFactory.connectionManager;
    }
    //设置连接池最大连接数
    public static void setMaxConnections(int maxConnections){
        if (maxConnections > 0 && maxConnections != httpClientFactory.getMaxConnections()) {
            if(httpClientFactory != null){
                httpClientFactory.shutdownIdleConnectionMonitor();
            }
            httpClientFactory = new HttpClientFactory(0, 0, maxConnections);
        }
    }

    //设置超时时间
  /*  public static void setTimeout(int timeout) {
        httpClientFactory.setTimeOut(timeout);
    }
*/
    public static void setGzip() {
        httpClientFactory.setGzip();
    }

    //设置连接超时时间
    /*public static void setConnectTimeout(int connectTimeout) {
        httpClientFactory.setConnectTimeout(connectTimeout);
    }*/
    
    public static String doUpload(String requestPath, Map<String, Object> opts,MultipartFile file, String encoding)
            throws ClientProtocolException, IOException {
    	return httpClientFactory.doUpload(requestPath, opts, encoding,file);
    }
    
    public static String doPost(String requestPath, Map<String, Object> opts, String encoding)
                    throws ClientProtocolException, IOException {
        return httpClientFactory.doPost(requestPath, opts, encoding);
    }

    public static String doGet(String url, String encoding, boolean isPB, Map<String, Object> opts) throws ClientProtocolException, IOException {
        return httpClientFactory.doGet(url, encoding, isPB,opts);
    }
    
    public static String doDelete(String url, String encoding, boolean isPB, Map<String, Object> opts)
    		throws ClientProtocolException, IOException {
        return httpClientFactory.doDelete(url, encoding, isPB,opts);
    }
    
    public static String doPut(String requestPath, Map<String, Object> opts, String encoding)
            throws ClientProtocolException, IOException {
    	return httpClientFactory.doPut(requestPath, opts, encoding);
}


}
