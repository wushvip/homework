package com.chinamobile.cmss.bcse.sdk.index.indexManager;

import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.chinamobile.cmss.bcse.sdk.entry.BCSEClient;
import com.chinamobile.cmss.bcse.sdk.tools.LogUtil;

/**
 * 实现文件上传到服务器
 * 
 * @author jinjing
 *
 */
public class UpdateFile {

	public static boolean formUpload(BCSEClient client, String userId, String appId, String tableId, String filePath) {
		LogUtil.loggerEntrance(LogUtil.INDEX_LOG,"formUpload start");
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			//HttpPost httppost = new HttpPost(client.getUrl() + "/application/dataUpload/index");
			HttpPost httppost = new HttpPost(client.getUrl() + "/apps/"+appId+"/data/local");

			FileBody file = new FileBody(new File(filePath));
			StringBody userIdBody = new StringBody(userId, ContentType.TEXT_PLAIN);
			StringBody appIdBody = new StringBody(appId, ContentType.TEXT_PLAIN);
			StringBody tableIdBody = new StringBody(tableId, ContentType.TEXT_PLAIN);

			HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("file", file).addPart("userId", userIdBody)
					.addPart("appId", appIdBody).addPart("tableId", tableIdBody).build();

			httppost.setEntity(reqEntity);

			System.out.println("executing request " + httppost.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					System.out.println("Response content length: " + resEntity.getContentLength());
				}
				EntityUtils.consume(resEntity);
			} finally {
				response.close();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				httpclient.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
