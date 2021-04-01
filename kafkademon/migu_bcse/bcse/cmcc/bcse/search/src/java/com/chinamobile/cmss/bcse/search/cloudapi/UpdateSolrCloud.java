package com.chinamobile.cmss.bcse.search.cloudapi;
/*package com.bcse.search.cloudapi;

import java.io.File;
import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.client.solrj.response.UpdateResponse;



*//***
 *
 * @author chenmin
 * @date 2015-6-14
 *       
 *       TODO
 *//*
public class UpdateSolrCloud {
	*//**
	 * 更新数据
	 * 
	 * @param filePath
	 * @return boolean
	 * 
	 *//*
	public static boolean updateFromCsvByFile(String filePath) {
		if(null==filePath||filePath.equals("")){
			return false;
		}
		CloudSolrClient cloudSolrServer = ConnectSolrCloud.getCloudSolrServer();
		boolean flag = false;
		ContentStreamUpdateRequest up = new ContentStreamUpdateRequest(
				"/update/csv");
		String contentType = "text/plain; charset=gb18030";
		try {
			up.addFile(new File(filePath), contentType);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			UpdateResponse response = up.process(cloudSolrServer);
			if (response.getStatus() == 0) {
				UpdateResponse response1 = cloudSolrServer.commit();
				if (response1.getStatus() == 0) {
					flag = true;
				}
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		cloudSolrServer.shutdown();
		return flag;
	}
	
	
	

	public static void main(String[] args) {
		System.out.print("start:"+System.currentTimeMillis());
		updateFromCsvByFile("test.csv");
		System.out.print("end"+System.currentTimeMillis());
		
	}

}
*/