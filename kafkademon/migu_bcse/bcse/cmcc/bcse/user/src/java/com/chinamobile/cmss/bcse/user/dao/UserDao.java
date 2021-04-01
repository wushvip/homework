package com.chinamobile.cmss.bcse.user.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.chinamobile.cmss.bcse.user.bean.UserBean;


/**
 * 
 * @ClassName: UserDao 
 * @Description: dao操作
 * @author: lijingjing
 * @date: 2017年3月7日 下午4:55:21
 */
public interface UserDao {

	/**
	 * @Title: selectUserName
	 * @Description: 用户登陆，查询用户有消息
	 * @param infoParam
	 * @return
	 * @return: UserBean
	 */
	public UserBean selectUserName(HashMap<String, String> infoParam);
	
	/**
	 * 
	 * @Title: selectUserInfo 
	 * @Description: 获取用户信息
	 * @param userId
	 * @return
	 * @return: UserInfoDataBean
	 */
//	public UserInfoDataBean selectUserInfo(String userId);
	
	/**
	 * 
	 * @Title: checkPwd 
	 * @Description: 验证密码
	 * @param infoParam
	 * @return
	 * @return: UserBean
	 */
	public UserBean checkPwd(UserBean userBean);
	/**
	 * 
	 * @Title: checkPwd 
	 * @Description: 根据id验证用户
	 * @param infoParam
	 * @return
	 * @return: UserBean
	 */
	public UserBean checkPwdId(UserBean userBean);
	
	/** 
	 * @Title: selectUserList 
	 * @Description: TODO 用户列表展示
	 * @param infoParam
	 * @return
	 * @return: ArrayList<UserBean>
	 */
	public ArrayList<UserBean> selectUserList(UserBean userBean);
	
	public ArrayList<UserBean> selectUserByOA(UserBean userBean);
	public ArrayList<UserBean> selectOaUser(UserBean userBean);
	
	/**
	 * 
	 * @Title: checkPwd 
	 * @Description: 根据id获取用户
	 * @param infoParam
	 * @return
	 * @return: UserBean
	 */
	public UserBean getUserById(String userId);
	/**
	 * 
	 * @Title: checkPwd 
	 * @Description: 根据name验证用户
	 * @param infoParam
	 * @return
	 * @return: UserBean
	 */
	public UserBean getUserByName(String userName);
	
	
	/**
	 * 
	 * @Title: getTotalItems 
	 * @Description: 获取全部用户总条数
	 * @param userBean
	 * @return
	 * @return: int
	 */
	public int getTotalItems(UserBean userBean);
	/** 
	 * @Title: selectAddUser 
	 * @Description: 查询要添加的用户是否已存在
	 * @param infoParam
	 * @return
	 * @return: UserBean
	 */
	public ArrayList<UserBean> selectAddUser(UserBean userBean);
	
	/** 
	 * @Title: insertUser 
	 * @Description: TODO 添加用户
	 * @param userBean
	 * @return
	 * @return: int
	 */
	public int insertUser(UserBean userBean);
	
	
	/** 
	 * @Title: deleUser 
	 * @Description: TODO 删除用户
	 * @param infoParam
	 * @return
	 * @return: int
	 */
	public int deleUser(UserBean userBean);
	
	
	/** 
	 * @Title: updateUser 
	 * @Description: TODO 修改用户
	 * @param userBean 
	 * @return
	 * @return: int
	 */
	public int  updateUser(UserBean userBean);
	
	/** 
	 * @Title: obscureSelectUser 
	 * @Description: TODO 模糊查询
	 * @param searchName
	 * @return
	 * @return: ArrayList<UserBean>
	 */
	public ArrayList<UserBean> obscureSelectUser(UserBean userBean);

}