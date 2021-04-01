package com.chinamobile.cmss.bcse.user.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.app.bean.AppInfoBean;
import com.chinamobile.cmss.bcse.app.bean.AppReqBean;
import com.chinamobile.cmss.bcse.app.dao.AppInfoBeanDao;
import com.chinamobile.cmss.bcse.app.service.ApplicationManagerService;
import com.chinamobile.cmss.bcse.config.service.impl.RedisService;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.exception.SystemException;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.MailUtil;
import com.chinamobile.cmss.bcse.tool.tools.Md5Util;
import com.chinamobile.cmss.bcse.tool.tools.OaUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;
import com.chinamobile.cmss.bcse.user.bean.UserBean;
import com.chinamobile.cmss.bcse.user.dao.UserDao;
import com.chinamobile.cmss.bcse.user.service.IUserService;
import com.chinamobile.cmss.bcse.validate.authority.ValidatorApi;


/**
 * 
 * @ClassName: IUserServiceImpl 
 * @Description: 用户管理逻辑实现
 * @author: lijingjing
 * @date: 2017年3月7日 下午4:44:16
 */
@Service("iUserService")
public class IUserServiceImpl implements IUserService {
	@Resource
	private ValidatorApi uImpl;
	
	@Resource
	private UserDao userDao;
	@Resource
	private AppInfoBeanDao appInfoBeanDao;
	
	@Resource
	private RedisService redisService;
	@Resource
	private ApplicationManagerService applicationManagerService;

	/**
	 * 
	 * @Title: userLogin
	 * @Description: 登录验证
	 * @param userBean
	 * @param resultBean
	 * @Date:2016年5月25日
	 */
	public void userLogin(UserBean userBean, ResultBean resultBean) {

		UserBean userInfo = new UserBean();
		// 登录验证
		try {
			userInfo = userDao.checkPwd(userBean);
		} catch (Exception e) {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG,MsgConfig.DB_EXCP+": \n");
			e.printStackTrace();
			throw new SystemException(MsgConfig.SYS_EXCP);
		}

		if (userInfo == null) {
			// 用户记录不存在
			Tool.serviceException(resultBean, MsgConfig.NO_RECORDS_MSG);
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, "not found user,userName:"+userBean.getUserName());
			return;
		} else {
			if (userInfo.getUserStatus().equals(Config.USER_STOP_STATUS)) {
				// 用户状态为停用
				Tool.serviceException(resultBean, MsgConfig.USER_STOP_MSG);
				LogUtil.loggerEntrance(LogUtil.WEB_LOG, "user's status is illegaly,userStatus is "+userInfo.getUserStatus());
				return;
			}
		}
		resultBean.setResult(userInfo);
	}

	
	/**
	 * 
	 * @Title: userLogin
	 * @Description: 登录验证
	 * @param userBean
	 * @param resultBean
	 * @Date:2016年5月25日
	 */
	public void oaUserLogin(UserBean userBean, ResultBean resultBean) {
		//查询mysql,验证oa账户是否被关联，且用户状态正常
		ArrayList<UserBean> userList = new ArrayList<UserBean>();
		try {
			userList = userDao.selectOaUser(userBean);
			if (userList ==null || userList.size()<1 ) {
				Tool.serviceException(resultBean, MsgConfig.NO_RECORDS_OA);
				LogUtil.loggerEntrance(LogUtil.WEB_LOG, "not found user,userName:"+userBean.getUserName());
				return;
			}
			String status = userList.get(0).getUserStatus();
			if (status != null && status.equals(Config.USER_STOP_STATUS) ) {
				Tool.serviceException(resultBean, MsgConfig.USER_STOP_MSG);
				LogUtil.loggerEntrance(LogUtil.WEB_LOG, "user is stoped,userName:"+userBean.getUserName());
				return;
			}
			//发送请求到oa系统验证用户登录
			String msg = OaUtil.LoginOA(userBean.getUserName(),userBean.getPassword());
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, "oa system result:"+msg);
			JSONObject json = JSONObject.parseObject(msg);
			if (json == null || !"000000".equals(json.getString("code"))) {
				Tool.serviceException(resultBean, MsgConfig.NO_RECORDS_OA);
				LogUtil.loggerEntrance(LogUtil.WEB_LOG, "not found oa user,userName:"+userBean.getUserName());
				return;
			}
		} catch (Exception e) {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG,MsgConfig.SYS_EXCP+": \n");
			e.printStackTrace();
			throw new SystemException(MsgConfig.SYS_EXCP);
		}
		//验证通过后将用户的信息返回前端
		resultBean.setResult(userList.get(0));
		
		
	}
	
	/**
	 * 
	 * @Title: showUserList
	 * @Description: 展示用户列表
	 * @param userBean
	 * @param resultBean
	 * @throws Exception
	 * @Date:2016年5月25日
	 */
	public void showUserList(UserBean userBean, ResultBean resultBean) throws Exception {
		if(!uImpl.authorityValidate(userBean.getUserId())){
			//不是管理员
			Tool.serviceException(resultBean, MsgConfig.AUTHORITY_NO);
			return;
		}
			
		ArrayList<UserBean> userList = new ArrayList<UserBean>();
		//计算起始条数
		userBean.setStartNum((userBean.getPageIndex()-1)*userBean.getPageNum());
		//总条数
		int totalitems = 0;
		try {
			//模糊查询时，处理查询次的特殊字符%
			if (userBean.getSearchName()!= null) {
				String searchName = Tool.sqlTransferred(userBean.getSearchName());
				userBean.setSearchName(searchName);
			}
			
			userList = userDao.selectUserList(userBean);
			totalitems = userDao.getTotalItems(userBean);
		} catch (Exception e) {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG,MsgConfig.SYS_EXCP+": \n");
			e.printStackTrace();
			throw new SystemException(MsgConfig.SYS_EXCP);
		}
		JSONObject resultJson = new JSONObject();
		resultJson.put("userList", userList);
		resultJson.put("totalItems", totalitems);
		resultBean.setResult(resultJson);

	}

	/**
	 * 
	 * @Title: addUser
	 * @Description: 添加用户
	 * @param userBean
	 * @param resultBean
	 * @throws Exception
	 * @Date:2016年5月25日
	 */
	@Transactional(timeout = 2)
	public void addUser(UserBean userBean, ResultBean resultBean) throws Exception {
		if(!uImpl.authorityValidate(userBean.getUserId())){
			Tool.serviceException(resultBean, MsgConfig.AUTHORITY_NO);
			return;
		}
		ArrayList<UserBean> userList = new ArrayList<UserBean>();
		//判断是否关联指定oa账号
		userList = userDao.selectUserByOA(userBean);
		if (userList !=null && userList.size()>0 ) {
			Tool.serviceException(resultBean, MsgConfig.FORBID_REPEAT_OA);
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, MsgConfig.FORBID_REPEAT_OA+",userName:"+userBean.getUserName()+",oaUserName:"+userBean.getOaUserName());
			return;
		}
		// 生成一个随机的8位密码
		String addPwd = Tool.getRandomString(8);

		// 将密码加密后保存到数据库中
		String encodePassword = Md5Util.encrypt(addPwd);
		userBean.setPassword(encodePassword);
		userBean.setUserId(Tool.getUuid());
		userBean.setRole(Config.ROLE_COMMON);
		//生成secretkey
		userBean.setSecretKey(Tool.getUuid());
		int addNum = 0;

		// 验证是否存在要添加的用户名
		UserBean addUser = null;
		try {
			addUser = userDao.getUserByName(userBean.getUserName());
		} catch (Exception e) {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG,MsgConfig.DB_EXCP+": \n");
			e.printStackTrace();
			throw new SystemException(MsgConfig.SYS_EXCP);
		}

		if (addUser != null) {
			// 添加的用户已经存在
			Tool.serviceException(resultBean, MsgConfig.RECORDS_EXIST);
			return;

		} else {
			try {
				addNum = userDao.insertUser(userBean);

			} catch (Exception e) {
				LogUtil.loggerEntrance(LogUtil.WEB_LOG,MsgConfig.DB_EXCP+": \n");
				e.printStackTrace();
				throw new SystemException(MsgConfig.SYS_EXCP);
			}

			if (addNum > 0) {
				resultBean.setStatus(Config.RESULT_SUCCESS);
				// 插入成功，再发送邮件
				try {
					//同步到redis
					//发送邮件
					MailUtil.sendEmail(userBean.getUserMail(), addPwd);
					//添加到redis
//					UserBean redisBean = new UserBean();
//					redisBean.setUserId(userBean.getUserId());
//					redisBean.setRole(userBean.getRole());
//					redisBean.setUserStatus(userBean.getUserStatus());
//					redisBean.setSecretKey(userBean.getSecretKey());
					//userId,role,userStatus,secretKey有意义
					userBean.setPassword(null);
					redisService.saveOrUpdate(userBean.getUserId(), userBean);
				} catch (Exception e) {
					LogUtil.loggerEntrance(LogUtil.WEB_LOG,MsgConfig.MAIL_EXCP+": \n");
					e.printStackTrace();
					throw new SystemException(MsgConfig.SYS_EXCP);
				}
			} else {
				Tool.serviceException(resultBean, MsgConfig.FAIL_MESSAGE);
				return;
			}

		}

		resultBean.setMessage(MsgConfig.MAIL_MSG+userBean.getUserMail());
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "add user :" + userBean.getUserName() + "," + addNum);

	}

	/**
	 * 
	 * @Title: delUser
	 * @Description: 删除用户
	 * @param userBean
	 * @param resultBean
	 * @Date:2016年5月25日
	 */
	@Transactional(timeout = 2)
	public void delUser(UserBean userBean, ResultBean resultBean) {
		//只有管理员才会删除用户，用户自己不能删除自己(不包括管理员)
		try {
			if(!uImpl.authorityValidate(userBean.getUserId())){
				Tool.serviceException(resultBean, MsgConfig.AUTHORITY_NO);
				return;
			}
		} catch (Exception e) {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG,MsgConfig.SYS_EXCP+": \n");
			e.printStackTrace();
			throw new SystemException(MsgConfig.SYS_EXCP);
		}
		//如果userId是管理员，则不能删除自己
		if(userBean.getUserId() !=null && userBean.getUserId().equals(userBean.getId())){
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, MsgConfig.DEL_NO);
			Tool.serviceException(resultBean, MsgConfig.DEL_NO);
			return;
		}
		int deleteNum = 0;
		try {
			AppReqBean appReqBean = new AppReqBean();
			//根据用户ID，获取所有应用，并在redis中删除
			appReqBean.setUserId(userBean.getId());
			appReqBean.setRole(Config.ROLE_COMMON);
			appReqBean.setPageIndex(-1);
			ArrayList<AppInfoBean> appList = appInfoBeanDao.getAppList(appReqBean);
			ArrayList<String> keys = new ArrayList<String>();
			for (AppInfoBean appInfoBean : appList) {
				keys.add(appInfoBean.getAppId());
			}

			//删除用户
			deleteNum = userDao.deleUser(userBean);
			redisService.delete(userBean.getId());
			//删除redis中用户下面的应用，数据库中的应用通过外键级联自动删除
			redisService.delete(keys);
		} catch (Exception e) {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG,MsgConfig.DB_EXCP+": \n");
			e.printStackTrace();
			throw new SystemException(MsgConfig.SYS_EXCP);
		}

		if (deleteNum <= 0) {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG,"delete user result is :"+deleteNum);
			Tool.serviceException(resultBean, MsgConfig.FAIL_MESSAGE);
			return;
		}
	}

	/**
	 * 
	 * @Title: delUser
	 * @Description: 更新密码
	 * @param userBean
	 * @param resultBean
	 * @Date:2016年5月25日
	 */
	@Transactional(timeout = 2)
	public void updatePwd(UserBean userBean, ResultBean resultBean) {
		//目前只有自己修改自己的密码
		if(!userBean.getUserId().equals(userBean.getId())){
			Tool.serviceException(resultBean, MsgConfig.AUTHORITY_NO);
			return;
		}
		try {
			
			if(userDao.checkPwdId(userBean) == null){
				Tool.serviceException(resultBean, MsgConfig.OLD_PWD_FAIL);
				return;
				
			}
			
			if (userDao.updateUser(userBean)<= 0) {
				Tool.serviceException(resultBean, MsgConfig.FAIL_MESSAGE);
				return;
			}
		} catch (Exception e) {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG,MsgConfig.DB_EXCP+": \n");
			e.printStackTrace();
			throw new SystemException(MsgConfig.SYS_EXCP);
		}
	}
	
	
	/**
	 * 
	 * @Title: updateUser
	 * @Description: 更新用户
	 * @param userBean
	 * @param resultBean
	 * @Date:2016年5月25日
	 */
	@Transactional(timeout = 2)
	public void updateUser(UserBean userBean, ResultBean resultBean) {
		try {
			
			//判断是否关联指定oa账号
			if (!"".equals(userBean.getOaUserName()) && userBean.getOaUserName() != null) {
				ArrayList<UserBean> userList = userDao.selectUserByOA(userBean);
				if (userList !=null && userList.size()>0 ) {
					Tool.serviceException(resultBean, MsgConfig.FORBID_REPEAT_OA);
					LogUtil.loggerEntrance(LogUtil.WEB_LOG, MsgConfig.FORBID_REPEAT_OA+",userName:"+userBean.getUserName()+",oaUserName:"+userBean.getOaUserName());
					return;
				}
			}

			
			if (userDao.updateUser(userBean)<= 0) {
				Tool.serviceException(resultBean, MsgConfig.FAIL_MESSAGE);
				return;
			}
			UserBean user = userDao.getUserById(userBean.getId());
			redisService.saveOrUpdate(userBean.getId(), user);
		} catch (Exception e) {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG,MsgConfig.DB_EXCP+": \n");
			e.printStackTrace();
			throw new SystemException(MsgConfig.SYS_EXCP);
		}
		
	}

	/**
	 * 
	 * @Title: resetPassword
	 * @Description: 重置密码
	 * @param userBean
	 * @param resultBean
	 * @Date:2016年5月25日
	 */
	public void resetPassword(UserBean ReqUserBean, ResultBean resultBean) {

		UserBean userBean = new UserBean();
		try {
			userBean = userDao.getUserById(ReqUserBean.getId());
		} catch (Exception e) {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG,MsgConfig.DB_EXCP+": \n");
			e.printStackTrace();
			throw new SystemException(MsgConfig.SYS_EXCP);
		}

		if (userBean == null ) {
			// 用户在数据库中不存在
			Tool.serviceException(resultBean, MsgConfig.NO_RECORDS_MSG);
			return;
		}
		// 获取用户名和新密码
		String mail = userBean.getUserMail();
		String newPassword = Tool.getRandomString(8);
		int retFlag = 0;

		// 将重置的密码发送到用户邮箱
		try {
			MailUtil.sendEmail(mail, newPassword);
		} catch (Exception e1) {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG,MsgConfig.DB_EXCP+": \n");
			e1.printStackTrace();
			throw new SystemException(MsgConfig.SYS_EXCP);
		}
		// 只有当邮箱发送成功后，才能修改数据
		// 密码加密
		String encodePwd = Md5Util.encrypt(newPassword);
		userBean.setUpdatePassword(encodePwd);
		userBean.setId(ReqUserBean.getId());
		try {
			//密码没有存在redis中，所以不需要更新redis数据
			retFlag = userDao.updateUser(userBean);
		} catch (Exception e) {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG,MsgConfig.DB_EXCP+": \n");
			e.printStackTrace();
			throw new SystemException(MsgConfig.SYS_EXCP);
		}

		if (retFlag <= 0) {
			Tool.serviceException(resultBean, MsgConfig.FAIL_MESSAGE);
		}
	}
	
}
