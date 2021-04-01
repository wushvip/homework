package com.chinamobile.cmss.bcse.sdk.index.cleardata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.chinamobile.cmss.bcse.sdk.index.bean.FieldType;
import com.chinamobile.cmss.bcse.sdk.index.bean.TableField;
import com.chinamobile.cmss.bcse.sdk.index.bean.TableProperty;
import com.chinamobile.cmss.bcse.sdk.tools.ConvertToCSV;
import com.chinamobile.cmss.bcse.sdk.tools.ReadFromCsv;

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
		taHashMap.put("collecttime", tableField1);

		TableField tableField2 = new TableField();
		tableField2.setKey("0");
		tableField2.setType("date");
		taHashMap.put("pubtime", tableField2);

		TableField tableField3 = new TableField();
		tableField3.setKey("0");
		tableField3.setType(FieldType.INT);
		taHashMap.put("attribute", tableField3);

		tableProperty.setFieldMap(taHashMap);

		tableProperties.add(tableProperty);

		process("C:\\Users\\Administrator\\Desktop\\fromJson.csv", tableProperties);

	}

	public static boolean process(String clearFile, ArrayList<TableProperty> tableProperties) {

		System.out.println("start clean");
		System.out.println(clearFile);
		/**
		 * 通过tableProperties确定哪些字段的类型是date
		 */
		ArrayList<String> uniquekey = new ArrayList<String>();
		ArrayList<String> dateColoum = new ArrayList<String>();
		HashMap<String, TableField> taHashMap = null;

		for (int i = 0; i < tableProperties.size(); i++) {
			TableProperty tableProperty = tableProperties.get(i);
			taHashMap = tableProperty.getFieldMap();
			Iterator<Entry<String, TableField>> iterator = taHashMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, TableField> entry = (Entry<String, TableField>) iterator.next();
				TableField tableField = entry.getValue();

				System.out.println(entry.getKey() + ":" + entry.getValue().getType());
				if (tableField.getKey().equals("1")) { // 清洗主键
					uniquekey.add(entry.getKey());
					System.out.println("uniqueKey is" + entry.getKey());
				}
				if (tableField.getType().equals(FieldType.DATE)) { // 清洗date类型
					dateColoum.add(entry.getKey());
					System.out.println("date flied:" + entry.getKey());
				}
			

			}
		}
		try {
			// 从文件中读入数据
			List<String[]> lineList = ReadFromCsv.read(clearFile);

			if (lineList.size() == 0) {
				return false;
			}
			// 判断文件头是否正确
			String[] head = lineList.get(0);
			ArrayList hArrayList = new ArrayList<String>();
			for (String object : head) {
				hArrayList.add(object);
			}
			Iterator it = taHashMap.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, TableField> entry = (Entry<String, TableField>) it.next();
				hArrayList.remove(entry.getKey());
			}

			/*
			 * if (hArrayList.size() != 0) { return false; }
			 */

			// 添加清洗方法
			ArrayList<ClearMethodBase> methods = new ArrayList<ClearMethodBase>();
			methods.add(new UniqueKeyClearMethod(lineList, uniquekey));
			methods.add(new DateClearMethod(lineList, dateColoum));
		

			for (ClearMethodBase clearMethodBase : methods) {
				clearMethodBase.clearDateField();
			}
			// 写回文件中
			ConvertToCSV.writeToCsv(lineList, clearFile);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

}
