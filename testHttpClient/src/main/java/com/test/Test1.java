package com.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Test1 {

    public static void main(String[] args) {

    }

    public static void httpAsyncDel(String url){
        //创建请求连接配置
        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
        RequestConfig requestConfig = requestConfigBuilder
                .setConnectionRequestTimeout(20000)
                .setConnectTimeout(50000)
                .setSocketTimeout(50000)
                .build();

        //配置IO线程资源
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                .setBacklogSize(10)
                .setIoThreadCount(Runtime.getRuntime().availableProcessors())
                .setSoKeepAlive(true)
                .build();
        //配置线程池的大小
        ConnectingIOReactor ioReactor = null;
        try {
            ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
        } catch (IOReactorException e) {
            e.printStackTrace();
        }
        PoolingNHttpClientConnectionManager pccm = new PoolingNHttpClientConnectionManager(ioReactor);
        pccm.setMaxTotal(10);
        pccm.setDefaultMaxPerRoute(1);

        //创建httpClient
        CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.custom()
                .setConnectionManager(pccm)
                .setDefaultRequestConfig(requestConfig).build();
        httpAsyncClient.start();

        final HttpDelete request = new HttpDelete(url);
        httpAsyncClient.execute(request,new DeleteStorageCallBack());
        try {
            httpAsyncClient.close();
        } catch (IOException ignore) {
        }
    }

    static class DeleteStorageCallBack implements FutureCallback<HttpResponse> {
        private String url;
        DeleteStorageCallBack(){}

        DeleteStorageCallBack(String url){
            this.url = url;
        }

        public void completed(HttpResponse httpResponse) {
            if(httpResponse !=null){
                try {
                    String content = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        public void failed(Exception e) {
            Map<String,String> map = new HashMap<>();
        }

        public void cancelled() {
        }
    }

}
