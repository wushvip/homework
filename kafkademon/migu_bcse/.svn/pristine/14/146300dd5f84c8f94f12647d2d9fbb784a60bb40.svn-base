package com.chinamobile.cmss.bcse.index.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.app.bean.AppFieldBean;
import com.chinamobile.cmss.bcse.app.bean.AppReqBean;
import com.chinamobile.cmss.bcse.app.bean.AppTableMapBean;
import com.chinamobile.cmss.bcse.index.bean.AppDataHistoryBean;
import com.chinamobile.cmss.bcse.index.bean.TableProperty;
import com.chinamobile.cmss.bcse.index.dao.IndexDataHistoryDao;
import com.chinamobile.cmss.bcse.index.dao.IndexFieldBeanDao;
import com.chinamobile.cmss.bcse.index.dao.IndexTableMapBeanDao;
import com.chinamobile.cmss.bcse.index.entry.DeleteIndex;
import com.chinamobile.cmss.bcse.index.entry.RebuildIndex;
import com.chinamobile.cmss.bcse.index.entry.UpdateData;
import com.chinamobile.cmss.bcse.index.kafka.GetFromKafka;
import com.chinamobile.cmss.bcse.index.kafka.KafkaProduce;
import com.chinamobile.cmss.bcse.index.service.IndexDataOperaIndexService;
import com.chinamobile.cmss.bcse.index.tool.GetUniqueKeyFromProperties;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.tool.exception.OperateIndexParamException;
import com.chinamobile.cmss.bcse.tool.exception.SqlOrDatabaseException;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;
import com.chinamobile.cmss.bcse.tool.tools.Tool;

@Service("indexDataOperaIndexService")
public class IndexDataOperaIndexServiceImpl implements IndexDataOperaIndexService {

	@Resource
	IndexOperaParamUtil2 indexOperaParamUtil2;

	@Resource
	private IndexFieldBeanDao indexFieldBeanDao;

	@Resource
	IndexDataHistoryDao indexDataHistoryDao = null;

	@Resource
	IndexTableMapBeanDao indexTableMapBeanDao;

	/**
	 * 
	 * @Title: deleteIndexFromFile
	 * @Description: 只是删除索引。DeleteIndex.process(userId, appId, sourceFileMap,
	 *               tablePropertiesList);
	 * @param appReqBean
	 * @param resultBean
	 * @return: void
	 */
	@Override
	public void deleteIndexFromFile(AppReqBean appReqBean, ResultBean resultBean) {

		String userId = appReqBean.getUserId();
		String appId = appReqBean.getAppId();

		boolean deleteResult = true;
		if ("".equals(appId)) {
			deleteResult = false;
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, "删除索引时，获取应用名：" + appId + "为空!无法正常删除索引");
			resultBean.setStatus(Config.RESULT_FAIL);
			resultBean.setMessage("appId不能为空");
			return;
		}
		ArrayList<TableProperty> tablePropertiesList = new ArrayList<TableProperty>();
		LinkedHashMap<String, String> sourceFileMap = new LinkedHashMap<String, String>();
		// 数据表
		try {
			indexOperaParamUtil2.getDataTableParam(userId, appId, tablePropertiesList, sourceFileMap);
		} catch (Exception e) {
			LogUtil.loggerEntrance(userId, appId, ExceptionConstants.IndexgetParamError, LogUtil.WEB_LOG, e);
			throw new OperateIndexParamException(ExceptionConstants.IndexgetParamError);
		}

		for (TableProperty tableProperty : tablePropertiesList) {
			String fileSplit = appReqBean.getFileSplit();
			if (fileSplit != null && fileSplit != "") {
				tableProperty.setFileSplit(fileSplit);
			} else {
				tableProperty.setFileSplit(",");
			}
		}

		deleteResult = DeleteIndex.process(userId, appId, sourceFileMap, tablePropertiesList,
				appReqBean.getMutiValueSplit(), appReqBean.getFileSplit());
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "用户:" + userId + ",应用:" + appId + ",删除应用的结果为" + deleteResult);
		if (!deleteResult) {
			resultBean.setStatus(Config.RESULT_FAIL);
			resultBean.setMessage("删除索引失败");
		}

	}

	public class IndexOpType {
		public final static String ADD = "1";
		public final static String MOD = "2";
	}

	@Override
	public void updateFormJson(AppReqBean appReqBean, ResultBean resultBean) throws Exception {

		String appId = appReqBean.getAppId();
		String userId = appReqBean.getUserId();

		ArrayList<TableProperty> tablePropertiesList = new ArrayList<TableProperty>();
		LinkedHashMap<String, String> sourceFileMap = new LinkedHashMap<String, String>();
		// 数据表
		try {
			indexOperaParamUtil2.getDataTableParam(userId, appId, tablePropertiesList, sourceFileMap);
		} catch (Exception e) {
			LogUtil.loggerEntrance(userId, appId, ExceptionConstants.IndexgetParamError, LogUtil.WEB_LOG, e);
			throw new OperateIndexParamException(ExceptionConstants.IndexgetParamError);
		}

		if (appReqBean.getDatas() != null && !appReqBean.getDatas().isEmpty()) {
			JSONArray jsonArray = appReqBean.getDatas();
			ArrayList<String> jsonData = new ArrayList<String>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				jsonData.add(jsonObject.toString());
			}

			if (appReqBean.getType().equals("1")) {// 整条覆盖
				// 写入kafka
				KafkaProduce.process(appReqBean.getAppId(), "add", jsonData);
			} else if (appReqBean.getType().equals("2")) {// 部分字段覆盖
				// 写入kafka
				KafkaProduce.process(appReqBean.getAppId(), "mod", jsonData);
			} else {
				throw new Exception();
			}
			// 启动consumer消费kafka中的数据
			GetFromKafka.process(userId, appId, tablePropertiesList);
		} else {// do nothing

		}
	}

	public void deleteFormJson(AppReqBean appReqBean, ResultBean resultBean) {

		String appId = appReqBean.getAppId();
		String userId = appReqBean.getUserId();

		ArrayList<TableProperty> tablePropertiesList = new ArrayList<TableProperty>();
		LinkedHashMap<String, String> sourceFileMap = new LinkedHashMap<String, String>();
		// 数据表
		try {
			indexOperaParamUtil2.getDataTableParam(userId, appId, tablePropertiesList, sourceFileMap);
		} catch (Exception e) {
			LogUtil.loggerEntrance(userId, appId, ExceptionConstants.IndexgetParamError, LogUtil.WEB_LOG, e);
			throw new OperateIndexParamException(ExceptionConstants.IndexgetParamError);
		}

		String uniqueKey = GetUniqueKeyFromProperties.get(tablePropertiesList);

		JSONArray jsonArray = appReqBean.getDatas();
		if (jsonArray != null) {
			ArrayList<String> jsonData = new ArrayList<String>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				if (jsonObject.containsKey(uniqueKey)) {
					jsonData.add(jsonObject.getString(uniqueKey));
				}
			}
			KafkaProduce.process(appReqBean.getAppId(), "del", jsonData);
		}

		// 启动consumer消费kafka中的数据
		GetFromKafka.process(appId, userId, tablePropertiesList);
	}

	/**
	 * 
	 * @Title: updateAppIndex
	 * @Description:更新索引
	 * @param appReqBean
	 * @return
	 * @throws Exception
	 * @Date:2016年5月30日
	 */
	public void updateIndex(AppReqBean appReqBean, ResultBean resultBean, String type) {

		String appId = appReqBean.getAppId();
		String userId = appReqBean.getUserId();

		ArrayList<TableProperty> tablePropertiesList = new ArrayList<TableProperty>();
		LinkedHashMap<String, String> sourceFileMap = new LinkedHashMap<String, String>();
		// 数据表
		try {
			indexOperaParamUtil2.getDataTableParam(userId, appId, tablePropertiesList, sourceFileMap);
		} catch (Exception e) {
			LogUtil.loggerEntrance(userId, appId, ExceptionConstants.IndexgetParamError, LogUtil.WEB_LOG, e);
			throw new OperateIndexParamException(ExceptionConstants.IndexgetParamError);
		}

		for (TableProperty tableProperty : tablePropertiesList) {
			String fileSplit = appReqBean.getFileSplit();
			String mutivalueSplit = appReqBean.getMutiValueSplit();
			if (fileSplit != null && fileSplit != "") {
				tableProperty.setFileSplit(fileSplit);
			} else {
				tableProperty.setFileSplit(",");
			}
			if (mutivalueSplit != null && mutivalueSplit != "") {
				tableProperty.setMutiValueSplit(mutivalueSplit);
			} else {
				tableProperty.setMutiValueSplit("|");
			}

		}

		boolean updateResult = UpdateData.process(userId, appId, sourceFileMap, tablePropertiesList, type);
		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "用户:" + userId + ",应用:" + appId + ",更新索引的结果为" + updateResult);
		if (!updateResult) {
			resultBean.setMessage("更新索引数据异常");
			resultBean.setStatus(Config.RESULT_FAIL);
			return;
		}

	}

	/**
	 * 
	 * @Title: clearIndex
	 * @Description: 清空索引
	 * @param appReqBean
	 * @param resultBean
	 * @Date:2016年5月30日
	 */
	public void clearIndex(AppReqBean appReqBean, ResultBean resultBean) {

		String appId = appReqBean.getAppId();
		String userId = appReqBean.getUserId();

		ArrayList<TableProperty> tablePropertiesList = new ArrayList<TableProperty>();
		LinkedHashMap<String, String> sourceFileMap = new LinkedHashMap<String, String>();

		try {
			// 获取处理索引时需要的数据表的数据
			indexOperaParamUtil2.getDataTableParam(userId, appId, tablePropertiesList, sourceFileMap);
		} catch (Exception e) {
			LogUtil.loggerEntrance(userId, appId, ExceptionConstants.IndexgetParamError, LogUtil.WEB_LOG, e);
			throw new OperateIndexParamException(ExceptionConstants.IndexgetParamError);
		}

		sourceFileMap = new LinkedHashMap<String, String>();
		boolean clearResult = RebuildIndex.process(userId, appId, sourceFileMap, tablePropertiesList);

		LogUtil.loggerEntrance(LogUtil.WEB_LOG, "用户:" + userId + ",应用:" + appId + ",清空的结果为" + clearResult);
		if (!clearResult) {
			resultBean.setStatus(Config.RESULT_FAIL);
			resultBean.setMessage("清空索引操作失败");
			return;
		}

	}

	public void showIndexOper(AppReqBean appReqBean, ResultBean resultBean) {

		ArrayList<AppDataHistoryBean> historyList = null;
		int totalItems = 0;
		try {
			appReqBean.setEndDate(Tool.getDateTime(appReqBean.getEndDate(), 1));
			appReqBean.setStartNum((appReqBean.getPageIndex() - 1) * appReqBean.getPageNum());

			totalItems = indexDataHistoryDao.getTotalItemsHistory(appReqBean);
			historyList = indexDataHistoryDao.getOperaHistroy(appReqBean);
		} catch (Exception e) {
			LogUtil.loggerEntrance(appReqBean.getUserId(), appReqBean.getAppId(),
					ExceptionConstants.SqlOrDatabaseException, LogUtil.WEB_LOG, e);
			throw new SqlOrDatabaseException(ExceptionConstants.SqlOrDatabaseException);
		}

		JSONObject resultJson = new JSONObject();
		resultJson.put("historyList", historyList);
		resultJson.put("totalItems", totalItems);
		resultBean.setResult(resultJson);
	}

	@Override
	public boolean saveDataOperaHistory(AppReqBean appReqBean) {
		boolean resultFlag;
		if (indexDataHistoryDao.saveOperaHistroy(appReqBean)) {
			resultFlag = true;
		} else {
			resultFlag = false;
		}
		return resultFlag;
	}

	@Override
	public ResultBean getSampleData(AppReqBean appReqBean) {
		ArrayList<String> sampleData = new ArrayList<String>();
		ResultBean resultBean = new ResultBean();
		try {
			sampleData = makeSample(appReqBean);
			resultBean.setStatus("SUCCESS");
			resultBean.setResult(sampleData);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setStatus("FAIL");
			resultBean.setMessage(e.getMessage());
		}
		return resultBean;
	}

	@Override
	public ResultBean useSampleData(AppReqBean appReqBean) {
		ArrayList<String> sampleData = new ArrayList<String>();
		ResultBean resultBean = new ResultBean();
		try {
			sampleData = makeSample(appReqBean);
			String fileName = appReqBean.getAppId() + "_sample";
			File file = new File(fileName);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			for (String string : sampleData) {
				bw.append(string);
				bw.newLine();
			}

			bw.close();
			ArrayList<AppTableMapBean> appTableMapBeans = indexTableMapBeanDao.selectByAppId(appReqBean.getAppId());
			if (appTableMapBeans.size() > 0) {
				String id = appTableMapBeans.get(0).getTableId();
				boolean updateFlag = indexTableMapBeanDao.updateFile(id, file.getAbsolutePath());
				updateIndex(appReqBean, resultBean, "1");
			} else {
				throw new Exception("not find appid");
			}

			resultBean.setStatus("SUCCESS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultBean.setStatus("FAIL");

		}

		return resultBean;
	}

	private ArrayList<String> makeSample(AppReqBean appReqBean) throws Exception {
		ArrayList<String> sampleData = new ArrayList<String>();

		ArrayList<AppFieldBean> appFieldBeans = indexFieldBeanDao.selectFields(appReqBean.getAppId());

		String header = "";
		String data = "";
		int uniqueKey = 1;
		for (AppFieldBean appFieldBean : appFieldBeans) {
			if (appFieldBean.getDynamicField().equals("1")) {
				String name = appFieldBean.getFieldName();
				name = "test" + name.substring(1, name.length());
				appFieldBean.setFieldName(name);
			}
			if (appFieldBean.getSrcField().equals("")) {
				header = header + "," + appFieldBean.getFieldName();
				String dataTmp = "";
				if (appFieldBean.getIsPk().equals("1")) {
					dataTmp = (uniqueKey++) + "";

				} else {
					switch (appFieldBean.getFieldType()) {

					case "LONG":
						dataTmp = "1";
						break;
					case "ANSJ":
						dataTmp = "测试数据";
						break;
					case "DATE":
						dataTmp = "2016-01-01 01:00:00";
						break;
					case "DOUBLE":
						dataTmp = "1.0";
						break;
					case "FLOAT":
						dataTmp = "1.0";
						break;
					case "INT":
						dataTmp = "1";
						break;
					case "JP_NoSplit":
						dataTmp = "简拼";
						break;
					case "QP_NoSplit":
						dataTmp = "全拼";
						break;
					case "STRING":
						dataTmp = "string";
						break;
					case "TEXT":
						dataTmp = "text";
						break;
					case "ANSJ_JP":
						dataTmp = "简拼";
						break;
					case "ANSJ_QP":
						dataTmp = "全拼";
						break;
					case "ANSJ_PY":
						dataTmp = "拼音";
						break;
					case "JP_Suggest":
						dataTmp = "jp";
						break;
					case "QP_Suggest":
						dataTmp = "quanpin";
						break;
					}
				}
				data = data + "," + dataTmp;
			}
		}
		if (header.length() > 0)
			header = header.substring(1, header.length());
		data = data.substring(1, data.length());
		sampleData.add(header);
		sampleData.add(data);

		return sampleData;
	}

}
