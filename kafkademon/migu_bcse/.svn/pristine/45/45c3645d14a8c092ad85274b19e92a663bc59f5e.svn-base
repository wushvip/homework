package com.chinamobile.cmss.bcse.validate.authority.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinamobile.cmss.bcse.config.service.impl.RedisService;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.user.bean.UserBean;
import com.chinamobile.cmss.bcse.validate.authority.ValidatorApi;



/**
 * 
 * @ClassName: UserValidatorImpl 
 * @Description: 校验用户相关
 * @author: lijingjing
 * @date: 2017年3月3日 下午4:11:27
 */
@Service("uImpl")
public class UserValidatorImpl implements ValidatorApi{
	
	@Resource
	private RedisService redisService;
	
	/**
	 * 校验用户是否存在
	 */
	public boolean existValidate(Object userId) throws Exception{
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "uImpl:check the user is exist or not");
		UserBean user = (UserBean)redisService.get(String.valueOf(userId));
		if ( user==null) {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, "uImpl:the user is not exist,userId="+userId);
			return false;
		}
		
		//check user status
		if (!Config.USER_NORMAL_STATUS.equals(user.getUserStatus())) {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, "uImpl:the user status is"+user.getUserStatus()+",userId="+userId);
			return false;
		}
		return true;
		
	}

	/**
	 * 校验key是否有权限操作value
	 */
	public  boolean relationValidate(Object key,Object value) throws Exception{
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "uImpl:check the relation of key and value"
				+ ",key="+key+",value="+value);
		if(key != null && value != null && key.equals(value)){
			return existValidate(key);
		}
		return authorityValidate(key);


		
	}
	
	
	/**
	 * 校验是否有管理员权限
	 */
	public  boolean authorityValidate(Object userId) throws Exception{
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "uImpl:check user authority,userId="+userId);
		UserBean user = (UserBean) redisService.get(String.valueOf(userId));
		if(user!=null && Config.USER_NORMAL_STATUS.equals(user.getUserStatus())){
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, "uImpl:userstatus is normal and role is "+user.getRole());
			return Config.ROLE_ADMIN.equals(user.getRole());
		}
		return false;
		
	}

}
