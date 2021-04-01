package com.chinamobile.cmss.bcse.search.tool;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.cloud.ZkCLI;

import com.chinamobile.cmss.bcse.search.bean.ZkConfigFileType;
import com.chinamobile.cmss.bcse.search.cloudapi.ReloadSolrCloud;
import com.chinamobile.cmss.bcse.tool.config.Config;

public class ZookeeperUtil {
	
	
	public static final String SOLRCONFIG_FILE = "solrconfig.xml";
	public static final String SCHEMA_FILE = "schema.xml";
	public static final String ELEVATE_FILE = "elevate.xml";
	public static final String STOPWORDS = "stopwords.txt";
	public static final String SYNONYMS = "synonyms.txt";
	public static final String EXTDIC = "library/ext.dic";
	public static final String AMBIGUITY = "library/ambiguity.dic";

	public static boolean getFile(String collectionName, ZkConfigFileType zkConfigFileType) {
		if (collectionName == null || collectionName.trim().length() == 0)
			return false;
		String fileName = null;

		// 根据文档类型获取文档名称
		switch (zkConfigFileType) {
		case SOLRCONFIG:
			fileName = SOLRCONFIG_FILE;
			break;
		case SCHEMA:
			fileName = SCHEMA_FILE;
			break;
		case ELEVATE:
			fileName = ELEVATE_FILE;
			break;
		case STOPWORDS:
			fileName = STOPWORDS;
			break;
		case SYNONYMS:
			fileName = SYNONYMS;
			break;
		case EXTDIC:
			fileName = EXTDIC;
			break;
		case AMBIGUITY:
			fileName = AMBIGUITY;
			break;	
		default:
			break;
		}
		if (fileName == null)
			return false;
		String[] tempArgs = { "-zkhost", Config.ZK_HOST, "-cmd", "getFile",
				"/configs/" + collectionName + "/" + fileName,
				Config.ZKCONFIG_LOCALDIR + "/" + collectionName + "/" + fileName };
		try {
			ZkCLI.main(tempArgs);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean putFile(String collectionName, ZkConfigFileType zkConfigFileType) {
		if (collectionName == null || collectionName.trim().length() == 0)
			return false;
		String fileName = null;

		// 根据文档类型获取文档名称
		switch (zkConfigFileType) {
		case SOLRCONFIG:
			fileName = SOLRCONFIG_FILE;
			break;
		case SCHEMA:
			fileName = SCHEMA_FILE;
			break;
		case ELEVATE:
			fileName = ELEVATE_FILE;
			break;
		case STOPWORDS:
			fileName = STOPWORDS;
			break;
		case SYNONYMS:
			fileName = SYNONYMS;
			break;
		case EXTDIC:
			fileName = EXTDIC;
			break;
		case AMBIGUITY:
			fileName = AMBIGUITY;
			break;		
		default:
			break;
		}
		if (fileName == null)
			return false;
		String[] tempArgs = { "-zkhost", Config.ZK_HOST, "-cmd", "putfile",
				"/configs/" + collectionName + "/" + fileName,
				Config.ZKCONFIG_LOCALDIR + "/" + collectionName + "/" + fileName };
		try {
			ZkCLI.main(tempArgs);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean putFile(String collectionName, String filePath,ZkConfigFileType zkConfigFileType) {
		if (collectionName == null || collectionName.trim().length() == 0)
			return false;
		String fileName = null;

		// 根据文档类型获取文档名称
		switch (zkConfigFileType) {
		case SOLRCONFIG:
			fileName = SOLRCONFIG_FILE;
			break;
		case SCHEMA:
			fileName = SCHEMA_FILE;
			break;
		case ELEVATE:
			fileName = ELEVATE_FILE;
			break;
		case STOPWORDS:
			fileName = STOPWORDS;
			break;
		case SYNONYMS:
			fileName = SYNONYMS;
			break;
		case EXTDIC:
			fileName = EXTDIC;
			break;
		case AMBIGUITY:
			fileName = AMBIGUITY;
			break;		
		default:
			break;
		}
		if (fileName == null)
			return false;
		String[] tempArgs = { "-zkhost", Config.ZK_HOST, "-cmd", "putfile",
				"/configs/" + collectionName + "/" + fileName,
				filePath};
		try {
			ZkCLI.main(tempArgs);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static void main(String args[]) {
		//ZookeeperUtil.getFile("4717b323ea434105add968ce2e027b4f", ZkConfigFileType.SCHEMA);
		//ZookeeperUtil.putFile("4717b323ea434105add968ce2e027b4f", ZkConfigFileType.SCHEMA);
		try {
			ReloadSolrCloud.reload(Config.ZK_HOST, "4717b323ea434105add968ce2e027b4f");
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*try {
			ReloadSolrCloud.reload(Config.ZK_HOST, "384e9e937473493987c1bcb1a684d642");
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
