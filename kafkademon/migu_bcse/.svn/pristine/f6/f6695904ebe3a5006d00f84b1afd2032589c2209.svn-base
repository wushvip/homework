package com.chinamobile.cmss.bcse.evaluate.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinamobile.cmss.bcse.evaluate.bean.EvaluateAppInfoBean;
import com.chinamobile.cmss.bcse.evaluate.bean.EvaluateAppInfoReqBean;
import com.chinamobile.cmss.bcse.evaluate.bean.EvaluateAppInfoRspBean;
import com.chinamobile.cmss.bcse.evaluate.dao.EvaluateAppInfoBeanDao;
import com.chinamobile.cmss.bcse.evaluate.service.EvaluateAppInfoService;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.tool.exception.SqlOrDatabaseException;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;

/**
 * 
 * @ClassName: EvaluateAppInfoServiceImpl
 * @Description: 评测体系应用信息的service层代码
 * @author: lw
 * @date: 2016年11月29日09:35:14
 */
@Service("evaluateAppInfoService")
public class EvaluateAppInfoServiceImpl implements EvaluateAppInfoService {

	@Resource
	private EvaluateAppInfoBeanDao evaluateAppInfoBeanDao = null;


	/**
	 * 添加/更新评测应用信息表
	 */
	@Transactional(timeout = 2)
	@Override
	public void addAppInfoDetail(EvaluateAppInfoReqBean appReqBean, ResultBean resultBean) {
		//数据操作影响条数
		int insertFlag = 0;
		// 返回到前端的结果json
		EvaluateAppInfoRspBean appRspBean = new EvaluateAppInfoRspBean();
		
		try{
			//判断id是否存在，有则更新表
			if(StringUtils.isNotBlank(appReqBean.getAppId())){
				ArrayList<EvaluateAppInfoBean> existList = evaluateAppInfoBeanDao.isExistApp(appReqBean);
				if(existList != null && existList.size() > 0){
					insertFlag = evaluateAppInfoBeanDao.update(appReqBean);
				}else{
					insertFlag = evaluateAppInfoBeanDao.insert(appReqBean);
				}
			}else{
				// 获取唯一标识
				String newUuid = Tool.getUuid();
				appReqBean.setAppId(newUuid);
				insertFlag = evaluateAppInfoBeanDao.insert(appReqBean);
			}
		} catch (Exception e) {
			LogUtil.loggerEntrance(LogUtil.EVALUATE_LOG, e.getMessage());
//			e.printStackTrace();
			throw new SqlOrDatabaseException(ExceptionConstants.SqlOrDatabaseException);
		}
		if (insertFlag > 0) {
			appRspBean.setAppId(appReqBean.getAppId());
		} else {
			resultBean.setStatus(Config.RESULT_FAIL);
			resultBean.setMessage("保存基本信息sql影响条数为" + insertFlag);
		}

		resultBean.setResult(appRspBean);
	}
	
	
	public EvaluateAppInfoBean getAppInfoById(String id){
		
		ArrayList<EvaluateAppInfoBean> appList = evaluateAppInfoBeanDao.getAppInfoById(id);
		
		return appList != null && appList.size() > 0 ? appList.get(0) : null;
	}
}
