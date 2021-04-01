package com.chinamobile.cmss.bcse.evaluate.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.chinamobile.cmss.bcse.config.service.impl.RedisService;
import com.chinamobile.cmss.bcse.index.tool.MD5;
import com.chinamobile.cmss.bcse.user.bean.UserBean;
import com.chinamobile.cmss.bcse.validate.util.HMACSHA1;

public class AuthUtils {
	
	
	
	public static void get(RedisService redisService,String userId){
		
		UserBean user = (UserBean)redisService.get(String.valueOf(userId));
		
		
	}
	
	
	/**
     * 用户认证
     * @return String
     */
    public String getAuthorization (String url,String type,Map<String, Object> opts,String Date,UserBean user){
    	String Authorization  = "";
    	String AccessKeyId = (String)opts.get("userId");
    	String SecretAccessKey = user.getSecretKey();
    	String HTTP_Verb = type;
    	String Content_MD5 = "";
    	String Content_Type = "application/json;charset=UTF-8";
    	String CanonicalizedResource = url;
    	String CanonicalizedHeaders ="";
    	//CanonicalizedResource
    	if("GET".equals(type)||"DELETE".equals(type)){
    		CanonicalizedResource = url.substring(0, url.indexOf("?"));
    	} else if("POST".equals(type)){
    		CanonicalizedResource = url;
    	}
    	//Content-MD5：
    	//Content_MD5 = getContent_MD5(opts);
    	
    	String StringToSign = HMACSHA1.getStringToSign(HTTP_Verb, "", Content_Type, Date, "", "user");
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
    	StringBuilder sb = new StringBuilder();
		List<Map.Entry<String, Object>> list = sortForMap(opts);
		for(Map.Entry<String, Object> entry:list){
			String key = null;
    		String value = null;
			key = MD5.getMD5(entry.getKey());
			value = MD5.getMD5(String.valueOf(entry.getValue())); 
			sb.append("&").append(key).append("=").append(value);
		}
		 
    	return sb.substring(1);
    }

}
