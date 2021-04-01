package com.chinamobile.cmss.bcse.app.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.app.bean.AppFieldBean;
import com.chinamobile.cmss.bcse.app.bean.AppInfoBean;
import com.chinamobile.cmss.bcse.app.bean.AppReqBean;
import com.chinamobile.cmss.bcse.app.bean.AppRspBean;
import com.chinamobile.cmss.bcse.app.bean.AppTableMapBean;
import com.chinamobile.cmss.bcse.app.dao.AppFieldBeanDao;
import com.chinamobile.cmss.bcse.app.dao.AppInfoBeanDao;
import com.chinamobile.cmss.bcse.app.dao.AppTableMapBeanDao;
import com.chinamobile.cmss.bcse.app.service.ApplicationManagerService;
import com.chinamobile.cmss.bcse.config.service.impl.FieldsServiceImpl;
import com.chinamobile.cmss.bcse.config.service.impl.RedisService;
import com.chinamobile.cmss.bcse.datastatistics.bean.DataInfoBean;
import com.chinamobile.cmss.bcse.datastatistics.service.IDataInfoService;
import com.chinamobile.cmss.bcse.datastatistics.service.ISearchTimesService;
import com.chinamobile.cmss.bcse.index.bean.AppDataHistoryBean;
import com.chinamobile.cmss.bcse.index.dao.IndexDataHistoryDao;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.exception.SystemException;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;
import com.chinamobile.cmss.bcse.validate.authority.ValidatorApi;


/**
 * 
 * @ClassName: ApplicationManagerServiceImpl
 * @Description: 应用管理的service层代码
 * @author: jinjing
 * @date: 2016年2月17日 上午10:42:38
 */
@Service("applicationManagerService")
public class ApplicationManagerServiceImpl implements ApplicationManagerService {

	@Resource
	private IndexEntry indexEntry;
	@Resource
	private AppInfoBeanDao appInfoBeanDao = null;
	@Resource
	private AppTableMapBeanDao appTableMapBeanDao = null;
	@Resource
	private AppFieldBeanDao appFieldBeanDao = null;
	@Resource
	private ISearchTimesService searchTimesService = null;
	@Resource
	private IDataInfoService dataInfoService = null;
	@Resource
	private ValidatorApi uImpl;
	@Resource
	private ValidatorApi appImpl;
	@Resource
	private RedisService redisService;
	@Resource
	private IndexDataHistoryDao indexDataHistoryDao = null;
	@Resource
	private FieldsServiceImpl fieldsService;

	/**
	 * @Title: modifyAppStatus
	 * @Description: 启动或者停用用户的应用
	 * 更新用户状态需要调用的逻辑
	 * @param appReqBean
	 * @return
	 * @return: AppRspBean
	 */
	@Override
	public void stopOrOpenApp(AppReqBean appReqBean) {

		try {
			
			appReqBean.setOperaStatus(Config.APP_ING_STATUS);
			appInfoBeanDao.StopOrOpenAppStatus(appReqBean);
			//同步更新redis
			appReqBean.setRole(Config.ROLE_COMMON);
			appReqBean.setPageIndex(-1);
			ArrayList<AppInfoBean> appList = appInfoBeanDao.getAppList(appReqBean);
			ArrayList<String> keys = new ArrayList<String>();
			for (AppInfoBean appInfoBean : appList) {
				keys.add(appInfoBean.getAppId());
			}
			redisService.delete(keys);
		} catch (Exception e) {
			LogUtil.loggerEntrance(LogUtil.APP_LOG,MsgConfig.SYS_EXCP);
			e.printStackTrace();
			throw new SystemException(MsgConfig.SYS_EXCP);
		}
	}

	/**
	 * 
	 * @Title: showAppList
	 * @Description: 展示应用列表
	 * @param appReqBean
	 * @param resultBean
	 * @Date:2016年5月30日
	 */
	public void showAppList(AppReqBean appReqBean, ResultBean resultBean) {

		if (appReqBean.getPageIndex() < 1 || (appReqBean.getPageNum() < 1 && appReqBean.getPageNum() != -1)) {
			Tool.serviceException(resultBean, MsgConfig.PAGE_RAGE_MSG);
			return;
		}
			
		try {
			if(!uImpl.authorityValidate(appReqBean.getUserId())){
				appReqBean.setRole(Config.ROLE_COMMON);
			}
		} catch (Exception e) {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG,MsgConfig.SYS_EXCP+": \n");
			e.printStackTrace();
			throw new SystemException(MsgConfig.SYS_EXCP);
		}

		//创建中的应用不被查询出
		appReqBean.setAppStatus(Config.APP_ING_STATUS);
		// 添加分页操作
		// 起始条数
		appReqBean.setStartNum((appReqBean.getPageIndex() - 1) * appReqBean.getPageNum());
		int totalItems = 0;
		ArrayList<AppInfoBean> appList = null;
		try {
			appList = appInfoBeanDao.getAppList(appReqBean);
			totalItems = appInfoBeanDao.getTotalItemsNum(appReqBean);
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, "totalItems num is :" + totalItems);
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, "appList list is :" + appList);
			// 获取当前应用的数据
			for (AppInfoBean appInfoBean : appList) {
				// 获取昨日搜索次数
				int searchTimes = searchTimesService.yesterdaySearchTimes(appInfoBean.getUserId(), appInfoBean.getAppId());
				appInfoBean.setSearchNum(searchTimes);
				// 昨日每秒搜索请求量峰值
				int maxSearchTimes = searchTimesService.maxSearchTimes(appInfoBean.getUserId(), appInfoBean.getAppId());
				appInfoBean.setMaxSearchPerSec(maxSearchTimes);
			}
		} catch (Exception e) {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG,MsgConfig.SYS_EXCP+": \n");
			e.printStackTrace();
			throw new SystemException(MsgConfig.SYS_EXCP);
		}
		JSONObject resultJson = new JSONObject();
		resultJson.put("appList", appList);
		resultJson.put("totalItems", totalItems);
		resultBean.setResult(resultJson);
	}

	/**
	 * 
	 * @Title: delete
	 * @Description: 删除应用
	 * @param appReqBean
	 * @return
	 * @return: AppRspBean
	 */
	@Transactional(timeout = 2)
	@Override
	public void delete(AppReqBean appReqBean, ResultBean resultBean) {

		// 数据处理接口的删除索引
		try {
			
			if (!(indexEntry.deleteIndex(appReqBean, resultBean))) {
				Tool.serviceException(resultBean, MsgConfig.FAIL_MESSAGE);
				LogUtil.loggerEntrance(LogUtil.APP_LOG, "delete index is fail,appId="+appReqBean.getAppId());
				return;
			}
		} catch (Exception e) {
			LogUtil.loggerEntrance(appReqBean.getUserId(), appReqBean.getAppId(),Config.FAIL_SYS_CODE, LogUtil.WEB_LOG,e);
			throw new SystemException(MsgConfig.SYS_EXCP);
		}

		// 当索引删除成功之后再去删除数据库中对应的应用信息
		try {
			appInfoBeanDao.DeleteAppDetail(appReqBean);
			redisService.deleteApp(appReqBean.getAppId());
		} catch (Exception e) {
			LogUtil.loggerEntrance(appReqBean.getUserId(), appReqBean.getAppId(),Config.FAIL_SYS_CODE, LogUtil.WEB_LOG,e);
			throw new SystemException(MsgConfig.SYS_EXCP);
		}
	}

	/**
	 * 
	 * @Title: update
	 * @Description: 更新应用
	 * @param appReqBean
	 * @param resultBean
	 * @Date:2016年5月30日
	 */
	@Transactional(timeout = 2)
	@Override
	public void update(AppReqBean appReqBean, ResultBean resultBean) {

		int updateNum = 0;
		try {
			updateNum = appInfoBeanDao.modifyAppStatus(appReqBean);
			AppInfoBean result = appInfoBeanDao.showSignAppDetail(appReqBean);
			if (result != null) {
					redisService.saveOrUpdate(result.getAppId(), result);
			}
		} catch (Exception e) {
			LogUtil.loggerEntrance(appReqBean.getUserId(), appReqBean.getAppId(),Config.FAIL_SYS_CODE, LogUtil.WEB_LOG,e);
			throw new SystemException(MsgConfig.SYS_EXCP);
		}

		if (updateNum <= 0) {
			Tool.serviceException(resultBean, MsgConfig.FAIL_MESSAGE);
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, "the result of update app sql is not greater than 1,result:" + updateNum);
			return;
			
		}

	}

	/**
	 * 
	 * @Title: showBasicInfo
	 * @Description: 展示应用基本信息
	 * @param appReqBean
	 * @param resultBean
	 * @Date:2016年5月30日
	 */
	public void showBasicInfo(AppReqBean appReqBean, ResultBean resultBean) {

		String userId = appReqBean.getUserId();
		String appId = appReqBean.getAppId();
		AppInfoBean result = null;
		DataInfoBean numBean = null;
		try {
			result = appInfoBeanDao.showSignAppDetail(appReqBean);
			// 查询当前应用的数据量(昨日的数据量)
			numBean = dataInfoService.dataNumberNew(userId, appId);
		} catch (Exception e) {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG,MsgConfig.SYS_EXCP+": \n");
			e.printStackTrace();
			throw new SystemException(MsgConfig.SYS_EXCP);
		}

		if (numBean == null) {
			result.setDataNum(0);
		} else {
			result.setDataNum(numBean.getSize());
		}
		// 获取昨日搜索次数
		try {
			int searchTimes = searchTimesService.yesterdaySearchTimes(userId, appId);
			result.setSearchNum(searchTimes);
			// 昨日每秒搜索请求量峰值
			int maxSearchTimes = searchTimesService.maxSearchTimes(userId, appId);
			result.setMaxSearchPerSec(maxSearchTimes);
		} catch (Exception e) {
			LogUtil.loggerEntrance(appReqBean.getUserId(), appReqBean.getAppId(),Config.FAIL_SYS_CODE, LogUtil.WEB_LOG,e);
			throw new SystemException(MsgConfig.SYS_EXCP);
		}
		JSONObject resultJson = new JSONObject();
		resultJson.put("info", result);
		resultBean.setResult(resultJson);

	}

	/**
	 * 
	 * @Title: showStructure
	 * @Description: 展示应用数据结构
	 * @param appReqBean
	 * @param resultBean
	 * @Date:2016年5月30日
	 */
	public void showStructure(AppReqBean appReqBean, ResultBean resultBean) {
		JSONObject resultJson = new JSONObject();
		// 获取应用基本信息
		ArrayList<AppInfoBean> appInfoBeans = new ArrayList<AppInfoBean>();
		ArrayList<AppTableMapBean> appTableMapBeans = new ArrayList<AppTableMapBean>();
		try {
			AppInfoBean app = new AppInfoBean();
			app = appInfoBeanDao.showSignAppDetail(appReqBean);
			if (app == null) {
				Tool.serviceException(resultBean, MsgConfig.NO_RECORDS_MSG);
				LogUtil.loggerEntrance(LogUtil.WEB_LOG, "appId="+appReqBean.getAppId()+","+MsgConfig.NO_RECORDS_MSG);
				return;
			}
			appInfoBeans.add(app);
			// 获取数据表信息
			appTableMapBeans = appTableMapBeanDao.selectByAppId(appReqBean.getAppId());

			/**
			 * 获取每张表的字段信息
			 */
			for (AppTableMapBean appTableMapBean : appTableMapBeans) {
				appTableMapBean.setFields(appFieldBeanDao.selectByTableId(appTableMapBean.getTableId()));
			}

		} catch (Exception e) {
			LogUtil.loggerEntrance(appReqBean.getUserId(), appReqBean.getAppId(),Config.FAIL_SYS_CODE, LogUtil.WEB_LOG,e);
			throw new SystemException(MsgConfig.SYS_EXCP);
		}
		resultJson.put("appList", appInfoBeans);
		resultJson.put("appTableList", appTableMapBeans);
		resultBean.setResult(resultJson);

	}

	/**
	 * @Title: addAppInfoDetail
	 * @Description: 添加应用基本信息
	 * @param appReqBean
	 * @return
	 * @return: AppRspBean
	 */
	@Transactional(timeout = 2)
	@Override
	public void addAppInfoDetail(AppReqBean appReqBean, ResultBean resultBean) {

		appReqBean.setCreateDate(Tool.getCurrentDateHour());
		//应用创建中
		appReqBean.setAppStatus(Config.APP_ING_STATUS);
		appReqBean.setCreateDate(Tool.getCurrentDateHour());
//		appReqBean.setAppTempFlag("0");

		// 返回到前端的结果json
		AppRspBean appRspBean = new AppRspBean();
		// 获取唯一标识
		appReqBean.setAppId(Tool.getUuid());
		// 是否存在当前要插入的应用名
		ArrayList<AppInfoBean> appList = null;
		// 判断是否存在当前应用名
		// 验证当前用户下是否有当前要添加的应用名，查询应用信息表即可(同一用户下应用名不能重复)
		appList = appInfoBeanDao.isExistApp(appReqBean);
		if (appList != null && appList.size() > 0) {
			Tool.serviceException(resultBean, MsgConfig.RECORDS_EXIST);
			return;
		}
		try {
			//删除除当前应用名的其他创建中的应用
			appInfoBeanDao.DeleteCreating(appReqBean);
			if (appInfoBeanDao.addAppInfoDetail(appReqBean) <= 0) {
				Tool.serviceException(resultBean, MsgConfig.FAIL_MESSAGE);
				LogUtil.loggerEntrance( LogUtil.WEB_LOG,"the result of add appinfo sql is not greater than 1");
				return;
				
			} 

		} catch (Exception e) {
			LogUtil.loggerEntrance(appReqBean.getUserId(), appReqBean.getAppId(),Config.FAIL_SYS_CODE, LogUtil.WEB_LOG,e);
			throw new SystemException(MsgConfig.SYS_EXCP);
		}

		appRspBean.setAppId(appReqBean.getAppId());

		resultBean.setResult(appRspBean);
	}

	/**
	 * @Title: updateAppInfoDetail
	 * @Description: 更新应用基本信息
	 * @param appReqBean
	 * @return
	 * @return: AppRspBean
	 */
	@Transactional(timeout = 2)
	@Override
	public void updateAppInfoDetail(AppReqBean appReqBean, ResultBean resultBean) {

		appReqBean.setCreateDate(Tool.getCurrentDateHour());
		appReqBean.setAppStatus(Config.APP_ING_STATUS);
		appReqBean.setCreateDate(Tool.getCurrentDateHour());
//		appReqBean.setAppTempFlag("0");

		ArrayList<AppInfoBean> appList = null;
		// 判断是否存在当前应用名
		// 验证当前用户下是否有当前要添加的应用名，查询应用信息表即可(同一用户下应用名不能重复)
		appList = appInfoBeanDao.isExistApp(appReqBean);
		if (appList != null && appList.size() > 0) {
			Tool.serviceException(resultBean, MsgConfig.RECORDS_EXIST);
			return;
		}
		// 返回到前端的结果json
		//删除除当前应用名的其他创建中的应用
//		appInfoBeanDao.DeleteCreating(appReqBean);
		if (appInfoBeanDao.updateAppDetail(appReqBean) <= 0) {
			Tool.serviceException(resultBean, MsgConfig.FAIL_MESSAGE);
			LogUtil.loggerEntrance( LogUtil.WEB_LOG,"the result of update appinfo sql is not greater than 1 ");
			return;
		}
	}

	/**
	 * @Title: saveAppStructure
	 * @Description: 保存应用结构
	 * @param appReqBean
	 * @return
	 * @return: AppRspBean
	 */
	@Transactional(timeout = 2)
	@Override
	public void saveAppStructure(AppReqBean appReqBean, ResultBean resultBean) {

		AppRspBean appRspBean = new AppRspBean();
		try {
			//删除原来的应用结构
			deleteAppStructure(appReqBean, resultBean);
			/**
			 * 数据表集合
			 */
			// 获取数据表的表级别的json数据
			ArrayList<AppTableMapBean> appTableMapBeans = appReqBean.getAppTableList();
			/**
			 * 插入每一张表，以及每张表的字段
			 */
			for (AppTableMapBean appTableMapBean : appTableMapBeans) {

				// 插入应用的表信息
				String tableUuid = Tool.getUuid();
				appTableMapBean.setTableId(tableUuid);
				appTableMapBean.setAppId(appReqBean.getAppId());
				// 插入APP_TABLE_MAPPING表
				appTableMapBeanDao.insert(appTableMapBean);

				// ArrayList<AppFieldBean> hasForeignKey = new
				// ArrayList<AppFieldBean>();
				/**
				 * 插入field信息,先插入没有外键信息的
				 */
				ArrayList<AppFieldBean> appFieldBeans = appTableMapBean.getFields();
				for (AppFieldBean appFieldBean : appFieldBeans) {
					appFieldBean.setCreateDate(Tool.getCurrentDateHour());
					appFieldBean.setId(Tool.getUuid());
					appFieldBean.setTableId(tableUuid);
					if ("1".equals(appFieldBean.getDynamicField())) {
						//是动态字段，需要以*开头
						if (!appFieldBean.getFieldName().startsWith("*")) {
							Tool.serviceException(resultBean, MsgConfig.DYNAMIC_MSG);
							return;
						}
					}
					appFieldBeanDao.insert(appFieldBean);
				}
			}

			/**
			 * 粗排是"0"，精排是"1"，按域检索是"4"
			*/
			// 插入粗排、精排default数据
			/*RuleBeanWithBLOBs ruleBeanWithBLOBs = new RuleBeanWithBLOBs();
			ruleBeanWithBLOBs.setAppId(appReqBean.getAppId());
			ruleBeanWithBLOBs.setId(Tool.getUuid());
			ruleBeanWithBLOBs.setUserId(appReqBean.getUserId());
			ruleBeanWithBLOBs.setCreateDate(new Date());
			ruleBeanWithBLOBs.setRuleType("0");
			ruleBeanWithBLOBs.setStatus(1);
			ruleBeanWithBLOBs.setRuleName("default");
			ruleBeanWithBLOBs.setIncludeFields("all");
			ruleBeanWithBLOBs.setFieldWeights("1");
			ruleBeanDao.insertSelective(ruleBeanWithBLOBs); */
			// 插入精排数据
/*			RuleBeanWithBLOBs ruleBeanWithBLOBCare = new RuleBeanWithBLOBs();
			ruleBeanWithBLOBCare.setAppId(appReqBean.getAppId());
			ruleBeanWithBLOBCare.setId(Tool.getUuid());
			ruleBeanWithBLOBCare.setUserId(appReqBean.getUserId());
			ruleBeanWithBLOBCare.setCreateDate(new Date());
			ruleBeanWithBLOBCare.setRuleType("1");
			ruleBeanWithBLOBCare.setStatus(1);
			ruleBeanWithBLOBCare.setRuleName("default");

			ruleBeanDao.insertSelective(ruleBeanWithBLOBCare);*/

			/**
			 * 插入索引集合(高级配置中的按域检索)
			 */
			/*ArrayList<RuleBeanWithBLOBs> ruleBeanWithBLOBList = appReqBean.getRuleBeanList();
			for (RuleBeanWithBLOBs ruleBeanWithBLOB : ruleBeanWithBLOBList) {
				// 插入appId
				ruleBeanWithBLOB.setAppId(appReqBean.getAppId());
				ruleBeanWithBLOB.setCreateDate(new Date());
				ruleBeanWithBLOB.setId(Tool.getUuid());
				ruleBeanWithBLOB.setRuleType("4");
				ruleBeanDao.insertSelective(ruleBeanWithBLOB);

			}*/
			appRspBean.setOperaType(appReqBean.getOperaType());

		} catch (Exception e) {
			appRspBean.setOperaType(appReqBean.getOperaType());
			LogUtil.loggerEntrance(appReqBean.getUserId(), appReqBean.getAppId(),Config.FAIL_SYS_CODE, LogUtil.WEB_LOG,e);
			throw new SystemException(MsgConfig.SYS_EXCP);
		}

	}

	/**
	 * @Title: createApp
	 * @Description: 创建应用
	 * @param appReqBean
	 * @return
	 * @return: AppRspBean
	 */
	@Transactional(timeout = 2)
	@Override
	public void createApp(AppReqBean appReqBean, ResultBean resultBean) {
		// 创建索引接口
		boolean createResult = false;
		try {
			createResult = indexEntry.createIndex(appReqBean);
		} catch (Exception e) {
			LogUtil.loggerEntrance(appReqBean.getUserId(), appReqBean.getAppId(),Config.FAIL_SYS_CODE, LogUtil.APP_LOG,e);
			throw new SystemException(MsgConfig.SYS_EXCP);
		}

		int updateNum = 0;
		// 索引创建如果成功，则将数据插入到数据库
		if (!createResult) {
			Tool.serviceException(resultBean, MsgConfig.FAIL_MESSAGE);
			return;
		}
		appReqBean.setAppStatus(Config.APP_NORMAL_STATUS);
		updateNum = appInfoBeanDao.modifyAppStatus(appReqBean);

		if (updateNum <=0 ) {
			Tool.serviceException(resultBean, MsgConfig.FAIL_MESSAGE);
			return;
		} 
		AppInfoBean result = appInfoBeanDao.showSignAppDetail(appReqBean);
		try {
			if (result != null) {
				redisService.saveOrUpdate(result.getAppId(), result);
			}
			fieldsService.addSelectHandler(appReqBean.getAppId(), appReqBean.getUserId());
		} catch (Exception e) {
			LogUtil.loggerEntrance(appReqBean.getUserId(), appReqBean.getAppId(),Config.FAIL_SYS_CODE, LogUtil.APP_LOG,e);
			throw new SystemException(MsgConfig.SYS_EXCP);
		}
		
		
	}

	/**
	 * @Title: isExistCreatingApp
	 * @Description: 是否存在创建中应用
	 * @param appReqBean
	 * @return
	 * @return: AppRspBean
	 */
	@Override
	public void isExistCreatingApp(AppReqBean appReqBean, ResultBean resultBean) {

		appReqBean.setAppStatus(Config.APP_ING_STATUS);
		ArrayList<AppInfoBean> result = new ArrayList<AppInfoBean>();
		try {
			result = appInfoBeanDao.getAppListByStatus(appReqBean);
		} catch (Exception e) {
			LogUtil.loggerEntrance(LogUtil.WEB_LOG,MsgConfig.SYS_EXCP+": \n");
			e.printStackTrace();
			throw new SystemException(MsgConfig.SYS_EXCP);
		}

		JSONObject dataJson = new JSONObject();
		if (result != null && result.size()>0) {
			// 说明当前用户存在创建中的应用
			dataJson.put("isExist", "1");
			dataJson.put("appInfoDetail", result.get(0));

		} else {
			dataJson.put("isExist", "0");
			dataJson.put("appInfoDetail", result);

		}
		resultBean.setResult(dataJson);

	}

	/**
	 * 
	 * @Title: updateAppStructure
	 * @Description: 更新应用结构
	 * @param appReqBean
	 * @param resultBean
	 * @return: void
	 */
	@Override
	public void updateAppStructure(AppReqBean appReqBean, ResultBean resultBean) {
		try {
			deleteAppStructure(appReqBean, resultBean);
			if (resultBean.getStatus().equals(Config.RESULT_FAIL)) {
				return;
			}
			saveAppStructure(appReqBean, resultBean);
		} catch (Exception e) {
			throw e;
		}

	}

	
	/**
	 * 
	 * @Title: showIndexOper 
	 * @Description: 获取索引处理记录
	 * @param appReqBean
	 * @param resultBean
	 * @return: void
	 */
	public void showIndexOper(AppReqBean appReqBean, ResultBean resultBean) {

		ArrayList<AppDataHistoryBean> historyList = new ArrayList<AppDataHistoryBean>();
		int totalItems = 0;
		try {
			appReqBean.setEndDate(Tool.getDateTime(appReqBean.getEndDate(), 1));
			appReqBean.setStartNum((appReqBean.getPageIndex() - 1) * appReqBean.getPageNum());
			
			totalItems = indexDataHistoryDao.getTotalItemsHistory(appReqBean);
			historyList = indexDataHistoryDao.getOperaHistroy(appReqBean);
		} catch (Exception e) {
			LogUtil.loggerEntrance(appReqBean.getUserId(), appReqBean.getAppId(),Config.FAIL_SYS_CODE, LogUtil.WEB_LOG,e);
			throw new SystemException(MsgConfig.SYS_EXCP);
		}

		JSONObject resultJson = new JSONObject();
		resultJson.put("historyList", historyList);
		resultJson.put("totalItems", totalItems);
		resultBean.setResult(resultJson);
	}
	/**
	 * @Title: deleteAppStructure
	 * @Description: 删除应用结构
	 * @param appReqBean
	 * @return
	 * @return: boolean
	 */
	@Transactional(timeout = 2)
	@Override
	public void deleteAppStructure(AppReqBean appReqBean, ResultBean resultBean) {
		try {

			/**
			 * 删除数据表，级联删除字段信息
			 */
			appTableMapBeanDao.deleteByAppId(appReqBean.getAppId());
			/**
			 * 删除索引
			 *//*
			ruleBeanDao.deleteRulesByAppId(appReqBean.getAppId(), "4");
			// 删除粗排
			ruleBeanDao.deleteRulesByAppId(appReqBean.getAppId(), "0");
			// 删除精排
			ruleBeanDao.deleteRulesByAppId(appReqBean.getAppId(), "1");*/
		} catch (Exception e) {
			LogUtil.loggerEntrance(appReqBean.getUserId(), appReqBean.getAppId(),Config.FAIL_SYS_CODE, LogUtil.WEB_LOG,e);
			throw new SystemException(MsgConfig.SYS_EXCP);
		}
	}
	
	/**
	 * 
	 * @Title: isNormalApp 
	 * @Description: 是否为可用应用
	 * @param appId
	 * @return
	 * @return: boolean
	 */
	public boolean isUsed(String appId){
		
		try {
			AppInfoBean app = (AppInfoBean)redisService.get(appId);
			if (Config.APP_NORMAL_STATUS.equals(app.getAppStatus())) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("isUsed method exception");
		}

		return false;
	}

}
