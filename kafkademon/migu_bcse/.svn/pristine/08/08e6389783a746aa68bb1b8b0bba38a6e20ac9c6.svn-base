package com.chinamobile.cmss.bcse.cleardata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.chinamobile.cmss.bcse.index.bean.TableField;
import com.chinamobile.cmss.bcse.index.bean.TableProperty;
import com.chinamobile.cmss.bcse.index.constant.FieldType;
import com.chinamobile.cmss.bcse.index.kafka.CsvToJson;
import com.chinamobile.cmss.bcse.index.kafka.KafkaProduce;
import com.chinamobile.cmss.bcse.index.tool.ReadFromCsv;

/**
 * 
 * @ClassName: ClearUpIndexData
 * @Description: 上传索引的数据清洗的接口
 * @author: jinjing
 * @date: 2016�?2�?16�? 下午3:24:38
 */
public class ClearUpIndexData {

	public static void main(String[] args) {

		ArrayList<TableProperty> tableProperties = new ArrayList<TableProperty>();
		TableProperty tableProperty = new TableProperty();
		tableProperty.setIsMainTable("1");
		tableProperty.setTableId("");
		HashMap<String, TableField> taHashMap = new HashMap<String, TableField>();

		TableField tableField1 = new TableField();
		tableField1.setKey("0");
		tableField1.setType("date");
		taHashMap.put("EXPIRE_DATE", tableField1);

		TableField tableField2 = new TableField();
		tableField2.setKey("0");
		tableField2.setType("date");
		taHashMap.put("VALID_DATE", tableField2);
		/*
		 * TableField tableField3 = new TableField(); tableField3.setKey("0");
		 * tableField3.setType(FieldType.INT); taHashMap.put("attribute",
		 * tableField3);
		 */

		tableProperty.setFieldMap(taHashMap);

		tableProperties.add(tableProperty);

		process("C:\\Users\\Administrator\\Desktop\\game.csv", tableProperties, null, null);

	}

	public static boolean process(String clearFile, ArrayList<TableProperty> tableProperties, String appId, String op) {

		System.out.println("start clean");
		System.out.println(clearFile);
		/**
		 * 通过tableProperties确定哪些字段的类型是date
		 */
		ArrayList<String> dateColoum = new ArrayList<String>();

		HashMap<String, TableField> taHashMap = null;

		char fileSplit = ',';
		String mutiValueSplit = "|";
		ArrayList<String> mutiValueFields = new ArrayList<String>();

		for (int i = 0; i < tableProperties.size(); i++) {
			TableProperty tableProperty = tableProperties.get(i);
			taHashMap = tableProperty.getFieldMap();
			String mSplit = tableProperty.getMutiValueSplit();
			if (!mSplit.equals("") && mSplit != null) {
				mutiValueSplit = mSplit;
			}
			String split = tableProperties.get(i).getFileSplit();
			if (!split.equals("") && split != null) {
				fileSplit = split.toCharArray()[0];
			}

			Iterator<Entry<String, TableField>> iterator = taHashMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, TableField> entry = (Entry<String, TableField>) iterator.next();
				TableField tableField = entry.getValue();
				System.out.println(entry.getKey() + ":" + entry.getValue().getType());
				// 多值
				if (tableField.getIsMutivalue().equals("1")) {
					mutiValueFields.add(entry.getKey());
				}
				if (tableField.getType().equals(FieldType.DATE)) { // 清洗date类型
					dateColoum.add(entry.getKey());
					System.out.println("date flied:" + entry.getKey());
				}

			}
		}
		try {
			// 从文件中读入数据
			List<String[]> lineList = ReadFromCsv.read(clearFile, fileSplit);

			if (lineList.size() == 0) {
				return false;
			}

			// 添加清洗方法
			ArrayList<ClearMethodBase> methods = new ArrayList<ClearMethodBase>();

			methods.add(new DateClearMethod(lineList, dateColoum));

			for (ClearMethodBase clearMethodBase : methods) {
				clearMethodBase.clearDateField();
			}

			// 将csv转成json
			ArrayList<String> jsonData = CsvToJson.convert(lineList, mutiValueFields, mutiValueSplit);

			// 写入kafka
			KafkaProduce.process(appId, op, jsonData);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

}
