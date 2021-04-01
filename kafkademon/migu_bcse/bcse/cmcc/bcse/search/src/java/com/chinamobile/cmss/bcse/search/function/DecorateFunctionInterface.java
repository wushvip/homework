package com.chinamobile.cmss.bcse.search.function;

import org.apache.solr.client.solrj.response.QueryResponse;

public interface DecorateFunctionInterface {

	public void resultListDecorate();

	public void facetDecorate();

	public void hightLightDecorate();

	public void groupStaticDecorate();

	public void process(QueryResponse response);

}


