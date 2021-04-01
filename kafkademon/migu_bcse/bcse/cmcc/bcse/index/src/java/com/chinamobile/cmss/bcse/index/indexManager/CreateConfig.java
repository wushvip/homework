package com.chinamobile.cmss.bcse.index.indexManager;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.solr.common.cloud.SolrZkClient;
import org.apache.solr.common.cloud.ZkConfigManager;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.chinamobile.cmss.bcse.index.bean.FieldProperty;
import com.chinamobile.cmss.bcse.index.bean.SchemaProperty;
import com.chinamobile.cmss.bcse.index.bean.SolrConfProperty;
import com.chinamobile.cmss.bcse.index.tool.CopyDic;
import com.chinamobile.cmss.bcse.index.tool.DeleteDir;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;
import com.sun.tools.javac.util.List;

/**
 * 
 * @ClassName: CreateConfig
 * @Description: 生成配置文件
 * @author: jinjing
 * @date: 2016年2月16日 下午3:16:16
 */
public class CreateConfig {

	/*public static void main(String[] args) {

		SchemaProperty schemaProperty = new SchemaProperty();
		ArrayList<SolrConfProperty> solrConfPropertys = new ArrayList<SolrConfProperty>();

		// schemaProperty.setFieldMap(fieldMap);

		
		 * addConfig( schemaProperty,solrConfPropertys, "/conf/",
		 * CreateConfig.class.getClassLoader().getResource("")
		 * .getPath()+"\\ConFile\\"+"schema.xml",
		 * CreateConfig.class.getClassLoader().getResource("")
		 * .getPath()+"\\ConFile\\"+"solrconfig.xml");
		 

		HashMap<String, FieldProperty> fieldmap = new HashMap<String, FieldProperty>();
		FieldProperty fieldProperty = new FieldProperty();
		fieldProperty.setFieldType("string");
		fieldProperty.setIsIndex(true);
		fieldProperty.setIsStored(true);
		fieldProperty.setIS_MUTIVALUE(true);

		FieldProperty fieldProperty2 = new FieldProperty();
		fieldProperty2.setFieldType("string");
		fieldProperty2.setIsIndex(true);
		fieldProperty2.setIsStored(true);
		fieldProperty2.setIS_MUTIVALUE(true);
		fieldmap.put("qq", fieldProperty2);

		fieldmap.put("jinjing", fieldProperty);
		schemaProperty.setFieldMap(fieldmap);

		try {
			System.out.println(process(schemaProperty, solrConfPropertys,
					"C:\\Users\\Administrator\\Desktop\\git6\\se\\bcse\\conf\\",
					"C:\\Users\\Administrator\\Desktop\\git6\\se\\bcse\\cmcc\\bcse\\resources\\TemplateConf",
					"a425c0e43f054f5fb0b6386a64637b6b"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * 
	 * @Title: process
	 * @Description: 入口
	 * @param schemaProperty
	 * @param solrConfPropertys
	 * @param confPath
	 * @param baseConfig
	 * @param coreName
	 * @return
	 * @throws Exception
	 * @return: Boolean
	 */
	public static Boolean process(SchemaProperty schemaProperty, ArrayList<SolrConfProperty> solrConfPropertys,
			String confPath, String baseConfig, String coreName) throws Exception {

		// 建立本地配置文件夹
		mkDIR(confPath);

		// 拷贝模板文件到本地配置文件夹
		copyFiles(baseConfig, confPath);

		// 重写schema文件
		reWriteSchema(schemaProperty, confPath);

		// 重写solrconfig文件
		reWriteSolrConfig(schemaProperty, solrConfPropertys, confPath);

		// 上传zookeeper

		System.out.println("confpath:" + confPath);
		/*
		 * ZkController.uploadConfigDir(new SolrZkClient(Config.ZK_HOST, 10000,
		 * 10000), new File(confPath), coreName);
		 */
		ZkConfigManager confManager = new ZkConfigManager(new SolrZkClient(Config.ZK_HOST, 10000, 10000));
		confManager.uploadConfigDir(Paths.get(confPath), coreName);

		System.out.println("zookeeper update");
		return true;
	}

	/**
	 * 
	 * @Title: mkDIR
	 * @Description: 建立文件夹
	 * @param path
	 * @return: void
	 */
	private static void mkDIR(String path) {
		File file = new File(path);
		if (!file.exists()) {
			System.out.println(file.mkdirs());
		} else {
			if (!file.delete()) {
				DeleteDir.process(new File(path));
			}
			file.mkdir();
		}
	}

	/**
	 * 
	 * @Title: copyFiles
	 * @Description: 將配置的模板文件複製到指定目錄下，以方便修改
	 * @param baseConfig
	 * @param confPath
	 * @return: void
	 */
	private static void copyFiles(String baseConfig, String confPath) {
		try {

			File[] file = (new File(baseConfig)).listFiles();
			for (int i = 0; i < file.length; i++) {
				if (file[i].isFile()) {
					// 复制文件
					CopyDic.copyFile(file[i], new File(confPath + file[i].getName()));
				}
				if (file[i].isDirectory()) {
					// 复制目录
					String sorceDir = baseConfig + File.separator + file[i].getName();
					String targetDir = confPath + File.separator + file[i].getName();
					CopyDic.copyDirectiory(sorceDir, targetDir);
				}
			}

			System.out.println("copy ok");
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.loggerEntrance(LogUtil.INDEX_LOG, e.getMessage());
		}
	}

	/**
	 * 
	 * @Title: reWriteSchema
	 * @Description: 修改schema文件
	 * @param schemaProperty
	 * @param confPath
	 * @throws Exception
	 * @return: void
	 */
	private static void reWriteSchema(SchemaProperty schemaProperty, String confPath) throws Exception {
		HashMap<String, FieldProperty> fieldmap = schemaProperty.getFieldMap();
		Iterator<Entry<String, FieldProperty>> iter = fieldmap.entrySet().iterator();

		SAXReader reader = new SAXReader();
		System.out.println(new File(confPath + "schema.xml").getAbsolutePath());
		Document doc = reader.read(new File(confPath + "schema.xml"));

		Element root = doc.getRootElement();
		@SuppressWarnings("unchecked")
		java.util.List<Element> list = root.content();

		while (iter.hasNext()) {
			Map.Entry<String, FieldProperty> entry = (Map.Entry<String, FieldProperty>) iter.next();
			String name = (String) entry.getKey();
			FieldProperty fieldProperty = (FieldProperty) entry.getValue();

			String fieldType = fieldProperty.getFieldType();

			if (fieldProperty.getIsPk()) { // 主键写入uniqueKey
				Element uniqueKey = DocumentHelper.createElement("uniqueKey");
				uniqueKey.setText(name);
				list.add(5, uniqueKey);
			}

			String index = "";
			if (fieldProperty.getIsIndex()) {
				index = "true";
			} else {
				index = "false";
			}
			String store = "";
			if (fieldProperty.getIsStored()) {
				store = "true";
			} else {
				store = "false";
			}

			// 处理copyField
			if (fieldProperty.getSRC_FIELD() != null && fieldProperty.getSRC_FIELD().length() > 0) {

				String[] fields = fieldProperty.getSRC_FIELD().split(",");

				for (String string : fields) {
					String dest = "";
					if (fieldmap.get(name).isDYNAMIC_FIELD()) {
						dest = name;
					} else {
						dest = name;
					}
					Element elementcopy = DocumentHelper.createElement("copyField").addAttribute("source", string)
							.addAttribute("dest", dest);

					list.add(5, elementcopy);
				}

			}

			String elementType = "field";
			if (fieldProperty.isDYNAMIC_FIELD()) {
				elementType = "dynamicField";
			}

			// 判断多值
			Element fieldElement = null;
			if (fieldProperty.isIS_MUTIVALUE()) {
				// 写入field
				fieldElement = DocumentHelper.createElement(elementType).addAttribute("name", name)
						.addAttribute("type", fieldType).addAttribute("indexed", index).addAttribute("stored", store)
						.addAttribute("multiValued", "true");

			} else {

				fieldElement = DocumentHelper.createElement(elementType).addAttribute("name", name)
						.addAttribute("type", fieldType).addAttribute("indexed", index).addAttribute("stored", store);
				if (fieldProperty.getSRC_FIELD() != null && fieldProperty.getSRC_FIELD().length() > 0) {
					fieldElement.addAttribute("multiValued", "true");
				}
			}
			list.add(5, fieldElement);

		}

		iter = fieldmap.entrySet().iterator();

		OutputFormat outputFormat = OutputFormat.createPrettyPrint();
		outputFormat.setEncoding("UTF-8");

		XMLWriter writer = new XMLWriter(new FileWriter(confPath + "schema.xml"), outputFormat);
		writer.write(doc);
		writer.close();
		System.out.println("schema ok");

	}

	/**
	 * 
	 * @Title: reWriteSolrConfig
	 * @Description: 修改solrconfig文件
	 * @param solrConfPropertys
	 * @param confPath
	 * @throws Exception
	 * @return: void
	 */
	private static void reWriteSolrConfig(SchemaProperty schemaProperty, ArrayList<SolrConfProperty> solrConfPropertys,
			String confPath) throws Exception {

		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File(confPath + "solrconfig.xml"));

		Element root = doc.getRootElement();
		@SuppressWarnings("unchecked")
		java.util.List<Element> list = root.content();

		// 写多值字段
		HashMap<String, FieldProperty> maps = schemaProperty.getFieldMap();
		Set<String> keys = maps.keySet();

		String xpath = "/config/requestHandler[@name='/update/csv']/lst";
		java.util.List<Element> list2 = doc.selectNodes(xpath);
		for (String key : keys) {
			if (maps.get(key).isIS_MUTIVALUE()) {

				Element str1 = DocumentHelper.createElement("str").addAttribute("name", "f." + key + ".split");
				str1.setText("true");
				Element str2 = DocumentHelper.createElement("str").addAttribute("name", "f." + key + ".separator");
				str2.setText("|");
				list2.get(0).add(str1);
				list2.get(0).add(str2);
			}
		}

		// 写入每个requestHander
		for (SolrConfProperty solrConfProperty : solrConfPropertys) {

			String requestName = solrConfProperty.getRequestHeaderName();

			Element requestElement = DocumentHelper.createElement("requestHandler")
					.addAttribute("name", "/indexfield_" + requestName).addAttribute("class", "solr.SearchHandler");

			Element lst = DocumentHelper.createElement("lst").addAttribute("name", "defaults");

			Element explicit = DocumentHelper.createElement("str").addAttribute("name", "echoParams");
			explicit.setText("explicit");
			lst.add(explicit);

			Element edismax = DocumentHelper.createElement("str").addAttribute("name", "defType");
			edismax.setText("edismax");
			lst.add(edismax);

			Element rows = DocumentHelper.createElement("int").addAttribute("name", "rows");
			rows.setText("10");
			lst.add(rows);

			Element qf = DocumentHelper.createElement("str").addAttribute("name", "qf");
			Iterator<Entry<String, Integer>> iterator = solrConfProperty.getRequestField().entrySet().iterator();
			String qfString = "";
			while (iterator.hasNext()) {
				Entry<String, Integer> temp = (Entry<String, Integer>) iterator.next();
				if (temp.getKey().indexOf("*") != -1) {
					continue;
				} else {
					qfString += temp.getKey() + " ";
				}
			}

			qf.setText(qfString);
			lst.add(qf);

			requestElement.add(lst);

			Element arr = DocumentHelper.createElement("arr").addAttribute("name", "last-components");

			Element elevator = DocumentHelper.createElement("str");
			elevator.setText("elevator");
			arr.add(elevator);

			requestElement.add(arr);

			list.add(requestElement);
		}

		OutputFormat outputFormat = OutputFormat.createPrettyPrint();
		outputFormat.setEncoding("UTF-8");

		XMLWriter writer = new XMLWriter(new FileWriter(confPath + "solrconfig.xml"), outputFormat);
		writer.write(doc);
		writer.close();
		System.out.println("solrconfig ok");
	}
}
