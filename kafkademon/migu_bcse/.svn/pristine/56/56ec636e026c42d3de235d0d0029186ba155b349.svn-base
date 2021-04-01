package com.chinamobile.cmss.bcse.sdk.tools;

import java.io.File;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinamobile.cmss.bcse.sdk.entry.BCSEClient;
import com.chinamobile.cmss.bcse.sdk.tools.LogUtil;
import com.chinamobile.cmss.bcse.sdk.util.HttpClientFactory;

/**
 * 实现文件上传到服务器
 * 
 * @author jinjing
 *
 */
public class UpdateDic {
	private static final Logger logger = LoggerFactory
            .getLogger(UpdateDic.class);
	/**
	 * 
	 * @Title: upload 
	 * @Description: TODO
	 * @param client
	 * @param path   /config/dic/stop
	 * @param filePath
	 * @return
	 * @return: boolean
	 */
	public static String upload(BCSEClient client,String path,String filePath) {
		LogUtil.loggerEntrance(LogUtil.CONFIG_LOG,"upload dic");
		String responseContent="";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httppost = new HttpPost(client.getUrl()+path);
			FileBody file = new FileBody(new File(filePath));
			StringBody userIdBody = new StringBody(client.getUserId(), ContentType.TEXT_PLAIN);
			StringBody appIdBody = new StringBody(client.getAppId(), ContentType.TEXT_PLAIN);

			HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("file", file).addPart("userId", userIdBody)
					.addPart("appId", appIdBody).build();

			httppost.setEntity(reqEntity);

			//System.out.println("executing request " + httppost.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				//System.out.println("----------------------------------------");
				//System.out.println(response.getStatusLine());
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
			            Header contentEncoding = resEntity.getContentEncoding();
			            if (contentEncoding != null
			                    && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
			                GzipDecompressingEntity gzipEntity = new GzipDecompressingEntity(
			                		resEntity);
			                responseContent = EntityUtils.toString(gzipEntity, "utf-8");
			            } else {
			                responseContent = EntityUtils.toString(resEntity, "utf-8");
			            }
			            EntityUtils.consume(resEntity);
			        } else {
			            logger.warn("Http entity is null! request url is {},response status is {}");
			        }
			} finally {
				response.close();
			}
			return responseContent;
		} catch (Exception e) {
			e.printStackTrace();
			return responseContent;
		} finally {
			try {
				httpclient.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	
	
	public static String upload(BCSEClient client,String path,String filePath,String mode) {
		LogUtil.loggerEntrance(LogUtil.CONFIG_LOG,"upload dic");
		String responseContent="";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httppost = new HttpPost(client.getUrl()+path);
			FileBody file = new FileBody(new File(filePath));
			StringBody userIdBody = new StringBody(client.getUserId(), ContentType.TEXT_PLAIN);
			StringBody appIdBody = new StringBody(client.getAppId(), ContentType.TEXT_PLAIN);
			StringBody modeBody = new StringBody(mode, ContentType.TEXT_PLAIN);

			HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("file", file).addPart("userId", userIdBody)
					.addPart("appId", appIdBody)
					.addPart("mode",modeBody)
					.build();

			httppost.setEntity(reqEntity);

			//System.out.println("executing request " + httppost.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				//System.out.println("----------------------------------------");
				//System.out.println(response.getStatusLine());
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
			            Header contentEncoding = resEntity.getContentEncoding();
			            if (contentEncoding != null
			                    && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
			                GzipDecompressingEntity gzipEntity = new GzipDecompressingEntity(
			                		resEntity);
			                responseContent = EntityUtils.toString(gzipEntity, "utf-8");
			            } else {
			                responseContent = EntityUtils.toString(resEntity, "utf-8");
			            }
			            EntityUtils.consume(resEntity);
			        } else {
			            logger.warn("Http entity is null! request url is {},response status is {}");
			        }
			} finally {
				response.close();
			}
			return responseContent;
		} catch (Exception e) {
			e.printStackTrace();
			return responseContent;
		} finally {
			try {
				httpclient.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
