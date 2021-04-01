package com.chinamobile.cmss.bcse.user.service;

import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.user.bean.UserBean;


/**
 * @ClassName: IUserService
 * @Description: 用户管理服务层
 * @author: quanbing
 * @date: 2016年1月29日 下午6:11:17
 */
public interface IUserService {

	/**
	 * @Title: userLogin
	 * @Description: 用户登陆
	 * @param userBean
	 * @return
	 * @return: void
	 */
	public void userLogin(UserBean userBean,ResultBean resultBean);
	public void oaUserLogin(UserBean userBean,ResultBean resultBean);
	
	
	/** 
	 * @Title: getUserList 
	 * @Description: TODO 获取用户列表
	 * @param userReqBean
	 * @return
	 * @return: void
	 * @throws Exception 
	 */
	public void showUserList(UserBean userBean,ResultBean resultBean) throws Exception;
	
	
	/** 
	 * @Title: addUser  添加用户
	 * @Description: TODO
	 * @param userReqBean
	 * @return
	 * @return: void
	 */
	public void addUser(UserBean userBean,ResultBean resultBean) throws Exception;
	
	/** 
	 * @Title: delUser 
	 * @Description: TODO 删除用户
	 * @param userBean
	 * @return
	 * @return: void
	 */
	public void delUser(UserBean userBean,ResultBean resultBean);
	
	
	/** 
	 * @Title: updateUser 
	 * @Description: 更新用户信息
	 * @param userBean
	 * @return
	 * @return: void
	 */
	public void updateUser(UserBean userBean,ResultBean resultBean);
	
	
	public void updatePwd(UserBean userBean,ResultBean resultBean);
	
	
	/** 
	 * @Title: resetPassword 
	 * @Description: TODO 重置密码
	 * @param userBean
	 * @return
	 * @return: void
	 */
	public void resetPassword(UserBean userBean,ResultBean resultBean);
	
}
