package com.chinamobile.cmss.bcse.validate.authority.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinamobile.cmss.bcse.app.bean.AppInfoBean;
import com.chinamobile.cmss.bcse.config.service.impl.RedisService;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.exception.SystemException;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.user.bean.UserBean;
import com.chinamobile.cmss.bcse.validate.authority.ValidatorApi;



/**
 * 
 * @ClassName: AppValidatorImpl 
 * @Description: 校验应用相关
 * @author: lijingjing
 * @date: 2017年3月3日 下午4:10:38
 */
@Service("appImpl")
public class AppValidatorImpl implements ValidatorApi{

	@Resource
	private RedisService redisService;
	@Resource
	private UserValidatorImpl uImpl;
	
	/**
	 * 应用是否存在
	 */
	public boolean existValidate(Object appId) throws Exception{
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "appImpl:check the appId is exist or not,appId="+appId);
		AppInfoBean app = (AppInfoBean)redisService.get(String.valueOf(appId));
		if (app ==null) {
			return false;
		}

		return true;
		
	}

	/**
	 * 校验
	 */
	public  boolean relationValidate(Object userId,Object appId) throws Exception{
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "appImpl:check the relation of key and value"
				+ ",userId="+userId+",appId="+appId);
		AppInfoBean appInfo = (AppInfoBean) redisService.get(String.valueOf(appId));
		if (appInfo == null  ) {
			return false;
		}
		//判断获取出来的应用的userId是否与key相同
		if (String.valueOf(userId).equals(appInfo.getUserId()) && uImpl.existValidate(userId)) {
			return true;
		}

		return uImpl.authorityValidate(userId);
		
	}
	
	
	/**
	 * 不会用到
	 */
	public  boolean authorityValidate(Object source) throws Exception{
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "Invalid method");
		throw new SystemException(MsgConfig.SYS_EXCP);
		
	}

}
