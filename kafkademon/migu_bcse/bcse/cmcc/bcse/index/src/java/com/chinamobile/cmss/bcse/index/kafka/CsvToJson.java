package com.chinamobile.cmss.bcse.index.kafka;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.bcse.index.bean.TableField;
import com.chinamobile.cmss.bcse.index.bean.TableProperty;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

/**
 * 将csv转成json
 * 
 * @author jinjing
 *
 */
public class CsvToJson {

	/**
	 * csv转json
	 * 
	 * @param lineList
	 * @return ArrayList<String> jsonDate
	 */
	public static ArrayList<String> convert(List<String[]> lineList, ArrayList<String> mutiValueFields,
			String mutiValueSplit) {
		ArrayList<String> jsonDate = new ArrayList<>(lineList.size() - 1);
		String[] heads = lineList.get(0);

		// 对一些split的保留字进行处理
		mutiValueSplit = "\\" + mutiValueSplit;

		for (int i = 1; i < lineList.size(); i++) {
			String[] temp = lineList.get(i);
			JSONObject jsonObject = new JSONObject();
			for (int j = 0; j < temp.length; j++) {
				if (temp[j].equals(""))
					continue;

				if (mutiValueFields.contains(heads[j])) {// 多值
					String[] mutivalues = temp[j].split(mutiValueSplit, -1);
					List arrayList = Arrays.asList(mutivalues);
					JSONArray jsonArray = new JSONArray(arrayList);
					jsonObject.put(heads[j], jsonArray);

				} else {
					jsonObject.put(heads[j], temp[j]);
				}
			}
			jsonDate.add(jsonObject.toString());
		}
		return jsonDate;
	}

	/**
	 * 通过后缀判断字段是否是动态字段
	 * 
	 */
	public static boolean isDynamicField(String inputField, String rightField) {
		if (inputField == null || rightField == null) {
			return false;
		}
		String[] fields = rightField.split("\\*");
		if (fields.length != 2) {
			return false;
		}
		if (inputField.endsWith(fields[1])) {
			return true;
		} else {
			return false;
		}

	}



	public static File reverse(ArrayList<String> jsons, ArrayList<String> headers, TableProperty tableProperty)
			throws Exception {
		LogUtil.loggerEntrance(LogUtil.INDEX_LOG, "json convert to csv");
		File file = new File("tmp" + System.currentTimeMillis());

		CsvWriter csvWriter = new CsvWriter(new FileOutputStream(file), ',', Charset.forName("UTF-8"));
		csvWriter.setForceQualifier(true);
		String[] header = new String[headers.size()];

		for (int i = 0; i < headers.size(); i++) {
			header[i] = headers.get(i);
		}
		// 写入文件头
		csvWriter.writeRecord(header);
		HashMap<String, TableField> map = tableProperty.getFieldMap();
		Iterator<Entry<String, TableField>> iterator = map.entrySet().iterator();
		for (int i = 0; i < jsons.size(); i++) {
			String[] tmp = new String[headers.size()];
			for (int j = 0; j < headers.size(); j++) {
				JSONObject jsonObject = JSONObject.parseObject(jsons.get(i));
				if (jsonObject.containsKey(headers.get(j))) {
					if (tableProperty.getFieldMap().get(headers.get(j)).getIsMutivalue().equals("1")) {
						JSONArray jsonArray = jsonObject.getJSONArray(headers.get(j));
						String tmp2 = "";
						if (jsonArray.size() > 0) {
							tmp2 = jsonArray.getString(0);
							for (int k = 1; k < jsonArray.size(); k++) {
								tmp2 = tmp2 + tableProperty.getMutiValueSplit() + jsonArray.getString(k);
							}
						} else {
							tmp2 = "";
						}
						tmp[j] = tmp2;

					} else {
						tmp[j] = jsonObject.getString(headers.get(j));
					}
				} else {
					// 动态字段处理
					boolean flag = false;
					String dicKey = headers.get(j);
					Iterator<String> sIterator = jsonObject.keySet().iterator();
					while (sIterator.hasNext()) {

						String infield = sIterator.next();
						//LogUtil.InfoLog(LogUtil.INDEX_LOG, infield + "---" + dicKey);
						if (isDynamicField(infield, dicKey)) {
							tmp[j] = jsonObject.getString(infield);
							flag = true;
							break;
						}
					}
					if (!flag) {
						tmp[j] = "";;
					} 

				}
			}

			csvWriter.writeRecord(tmp);
		}
		csvWriter.close();
		LogUtil.loggerEntrance(LogUtil.INDEX_LOG, "json convert to csv finish");
		return file;
	}
}
