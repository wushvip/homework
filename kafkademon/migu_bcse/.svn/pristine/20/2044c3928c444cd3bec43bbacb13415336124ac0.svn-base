package com.chinamobile.cmss.bcse.evaluate.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.JSON;
import com.chinamobile.cmss.bcse.config.service.impl.RedisService;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.Md5Util;
import com.chinamobile.cmss.bcse.user.bean.UserBean;
import com.chinamobile.cmss.bcse.validate.util.HMACSHA1;



public class AuthorizationUtil {
	
	
	 /**
     * 用户认证
     * @return String
     */
    public static String getAuthorization (String url,String type,String param,String Date,String userId){
    	String CanonicalizedResource = url;
    	String Authorization  = "";
    	String HTTP_Verb = type;
    	String Content_MD5 = "";
    	String Content_Type = "application/json;charset=UTF-8";
    	String CanonicalizedHeaders ="";
    	Map<String, Object> opts = new HashMap<String, Object>();
    	//CanonicalizedResource
    	if("GET".equals(type)||"DELETE".equals(type)){
    		if(url.contains("?")){
    			CanonicalizedResource = url.substring(0, url.indexOf("?"));
    			
    		}else {
    			CanonicalizedResource = url;
    		}
    		opts = getURLMap(param);
    		
    	} else if("POST".equals(type)||"PUT".equals(type)){
    		CanonicalizedResource = url;
    		opts=JSON.parseObject(param,Map.class);
    	}
    	
    	
    	RedisService redisService = (RedisService)Config.ac.getBean("redisService");
    	String AccessKeyId = userId;
    	UserBean user = (UserBean)redisService.get(String.valueOf(AccessKeyId));
    	String SecretAccessKey = user.getSecretKey();
    	
    	
    	//Content-MD5：
    	Content_MD5 = getContent_MD5(opts);
    	
    	String StringToSign = HMACSHA1.getStringToSign(HTTP_Verb, Content_MD5, Content_Type, Date, "", "");
    	String Signature=HMACSHA1.getSignature(SecretAccessKey,StringToSign);
    	Authorization = "SE" + " " + AccessKeyId + ":" + Signature;
    	return Authorization;
    }
    /**
     * 对map排序
     */
    public static List<Map.Entry<String, Object>> sortForMap(Map map){
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
    public static String getContent_MD5(Map<String, Object> opts){
    	if(opts==null || opts.isEmpty()){
    		return "";
    	}
    	JSONObject paramJson = new JSONObject();
    	LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
    	//StringBuilder sb = new StringBuilder();
		List<Map.Entry<String, Object>> list = sortForMap(opts);
		for(Map.Entry<String, Object> entry:list){
			map.put(entry.getKey(), entry.getValue());
		}
		paramJson = JSONObject.parseObject(JSON.toJSONString(map),Feature.OrderedField);
		LogUtil.loggerEntrance(LogUtil.EVALUATE_LOG, "md5加密内容:" + paramJson);
    	return Md5Util.encrypt(String.valueOf(paramJson).replaceAll("\"", ""));
    }
	
	public static Map<String, Object> getURLMap(String param){
		Map<String, Object> opts = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		//划分参数
		String a[]  =  param.split("&");
		for(int i = 0 ;i<a.length;i++){
			//划分参数名和参数值
			String b[] = a[i].split("=");
			if(!"".equals(b[0])&&b[0]!=null){
				opts.put(b[0], b[1]);
				//encode  未完成
				//sb.append("&").append(key).append("=").append(value);
			}
		}
		//param = sb.substring(1);
		return opts;
	}
}
