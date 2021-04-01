package com.chinamobile.cmss.bcse.search.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.dom4j.Document;
import org.dom4j.Element;

import com.chinamobile.cmss.bcse.search.bean.ZkConfigFileType;
import com.chinamobile.cmss.bcse.search.cloudapi.ReloadSolrCloud;
import com.chinamobile.cmss.bcse.search.tool.XmlTool;
import com.chinamobile.cmss.bcse.search.tool.ZookeeperUtil;
import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.tool.exception.ExcuteQueryException;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;



/**
 * @ClassName: RuleConfigImpl
 * @Description: 高级配置模块规则更新后配置文件更新实现类
 * @author: zhenglinfeng
 * @date: 2016年2月16日 上午9:36:24
 */
public class RuleConfigImpl implements IRuleConfig {/*
	
	 * private ZkConfigManager zkConfigManager = null; public RuleConfigImpl(){
	 * zkConfigManager = new ZkConfigManager( new SolrZkClient(Config.ZK_HOST,
	 * 10000, 10000)); }
	 

	
	 * (non Javadoc)
	 * 
	 * @Title: addRule
	 * 
	 * @Description: 配置文件添加规则
	 * 
	 * @param ruleBeanWithBLOBs
	 * 
	 * @return boolean
	 * 
	 * @throws ConfigXMLUpdateFailureException
	 * 
	 * @see com.bcse.search.config.RuleConfig#addRule(com.bcse.web.bean.rule.
	 * RuleBeanWithBLOBs)
	 
	@Override
	public boolean addRule(RuleBeanWithBLOBs ruleBeanWithBLOBs) throws ConfigXMLUpdateFailureException {
		// TODO Auto-generated method stub
		if (ruleBeanWithBLOBs == null)
			return false;

		String appId = ruleBeanWithBLOBs.getAppId();
		if (appId == null || appId.trim().length() == 0)
			return false;

		String ruleType = ruleBeanWithBLOBs.getRuleType();
		boolean flag = true;
		Document document = null;
		// 根据规则类型做响应操作
		switch (ruleType) {
		// 添加粗排规则
		case "0": {
			String ruleName = Config.ROUGH_SORT_PREX + ruleBeanWithBLOBs.getId();
			String includeFields = ruleBeanWithBLOBs.getIncludeFields();
			String fieldWeights = ruleBeanWithBLOBs.getFieldWeights();

			if (ruleName == null || includeFields == null || fieldWeights == null) {
				return false;
			}
			String[] confFields = includeFields.split(";");
			String[] confWeights = fieldWeights.split(";");

			if (confFields.length == 0 || confWeights.length == 0) {
				return false;
			}
			String qfString = "";
			try {
				ZookeeperUtil.getFile(appId, ZkConfigFileType.SOLRCONFIG);
				document = XmlTool.readXML(Config.ZKCONFIG_LOCALDIR + "/" + appId + "/solrconfig.xml", "utf-8");
				if (document == null) {
					return false;
				}
				for (int i = 0, j = 0; i < confFields.length && j < confWeights.length; i++, j++) {
					qfString = qfString + " " + confFields[i] + "^" + confWeights[j];
				}
				Element rootElement = (Element) document.getRootElement();
				Element queryElement = rootElement.addElement("requestHandler");
				queryElement.addAttribute("name", "/" + ruleName);
				queryElement.addAttribute("class", "solr.SearchHandler");

				addLastComponents(queryElement);

				Element lst = queryElement.addElement("lst");
				lst.addAttribute("name", "defaults");
				Element str = lst.addElement("str");
				str.addAttribute("name", "echoParams");
				str.setText("explicit");
				Element typeStr = lst.addElement("str");
				typeStr.addAttribute("name", "defType");
				typeStr.setText("edismax");
				Element qfStr = lst.addElement("str");
				qfStr.addAttribute("name", "qf");
				qfStr.setText(qfString);
				XmlTool.writeXML(document, Config.ZKCONFIG_LOCALDIR + "/" + appId + "/solrconfig.xml", "utf-8");
				ZookeeperUtil.putFile(appId, ZkConfigFileType.SOLRCONFIG);
			} catch (Exception e) {
				flag = false;
				e.printStackTrace();
				LogUtil.loggerEntrance(ruleBeanWithBLOBs.getUserId(), ruleBeanWithBLOBs.getAppId(),
						ExceptionConstants.ConfigXMLUpdateError, LogUtil.SEARCH_LOG, e);
				throw new ConfigXMLUpdateFailureException(e);
			}
			break;
		}

			// 添加查询分析规则
		case "2": {
			int isExtend = ruleBeanWithBLOBs.getIsExtend();
			int isStopword = ruleBeanWithBLOBs.getIsStopword();
			int isSynonyms = ruleBeanWithBLOBs.getIsSynonyms();
			// solrconfigXmlFile = Config.CORE_CONFIG_DIR + appId +
			// File.separator + "schema.xml";

			String fieldType = Config.FIELD_TYPE;
			try {
				ZookeeperUtil.getFile(appId, ZkConfigFileType.SCHEMA);
				document = XmlTool.readXML(Config.ZKCONFIG_LOCALDIR + "/" + appId + "/schema.xml", "utf-8");
				if (document == null) {
					return false;
				}

				Element rootElement = (Element) document.getRootElement();
				Element thisWord = (Element) rootElement.selectSingleNode("fieldType[@name='" + fieldType + "']");
				if (thisWord == null) {
					return false;
				}
				if (isExtend == 1) {
					Element paramElement = (Element) thisWord.selectSingleNode("analyzer[@type='index']");
					Element tokenElement = (Element) paramElement
							.selectSingleNode("tokenizer[@class='org.ansj.splitWord.analysis.AnsjTokenizerFactory']");
					tokenElement.setAttributeValue("extDic", "library/ext.dic");
				}
				Element paramElement = (Element) thisWord.selectSingleNode("analyzer[@type='query']");
				if (isStopword == 1) {
					Element tokenElement = (Element) paramElement
							.selectSingleNode("filter[@class='solr.StopFilterFactory']");
					if (tokenElement != null) {
						tokenElement.setAttributeValue("class", "solr.StopFilterFactory");
						tokenElement.setAttributeValue("ignoreCase", "true");
						tokenElement.setAttributeValue("words", "stopwords.txt");
					} else {
						Element filter = paramElement.addElement("filter");
						filter.addAttribute("class", "solr.StopFilterFactory");
						filter.addAttribute("ignoreCase", "true");
						filter.addAttribute("words", "stopwords.txt");
					}
				}
				if (isSynonyms == 1) {
					Element tokenElement = (Element) paramElement
							.selectSingleNode("filter[@class='solr.SynonymFilterFactory']");
					if (tokenElement != null) {
						tokenElement.setAttributeValue("class", "solr.SynonymFilterFactory");
						tokenElement.setAttributeValue("ignoreCase", "true");
						tokenElement.setAttributeValue("synonyms", "synonyms.txt");
					} else {
						Element filter = paramElement.addElement("filter");
						filter.addAttribute("class", "solr.SynonymFilterFactory");
						filter.addAttribute("synonyms", "synonyms.txt");
					}

				}
				// 重写xml
				// XmlTool.writeXML(document, solrconfigXmlFile, "utf-8");
				XmlTool.writeXML(document, Config.ZKCONFIG_LOCALDIR + "/" + appId + "/schema.xml", "utf-8");
				ZookeeperUtil.putFile(appId, ZkConfigFileType.SCHEMA);
			} catch (Exception e) {
				flag = false;
				LogUtil.loggerEntrance(ruleBeanWithBLOBs.getUserId(), ruleBeanWithBLOBs.getAppId(),
						ExceptionConstants.ConfigXMLUpdateError, LogUtil.SEARCH_LOG, e);
				throw new ConfigXMLUpdateFailureException("配置文件更新失败");
			}
			break;
		}
			// 添加智能联想规则
		case "3": {
			String field = ruleBeanWithBLOBs.getIncludeFields();
			String ruleString = Config.INTEL_ASSOCIATE_PREX + ruleBeanWithBLOBs.getId();

			if (appId == null || field == null || ruleString == null) {
				return false;
			}

			String[] fields = field.split(";");
			if (fields == null) {
				return false;
			}
			String qfString = "";
			try {
				ZookeeperUtil.getFile(appId, ZkConfigFileType.SOLRCONFIG);
				document = XmlTool.readXML(Config.ZKCONFIG_LOCALDIR + "/" + appId + "/solrconfig.xml", "utf-8");
				if (document == null) {
					return false;
				}
				for (int i = 0; i < fields.length; i++) {
					qfString = qfString + " " + fields[i];
				}
				Element rootElement = (Element) document.getRootElement();
				Element queryElement = rootElement.addElement("requestHandler");
				queryElement.addAttribute("name", "/" + ruleString);
				queryElement.addAttribute("class", "solr.SearchHandler");

				addLastComponents(queryElement);

				Element lst = queryElement.addElement("lst");
				lst.addAttribute("name", "defaults");
				Element str = lst.addElement("str");
				str.addAttribute("name", "echoParams");
				str.setText("explicit");
				Element typeStr = lst.addElement("str");
				typeStr.addAttribute("name", "defType");
				typeStr.setText("edismax");
				Element qfStr = lst.addElement("str");
				qfStr.addAttribute("name", "qf");
				qfStr.setText(qfString);
				// 重写xml
				XmlTool.writeXML(document, Config.ZKCONFIG_LOCALDIR + "/" + appId + "/solrconfig.xml", "utf-8");
				ZookeeperUtil.putFile(appId, ZkConfigFileType.SOLRCONFIG);
			} catch (Exception e) {
				flag = false;
				LogUtil.loggerEntrance(ruleBeanWithBLOBs.getUserId(), ruleBeanWithBLOBs.getAppId(),
						ExceptionConstants.ConfigXMLUpdateError, LogUtil.SEARCH_LOG, e);
				throw new ConfigXMLUpdateFailureException("配置文件更新失败");
			}
			break;
		}
			// 添加按域检索规则
		case "4": {
			String includeFields = ruleBeanWithBLOBs.getIncludeFields();
			String ruleName = Config.INDEX_FIELD_PREX + ruleBeanWithBLOBs.getRuleName();
			if (includeFields == null || ruleName == null) {
				return false;
			}
			String[] fields = includeFields.split(";");
			if (fields == null) {
				return false;
			}
			try {
				ZookeeperUtil.getFile(appId, ZkConfigFileType.SOLRCONFIG);
				document = XmlTool.readXML(Config.ZKCONFIG_LOCALDIR + "/" + appId + "/solrconfig.xml", "utf-8");
				if (document == null)
					return false;
				String qfString = "";
				for (int i = 0; i < fields.length; i++) {
					qfString = qfString + " " + fields[i];
				}
				Element rootElement = (Element) document.getRootElement();
				Element queryElement = rootElement.addElement("requestHandler");
				queryElement.addAttribute("name", "/" + ruleName);
				queryElement.addAttribute("class", "solr.SearchHandler");
				addLastComponents(queryElement);
				Element lst = queryElement.addElement("lst");
				lst.addAttribute("name", "defaults");
				Element str = lst.addElement("str");
				str.addAttribute("name", "echoParams");
				str.setText("explicit");
				Element typeStr = lst.addElement("str");
				typeStr.addAttribute("name", "defType");
				typeStr.setText("edismax");
				Element qfStr = lst.addElement("str");
				qfStr.addAttribute("name", "qf");
				qfStr.setText(qfString);
				// 重写xml
				XmlTool.writeXML(document, Config.ZKCONFIG_LOCALDIR + "/" + appId + "/solrconfig.xml", "utf-8");
				ZookeeperUtil.putFile(appId, ZkConfigFileType.SOLRCONFIG);
			} catch (Exception e) {
				LogUtil.loggerEntrance(ruleBeanWithBLOBs.getUserId(), ruleBeanWithBLOBs.getAppId(),
						ExceptionConstants.ConfigXMLUpdateError, LogUtil.SEARCH_LOG, e);
				flag = false;
				throw new ConfigXMLUpdateFailureException("配置文件更新失败");
			}
			break;
		}
			// 更新搜索推广规则
		case "6": {
			// solrconfigXmlFile = Config.CORE_CONFIG_DIR + appId +
			// File.separator + "elevate.xml";
			String[] docIds = null;
			if (null != ruleBeanWithBLOBs.getSpreadIds()) {
				docIds = ruleBeanWithBLOBs.getSpreadIds().split(";");
			} else {
				return false;
			}
			try {
				ZookeeperUtil.getFile(appId, ZkConfigFileType.ELEVATE);
				document = XmlTool.readXML(Config.ZKCONFIG_LOCALDIR + "/" + appId + "/elevate.xml", "utf-8");
				if (document == null) {
					return false;
				}
				Element rootElement = (Element) document.getRootElement();
				// 找是否有该关键字的节点
				Element thisWord = (Element) rootElement
						.selectSingleNode("query[@text='" + ClientUtils.escapeQueryChars(ruleBeanWithBLOBs.getIncludeKeywords()) + "']");
				if (null == thisWord) {
					// 加入新节点
					Element queryElement = rootElement.addElement("query");
					queryElement.addAttribute("text", ClientUtils.escapeQueryChars(ruleBeanWithBLOBs.getIncludeKeywords()));
					for (int i = 0; i < docIds.length; ++i) {
						Element docElement = queryElement.addElement("doc");
						docElement.addAttribute("id", docIds[i]);
					}
				} else {
					// 修改节点
					rootElement.remove(thisWord);
					rootElement.add(thisWord);
					for (int i = 0; i < docIds.length; ++i) {

						Element docidElement = (Element) thisWord.selectSingleNode("doc[@id='" + docIds[i] + "']");

						// 节点存在
						if (null != docidElement) {
							continue;
						} else {
							Element docElement = thisWord.addElement("doc");
							docElement.addAttribute("id", docIds[i]);
						}
					}
				}
				XmlTool.writeXML(document, Config.ZKCONFIG_LOCALDIR + "/" + appId + "/elevate.xml", "utf-8");
				ZookeeperUtil.putFile(appId, ZkConfigFileType.ELEVATE);
			} catch (Exception e) {
				flag = false;
				LogUtil.loggerEntrance(ruleBeanWithBLOBs.getUserId(), ruleBeanWithBLOBs.getAppId(),
						ExceptionConstants.ConfigXMLUpdateError, LogUtil.SEARCH_LOG, e);
			}
			break;
		}
		default: {
			return true;
		}
		}
		if (flag) {
			flag = reload(ruleBeanWithBLOBs.getUserId(), appId);
		}
		return flag;
	}

	
	 * (non Javadoc)
	 * 
	 * @Title: modRule
	 * 
	 * @Description: 更改配置文件已有规则
	 * 
	 * @param ruleBeanWithBLOBs
	 * 
	 * @return boolean
	 * 
	 * @throws ConfigXMLUpdateFailureException
	 * 
	 * @see com.bcse.search.config.RuleConfig#modRule(com.bcse.web.bean.rule.
	 * RuleBeanWithBLOBs)
	 
	@Override
	public boolean modRule(RuleBeanWithBLOBs ruleBeanWithBLOBs) throws ConfigXMLUpdateFailureException {
		// TODO Auto-generated method stub
		if (ruleBeanWithBLOBs == null)
			return false;

		String appId = ruleBeanWithBLOBs.getAppId();
		if (appId == null || appId.trim().length() == 0)
			return false;

		String ruleType = ruleBeanWithBLOBs.getRuleType();
		boolean flag = true;

		Document document = null;

		switch (ruleType) {
		// 粗排规则更新
		case "0": {
			String confName = Config.ROUGH_SORT_PREX + ruleBeanWithBLOBs.getId();
			String confWeight = ruleBeanWithBLOBs.getFieldWeights();
			String confField = ruleBeanWithBLOBs.getIncludeFields();
			if (confField == null || confName == null || confWeight == null) {
				return false;
			}

			String[] confFields = confField.split(";");
			String[] confWeights = confWeight.split(";");

			if (confFields.length == 0 || confWeights.length == 0) {
				return false;
			}
			String qfString = "";
			try {
				ZookeeperUtil.getFile(appId, ZkConfigFileType.SOLRCONFIG);
				document = XmlTool.readXML(Config.ZKCONFIG_LOCALDIR + "/" + appId + "/solrconfig.xml", "utf-8");
				if (document == null) {
					return false;
				}
				for (int i = 0, j = 0; i < confFields.length && j < confWeights.length; i++, j++) {
					qfString = qfString + " " + confFields[i] + "^" + confWeights[j];
				}
				Element rootElement = (Element) document.getRootElement();
				Element thisWord = (Element) rootElement.selectSingleNode("requestHandler[@name='/" + confName + "']");
				Element paramElement = (Element) thisWord.selectSingleNode("lst[@name='defaults']");
				Element qfElement = (Element) paramElement.selectSingleNode("str[@name='qf']");
				qfElement.setText(qfString);
				XmlTool.writeXML(document, Config.ZKCONFIG_LOCALDIR + "/" + appId + "/solrconfig.xml", "utf-8");
			} catch (Exception e) {
				flag = false;
				LogUtil.loggerEntrance(ruleBeanWithBLOBs.getUserId(), ruleBeanWithBLOBs.getAppId(),
						ExceptionConstants.ConfigXMLUpdateError, LogUtil.SEARCH_LOG, e);
			}
			break;
		}
			
			 * // 更新查询分析规则(逻辑与添加查询分析相同，如果已存在规则，则重新设置，否则添加规则) case "2": { int
			 * isExtend = ruleBeanWithBLOBs.getIsExtend(); int isStopword =
			 * ruleBeanWithBLOBs.getIsStopword(); int isSynonyms =
			 * ruleBeanWithBLOBs.getIsSynonyms();
			 * 
			 * solrconfigXmlFile = Config.CORE_CONFIG_DIR + appId +
			 * File.separator + "schema.xml";
			 * 
			 * String fieldType = Config.FIELD_TYPE; try { document =
			 * XmlTool.readXML(solrconfigXmlFile, "utf-8"); if (document ==
			 * null) { return false; } Element rootElement = (Element)
			 * document.getRootElement(); Element thisWord = (Element)
			 * rootElement.selectSingleNode("fieldType[@name='" + fieldType +
			 * "']"); if (thisWord == null) { return false; } if (isExtend == 1)
			 * { Element paramElement = (Element)
			 * thisWord.selectSingleNode("analyzer[@type='index']"); Element
			 * tokenElement = (Element) paramElement .selectSingleNode(
			 * "tokenizer[@class='org.ansj.splitWord.analysis.AnsjTokenizerFactory']"
			 * ); tokenElement.setAttributeValue("extDic", "library/ext.dic"); }
			 * Element paramElement = (Element)
			 * thisWord.selectSingleNode("analyzer[@type='query']"); if
			 * (isStopword == 1) { Element tokenElement = (Element) paramElement
			 * .selectSingleNode("filter[@class='solr.StopFilterFactory']"); if
			 * (tokenElement != null) { tokenElement.setAttributeValue("class",
			 * "solr.StopFilterFactory");
			 * tokenElement.setAttributeValue("ignoreCase", "true");
			 * tokenElement.setAttributeValue("words", "stopwords.txt"); } else
			 * { Element filter = paramElement.addElement("filter");
			 * filter.addAttribute("class", "solr.StopFilterFactory");
			 * filter.addAttribute("ignoreCase", "true");
			 * filter.addAttribute("words", "stopwords.txt"); } } if (isSynonyms
			 * == 1) { Element tokenElement = (Element) paramElement
			 * .selectSingleNode("filter[@class='solr.SynonymFilterFactory']");
			 * if (tokenElement != null) {
			 * tokenElement.setAttributeValue("class",
			 * "solr.SynonymFilterFactory");
			 * tokenElement.setAttributeValue("ignoreCase", "true");
			 * tokenElement.setAttributeValue("synonyms", "synonyms.txt"); }
			 * else { Element filter = paramElement.addElement("filter");
			 * filter.addAttribute("class", "solr.SynonymFilterFactory");
			 * filter.addAttribute("synonyms", "synonyms.txt"); }
			 * 
			 * } // 重写xml XmlTool.writeXML(document, solrconfigXmlFile,
			 * "utf-8"); } catch (Exception e) { flag = false;
			 * LogUtil.loggerEntrance(ruleBeanWithBLOBs.getUserId(),
			 * ruleBeanWithBLOBs.getAppId(), "", LogUtil.SEARCH_LOG, e); }
			 * break; }
			 
			
			 * // 更新智能联想规则 case "3": { String field =
			 * ruleBeanWithBLOBs.getIncludeFields(); String ruleString =
			 * Config.INTEL_ASSOCIATE_PREX + ruleBeanWithBLOBs.getId();
			 * 
			 * if (field == null || ruleString == null) { return false; }
			 * 
			 * String[] fields = field.split(";"); if (fields == null) { return
			 * false; } String qfString = ""; try { document =
			 * XmlTool.readXML(solrconfigXmlFile, "utf-8"); if (document ==
			 * null) { return false; } for (int i = 0; i < fields.length; i++) {
			 * qfString = qfString + " " + fields[i]; } Element rootElement =
			 * (Element) document.getRootElement(); Element thisWord = (Element)
			 * rootElement .selectSingleNode("requestHandler[@name='/" +
			 * ruleString + "']"); Element paramElement = (Element)
			 * thisWord.selectSingleNode("lst[@name='defaults']"); Element
			 * qfElement = (Element)
			 * paramElement.selectSingleNode("str[@name='qf']");
			 * qfElement.setText(qfString); XmlTool.writeXML(document,
			 * solrconfigXmlFile, "utf-8"); } catch (Exception e) { flag =
			 * false; LogUtil.loggerEntrance(ruleBeanWithBLOBs.getUserId(),
			 * ruleBeanWithBLOBs.getAppId(), "", LogUtil.SEARCH_LOG, e); }
			 * break; }
			 
			// 更新按域检索规则
		case "4": {
			String field = ruleBeanWithBLOBs.getIncludeFields();
			String ruleString = Config.INDEX_FIELD_PREX + ruleBeanWithBLOBs.getRuleName();
			if (field == null || ruleString == null) {
				return false;
			}

			String[] fields = field.split(";");
			if (fields == null) {
				return false;
			}
			try {
				ZookeeperUtil.getFile(appId, ZkConfigFileType.SOLRCONFIG);
				document = XmlTool.readXML(Config.ZKCONFIG_LOCALDIR + "/" + appId + "/solrconfig.xml", "utf-8");
				if (document == null) {
					return false;
				}
				String qfString = "";
				for (int i = 0; i < fields.length; i++) {
					qfString = qfString + " " + fields[i];
				}
				Element rootElement = (Element) document.getRootElement();
				Element thisWord = (Element) rootElement
						.selectSingleNode("requestHandler[@name='/" + ruleString + "']");
				Element paramElement = (Element) thisWord.selectSingleNode("lst[@name='defaults']");
				Element qfElement = (Element) paramElement.selectSingleNode("str[@name='qf']");
				qfElement.setText(qfString);
				XmlTool.writeXML(document, Config.ZKCONFIG_LOCALDIR + "/" + appId + "/solrconfig.xml", "utf-8");
				ZookeeperUtil.putFile(appId, ZkConfigFileType.SOLRCONFIG);
			} catch (Exception e) {
				flag = false;
				LogUtil.loggerEntrance(ruleBeanWithBLOBs.getUserId(), ruleBeanWithBLOBs.getAppId(),
						ExceptionConstants.ConfigXMLUpdateError, LogUtil.SEARCH_LOG, e);
			}
			break;
		}
		default:
			return true;
		}
		if (flag) {
			flag = reload(ruleBeanWithBLOBs.getUserId(), appId);
		}
		return flag;
	}

	
	 * (non Javadoc)
	 * 
	 * @Title: delRule
	 * 
	 * @Description: 删除配置文件中存在的规则
	 * 
	 * @param ruleBeanWithBLOBs
	 * 
	 * @return boolean
	 * 
	 * @throws ConfigXMLUpdateFailureException
	 * 
	 * @see com.bcse.search.config.RuleConfig#delRule(com.bcse.web.bean.rule.
	 * RuleBeanWithBLOBs)
	 
	@Override
	public boolean delRule(RuleBeanWithBLOBs ruleBeanWithBLOBs) throws ConfigXMLUpdateFailureException {
		// TODO Auto-generated method stub
		if (ruleBeanWithBLOBs == null)
			return false;

		String appId = ruleBeanWithBLOBs.getAppId();
		if (appId == null || appId.trim().length() == 0)
			return false;

		String ruleType = ruleBeanWithBLOBs.getRuleType();
		boolean flag = true;

		Document document = null;

		switch (ruleType) {
		// 删除粗排规则
		case "0": {
			String ruleId = Config.ROUGH_SORT_PREX + ruleBeanWithBLOBs.getId();
			if (ruleId == null) {
				return false;
			}

			try {
				ZookeeperUtil.getFile(appId, ZkConfigFileType.SOLRCONFIG);
				document = XmlTool.readXML(Config.ZKCONFIG_LOCALDIR + "/" + appId + "/solrconfig.xml", "utf-8");
				if (document == null) {
					return false;
				}
				Element rootElement = (Element) document.getRootElement();
				Element thisWord = (Element) rootElement.selectSingleNode("requestHandler[@name='/" + ruleId + "']");
				if (thisWord == null) {
					return false;
				}
				rootElement.remove(thisWord);
				XmlTool.writeXML(document, Config.ZKCONFIG_LOCALDIR + "/" + appId + "/solrconfig.xml", "utf-8");
				ZookeeperUtil.putFile(appId, ZkConfigFileType.SOLRCONFIG);
			} catch (Exception e) {
				flag = false;
				LogUtil.loggerEntrance(ruleBeanWithBLOBs.getUserId(), ruleBeanWithBLOBs.getAppId(),
						ExceptionConstants.ConfigXMLUpdateError, LogUtil.SEARCH_LOG, e);
			}
			break;
		}
		case "2": {
			int isExtend = ruleBeanWithBLOBs.getIsExtend();
			int isStopword = ruleBeanWithBLOBs.getIsStopword();
			int isSynonyms = ruleBeanWithBLOBs.getIsSynonyms();
			String ruleId = Config.INTEL_ASSOCIATE_PREX + ruleBeanWithBLOBs.getId();

			if (ruleId == null) {
				return false;
			}
			// solrconfigXmlFile = Config.CORE_CONFIG_DIR + appId +
			// File.separator + "schema.xml";

			String fieldType = Config.FIELD_TYPE;
			try {
				ZookeeperUtil.getFile(appId, ZkConfigFileType.SCHEMA);
				document = XmlTool.readXML(Config.ZKCONFIG_LOCALDIR + "/" + appId + "/schema.xml", "utf-8");
				if (document == null) {
					return false;
				}
				Element rootElement = (Element) document.getRootElement();
				Element thisWord = (Element) rootElement.selectSingleNode("fieldType[@name='" + fieldType + "']");
				if (thisWord == null) {
					return false;
				}
				if (isExtend == 1) {
					Element paramElement = (Element) thisWord.selectSingleNode("analyzer[@type='index']");
					Element tokenElement = (Element) paramElement
							.selectSingleNode("tokenizer[@class='org.ansj.splitWord.analysis.AnsjTokenizerFactory']");
					tokenElement.setAttributeValue("extDic", "");

				}
				Element paramElement = (Element) thisWord.selectSingleNode("analyzer[@type='query']");
				if (isStopword == 1) {
					Element tokenElement = (Element) paramElement
							.selectSingleNode("filter[@class='solr.StopFilterFactory']");
					paramElement.remove(tokenElement);
				}
				if (isSynonyms == 1) {
					Element tokenElement = (Element) paramElement
							.selectSingleNode("filter[@class='solr.SynonymFilterFactory']");
					paramElement.remove(tokenElement);

				}
				// 重写xml
				XmlTool.writeXML(document, Config.ZKCONFIG_LOCALDIR + "/" + appId + "/schema.xml", "utf-8");
				ZookeeperUtil.putFile(appId, ZkConfigFileType.SCHEMA);
			} catch (Exception e) {
				flag = false;
				LogUtil.loggerEntrance(ruleBeanWithBLOBs.getUserId(), ruleBeanWithBLOBs.getAppId(),
						ExceptionConstants.ConfigXMLUpdateError, LogUtil.SEARCH_LOG, e);
			}
			break;
		}
		case "3": {
			String field = ruleBeanWithBLOBs.getIncludeFields();
			String ruleString = Config.INTEL_ASSOCIATE_PREX + ruleBeanWithBLOBs.getId();

			if (field == null || ruleString == null) {
				return false;
			}

			try {
				// Config.ZKCONFIG_LOCALDIR+"/"+appId+"/schema.xml"
				ZookeeperUtil.getFile(appId, ZkConfigFileType.SOLRCONFIG);
				document = XmlTool.readXML(Config.ZKCONFIG_LOCALDIR + "/" + appId + "/solrconfig.xml", "utf-8");
				if (document == null) {
					return false;
				}
				Element rootElement = (Element) document.getRootElement();
				Element thisWord = (Element) rootElement
						.selectSingleNode("requestHandler[@name='/" + ruleString + "']");
				if (thisWord == null) {
					return false;
				}
				rootElement.remove(thisWord);
				// 重写xml
				XmlTool.writeXML(document, Config.ZKCONFIG_LOCALDIR + "/" + appId + "/solrconfig.xml", "utf-8");
				ZookeeperUtil.putFile(appId, ZkConfigFileType.SOLRCONFIG);
			} catch (Exception e) {
				flag = false;
				LogUtil.loggerEntrance(ruleBeanWithBLOBs.getUserId(), ruleBeanWithBLOBs.getAppId(),
						ExceptionConstants.ConfigXMLUpdateError, LogUtil.SEARCH_LOG, e);
			}
			break;
		}

		case "4": {
			String field = ruleBeanWithBLOBs.getIncludeFields();
			String ruleString = Config.INDEX_FIELD_PREX + ruleBeanWithBLOBs.getRuleName();
			if (field == null || ruleString == null) {
				return false;
			}

			String[] fields = field.split(";");
			if (fields == null) {
				return false;
			}
			try {
				ZookeeperUtil.getFile(appId, ZkConfigFileType.SOLRCONFIG);
				document = XmlTool.readXML(Config.ZKCONFIG_LOCALDIR + "/" + appId + "/solrconfig.xml", "utf-8");
				if (document == null) {
					return false;
				}
				Element rootElement = (Element) document.getRootElement();
				Element thisWord = (Element) rootElement
						.selectSingleNode("requestHandler[@name='/" + ruleString + "']");
				if (thisWord == null) {
					return false;
				}
				rootElement.remove(thisWord);
				// 重写xml
				XmlTool.writeXML(document, Config.ZKCONFIG_LOCALDIR + "/" + appId + "/solrconfig.xml", "utf-8");
				ZookeeperUtil.putFile(appId, ZkConfigFileType.SOLRCONFIG);
			} catch (Exception e) {
				LogUtil.loggerEntrance(ruleBeanWithBLOBs.getUserId(), ruleBeanWithBLOBs.getAppId(),
						ExceptionConstants.ConfigXMLUpdateError, LogUtil.SEARCH_LOG, e);
				flag = false;
			}
			break;
		}

		default:
			return true;
		}
		if (flag) {
			flag = reload(ruleBeanWithBLOBs.getUserId(), appId);
		}
		return flag;
	}

	
	 * (non Javadoc)
	 * 
	 * @Title: delRule
	 * 
	 * @Description: 搜索推广批量删除
	 * 
	 * @param ruleBeanWithBLOBs
	 * 
	 * @return boolean
	 * 
	 * @throws ConfigXMLUpdateFailureException
	 * 
	 * @see com.bcse.search.config.RuleConfig#delRule(java.util.List)
	 
	@Override
	public boolean delRule(List<RuleBeanWithBLOBs> ruleBeanWithBLOBs) throws ConfigXMLUpdateFailureException {
		if (ruleBeanWithBLOBs == null || ruleBeanWithBLOBs.size() == 0)
			return false;

		String appId = ruleBeanWithBLOBs.get(0).getAppId();
		String userId = ruleBeanWithBLOBs.get(0).getUserId();
		boolean flag = true;

		try {
			// Config.ZKCONFIG_LOCALDIR+"/"+appId+"/solrconfig.xml"
			ZookeeperUtil.getFile(appId, ZkConfigFileType.ELEVATE);
			Document document = XmlTool.readXML(Config.ZKCONFIG_LOCALDIR + "/" + appId + "/elevate.xml", "utf-8");
			if (document == null) {
				return false;
			}
			
			Element rootElement = (Element) document.getRootElement();
			for (RuleBeanWithBLOBs ruleBean : ruleBeanWithBLOBs) {
				String keyWords = ruleBean.getIncludeKeywords();
				if(keyWords == null || keyWords.trim().length() == 0)
					return false;
				
				String[] includeKeyWords = keyWords.split(";");
				if (includeKeyWords != null && includeKeyWords.length > 0) {
					for (String includeKeyWord : includeKeyWords) {
						// 找是否有该关键字的节点
						Element thisWord = (Element) rootElement
								.selectSingleNode("query[@text='" + ClientUtils.escapeQueryChars(includeKeyWord) + "']");
						if (null == thisWord)
							return false;
						thisWord.getParent().remove(thisWord);
					}
				}

			}
			XmlTool.writeXML(document, Config.ZKCONFIG_LOCALDIR + "/" + appId + "/elevate.xml", "utf-8");
			ZookeeperUtil.putFile(appId, ZkConfigFileType.ELEVATE);
		} catch (Exception e) {
			flag = false;
			LogUtil.loggerEntrance(userId, appId, ExceptionConstants.ConfigXMLUpdateError, LogUtil.SEARCH_LOG, e);
		}
		if (flag) {
			flag = reload(ruleBeanWithBLOBs.get(0).getUserId(), appId);
		}
		return flag;
	}

	
	 * (non Javadoc)
	 * 
	 * @Title: addRule
	 * 
	 * @Description: 搜索推广批量添加，当搜索推广批量上线时调用该接口
	 * 
	 * @param ruleBeanWithBLOBs
	 * 
	 * @return boolean
	 * 
	 * @throws ConfigXMLUpdateFailureException
	 * 
	 * @see com.bcse.search.config.RuleConfig#addRule(java.util.List)
	 
	@Override
	public boolean addRule(List<RuleBeanWithBLOBs> ruleBeanWithBLOBs) throws ConfigXMLUpdateFailureException {
		// 添加搜索推广规则
		if (ruleBeanWithBLOBs == null || ruleBeanWithBLOBs.size() == 0)
			return false;

		// 读取配置文件，为空则返回false
		String appId = ruleBeanWithBLOBs.get(0).getAppId();
		String userId = ruleBeanWithBLOBs.get(0).getUserId();
		// 修改配置文件
		boolean flag = true;
		try {
			ZookeeperUtil.getFile(appId, ZkConfigFileType.ELEVATE);
			Document document = XmlTool.readXML(Config.ZKCONFIG_LOCALDIR + "/" + appId + "/elevate.xml", "utf-8");

			if (document == null)
				return false;
			
			for (RuleBeanWithBLOBs ruleBean : ruleBeanWithBLOBs) {
				// 验证前端传递的是否是合法的文档id
				//validateSpreadIds(ruleBean);
				String[] docIds = null;
				String[] includeKeyWords = null;
				if (null != ruleBean.getIncludeKeywords()) {
					includeKeyWords = ruleBean.getIncludeKeywords().split(";");
				} else
					return false;

				if (null != ruleBean.getSpreadIds()) {
					docIds = ruleBean.getSpreadIds().split(";");
				} else {
					return false;
				}
				// 加入新节点
				Element rootElement = (Element) document.getRootElement();
				for (String includeKeyword : includeKeyWords) {
					Element queryElement = rootElement.addElement("query");
					queryElement.addAttribute("text", ClientUtils.escapeQueryChars(includeKeyword));
					for (int i = 0; i < docIds.length; ++i) {
						Element docElement = queryElement.addElement("doc");
						docElement.addAttribute("id", docIds[i]);
					}

				}
			}
			XmlTool.writeXML(document, Config.ZKCONFIG_LOCALDIR + "/" + appId + "/elevate.xml", "utf-8");
			ZookeeperUtil.putFile(appId, ZkConfigFileType.ELEVATE);
		} catch (Exception e) {
			flag = false;
			LogUtil.loggerEntrance(userId, appId, ExceptionConstants.ConfigXMLUpdateError, LogUtil.SEARCH_LOG, e);
		}
		if (flag) {
			flag = reload(ruleBeanWithBLOBs.get(0).getUserId(), appId);
		}
		return flag;
	}

	*//**
	 * @Title: addLastComponents
	 * @Description: solrconfig.xml配置文件中RequestHandler节点下增加last-component节点
	 * @param queryElement
	 * @return: void
	 *//*
	private void addLastComponents(Element queryElement) {
		Element arr = queryElement.addElement("arr");
		arr.addAttribute("name", "last-components");
		Element arrStr = arr.addElement("str");
		arrStr.setText("elevator");
	}

	*//**
	 * @Title: reload
	 * @Description: 更新指定用户下某个应用的配置文件后reload操作
	 * @param userId
	 * @param appId
	 * @return: boolean
	 *//*
	private boolean reload(String userId, String appId) {
		try {
			ReloadSolrCloud.reload(Config.ZK_HOST, appId);
		} catch (Exception e) {
			LogUtil.loggerEntrance(userId, appId, ExceptionConstants.ConfigXMLUpdateError, LogUtil.SEARCH_LOG, e);
			return false;
		}
		return true;
	}
*/}
