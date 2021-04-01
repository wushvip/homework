package com.chinamobile.cmss.bcse.search.sort;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.solr.common.SolrDocument;

/**
 *
 * @author chenmin
 * @date 2015年11月30日
 *
 *       TODO
 *
 */
public class SortFactor implements Comparable<SortFactor> {


	public static void main(String[] args) {
		/*
		 * String[] sortArrayStrings={"down:des"}; SolrDocumentList
		 * tempFilterDocumentList = new SolrDocumentList(); SolrDocument
		 * solrDocument1=new SolrDocument(); SolrDocument solrDocument2=new
		 * SolrDocument(); SolrDocument solrDocument3=new SolrDocument();
		 * solrDocument1.setField("down", 40); solrDocument2.setField("down",
		 * 20); solrDocument3.setField("down", 30); solrDocument1.setField("id",
		 * 1); solrDocument2.setField("id", 2); solrDocument3.setField("id", 3);
		 * tempFilterDocumentList.add(solrDocument1);
		 * tempFilterDocumentList.add(solrDocument2);
		 * tempFilterDocumentList.add(solrDocument3); ArrayList<SortFactor>
		 * list1 = new ArrayList<SortFactor>();
		 * 
		 * 
		 * for (int i = 0; i < tempFilterDocumentList.size(); i++) { SortFactor
		 * sortFactor = new SortFactor(i, tempFilterDocumentList.get(i),
		 * sortArrayStrings); list1.add(sortFactor); } for(SortFactor
		 * sortFactor:list1){ System.out.println(sortFactor.id); }
		 * java.util.Collections.sort(list1); for(SortFactor sortFactor:list1){
		 * System.out.println(sortFactor.id); }
		 */
		String x = "Mon Mar 02 13:57:49 CST 2015";
		SimpleDateFormat sdf1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
		try {
			Date date = sdf1.parse(x);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String sDate = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 排序因子排序
	 * 
	 */
	public int id;
	public SolrDocument solrDocument;
	// 最多两重排序
	public String[] sortArray;

	/**
	 * 
	 */
	public SortFactor(int id, SolrDocument solrDocument, String[] sortArray) {
		this.id = id;
		this.solrDocument = solrDocument;
		this.sortArray = sortArray;
	}

	public int compareTo(SortFactor o) {
		String[] keyValue = sortArray[0].split(":");

		double flag = 0;
		String a = "";
		String b = "";
		try {
			if(solrDocument.get(keyValue[0]) == null){
				a = String.valueOf(-Double.MAX_VALUE);
			}else{
				a = solrDocument.get(keyValue[0].trim()).toString();
			}
			
		} catch (Exception e) {
				return -1;
		}
		try {
			if(o.solrDocument.get(keyValue[0]) == null){
				b = String.valueOf(-Double.MAX_VALUE);
			}else{
				b = o.solrDocument.get(keyValue[0].trim()).toString();
			}
		} catch (Exception e) {
				return -1;
		}
		flag = parseString(a) - parseString(b);
		if (keyValue[1].equals("desc")) {
			if (flag < 0)
				return 1;
			else if (flag > 0)
				return -1;
		} else {
			if (flag < 0)
				return -1;
			else if (flag > 0)
				return 1;
		}
		if (flag == 0.0) {
			if (sortArray.length == 2) {
				String[] keyValue1 = sortArray[1].split(":");
				String a1 = solrDocument.get(keyValue1[0]).toString();
				String b1 = o.solrDocument.get(keyValue1[0]).toString();
				double flag1 = parseString(a1) - parseString(b1);
				if (keyValue1[1].equals("desc")) {
					if (flag1 < 0)
						return 1;
					else if (flag1 > 0)
						return -1;
					else
						return 0;
				} else {
					if (flag1 < 0)
						return -1;
					else if (flag1 > 0)
						return 1;
					else
						return 0;
				}
			}
		}
		return 0;

	}

	// SOLR返回的结果中时间类型需要转换
	private double parseString(String value) {
		double result = 0.0;
		if (value.contains("CST")) {
			SimpleDateFormat sdf1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
			try {
				Date date = sdf1.parse(value);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String sDate = sdf.format(date);
				result = Double.parseDouble(sDate);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			result = Double.parseDouble(value);
		}
		return result;
	}
}
