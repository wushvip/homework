package com.chinamobile.cmss.bcse.search.cloudapi;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.request.CollectionAdminRequest;
import org.apache.solr.client.solrj.response.CollectionAdminResponse;

import com.chinamobile.cmss.bcse.tool.config.Config;

/**
 *
 * @author chenmin
 * @date   2015�?1�?2�?
 *
 * TODO
 *    重新加载�?
 */
public class ReloadSolrCloud {
         /**
          * 重新加载�?
          * @param zkHost
          * @param collectionName
          * 
          * */
	     public static void reload(String zkHost,String collectonName) throws SolrServerException, IOException{
	    	     CollectionAdminRequest.Reload reload = new CollectionAdminRequest.Reload();
	    	     CloudSolrClient client = ConnectSolrCloud.getCloudSolrServers(zkHost, collectonName);
	    	     reload.setCollectionName(collectonName);
	    	     reload.process(client);
	     }
	     
	     public static void main(String[] args) throws SolrServerException, IOException{
	    	 reload(
	 					Config.ZK_HOST, "1021958386@qq.com_grey");
	    	 //collectionAdminRequest.getResponse().get("grey");
	     }
}
