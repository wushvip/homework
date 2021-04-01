package com.chinamobile.cmss.bcse.datastatistics.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.app.bean.AppInfoBean;
import com.chinamobile.cmss.bcse.config.service.impl.ListRedisService;
import com.chinamobile.cmss.bcse.config.service.impl.RedisService;
import com.chinamobile.cmss.bcse.datastatistics.bean.HotWordsBean;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogReqBean;
import com.chinamobile.cmss.bcse.datastatistics.bean.RedisDataType;
import com.chinamobile.cmss.bcse.datastatistics.dao.HotWordsDao;
import com.chinamobile.cmss.bcse.datastatistics.service.IHotWordsService;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.tool.exception.SystemException;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;


/**
 * @ClassName: IHotWordsServiceImpl
 * @Description: 热词
 * @author: zhuxiang
 * @date: 2017年3月10日 下午3:25:47
 */
@Service("hotWordsService")
public class IHotWordsServiceImpl implements IHotWordsService {
	@Resource
	private HotWordsDao hotWordsDao;
	@Resource
	private ListRedisService listRedisService;
	@Resource
	private RedisService redisService;
	
	/**
	 * 
	 * @Title: showHotWord
	 * @Description: 展示热词数据
	 * @param logReqBean
	 * @param resultBean 
	 * @Date:2017年3月10日
	 */
	public void showHotWord(LogReqBean logReqBean, ResultBean resultBean) {
		logReqBean.setStartDateStr(Tool.getDayTime(logReqBean.getStartDate()));
		logReqBean.setEndDateStr(Tool.getDayTime(logReqBean.getEndDate()));
		if(logReqBean.getCt()!=null || logReqBean.getBookType()!=null){
			logReqBean.setUserId(logReqBean.getUserId()+logReqBean.getCt()+logReqBean.getBookType());
		}
		ArrayList<HotWordsBean> list = null;
		int totalItems = 0;
		logReqBean.setStartNum((logReqBean.getPageIndex() - 1) * logReqBean.getPageNum());
		try {
			totalItems = hotWordsDao.getTotalItemsHotWord(logReqBean);
			list = hotWordsDao.selectHotSearchWordAsDays(logReqBean);
		} catch (Exception e) {
			LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG,MsgConfig.SYS_EXCP+": \n"+e.getMessage());
			LogUtil.loggerEntrance(logReqBean.getUserId(), logReqBean.getAppId(), Config.FAIL_SYS_CODE, MsgConfig.SYS_EXCP, e);
			throw new SystemException(MsgConfig.SYS_EXCP);

		}
		JSONObject resultJson = new JSONObject();
		resultJson.put("hotWordList", list);
		resultJson.put("totalItems", totalItems);
		resultBean.setResult(resultJson);

	}
	
	public void insertWord(HashMap<String, String> map) {
		try {
			hotWordsDao.insertHotWord(map);
		} catch (Exception e) {
			LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG,MsgConfig.SYS_EXCP+": \n"+e.getMessage());
			throw new SystemException(MsgConfig.SYS_EXCP);

		}
	}
	
	/**
	 * 
	 * @Title: showHotWordByWeek
	 * @Description: 取前一周数据
	 * @param logReqBean
	 * @param resultBean 
	 * @Date:2017年3月10日
	 */
	public void showHotWordLastWeek(LogReqBean logReqBean, ResultBean resultBean) throws Exception {
		getDataFromRedis(logReqBean,resultBean,RedisDataType.HOTWORD_WEEK);

	}

	/**
	 * 
	 * @Title: showHotWordByMonth
	 * @Description: 取前一个月数据
	 * @param logReqBean
	 * @param resultBean 
	 * @Date:2017年3月10日
	 */
	public void showHotWordLastMonth(LogReqBean logReqBean, ResultBean resultBean) throws Exception {
		getDataFromRedis(logReqBean,resultBean,RedisDataType.HOTWORD_MONTH);

	}

	/**
	 * 
	 * @Title: showHotWordYesterday
	 * @Description: 获取昨天的热词数据
	 * @param logReqBean
	 * @param resultBean 
	 * @Date:2017年3月10日
	 */
	public void showHotWordYesterday(LogReqBean logReqBean, ResultBean resultBean) throws Exception {
		getDataFromRedis(logReqBean,resultBean,RedisDataType.HOTWORD_DAY);

	}
	
	/**
	 * 
	 * @Title: getDataFromRedis 
	 * @Description: 从redis中获取热词数据
	 * @param logReqBean
	 * @param resultBean
	 * @param type
	 * @return: void
	 */
	public void getDataFromRedis(LogReqBean logReqBean, ResultBean resultBean,RedisDataType type) {
		LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG, "get hotword form redis ,type:"+type);
		
		//获取数据层的配置数据
		int totalNums = 0;
		String key = "";
		String htype = String.valueOf(type);
		boolean flag = false;
		ArrayList<String> hotwordList = new ArrayList<String>();
		ArrayList<HotWordsBean> dataList = new ArrayList<HotWordsBean>();
		try {
			if("admin".equals(logReqBean.getUserId())){
				
				AppInfoBean app = (AppInfoBean)redisService.get(logReqBean.getAppId());
				if (app ==null) {
					Tool.serviceException(resultBean, "redis中未找到指定应用");
					return;
				}
				if(logReqBean.getCt()!=null || logReqBean.getBookType()!=null){
					app.setUserId(app.getUserId()+logReqBean.getCt()+logReqBean.getBookType());
					logReqBean.setUserId(logReqBean.getUserId()+logReqBean.getCt()+logReqBean.getBookType());
				}
				key=app.getAppId()+app.getUserId()+htype;
			}else{
				if(logReqBean.getCt()!=null || logReqBean.getBookType()!=null){
					logReqBean.setUserId(logReqBean.getUserId()+logReqBean.getCt()+logReqBean.getBookType());
				}
				key =logReqBean.getAppId()+logReqBean.getUserId()+htype;
			}
		} catch (Exception e) {
			LogUtil.loggerEntrance(logReqBean.getUserId(), logReqBean.getAppId(), Config.FAIL_SYS_CODE, MsgConfig.SYS_EXCP, e);
			e.printStackTrace();
			throw e;
		}

		
		//Redis Lrange 返回列表中指定区间内的元素，区间以偏移量 START 和 END 指定。 其中 0 表示列表的第一个元素
			//int start = (logReqBean.getPageIndex() - 1) * logReqBean.getPageNum();
			logReqBean.setStartNum((logReqBean.getPageIndex() - 1) * logReqBean.getPageNum());
			long start = logReqBean.getStartNum();
			long end = start+logReqBean.getPageNum()-1;
			logReqBean.setEndDateStr(Tool.getDayTime(-1));

		try {
			hotwordList=(ArrayList)listRedisService.get(key,start, end);
			totalNums= listRedisService.getNum(key).intValue();
			//totalNums=((ArrayList)listRedisService.get(key,startinit,endinit)).size();
			if(hotwordList.size()>0){
				for(String str :hotwordList){
					JSONObject obj = JSON.parseObject(str);
					HotWordsBean hotWordsBean = new HotWordsBean();
					hotWordsBean.setKeywords(obj.getString("keywords"));
					hotWordsBean.setOperTime(obj.getString("operTime"));
					hotWordsBean.setSearchCount(Integer.valueOf(obj.getString("searchCount")));
					dataList.add(hotWordsBean);
				}
			}
			
			
		} catch (Exception e) {
			//LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG,MsgConfig.SYS_EXCP+": \n"+e.getMessage());
			LogUtil.loggerEntrance(logReqBean.getUserId(), logReqBean.getAppId(), Config.FAIL_SYS_CODE, MsgConfig.SYS_EXCP, e);
			e.printStackTrace();
			flag = true;
		}
		
		if(flag){
			hotwordList.clear();
			try{
			switch (htype) {
			case "HOTWORD_MONTH":
				//logReqBean.setOperDate(Tool.getDayTime(-1));
				dataList = hotWordsDao.selectHotSearchWordAsMonth(logReqBean);
				totalNums = hotWordsDao.getTotalItemsHotWordAsMonth(logReqBean);
				break;
			case "HOTWORD_WEEK":
				//logReqBean.setOperDate(Tool.getDayTime(-1));
				dataList = hotWordsDao.selectHotSearchWordAsWeek(logReqBean);
				totalNums = hotWordsDao.getTotalItemsHotWordAsWeek(logReqBean);
				break;
			case "HOTWORD_DAY":
				//logReqBean.setOperDate(Tool.getDayTime(-1));
				dataList = hotWordsDao.selectHotSearchWordYesterday(logReqBean);
				totalNums = hotWordsDao.getTotalItemsHotWordYesterday(logReqBean);
				break;
			default:
				logReqBean.setStartDateStr(Tool.getDayTime(-1));
				dataList = hotWordsDao.selectHotSearchWordAsDays(logReqBean);
				totalNums = hotWordsDao.getTotalItemsHotWord(logReqBean);
				break;
			}
			}catch(Exception e){
				//LogUtil.loggerEntrance(LogUtil.STATISTICS_LOG,MsgConfig.SYS_EXCP+": \n"+e.getMessage());
				LogUtil.loggerEntrance(logReqBean.getUserId(), logReqBean.getAppId(), Config.FAIL_SYS_CODE, MsgConfig.SYS_EXCP, e);
				e.printStackTrace();
				throw new SystemException(MsgConfig.SYS_EXCP);
			}
			
			/*if(dataList==null && dataList.isEmpty()){
				resultBean.setMessage(MsgConfig.NOT_RESULT);
			}else{
				for(HotWordsBean hotWordsBean:dataList){
					//hotwordList.add(hotWordsBean.getKeywords());
					hotwordList.add(JSONObject.toJSONString(hotWordsBean));
				}
			}*/
			
		}
		/*if (hotwordList.size()<=0) {
			resultBean.setMessage("无相关结果");
		}*/
		if (dataList.size()<=0) {
			resultBean.setMessage("无相关结果");
		}
			

			JSONObject resultJson = new JSONObject();
			//resultJson.put("hotWordList", hotwordList);
			resultJson.put("hotWordList", dataList);
			resultJson.put("totalItems", totalNums);
			resultBean.setResult(resultJson);
		
	}
}
