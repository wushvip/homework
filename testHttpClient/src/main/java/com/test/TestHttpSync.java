package com.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class TestHttpSync {

    public static void main(String[] args) {

    }
    public static void httpAsyncDel(String url){
        CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.createDefault();
        httpAsyncClient.start();
        final CountDownLatch latch = new CountDownLatch(1);
        final HttpDelete request = new HttpDelete(url);

        httpAsyncClient.execute(request, new FutureCallback<HttpResponse>() {
            public void completed(HttpResponse httpResponse) {
                latch.countDown();
                if(httpResponse !=null){
                    try {
                        String content = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                        System.out.println("del storage suceess entity:" + httpResponse.getEntity());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            public void failed(Exception e) {
                latch.countDown();
                System.out.println("del storage fail " + e);
            }

            public void cancelled() {
                latch.countDown();
            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            httpAsyncClient.close();
        } catch (IOException ignore) {
        }
    }
}
