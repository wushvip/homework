package com.chinamobile.cmss.bcse.tool.tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.chinamobile.cmss.bcse.tool.config.Config;


public class OaUtil {
	
	private static final Logger logger = Logger.getLogger(OaUtil.class);
	
	
	/**
    userName  页面传来的用户名
    password  js加密后的密码 
    
    返回值为 json传  具体参考 <<单点登录应用接入规范.2017.0320.docx>>【4.2 附2:认证用户&查询用户资料接口】
 */
 public static String LoginOA(String userName,String password){
		HttpURLConnection httpURLConnection = null;
		OutputStream outputStream = null;
		InputStream inptStream = null;
		String value = null;
		try{
			Map<String, String> params = new HashMap<String, String>();
			params.put("username",  userName);
			params.put("password",  password);
			byte[] data = getRequestData(params, "utf-8").toString().getBytes();
	        
			URL url = new URL(Config.OA_URL);  
	        
	        httpURLConnection = (HttpURLConnection)url.openConnection();
	        httpURLConnection.setConnectTimeout(3000);     
	        httpURLConnection.setDoInput(true);                
	        httpURLConnection.setDoOutput(true);               
	        httpURLConnection.setRequestMethod("POST");     
	        httpURLConnection.setUseCaches(false);               
	       
	        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	       
	        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
	        
	        outputStream = httpURLConnection.getOutputStream();
	        outputStream.write(data);
	        
	        int response = httpURLConnection.getResponseCode();           
	        if(response == HttpURLConnection.HTTP_OK) {
	             inptStream = httpURLConnection.getInputStream();
	             value =  dealResponseResult(inptStream);                    
	        }else{
	        	 return null;
	        }
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}finally{
			if(inptStream != null){
				try {
					inptStream.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
			if(outputStream != null){
				try {
					outputStream.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
			if(httpURLConnection != null){
				try {
					httpURLConnection.disconnect();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return value;
	}
 
	
 public static String dealResponseResult(InputStream inputStream) {
     String resultData = null;     
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
     byte[] data = new byte[1024];
     int len = 0;
      try {
         while((len = inputStream.read(data)) != -1) {
            byteArrayOutputStream.write(data, 0, len);
         }
      } catch (IOException e) {
   	 logger.error(e.getMessage(), e);
       }
      resultData = new String(byteArrayOutputStream.toByteArray());    
      return resultData;
  }  
 
 public static StringBuffer getRequestData(Map<String, String> params, String encode) {
     StringBuffer stringBuffer = new StringBuffer();       
     try {
           for(Map.Entry<String, String> entry : params.entrySet()) {
               stringBuffer.append(entry.getKey())
                     .append("=")
                     .append(URLEncoder.encode(entry.getValue(), encode))
                     .append("&");
           }
          stringBuffer.deleteCharAt(stringBuffer.length() - 1);    
       } catch (Exception e) {
       	logger.error(e.getMessage(), e);
      }
      return stringBuffer;
   }

}
