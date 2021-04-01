package com.chinamobile.cmss.bcse.sdk.tools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.sdk.index.bean.AppInfoBean;
import com.chinamobile.cmss.bcse.sdk.index.bean.AppRspBean;
import com.chinamobile.cmss.bcse.sdk.index.bean.TableField;
import com.chinamobile.cmss.bcse.sdk.index.bean.TableProperty;

public class AssembleAppResult {

	public static JSONObject assembleResult(String result) {
		//JSONObject jsonObject = JSONObject.fromObject(result);
		JSONObject jsonObject = JSONObject.parseObject(result);
		return jsonObject;
	}
	public static AppRspBean assembleResultBean(String result) {
		/*JSONObject jsonObject = JSONObject.fromObject(result);
		
		return assembleResult(jsonObject);*/
		return JSONObject.parseObject(result,AppRspBean.class);
	}


	/**
	 * 组装索引的相关结果
	 * 
	 * @param jsonObject1
	 * @return
	 */
	private static AppRspBean assembleResult(JSONObject jsonObject1) {

		AppRspBean appRspBean = new AppRspBean();
		/*Map<String, Class> classMap = new HashMap<String, Class>();

		classMap.put("appInfoBeans", AppInfoBean.class);
		classMap.put("tablePropertiesList", TableProperty.class);

		appRspBean = (AppRspBean) JSONObject.toBean(jsonObject1,
				AppRspBean.class, classMap);*/
		appRspBean = (AppRspBean) JSONObject.toJavaObject(jsonObject1, AppRspBean.class);
		
		
		/**
		 * 装填getFieldMap
		 */
		try {
			if (appRspBean.getTablePropertiesList() != null
					&& appRspBean.getTablePropertiesList().size() > 0) {
				appRspBean.getTablePropertiesList().get(0).getFieldMap()
						.clear();

				JSONObject hmJsonObject = jsonObject1
						.getJSONArray("tablePropertiesList").getJSONObject(0)
						.getJSONObject("fieldMap");
				//Iterator<String> it = hmJsonObject.keys();
				Iterator<String> it = (hmJsonObject.keySet()).iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					/*TableField temp = (TableField) JSONObject.toBean(
							JSON.parseObject(JSON.toJSONString(hmJsonObject.get(key))),
							//JSONObject.fromObject(hmJsonObject.get(key)),
							TableField.class);*/
					TableField temp = (TableField)JSONObject.toJavaObject(
							JSON.parseObject(JSON.toJSONString(hmJsonObject.get(key))),
							TableField.class);					
					appRspBean.getTablePropertiesList().get(0).getFieldMap()
							.put(key, temp);
				}
				System.out.println("ok");
				appRspBean.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return appRspBean;
	}
}
