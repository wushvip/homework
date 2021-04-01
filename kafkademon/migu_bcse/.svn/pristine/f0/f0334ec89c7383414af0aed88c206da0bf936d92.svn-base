package com.chinamobile.cmss.bcse.user.controller;


import javax.annotation.Resource;
import javax.ws.rs.BeanParam;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.app.bean.AppReqBean;
import com.chinamobile.cmss.bcse.app.service.ApplicationManagerService;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;
import com.chinamobile.cmss.bcse.user.bean.UserBean;
import com.chinamobile.cmss.bcse.user.bean.UserBean.addGroup;
import com.chinamobile.cmss.bcse.user.bean.UserBean.loginGroup;
import com.chinamobile.cmss.bcse.user.bean.UserBean.upPwdGroup;
import com.chinamobile.cmss.bcse.user.bean.UserBean.updateGroup;
import com.chinamobile.cmss.bcse.user.service.IUserService;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupA;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupG;
import com.chinamobile.cmss.bcse.validate.authority.impl.RequestAuthorization;

/**
 * 
 * @ClassName: UserController 
 * @Description: 用户管理模块
 * @author: lijingjing
 * @date: 2017年3月7日 下午4:40:27
 */
@Controller
public class UserController {
	@Resource
	private IUserService iUserService;
	@Resource
	private RequestAuthorization authService;
	@Resource
	private ApplicationManagerService applicationManagerService;

	/**
	 * 
	 * @Title: login
	 * @Description: 用户登录
	 * @param userBean
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResultBean login(@BeanParam @Validated(loginGroup.class) UserBean userReqBean,BindingResult result) {
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "login start,userName:"+userReqBean.getUserName());
		
		ResultBean resultBean = new ResultBean();
		if (result.hasErrors()) {
			Tool.validateParamsError(resultBean, result);
			return resultBean;
		}
		try {
			iUserService.userLogin(userReqBean, resultBean);

		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}
	
	/**
	 * 
	 * @Title: login
	 * @Description: oa用户登录
	 * @param userBean
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/oaLogin", method = RequestMethod.GET)
	public ResultBean oaLogin(@BeanParam @Validated(loginGroup.class) UserBean userReqBean,BindingResult result) {
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "oa login start,userName:"+userReqBean.getUserName());
		
		ResultBean resultBean = new ResultBean();
		if (result.hasErrors()) {
			Tool.validateParamsError(resultBean, result);
			return resultBean;
		}
		try {
			iUserService.oaUserLogin(userReqBean, resultBean);

		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}

	/**
	 * 
	 * @Title: userShow
	 * @Description: 获取用户列表
	 * @param userReqBean
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResultBean show(@BeanParam @Validated(GroupA.class)  UserBean userReqBean, BindingResult result) {

		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "get user LIST,userId:"+userReqBean.getUserId());
		ResultBean resultBean = new ResultBean();
		if (result.hasErrors()) {
			Tool.validateParamsError(resultBean, result);
			return resultBean;
		}
		

		try {
			if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(userReqBean)), false)) {

				Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);

				return resultBean;

			}
			iUserService.showUserList(userReqBean, resultBean);

		} catch (Exception e) {

			Tool.operateExceptionInfo(resultBean, e);
		}

		return resultBean;
	}

	/**
	 * 
	 * @Title: add
	 * @Description: 添加用户
	 * @param userReqBean
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResultBean add(@RequestBody @Validated(addGroup.class) UserBean userReqBean, BindingResult result) {
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "ADD USER");

		ResultBean resultBean = new ResultBean();
		if (result.hasErrors()) {
			Tool.validateParamsError(resultBean, result);
			return resultBean;
		}

		try {
			if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(userReqBean)), false)) {

				Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);

				return resultBean;

			}
			iUserService.addUser(userReqBean, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}

		return resultBean;
	}

	/**
	 * 
	 * @Title: delete
	 * @Description: 删除用户
	 * @param userBean
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResultBean delete(@BeanParam @Validated({GroupA.class,GroupG.class}) UserBean userBean, BindingResult result) {
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "del USER");
		ResultBean resultBean = new ResultBean();

		if (result.hasErrors()) {
			Tool.validateParamsError(resultBean, result);
			return resultBean;
		}

		try {
			if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(userBean)), false)) {

				Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);

				return resultBean;

			}
			iUserService.delUser(userBean, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}
		return resultBean;
	}

	/**
	 * 
	 * @Title: resetPassword
	 * @Description: 重置密码
	 * @param userBean
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/users/{id}/password", method = RequestMethod.DELETE)
	public ResultBean resetPwd(@BeanParam @Validated({GroupA.class,GroupG.class}) UserBean userBean, BindingResult result) {
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "resetPassword USER");
		ResultBean resultBean = new ResultBean();
		if (result.hasErrors()) {
			Tool.validateParamsError(resultBean, result);
			return resultBean;
		}

		try {
			if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(userBean)), false)) {

				Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);

				return resultBean;

			}
			iUserService.resetPassword(userBean, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}

		return resultBean;
	}

	/**
	 * 
	 * @Title: updatePwd 
	 * @Description: 更新密码
	 * @param userBean
	 * @param result
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/users/{id}/password", method = RequestMethod.PUT)
	public ResultBean updatePwd(@PathVariable("id") String id,@RequestBody @Validated(upPwdGroup.class) UserBean userBean, BindingResult result) {
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "UPDATE USER PASSWORD");
		ResultBean resultBean = new ResultBean();
		if (result.hasErrors()) {
			Tool.validateParamsError(resultBean, result);
			return resultBean;
		}
		try {
			userBean.setId(id);
			if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(userBean)), false)) {

				Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);

				return resultBean;

			}
			iUserService.updatePwd(userBean, resultBean);
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}

		return resultBean;
	}

	/**
	 * 
	 * @Title: updateUser
	 * @Description: 更新用户
	 * @param userBean
	 * @return
	 * @return: ResultBean
	 */
	@ResponseBody
	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	public ResultBean update(@PathVariable("id") String id,@RequestBody  @Validated({updateGroup.class}) UserBean userBean, BindingResult result) {
		ResultBean resultBean = new ResultBean();
		if (result.hasErrors()) {
			Tool.validateParamsError(resultBean, result);
			return resultBean;
		}
		try {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, "UPDATE USERINFO");
			userBean.setId(id);
			if (!authService.paramCheck(JSONObject.parseObject(JSON.toJSONString(userBean)), false)) {

				Tool.serviceException(resultBean, MsgConfig.PARAMS_CHECK);

				return resultBean;

			}
			iUserService.updateUser(userBean, resultBean);
			if (userBean.getUserStatus() != null) {
				AppReqBean appReqBean = new AppReqBean();
				appReqBean.setUserId(userBean.getId());
				if (userBean.getUserStatus().equals(Config.USER_STOP_STATUS)) {
					appReqBean.setAppStatus(Config.APP_STOP_STATUS);
				} else {
					appReqBean.setAppStatus(Config.APP_NORMAL_STATUS);
				}
				applicationManagerService.stopOrOpenApp(appReqBean);
			}
		} catch (Exception e) {
			Tool.operateExceptionInfo(resultBean, e);
		}

		return resultBean;
	}

}
