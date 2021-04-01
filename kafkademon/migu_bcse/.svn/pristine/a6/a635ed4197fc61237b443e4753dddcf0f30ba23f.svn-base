package com.chinamobile.cmss.bcse.config.listener;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.chinamobile.cmss.bcse.config.service.impl.RecoveryServiceImpl;
import com.chinamobile.cmss.bcse.config.service.impl.RedisService;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.tools.Md5Util;
import com.chinamobile.cmss.bcse.tool.tools.Tool;
import com.chinamobile.cmss.bcse.user.bean.UserBean;
import com.chinamobile.cmss.bcse.user.dao.UserDao;

/**
 * 
 * @ClassName: ServletListener
 * @Description: TODO
 * @author: Liusong
 * @date: 2017年3月7日 下午5:10:46
 */
public class ServletListener implements ApplicationListener<ContextRefreshedEvent> {
	private static Logger logger = Logger.getLogger(ServletListener.class);
	@Resource
	private RecoveryServiceImpl recoveryServiceImpl;
	@Resource
	private RedisService redisService;
	@Resource
	private UserDao userDao;

	private final String ADMIN = "admin";
	private final String ADMIN_MAIL = "admin@cmcc.com";

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.info("服务启动时加载所有应用的纠错词典");
		logger.info("----------start-------------");
		long start = System.currentTimeMillis();
		try {
			recoveryServiceImpl.train();
			long end = System.currentTimeMillis();
			logger.info("----------time is " + (end - start) + "ms-------------");
			logger.info("----------end-------------");

			// 初始化admin用户
			if (redisService.get(ADMIN) != null) {
				return;
			}
			logger.info("----------first insert admin user-------------");
			UserBean user = new UserBean();
			user.setUserId(ADMIN);
			user.setUserName(ADMIN);
			user.setPassword(Md5Util.encrypt(Config.ADMIN_PWD));
			user.setUserMail(ADMIN_MAIL);
			user.setRole(Config.ROLE_ADMIN);
			user.setUserStatus(Config.USER_NORMAL_STATUS);
			user.setSecretKey(Tool.getUuid());
			userDao.insertUser(user);
			redisService.saveOrUpdate(ADMIN, user);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
