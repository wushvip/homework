package com.chinamobile.cmss.bcse.sdk.entry;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.sdk.constant.Constant;
import com.chinamobile.cmss.bcse.sdk.index.bean.AppTableMapBean;
import com.chinamobile.cmss.bcse.sdk.index.bean.TableProperty;
import com.chinamobile.cmss.bcse.sdk.index.indexManager.ClearIndex;

import com.chinamobile.cmss.bcse.sdk.index.indexManager.UpdateFile;
import com.chinamobile.cmss.bcse.sdk.index.service.IndexOperaParamUtil;
import com.chinamobile.cmss.bcse.sdk.tools.ResultDecorate;
import com.chinamobile.cmss.bcse.sdk.util.IsGoodJson;


/**
 * @ClassName: DataOperController
 * @Description: 本地SDK入口
 * @author: jinjing
 * @date: 2016年2月16日 下午2:20:04
 */

public class BCSEIndex {

	private BCSEClient client;

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		BCSEClient bcseClient;
		try {
			bcseClient = new BCSEClient("f231a0bc170640eba55189f921996f28", "127.0.0.1", 8080, "bcse","");
			BCSEIndex bcseIndexOp = new BCSEIndex(bcseClient);
			// bcseIndexOp.clearIndex();
			System.out.println("最后结果：" + bcseIndexOp.rebuild("D:\\1111\\covert\\temp1.csv"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		long end = System.currentTimeMillis();

		System.out.println("time is" + (end - start) / 1000);

	}

	/**
	 * 构造函数
	 * 
	 * @param url
	 */
	public BCSEIndex(BCSEClient client) throws Exception {
		this.client = client;
	}

	/**
	 * 判断文件是否存在，后缀是否是csv
	 * 
	 * @param filePath
	 * @return
	 */
	private boolean verifyFile(String filePath) {

		if (!filePath.contains(".")) {
			return false;
		}
		int start = filePath.lastIndexOf('.');
		String fileType = filePath.substring(start, filePath.length());
		if (!fileType.equals(".csv"))
			return false;

		File file = new File(filePath);
		if (!file.exists())
			return false;
		return true;
	}

	/**
	 * 利用本地文件更新索引
	 * 
	 * @param filePath
	 * @return boolean
	 */
	private String update(String filePath,String mutiValueSplit,String fileSplit,String type) {

		if (!verifyFile(filePath)) {
			return ResultDecorate.decorate(false, "file not exsit or filepath is null");
		}

		boolean upflag = true;
		try {
			System.out.println("in" + filePath);
			if (!client.isFlag())
				return "";
			
			//ArrayList<TableProperty> tablePropertiesList = new ArrayList<TableProperty>();
			//LinkedHashMap<String, String> sourceFileMap = new LinkedHashMap<String, String>();
			//IndexOperaParamUtil.getDataTableParam(tablePropertiesList, sourceFileMap, client);
			//String tableId = tablePropertiesList.get(0).getTableId();
			
			
			String tableId = IndexOperaParamUtil.getAppTableId(client);
			if("".equals(tableId) || tableId==null){
				return ResultDecorate.decorate(false,"TableId未找到");
			}
			
			// 上传文件
			upflag = UpdateFile.formUpload(client, client.getUserId(), client.getAppId(), tableId, filePath);
			// 更新索引
			HashMap<String, Object> opts = new HashMap<String, Object>();
			opts.put("fileSplit", fileSplit);
			opts.put("mutiValueSplit", mutiValueSplit);
			opts.put("type", type);
			//String result = client.call(Constant.APP_POST_URL + "/index/update", opts, Constant.POST);
			String result = client.call(Constant.APP_POST_URL + "/"+Constant.appId+"/index", opts, Constant.POST);
			if(IsGoodJson.isGoodJson(result)){
				JSONObject jsonObject = JSON.parseObject(result);
				if (jsonObject.get("status").equals("FAIL")) {
					upflag = false;
				}
				System.out.println(result);
			}else{
				upflag = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			upflag = false;
		}
		return ResultDecorate.decorate(upflag);
	}
	/**
	 * 利用本地文件更新索引
	 * 
	 * @param filePath
	 * @return
	 */
	public String update(String filePath) {
		String mutiValueSplit = "|";
		String fileSplit = ",";
		String type = "";
		return update(filePath,mutiValueSplit,fileSplit,type);
	}
	/**
	 * 利用本地文件更新索引,用户可指定字段分隔符和多值分割符
	 * 
	 * @param filePath
	 * @param mutiValueSplit
	 * @param fileSplit
	 * @return
	 */
	public String update(String filePath,String mutiValueSplit,String fileSplit) {
		String type = "";
		if(fileSplit.length()!=1||mutiValueSplit.length()!=1||fileSplit.equals(mutiValueSplit)){
			return ResultDecorate.decorate("", Constant.STATUS_FAIL,Constant.MSG_SPLIT_NG , Constant.CODE_SERVICE);
		}
		if(Constant.FILESPLIT.contains(fileSplit) && Constant.MUTIVALUESPLIT.contains(mutiValueSplit)){
			return update(filePath,mutiValueSplit,fileSplit,type);
		}else{
			return ResultDecorate.decorate("", Constant.STATUS_FAIL,Constant.MSG_SPLIT_NG , Constant.CODE_SERVICE);
		}
	}

	/**
	 * 利用本地文件部分字段增量更新索引
	 * 
	 * @param filePath
	 * @return boolean
	 */
	public String updatePartIndex(String filePath) {
		String mutiValueSplit = "|";
		String fileSplit = ",";
		String type = "1";
		return update(filePath,mutiValueSplit,fileSplit,type);
	}
	/**
	 * 利用本地文件部分字段增量更新索引,用户可指定字段分隔符和多值分割符
	 * 
	 * @param filePath
	 * @param mutiValueSplit
	 * @param fileSplit
	 * @return
	 */
	public String updatePartIndex(String filePath,String mutiValueSplit,String fileSplit) {
		String type = "1";
		if(fileSplit.length()!=1||mutiValueSplit.length()!=1||fileSplit.equals(mutiValueSplit)){
			return ResultDecorate.decorate("", Constant.STATUS_FAIL,Constant.MSG_SPLIT_NG , Constant.CODE_SERVICE);
		}
		if(Constant.FILESPLIT.contains(fileSplit) && Constant.MUTIVALUESPLIT.contains(mutiValueSplit)){
			return update(filePath,mutiValueSplit,fileSplit,type);
		}else{
			return ResultDecorate.decorate("", Constant.STATUS_FAIL,Constant.MSG_SPLIT_NG , Constant.CODE_SERVICE);
		}
	}
	
	/**
	 * 将指定应用的索引部分字段根据上传的数据进行更新
	 * @param Datas
	 * @return boolean
	 */
	private String updateIndexs(JSONArray datas,String type) {
		boolean upflag = true;
		HashMap<String, Object> opts = new HashMap<String, Object>();
		opts.put("datas", datas);
		opts.put("type", type);
		try {
			String result = client.call(Constant.APP_POST_URL + "/"+Constant.appId+"/indexs", opts, Constant.POST);
			if(IsGoodJson.isGoodJson(result)){
				JSONObject jsonObject = JSON.parseObject(result);
				if (jsonObject.get("status").equals("FAIL")) {
					upflag = false;
				}
				System.out.println(result);
			}else{
				upflag = false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			upflag = false;
		}
		return ResultDecorate.decorate(upflag);
	}
	/**
	 * 将指定应用的索引部分字段根据上传的数据进行更新(部分更新)
	 * @param Datas
	 * @return boolean
	 */
	public String updatePartIndexByJson(JSONArray datas){
		return updateIndexs(datas,"2");
	}
	/**
	 * 将指定应用的索引部分字段根据上传的数据进行更新(全部更新)
	 * @param Datas
	 * @return boolean
	 */
	public String updateAllIndexByJson(JSONArray datas){
		return updateIndexs(datas,"1");
	}
	
	/**
	 * 删除指定的app的数据，数据以json数组方式传递
	 * @param Datas
	 * @return boolean
	 */
	public String deleteIndexs(JSONArray datas) {
		boolean deleteflag = true;
		HashMap<String, Object> opts = new HashMap<String, Object>();
		opts.put("datas", datas);
		opts.put("type", "1");
		try {
			String URL = Constant.APP_POST_URL + "/"+Constant.appId+"/indexs/delete";
			System.out.println(URL);
			String result = client.call(URL, opts, Constant.POST);
			if(IsGoodJson.isGoodJson(result)){
				JSONObject jsonObject = JSONObject.parseObject(result);
				if (jsonObject.get("status").equals("FAIL")) {
					deleteflag = false;
				}
				System.out.println(result);
			}else{
				deleteflag = false;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			deleteflag = false;
		}
		return ResultDecorate.decorate(deleteflag);
	}
	
	/**
	 * 利用本地文件重建索引
	 * 
	 * @param filePath
	 * @return boolean
	 */
	private String rebuild(String filePath) {

		if (!verifyFile(filePath)) {
			return ResultDecorate.decorate(false, "file not exsit or filepath is null");
		}

		boolean rebuildFlag = true;
		// 清空索引
		if (ClearIndex.process(client.getAppId(), client)) {
			// 更新文件
			String result = update(filePath);
			JSONObject jsonObject = JSON.parseObject(result);
			if (jsonObject.get("status").equals("FAIL")) {
				rebuildFlag = false;
			}
		} else {
			rebuildFlag = false;
		}
		return ResultDecorate.decorate(rebuildFlag);
	}

	/**
	 * 清空索引
	 * 
	 * @return
	 */
	public String clearIndex() {
		if (!client.isFlag())
			return "";
		return ResultDecorate.decorate(ClearIndex.process(client.getAppId(), client));

	}

	/**
	 * 删除文件中的索引,该功能待完善
	 * 
	 * @param filePath
	 * @return
	 */
	private String deleteIndex(String filePath,String mutiValueSplit,String fileSplit) {

		if (!verifyFile(filePath)) {
			return ResultDecorate.decorate(false, "file not exsit or filepath is null");
		}

		if (!client.isFlag())
			return "";
		boolean deleteResult = true;
		try {
			/*ArrayList<TableProperty> tablePropertiesList = new ArrayList<TableProperty>();
			LinkedHashMap<String, String> sourceFileMap = new LinkedHashMap<String, String>();

			IndexOperaParamUtil.getDataTableParam(tablePropertiesList, sourceFileMap, client);

			String tableId = tablePropertiesList.get(0).getTableId();*/
			
			String tableId = IndexOperaParamUtil.getAppTableId(client);
			if("".equals(tableId) || tableId==null){
				return ResultDecorate.decorate(false,"TableId未找到");
			}
			
			// 上传文件
			deleteResult = UpdateFile.formUpload(client, client.getUserId(), client.getAppId(), tableId, filePath);
			// 删除文件中的索引
			HashMap<String, Object> opts = new HashMap<String, Object>();
			opts.put("fileSplit", fileSplit);
			opts.put("mutiValueSplit", mutiValueSplit);
			//String result = client.call(Constant.APP_POST_URL + "/index/delete", opts, Constant.POST);
			String result = client.call(Constant.APP_POST_URL + "/"+Constant.appId+"/index", opts, Constant.DELETE);
			if(IsGoodJson.isGoodJson(result)){
				JSONObject jsonObject = JSON.parseObject(result);
				if (jsonObject.get("status").equals("FAIL")) {
					deleteResult = false;
				}
				System.out.println(result);
			}else{
				deleteResult = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			deleteResult = false;
		}
		return ResultDecorate.decorate(deleteResult);

	}
	
	public String deleteIndex(String filePath) {
		String mutiValueSplit = "|";
		String fileSplit = ",";
		return deleteIndex(filePath,mutiValueSplit,fileSplit);
	}
	
}
