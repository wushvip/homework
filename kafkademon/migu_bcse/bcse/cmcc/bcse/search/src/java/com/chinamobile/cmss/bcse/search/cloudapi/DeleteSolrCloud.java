package com.chinamobile.cmss.bcse.search.cloudapi;
/*package com.bcse.search.cloudapi;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;


*//**
 *
 * @author chenmin
 * @date 2015-6-14
 *
 *       TODO
 *
 *//*
public class DeleteSolrCloud {
	*//**
	 * 
	 * 
	 * @param filePath
	 *            String
	 * @param keyid
	 *            String
	 * @return Boolean
	 * 
	 * *//*
	public boolean deleteFromCsvfile(String filePath, int keyid) {
		if(null==filePath||filePath.equals("")){
			return false;
		}
		ArrayList<String[]> list = CsvFileTool.readCsv(filePath, true);
		ArrayList<String> docs = new ArrayList<String>();// 
	    String regex="[0-9]+?";
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(keyid+"");
		if(!m.find()||keyid>list.size()||keyid<0){
			return false;
		}
		for (String[] element : list) {
			if (null != element[keyid]) {
				docs.add(element[keyid]);
			}
		}
		CloudSolrClient cloudSolrServer = ConnectSolrCloud.getCloudSolrServer();
		Boolean resultflag = false;
		try {
			UpdateResponse response1 = cloudSolrServer.deleteById(docs);
			if (response1.getStatus() == 0) {
				UpdateResponse response2 = cloudSolrServer.commit();
				if (response2.getStatus() == 0) {
					resultflag = true;
				}
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultflag;
	}

	public static void main(String[] args) {
		CloudSolrClient cloudSolrServer = ConnectSolrCloud.getCloudSolrServer();
		try {
			cloudSolrServer.deleteByQuery("*:*");
			cloudSolrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
*/