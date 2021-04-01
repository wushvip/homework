package com.chinamobile.cmss.bcse.index.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chinamobile.cmss.bcse.index.bean.FieldProperty;
import com.chinamobile.cmss.bcse.index.bean.SchemaProperty;
import com.chinamobile.cmss.bcse.index.bean.SolrConfProperty;
import com.chinamobile.cmss.bcse.index.bean.TableField;
import com.chinamobile.cmss.bcse.index.bean.TableProperty;
import com.chinamobile.cmss.bcse.index.constant.FieldType;
import com.chinamobile.cmss.bcse.index.entry.CreateApp;
import com.chinamobile.cmss.bcse.index.entry.DeleteApp;
import com.chinamobile.cmss.bcse.index.entry.RebuildIndex;
import com.chinamobile.cmss.bcse.index.entry.UpdateData;

/**
 * 
 * @ClassName: Main
 * @Description: 索引管理组,测试接口
 * @author: jinjing
 * @date: 2016年2月18日 上午10:14:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class Entry {

	// 第一张表
	TableProperty tableProperty1 = new TableProperty();
	TableField tableField11 = new TableField();
	TableField tableField12 = new TableField();
	TableField tableField13 = new TableField();
	// 封装schemaProperty
	SchemaProperty schemaProperty = new SchemaProperty();
	HashMap<String, FieldProperty> fieldmap = new HashMap<String, FieldProperty>();

	ArrayList<TableProperty> tableProperties = new ArrayList<TableProperty>();

	// 封装solrConfPropertys
	ArrayList<SolrConfProperty> solrConfPropertys = new ArrayList<SolrConfProperty>();
	SolrConfProperty solrConfProperty = new SolrConfProperty();
	HashMap<String, Integer> reHashMap = new HashMap<String, Integer>();

	FieldProperty fieldProperty1 = new FieldProperty();
	FieldProperty fieldProperty2 = new FieldProperty();
	FieldProperty fieldProperty3 = new FieldProperty();
	// 封装tableList
	LinkedHashMap<String, String> tableList = new LinkedHashMap<String, String>();

	/**
	 * 初始化一些数据
	 * 
	 * @Title: init
	 * @Description: TODO
	 * @return: void
	 */
	private void init() {
		tableProperty1.setIsMainTable("1");
		tableProperty1.setTableId("main");
		HashMap<String, TableField> fieldMap1 = new HashMap<String, TableField>();

		tableField11.setKey("1");
		tableField11.setType(FieldType.STRING);
		fieldMap1.put("id", tableField11);

		tableField12.setKey("0");
		tableField12.setType(FieldType.STRING);
		fieldMap1.put("name", tableField12);

		tableField13.setKey("0");
		tableField13.setType(FieldType.STRING);
		// tableField13.setOutTable("second");
		fieldMap1.put("iname", tableField13);

		tableProperty1.setFieldMap(fieldMap1);

		tableProperties.add(tableProperty1);

		fieldProperty1.setFieldType("string");
		fieldProperty1.setIsIndex(true);
		fieldProperty1.setIsStored(true);
		fieldmap.put("id", fieldProperty1);

		fieldProperty2.setFieldType("string");
		fieldProperty2.setIsIndex(true);
		fieldProperty2.setIsStored(true);
		fieldmap.put("name", fieldProperty2);

		fieldProperty3.setFieldType("string");
		fieldProperty3.setIsIndex(true);
		fieldProperty3.setIsStored(true);
		fieldmap.put("iname", fieldProperty3);

		schemaProperty.setFieldMap(fieldmap);

		solrConfProperty.setRequestHeaderName("default");

		reHashMap.put("name", 1);
		reHashMap.put("iname", 1);
		solrConfProperty.setRequestField(reHashMap);

		tableList.put("main", "main.csv");
	}

	@Test
	public void testIndex() {
		init();
		// 创建应用
		boolean flag1 = CreateApp.process("testUser", "test", tableProperties, schemaProperty, solrConfPropertys,
				tableList);
		// 上传数据
		boolean flag2 = UpdateData.process("testUser", "test", tableList, tableProperties, "1");
		// 重建索引
		boolean flag3 = RebuildIndex.process("testUser", "test", tableList, tableProperties);
		// 删除应用
		boolean flag4 = DeleteApp.process("testUser", "test", tableProperties);
		if (flag1 && flag2 && flag3 && flag4) {
			System.out.println("index test pass");
		} else {
			System.out.println("index test not pass");
			System.out.println("flag1:" + flag1);
			System.out.println("flag2:" + flag2);
			System.out.println("flag3:" + flag3);
			System.out.println("flag4:" + flag4);
		}
	}

}
