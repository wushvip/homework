package com.chinamobile.cmss.bcse.sdk.entry;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.ClientProtocolException;
import org.springframework.web.multipart.MultipartFile;

import com.chinamobile.cmss.bcse.sdk.constant.Constant;
import com.chinamobile.cmss.bcse.sdk.util.HttpClientManager;


/**
 * @ClassName: BCSEClient
 * @Description: TODO bcse连接客户端
 * @author: chenmin
 * @date: 2016年4月13日 上午10:38:40
 */
public class BCSEClient {

	//private boolean flag = false;
	private boolean flag = true;
	private String url;
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private String ip;
	private int port;
	private String appId;
	private String userId;
	//private String path = "/sdk/getZkHost";
	private String secretAccessKey;

	public BCSEClient(String appId, String userId, String ip, int port,String secretAccessKey) throws Exception {
		//this(appId, userId, ip, port, new HashMap<String, Object>(), "/bcse",secretAccessKey);
		this(appId, userId, ip, port, new HashMap<String, Object>(), "/api",secretAccessKey);
	}

	public BCSEClient(String appId, String ip, int port, String url,String secretAccessKey) throws Exception {
		this(appId, "admin", ip, port, new HashMap<String, Object>(), url,secretAccessKey);
	}

	public BCSEClient(String appId, String userId, String url,String secretAccessKey) throws Exception {
	}

	public BCSEClient(String appId, String userId, String ip, int port, Map<String, Object> opts, String url,String secretAccessKey)
			throws Exception {
		Constant.appId = this.appId = appId;
		this.setPort(port);
		this.setIp(ip);
		Constant.userId = this.userId = userId;
		this.url = "http://" + ip + ":" + port + "/" + url;
		Constant.secretAccessKey=this.secretAccessKey=secretAccessKey;
		
		//opts.put("method", "getZkHost");

		/*JSONObject appRspBean = AssembleAppResult
				.assembleResult(call(Constant.APP_POST_URL + this.path, opts, Constant.POST));

		String ZkHost = (String) appRspBean.get("message");*/
		// String
		// ZkHost="223.105.0.236:2181,223.105.0.230:2181,223.105.0.225:2181";
		/*if (ZkHost != "" || ZkHost != null) {
			Constant.ZK_HOST = ZkHost;
			flag = true;
		} else {
			Exception e = new Exception("配置信息有误，请检查");
			throw e;
		}*/
		if ((opts != null) && (opts.size() > 0)) {

			if ((opts.get("maxConnections") != null)) {
				int maxConnections = (Integer) opts.get("maxConnections");
				HttpClientManager.setMaxConnections(maxConnections);
			}
		}
	}

	public void setMaxConnections(int maxConnections) {
		if (maxConnections > 0) {
			HttpClientManager.setMaxConnections(maxConnections);
		}
	}

	/**
	 * @Title: call
	 * @Description: TODO 对外调用接口
	 * @param path
	 * @param opts
	 * @param method
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @return: String
	 */
	public String call(String path, Map<String, Object> opts, String method)
			throws ClientProtocolException, IOException {
		String sendUrl = url + path;
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.putAll(opts);
		params.put("appId", this.appId);
		params.put("userId", this.userId);
		String result = doRequest(sendUrl, params, method);
		return result;
	}

	/**
	 * @Title: doRequest
	 * @Description: TODO 发送请求的服务
	 * @param url
	 * @param opts
	 * @param method
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @return: String
	 */
	protected String doRequest(String url, Map<String, Object> opts, String method)
			throws ClientProtocolException, IOException {
		String result = "";
		if (method.equals("POST")) {
			result = HttpClientManager.doPost(url, opts, "utf-8");
		} else if (method.equals("PUT")) {
			result = HttpClientManager.doPut(url, opts, "utf-8");
		} else if (method.equals("GET")) {
			Map<String, Object> optsForMD5 = new HashMap<String, Object>();
			url = url + getHTTPParamsStr(opts,optsForMD5);
			System.out.println("URL:"+url);
			result = HttpClientManager.doGet(url, "utf-8", false,optsForMD5);
		}else if (method.equals("DELETE")) {
			Map<String, Object> optsForMD5 = new HashMap<String, Object>();
			url = url + getHTTPParamsStr(opts,optsForMD5);
			result = HttpClientManager.doDelete(url, "utf-8", false,optsForMD5);
		}
		return result;
	}

	/**
	 * @Title: getHTTPParamsStr
	 * @Description: TODO
	 * @param opts
	 * @return
	 * @return: String
	 */
	protected String getHTTPParamsStr(Map<String, Object> opts,Map<String, Object> optsForMD5) {
		if ((opts == null) || (opts.size() <= 0)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Entry<String, Object> entry : opts.entrySet()) {
			String value = null;
			String key = null;
			try {
				key = percentEncode((String) entry.getKey());
				//value = percentEncode((String) entry.getValue());
				if(entry.getValue() == null){
					continue;
				}
				if(entry.getValue().getClass().getName().equals("java.util.ArrayList")){
					 List<String> list=(List<String>)entry.getValue();
					 for(String str:list){
						 sb.append("&").append(key).append("=").append(str); 
					 }
					 optsForMD5.put(key, list);
				}else{
					optsForMD5.put(key, String.valueOf(entry.getValue()));
					value = percentEncode(String.valueOf(entry.getValue()));
					sb.append("&").append(key).append("=").append(value);
				}
				
			} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			}
			
		}
		return "?" + sb.substring(1);
	}
	
	
	/**
	 * @Title: percentEncode
	 * @Description: TODO
	 * @param value
	 * @return
	 * @throws UnsupportedEncodingException
	 * @return: String
	 */
	private String percentEncode(String value) throws UnsupportedEncodingException {
		return value != null
				? URLEncoder.encode(value, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~") : null;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	
	public String upload(String path, Map<String, Object> opts,MultipartFile file, String method)
			throws ClientProtocolException, IOException {
		String sendUrl = url + path;
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.putAll(opts);
		params.put("appId", this.appId);
		params.put("userId", this.userId);
		String result=HttpClientManager.doUpload(sendUrl, params,file, "utf-8");
		return result;
	}
	
}
