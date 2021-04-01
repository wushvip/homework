package com.chinamobile.cmss.bcse.validate.authority.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.chinamobile.cmss.bcse.app.bean.AppInfoBean;
import com.chinamobile.cmss.bcse.app.bean.AppReqBean;
import com.chinamobile.cmss.bcse.app.dao.AppInfoBeanDao;
import com.chinamobile.cmss.bcse.config.service.impl.RedisService;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.exception.SystemException;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.Md5Util;
import com.chinamobile.cmss.bcse.user.bean.UserBean;
import com.chinamobile.cmss.bcse.user.dao.UserDao;
import com.chinamobile.cmss.bcse.validate.authority.ValidatorApi;
import com.chinamobile.cmss.bcse.validate.util.HMACSHA1;

/**
 * 
 * @ClassName: RequestAuthorization 
 * @Description: authorization做对称性校验
 * @author: lijingjing
 * @date: 2017年3月8日 下午5:55:46
 */
@Service("authService")
public class RequestAuthorization{

	private final String USERID = "userId";
	private final String ID = "id";
	private final String APPID = "appId";
	private final String AUTHORIZATION = "Authorization";
	private final String SE_DATE = "SE-Date";
	@Resource
	private UserDao userDao;
	@Resource
	private ValidatorApi uImpl;
	@Resource
	private ValidatorApi appImpl;
	@Resource
	private RedisService redisService;
	@Resource
	private AppInfoBeanDao appInfoBeanDao = null;
	
	
	/**
	 * 
	 * @Title: verify 
	 * @Description: 接口请求有效性校验
	 * @param authorization
	 * @param json
	 * @param httpRequest
	 * @return
	 * @return: boolean
	 */
	public  boolean verify(JSONObject json,HttpServletRequest httpRequest,String message){
		// 获取请求head中的authorization值

		String authorizationWeb = httpRequest.getHeader(AUTHORIZATION);
		//http请求时间戳
		String se_date = httpRequest.getHeader(SE_DATE);
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "The value of SE_DATE and AUTHORIZATION received from request head is"
				+ " \n "+AUTHORIZATION+"="+authorizationWeb+SE_DATE+"="+se_date);
		//判断重放攻击
		//当前时间戳
		long now = System.currentTimeMillis();
		
		if(se_date == null || authorizationWeb ==null){
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, "se_date or authorization  is null!");
			message = "鉴权参数不完整";
			return  false;
		}
		long date_long = Long.parseLong(se_date);
		//http请求时间与当前服务器时间相差60s，即判断为不合法请求（重放攻击）
		if((now-date_long)>60*1000){
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, "The current time and request time is greater than 60s");
			return  false;
		}
		//校验接受加密信息的格式
		String strArray[] = authorizationWeb.split(" ");
		if (strArray.length<=1) {
			return false;
		}
		String access = strArray[1];
		if (access== null) {
			return false;
		}
		
		String accessArray[] = access.split(":");
		if (accessArray.length != 2) {
			return false;
		}
		
		String accessKeyIdWeb = accessArray[0];
//		String webSignature = accessArray[1];
		
		//计算Signature
		//获取用户私钥
		String userId = accessKeyIdWeb;
		if ("".equals(userId) || userId == null) {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, "the accessKeyId value get from request is Invalid,accessKeyId="+userId);
			return false;
		}
		UserBean user = (UserBean) redisService.get(userId);
		if (user == null) {
			return false;
		}
		String secretAccessKey = user.getSecretKey();
		String accessKeyId = user.getUserId();
		
		//获取stringToSign相关信息
		String stringToSign = HMACSHA1.getStringToSign(httpRequest.getMethod(), getContentMD5(json), Config.CONTENT_TYPE_JSON, se_date, "", "");
//		String stringToSign = HMACSHA1.getStringToSign(httpRequest.getMethod(), getContentMD5(json), httpRequest.getContentType(), se_date, "", httpRequest.getRequestURL().toString());
		String signature =HMACSHA1.getSignature(secretAccessKey,stringToSign);
		String authorization = "SE"+" "+ accessKeyId +":"+signature;
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "the authorization of request head is :"+authorizationWeb);
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "the authorization of java create  is :"+authorization);
		if (!authorizationWeb.equals(authorization)) {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, "authorization check result is fail!");
			return false;
		}
		
		return true;
		
		
	}
	
	/**
	 * 
	 * @Title: paramCheck 
	 * @Description: TODO
	 * @param json,请求参数
	 * @param flag,是否需要对应用状态校验
	 * @return
	 * @return: boolean
	 */
	public boolean paramCheck(JSONObject json,boolean isOpen){
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "paramCheck start");
		
	
			String userId = json.getString(USERID);
			String id = json.getString(ID);
			String appId = json.getString(APPID);
		try {
			
			if (userId == null) {
				//接口自己校验相应参数
				return false;
			}
			//执行者与被操作应用的关系
			if (appId != null && userId !=null) {
				LogUtil.loggerEntrance(LogUtil.WEB_LOG, "appId and userId not null");
				AppInfoBean app = (AppInfoBean) redisService.get(appId);
				if (app == null) {
					LogUtil.loggerEntrance(LogUtil.WEB_LOG, "appInfo is null,appId="+appId);
					return false;
				}
				String status = app.getAppStatus();
				//禁用状态，禁止一切操作
				if (status == null || Config.APP_STOP_STATUS.equals(status)) {
					LogUtil.loggerEntrance(LogUtil.WEB_LOG, "app status is null or APP_STOP_STATUS,appId="+appId);
					return false;
				}
				
				//暂停状体,停用对外所有接口
				if (status != null && Config.APP_PAUSE_STATUS.equals(status)) {
					LogUtil.loggerEntrance(LogUtil.WEB_LOG, "app status is  APP_PAUSE_STATUS,appId="+appId);
					if(isOpen){
						return false;
					};
					return appImpl.relationValidate(userId, appId);
					
				}
				
				//正常状态
				if (status != null && Config.APP_NORMAL_STATUS.equals(status)) {
					LogUtil.loggerEntrance(LogUtil.WEB_LOG, "app status is  APP_NORMAL_STATUS,appId="+appId);

					return appImpl.relationValidate(userId, appId);
					
				}
				
			}
			
			//执行者与被操作用户的关系
			if (id != null && userId !=null) {
				LogUtil.loggerEntrance(LogUtil.WEB_LOG, "id and userId not null");
				return uImpl.relationValidate(userId, id);
			}
			
			if (id != null && appId !=null) {
				LogUtil.loggerEntrance(LogUtil.WEB_LOG, "id and appId not null");
				LogUtil.loggerEntrance(LogUtil.WEB_LOG, "the param \"appId\" and \"id\" is Invalid combination!");
				return false;
			}
			
		
			if(userId != null){
				return uImpl.existValidate(userId);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.loggerEntrance(userId, appId,Config.FAIL_SYS_CODE, LogUtil.WEB_LOG,e);
			throw new SystemException(MsgConfig.SYS_EXCP);
		}
		return false;
	}
	
	
	/**
	 * 
	 * @Title: creatingAppCheck 
	 * @Description: 创建中的应用不存在redis中，需要单独处理
	 * @param json
	 * @param isOpen
	 * @return
	 * @return: boolean
	 */
	public boolean creatingAppCheck(JSONObject json,boolean isOpen){
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "creatingAppCheck start");
		
	
			String userId = json.getString(USERID);
			String appId = json.getString(APPID);
		try {	
			
			if (userId == null) {
				//接口自己校验相应参数
				return false;
			}
			//执行者与被操作应用的关系
			if (appId != null && userId !=null) {
				LogUtil.loggerEntrance(LogUtil.WEB_LOG, "appId and userId not null");
				AppInfoBean app = (AppInfoBean) redisService.get(appId);
				if (app == null) {
					//查询数据库
					AppReqBean appReqBean = new AppReqBean();
					appReqBean.setAppId(appId);
					appReqBean.setUserId(userId);
					app = appInfoBeanDao.showSignAppDetail(appReqBean);
					if (app == null) {
						LogUtil.loggerEntrance(LogUtil.WEB_LOG, "appInfo is null,appId="+appId);
						return false;
					}
					
				}
				String status = app.getAppStatus();
				//创建中的状态
				if (status != null && Config.APP_ING_STATUS.equals(status)) {
					LogUtil.loggerEntrance(LogUtil.WEB_LOG, "app status is  APP_ING_STATUS,appId="+appId);

					return uImpl.existValidate(userId);
					
				}
				//禁用状态，禁止一切操作
				if (status == null || Config.APP_STOP_STATUS.equals(status)) {
					LogUtil.loggerEntrance(LogUtil.WEB_LOG, "app status is null or APP_STOP_STATUS,appId="+appId);
					return false;
				}
				
				//暂停状体,停用对外所有接口
				if (status != null && Config.APP_PAUSE_STATUS.equals(status)) {
					LogUtil.loggerEntrance(LogUtil.WEB_LOG, "app status is  APP_PAUSE_STATUS,appId="+appId);
					if(isOpen){
						return false;
					};
					return appImpl.relationValidate(userId, appId);
					
				}
				
				//正常状态
				if (status != null && Config.APP_NORMAL_STATUS.equals(status)) {
					LogUtil.loggerEntrance(LogUtil.WEB_LOG, "app status is  APP_NORMAL_STATUS,appId="+appId);

					return appImpl.relationValidate(userId, appId);
					
				}
				
			}
			
			if(userId != null){
				return uImpl.existValidate(userId);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.loggerEntrance(userId, appId,Config.FAIL_SYS_CODE, LogUtil.WEB_LOG,e);
			throw new SystemException(MsgConfig.SYS_EXCP);
		}
		return false;
	}
	
	/**
	 * 
	 * @Title: getContentMD5 
	 * @Description: 请求参数内容MD5加密
	 * @param json
	 * @return
	 * @return: String
	 */
	private static String getContentMD5(JSONObject json) {
		JSONObject paramJson = new JSONObject();
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		List<String> keyList = new ArrayList<String>();
		Set<String> keys = json.keySet();
		for (String key : keys) {
			keyList.add(key);
		}
		java.util.Collections.sort(keyList);
		for (String key : keyList) {
			map.put(key, json.get(key));

		}
		paramJson = JSONObject.parseObject(JSON.toJSONString(map),Feature.OrderedField);
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "MD5 param string:"+String.valueOf(paramJson).replaceAll("\"", ""));
		return Md5Util.encrypt(String.valueOf(paramJson).replaceAll("\"", ""));

	}
}
