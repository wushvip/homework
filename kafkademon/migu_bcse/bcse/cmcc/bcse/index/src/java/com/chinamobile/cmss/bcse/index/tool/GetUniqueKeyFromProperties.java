package com.chinamobile.cmss.bcse.index.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.chinamobile.cmss.bcse.index.bean.TableField;
import com.chinamobile.cmss.bcse.index.bean.TableProperty;

/**
 * 从properties中获取主键
 * 
 * @author jinjing
 *
 */
public class GetUniqueKeyFromProperties {
	public static String get(ArrayList<TableProperty> tableProperties) {
		String uniqueKey = "";
		// 获取主表主键为uniqueKey
		for (TableProperty tableProperty : tableProperties) {
			if (tableProperty.getIsMainTable().equals("1")) { // 主表
				HashMap<String, TableField> fileMap = tableProperty.getFieldMap();
				Iterator<Entry<String, TableField>> it = fileMap.entrySet().iterator();
				while (it.hasNext()) {
					Entry<String, TableField> entry = it.next();
					if (entry.getValue().getKey().equals("1")) {
						uniqueKey = entry.getKey();
					}
				}
			}
		}
		return uniqueKey;
	}
}
