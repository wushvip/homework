package com.chinamobile.cmss.bcse.evaluate.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.config.service.impl.RedisService;
import com.chinamobile.cmss.bcse.evaluate.bean.EvaluateAppInfoBean;
import com.chinamobile.cmss.bcse.evaluate.bean.EvaluateDataInfoBean;
import com.chinamobile.cmss.bcse.evaluate.bean.EvaluateResultReqBean;
import com.chinamobile.cmss.bcse.evaluate.bean.HttpRequestDataBean;
import com.chinamobile.cmss.bcse.evaluate.bean.HttpResponseDataBean;
import com.chinamobile.cmss.bcse.evaluate.bean.PointBean;
import com.chinamobile.cmss.bcse.evaluate.bean.PointResBean;
import com.chinamobile.cmss.bcse.evaluate.bean.SingleDataBean;
import com.chinamobile.cmss.bcse.evaluate.bean.TotalPointBean;
import com.chinamobile.cmss.bcse.evaluate.bean.vo.PointResTransfer;
import com.chinamobile.cmss.bcse.evaluate.dao.EvaluateDataInfoBeanDao;
import com.chinamobile.cmss.bcse.evaluate.service.EvaluateAppInfoService;
import com.chinamobile.cmss.bcse.evaluate.service.EvaluateResultService;
import com.chinamobile.cmss.bcse.evaluate.utils.FileUtil;
import com.chinamobile.cmss.bcse.evaluate.utils.HttpRequestUtil;
import com.chinamobile.cmss.bcse.search.bean.BCSEQuery;
import com.chinamobile.cmss.bcse.search.bean.SearchBean;
import com.chinamobile.cmss.bcse.search.bean.SearchInput;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.exception.EvaluateException;
import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.tool.exception.SqlOrDatabaseException;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.chinamobile.cmss.bcse.tool.tools.ResultBean;


@Service("evaluateResultService")
public class EvaluateResultServiceImpl implements EvaluateResultService {

	@Resource
	private EvaluateDataInfoBeanDao dataInfoBeanDao;
	

	@Resource
	private EvaluateAppInfoService appInfoService;

	@Override
	public void executeEvaluation(EvaluateResultReqBean resultReqBean, ResultBean resultBean,HttpServletRequest request) {
		PointResBean pointResBean = new PointResBean();
		try{
			//执行评测
			getEvaluationResult(resultReqBean, resultBean, request, pointResBean);
		}catch(Exception e){
			resultBean.setMessage("执行评测失败");
			resultBean.setStatus(Config.RESULT_FAIL);
			e.printStackTrace();
			LogUtil.loggerEntrance(LogUtil.EVALUATE_LOG, e.getMessage());
			throw new EvaluateException(ExceptionConstants.EvaluateError);
		}
	}

	@Override
	public PointResBean getExportResult(EvaluateResultReqBean resultReqBean, ResultBean resultBean,HttpServletRequest request){

		PointResBean pointResBean = new PointResBean();
		//执行评测
		getEvaluationResult(resultReqBean,resultBean, request, pointResBean);
		//返回结果
		return pointResBean;
	}

	
	private void getEvaluationResult(EvaluateResultReqBean resultReqBean, ResultBean resultBean,HttpServletRequest request,PointResBean pointResBean) {

		// 1.根据dataid获取语料
		EvaluateDataInfoBean dataInfoBean = new EvaluateDataInfoBean();
		try{
			getEvaluateData(resultReqBean, dataInfoBean);
		}catch(Exception e){
			resultBean.setMessage("获取语料失败");
			resultBean.setStatus(Config.RESULT_FAIL);
			return;
		}
		
		// 获取应用信息表
		EvaluateAppInfoBean appInfoBean = appInfoService.getAppInfoById(resultReqBean.getAppId());
		HttpRequestDataBean requestBean = new HttpRequestDataBean();
		
		getHttpRequestData(requestBean,resultReqBean, appInfoBean, dataInfoBean,request);

		// 2.根据语料去搜索得到结果

		HttpResponseDataBean responseBean  = new HttpResponseDataBean();
		
		try{
			responseBean = getHttpResponseData(requestBean, appInfoBean, dataInfoBean,resultReqBean.getUserId());

			parseToBean(responseBean);
		}catch(Exception e){
			resultBean.setMessage("搜索异常");
			resultBean.setStatus(Config.RESULT_FAIL);
		}

		// 3.打分
		execute(responseBean, pointResBean);
		
		resultBean.setResult(pointResBean);
	}
	
	/**
	 * 
	 * @Title: execute
	 * @Description: 执行评测打分
	 * @param appInfoBean
	 * @param dataInfoBean
	 * @return: void
	 */
	private void execute(HttpResponseDataBean appInfoBean, PointResBean pointResBean) {
		List<SingleDataBean> appList = appInfoBean.getDataList();	
		if(appList !=null && appList.size()>0){
			int sameNum;
			int firstNum;
			int dataSize;
			int appSize;
			float accPoi = 0;
			float recallPoi = 0;
			float reverPoi = 0;
			float averPoi = 0;
			String accuracyPoint = "";
			String recallPoint = "";
			String reversePoint = "";
			String averagePoint = "";
			String searchWord = "";
			String dataSearchWord = "";
			DecimalFormat df = new DecimalFormat("0.00");// 格式化小数
			TotalPointBean totalPointBean = new TotalPointBean();
			for (int i = 0; i < appList.size(); i++) {
				List<String> appIds = new ArrayList<>();
				List<String> dataIds = new ArrayList<>();
				List<String> appNames = new ArrayList<>();
				List<String> dataNames = new ArrayList<>();	
				List<String> sourceDataIds = new ArrayList<>();
				List<Integer> orderList = new ArrayList<>();
				PointBean pointBean = new PointBean();
				searchWord = appList.get(i).getSearchWord();
				dataSearchWord = appList.get(i).getDataSeachWord();
			
				appIds = appList.get(i).getIds();
				dataIds = appList.get(i).getDataIds();
				sourceDataIds.addAll(dataIds);
				appNames = appList.get(i).getNames();
				dataNames = appList.get(i).getDataNames();
				dataSize = dataIds.size();
				if(appIds == null){
					System.out.println(i);
				}
				appSize = appIds.size();	
				sourceDataIds.retainAll(appIds);
				sameNum = sourceDataIds.size();				
				float average = 0;
				if(dataSize != 0 && appSize != 0){
					// 准确率
					accuracyPoint = df.format((float) sameNum / appSize);
					// 召回率
					recallPoint = df.format((float) sameNum / dataSize);
					// 排序倒数
					if (appIds != null && appIds.size() > 0) {
						firstNum = dataIds.indexOf(appIds.get(0));
						if (firstNum != -1) {
							reversePoint = df.format((float) 1 / (firstNum + 1));
						}else{
							reversePoint = "0.00";
						}
					}
					// 平均准确率
					for (int j = 0; j < appIds.size(); j++) {
						if (dataIds.indexOf(appIds.get(j)) != -1) {
							orderList.add(dataIds.indexOf(appIds.get(j)));
						}
					}
					//升序排列
					 Collections.sort(orderList);
					 
					 for(int m=0;m<orderList.size();m++ ){
						 average += (float) (m + 1) / (orderList.get(m)+1);
					 }
					average = average / appSize;
					averagePoint = df.format(average);
				}else{
					accuracyPoint ="0.00";
					recallPoint ="0.00";
					averagePoint ="0.00";
					reversePoint = "0.00";
				}
				if (searchWord.equals(dataSearchWord)) {
					pointBean.setSearchWord(searchWord);
					pointBean.setpPoint(accuracyPoint);
					pointBean.setrPoint(recallPoint);
					pointBean.setApPoint(averagePoint);
					pointBean.setArPoint(reversePoint);
					pointBean.setSearchIdResults(appIds);
					pointBean.setSearchNameResults(appNames);
					pointBean.setDataIdResults(dataIds);
					pointBean.setDataNameResults(dataNames);
					pointResBean.getPoints().add(pointBean);
				}

				accPoi += Float.valueOf(accuracyPoint) ;
				recallPoi += Float.valueOf(recallPoint) ;
				reverPoi += Float.valueOf(reversePoint);
				averPoi += Float.valueOf(averagePoint) ;
				
			}
			accPoi = accPoi/appList.size();
			recallPoi = recallPoi/appList.size();
			reverPoi =reverPoi /appList.size();
			averPoi = averPoi /appList.size();
			totalPointBean.setpTotalPoint(df.format(accPoi));
			totalPointBean.setrTotalPoint(df.format(recallPoi));
			totalPointBean.setArTotalPoint(df.format(reverPoi));
			totalPointBean.setApTotalPoint(df.format(averPoi));
			pointResBean.setTotalPoint(totalPointBean);
			JSONObject retJson = new JSONObject();
			retJson.put("result", pointResBean);
			System.out.println(retJson);
		}
		
	}

	/**
	 * 
	 * @Title: getEvaluateData
	 * @Description: 获取语料
	 * @param resultReqBean
	 * @param dataInfoBean
	 * @return: void
	 */
	private void getEvaluateData(EvaluateResultReqBean resultReqBean, EvaluateDataInfoBean dataInfoBean) {

		ArrayList<SingleDataBean> dataList = new ArrayList<>();
		String path = dataInfoBeanDao.selectByPrimaryKey(resultReqBean.getDataId()).getSourceDir();
		// 解析csv
		parseCsv(path, dataList);
		dataInfoBean.setDataList(dataList);
	}

	/**
	 * 
	 * @Title: parseCsv
	 * @Description: 解析csv
	 * @param path
	 * @param dataList
	 * @return: void
	 */
	private void parseCsv(String path, ArrayList<SingleDataBean> dataList) {
		ArrayList<String> idList = null;
		ArrayList<String> nameList = null;
		if (path != null && !path.equals("")) {

			ArrayList<String[]> csvList = FileUtil.readCsv(path);
			for (String[] record : csvList) {
				idList = new ArrayList<>();
				nameList = new ArrayList<>();
				SingleDataBean dataBean = new SingleDataBean();
				try {
					if (record[1] != null && !record[1].equals("")) {
						String[] ids = record[1].split("\\|");
						for (String id : ids) {
							idList.add(id);
						}
					}
					if (record[2] != null && !record[2].equals("")) {
						String[] names = record[2].split("\\|");
						for (String name : names) {
							nameList.add(name);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				dataBean.setDataSeachWord(record[0]);
				dataBean.setDataIds(idList);
				dataBean.setDataNames(nameList);
				dataList.add(dataBean);
			}

		}

	}

	/**
	 * 
	 * @Title: httpRequestInfo
	 * @Description: 请求数据
	 * @param resultReqBean
	 *            应用ID
	 * @param requestWord
	 *            请求词
	 * @return: HttpRequestDataBean
	 */
	private void getHttpRequestData(HttpRequestDataBean reqBean,EvaluateResultReqBean resultReqBean, EvaluateAppInfoBean appInfoBean,
			EvaluateDataInfoBean dataInfoBean,HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		String localUrl = url.substring(0, url.indexOf(request.getContextPath()));
		String localRequestUrl = localUrl + request.getContextPath() + "/search";
		// 请求信息集合
		Map<String, String> params = new HashMap<String, String>();
		if (appInfoBean == null) {
			LogUtil.loggerEntrance(LogUtil.EVALUATE_LOG, "评测体系获取应用基本信息失败!===>  resultReqBean:" + resultReqBean.getAppId());
			return;
		}
		reqBean.setIsThirdPart(appInfoBean.getIsThirdPart());
		// 判断应用信息是否为第三方请求
		if (appInfoBean != null && appInfoBean.getIsThirdPart()) {
			reqBean.setUrl(appInfoBean.getRequestUrl());
			reqBean.setRequestType(appInfoBean.getRequestType());

			// 替换请求词
			JSONObject obj = JSONObject.parseObject(appInfoBean.getRequestContent());
			for (SingleDataBean singleData : dataInfoBean.getDataList()) {
				obj.put(appInfoBean.getRequestWord(), singleData.getSearchWord());
				params.put(singleData.getSearchWord(), obj.toString());
			}
			// 请求类型 Content-Type: application/json TODO

			// 本地应用搜索请求
		} else {
			reqBean.setUrl(localRequestUrl);
			reqBean.setRequestType("1");
			// 替换请求词
		  /*SearchBean searchBean = getDefaultSearchBean();
			searchBean.setPageNum(resultReqBean.getEvaluateNumber());
			searchBean.setAppId(appInfoBean.getId());
			searchBean.setUserId(resultReqBean.getUserId());*/
			
			
			
			for (SingleDataBean singleData : dataInfoBean.getDataList()) {
				JSONObject object=new JSONObject();
				object.put("pageNum", resultReqBean.getEvaluateNumber());
				object.put("appId", appInfoBean.getId());
				object.put("userId", resultReqBean.getUserId());
				object.put("sortConfig", appInfoBean.getSortConfig());
				object.put("searchQuery",singleData.getDataSeachWord() );
				params.put(singleData.getDataSeachWord(), object.toString());
			}               
			
		}
		reqBean.setParams(params);
	}

	/**
	 * 获取响应的搜索结果
	 * 
	 * @param request
	 * @param dataInfoBean
	 * @param appInfo
	 * @return
	 */
	private HttpResponseDataBean getHttpResponseData(HttpRequestDataBean request, EvaluateAppInfoBean appInfoBean,
			EvaluateDataInfoBean dataInfoBean,String userId) {
		HttpResponseDataBean response = new HttpResponseDataBean();
		if (request.getParams() == null || StringUtils.isBlank(request.getUrl())) {
			LogUtil.loggerEntrance(LogUtil.EVALUATE_LOG, "请求数据异常===>  request:" + request + " ;  request params:"
					+ request.getParams() + " ; request url:" + request.getUrl());
			return null;
		}
		response.setAppInfoBean(appInfoBean);
		response.setDataInfoBean(dataInfoBean);
		Map<String, String> requestParams = request.getParams();
		Map<String, String> responseValues = new HashMap<String, String>();
		// POST
		if ("1".equals(request.getRequestType())) {
			for (String param : requestParams.keySet()) {
				String res = HttpRequestUtil.sendPost(request.getUrl(), requestParams.get(param),userId);
				
				responseValues.put(param, res);
			}
		} else {
			for (String param : requestParams.keySet()) {
				String res = HttpRequestUtil.sendGet(request.getUrl(), requestParams.get(param));
				responseValues.put(param, res);
			}
		}
		response.setResponseValues(responseValues);
		return response;
	}

	/**
	 * 将响应结果根据不同应用转换成对应bean，过滤不需要的字段最终进行打分
	 * 
	 */
	private void parseToBean(HttpResponseDataBean response) {
		if (response == null) {
			return;
		}
		// json类型的返回结果，key为搜索词，value为该搜索词对应的搜索结果字符串
		Map<String, String> resultJsonMap = response.getResponseValues();
		// 将要被转换成的集合
		List<SingleDataBean> dataList = response.getDataInfoBean().getDataList();
		EvaluateAppInfoBean appInfoBean = response.getAppInfoBean();
		String[] evaluateFields = appInfoBean.getEvaluateField().split(",");
		if (!appInfoBean.getIsThirdPart()) {
			// 遍历结果集，对应搜索词的结果装入singledatabean中
			for(SingleDataBean bean:dataList){
				String searchWord = bean.getDataSeachWord();
//			for (String searchWord : resultJsonMap.keySet()) {
//				SingleDataBean bean = new SingleDataBean();
//				for (SingleDataBean dataBean:dataList) {
//					if(searchWord.equals(dataBean.getDataSeachWord())){
//						bean = dataBean;
//						break;
//					}
//				}
				ArrayList<String> ids = new ArrayList<String>();
				ArrayList<String> names = new ArrayList<String>();
				// 取出当前搜索词的搜索结果
				String resultJson = resultJsonMap.get(searchWord);
				JSONObject obj = JSONObject.parseObject(resultJson);
				// 取出搜索结果中result节点下的对象，这里涉及到bcse搜索返回结果的格式解析
				JSONObject result = obj.getJSONObject("result");
				JSONArray resultList = result.getJSONArray("resultList");
				for (int i = 0; i < resultList.size(); i++) {
					JSONObject resultObj = resultList.getJSONObject(i);
					//解析字段目前限制为2个
					if(evaluateFields.length != 2){
						LogUtil.loggerEntrance(LogUtil.EVALUATE_LOG, "解析字段异常异常===>  evaluateFields:" + evaluateFields);
						return;
					}
					String id = resultObj.get(evaluateFields[0]) + "";
					String name = resultObj.get(evaluateFields[1]) + "";
					if (StringUtils.isNotBlank(id)) {
						ids.add(id);
						names.add(name);
					}
				}
				bean.setSearchWord(searchWord);
				bean.setIds(ids);
				bean.setNames(names);
			}
		} else {
			// 第三方应用 TODO
		}
		response.setDataList(dataList);
	}

	
	/**
	 * 评测结果转换
	 */
	@Override
	public ArrayList<PointResTransfer> parseTransfer(PointResBean result){
		ArrayList<PointResTransfer> list=new ArrayList<PointResTransfer>();
		List<PointBean> points = result.getPoints();
		//设置平均得分
		PointResTransfer transfer=new PointResTransfer();
		transfer.setApPoint(result.getTotalPoint().getApTotalPoint());
		transfer.setpPoint(result.getTotalPoint().getpTotalPoint());
		transfer.setrPoint(result.getTotalPoint().getrTotalPoint());
		transfer.setArPoint(result.getTotalPoint().getArTotalPoint());
		list.add(transfer);
		
		for (PointBean point : points) {
			transfer=new PointResTransfer();
			//将结果封装进transfer类中
			transfer.setSearchWord(point.getSearchWord());
			
			transfer.setApPoint(point.getApPoint());
			transfer.setpPoint(point.getpPoint());
			transfer.setrPoint(point.getrPoint());
			transfer.setArPoint(point.getArPoint());
			String dataResultId ="";
			String dataResult= "";
			String responseResultId ="";
			String responseResult ="";
			if(point.getDataIdResults()!=null && point.getDataIdResults().size()>0){
				dataResultId = point.getDataIdResults().get(0);
				for (int i = 1; i < point.getDataIdResults().size(); i++) {
					dataResultId += "," + point.getDataIdResults().get(i) ;
				}
			}
			if(point.getDataNameResults()!=null && point.getDataNameResults().size()>0){
				dataResult = point.getDataNameResults().get(0) ;
				for (int i = 1; i < point.getDataNameResults().size(); i++) {
					dataResult += "," + point.getDataNameResults().get(i) ;
				}
			}
			
			if(point.getSearchIdResults()!=null && point.getSearchIdResults().size()>0){
				responseResultId = point.getSearchIdResults().get(0);
				for (int i = 1; i <point.getSearchIdResults().size(); i++) {
					responseResultId += "," + point.getSearchIdResults().get(i) ;
				}
			}
			
			if(point.getSearchNameResults()!=null && point.getSearchNameResults().size()>0){
				responseResult = point.getSearchNameResults().get(0);
				for (int i = 1; i <point.getSearchNameResults().size(); i++) {
					responseResult += "," + point.getSearchNameResults().get(i) ;
				}
			}
			transfer.setDataResultId(dataResultId);
			transfer.setDataResult(dataResult);
			transfer.setResponseResultId(responseResultId);
			transfer.setResponseResult(responseResult);
			list.add(transfer);
		}
		
		
		return list;
	}
	/**
	 * 默认searchbean
	 * 
	 * @return
	 */
	private void getDefaultSearchBean() {
		/*SearchBean searchBean = new SearchBean();
		searchBean.setAppId("11a4e894bbc240518e135237c653098c");
		searchBean.setClFilter("");
		searchBean.setFieldSearch("default");
		searchBean.setFilterRules("");
		searchBean.setIsAnalysis("0");
		searchBean.setIsShield("0");
		searchBean.setIsShow("0");
		searchBean.setIsSpread("0");
		searchBean.setPageIndex(0);
		searchBean.setPageNum(20);
		searchBean.setRankType("1");
		searchBean.setSearchQuery("三国");
		searchBean.setSortRule("");
		searchBean.setUserId("admin");
		return searchBean;*/
	}

	public static void main(String[] args) {
	System.out.println();
	}


}
