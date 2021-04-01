package com.chinamobile.cmss.bcse.validate.filter;

import javax.servlet.ServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created with antnest-platform User: chenyuan Date: 12/24/14 Time: 10:39 AM
 */
public class HttpHelper {

	/**
	 * 获取请求Body
	 *
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	public static JSONObject getBodyString(ServletRequest request) throws IOException {
		StringBuilder sb = new StringBuilder();
		InputStream inputStream = null;
		BufferedReader reader = null;
		try {
			inputStream = request.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "get from request body param:"+sb.toString());
		return JSONObject.parseObject(sb.toString(),Feature.OrderedField);
	}
}