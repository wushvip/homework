package com.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestHttpClient {
    public static void main(String[] args) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(50000)
                .setSocketTimeout(50000)
                .setConnectionRequestTimeout(10000)
                .build();

        //配置io线程
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom().
                setIoThreadCount(Runtime.getRuntime().availableProcessors())
                .setSoKeepAlive(true)
                .build();
        //设置连接池大小
        ConnectingIOReactor ioReactor=null;
        try {
            ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
        } catch (IOReactorException e) {
            e.printStackTrace();
        }
        PoolingNHttpClientConnectionManager connManager = new PoolingNHttpClientConnectionManager(ioReactor);
        connManager.setMaxTotal(10);
        connManager.setDefaultMaxPerRoute(2);

        //创建httpAsyncClient
        final CloseableHttpAsyncClient client = HttpAsyncClients.custom().
                setConnectionManager(connManager)
                .setDefaultRequestConfig(requestConfig)
                .build();


        //构造请求
        String url = "http://10.139.16.209:7080/bdoc/v2/op/workorder/volumes/2/12/bbzljjcg-xepr-yscr-lcwi-athffejp/test09252/19";
        List<HttpDelete> list = new ArrayList<HttpDelete>();
        for(int i=0;i<5;i++){
            HttpDelete httpDelete = new HttpDelete(url);
            httpDelete.addHeader(HTTP.CONTENT_TYPE,"application/json");
            StringEntity entity = null;
            list.add(httpDelete);
        }
        //start
        client.start();

        //异步请求
//        client.execute(list.get(i), new Back());

        for(int i=0;i<5;i++){
            System.out.println("this is " + i + "调用远程方法");
            client.execute(list.get(i), new Back(i));
        }

//        while(true){
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        System.out.println("请求结束");
//        try {
//            client.close();
//        } catch (IOException ignore) {
//        }
    }


    static class Back implements FutureCallback<HttpResponse> {

        private long start = System.currentTimeMillis();
        private int i;
        Back() {
        }
        Back(int i) {
            this.i = i;
        }
        public void completed(HttpResponse httpResponse) {
            try {
                System.out.println("第" + i + "次success：" + "cost is:" + (System.currentTimeMillis() - start) + ":" + EntityUtils.toString(httpResponse.getEntity()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void failed(Exception e) {
            System.err.println("第" + i + "次：" + " cost is:" + (System.currentTimeMillis() - start) + ":" + e);
        }

        public void cancelled() {

        }
    }
}
