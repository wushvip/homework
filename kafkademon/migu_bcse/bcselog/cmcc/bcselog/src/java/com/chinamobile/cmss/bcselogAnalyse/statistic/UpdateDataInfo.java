package com.chinamobile.cmss.bcselogAnalyse.statistic;

import com.chinamobile.cmss.bcselogAnalyse.tools.MysqlConn;


/** 
 * @ClassName: UpdateDataInfo 
 * @Description: 更新数据量表
 * @author: yangjing
 * @date: 2016年6月13日 上午11:03:56  
 */
public class UpdateDataInfo {
	
	/** 
	 * @Title: process 
	 * @Description: 取最新一次记录的数据量为此时的数据量
	 * @return: void
	 */
	public static void process(){
		MysqlConn mysqlConn = new MysqlConn();
		String insert = "insert into DATA_INFO(USER_ID,APP_ID,DATE_TIME,SIZE,FLAG) "
				+ "select USER_ID,APP_ID,date_format(NOW(),'%Y-%m-%d %T'),SIZE,FLAG "
				+ "from (select * from DATA_INFO order by DATE_TIME desc) "
				+ "as tmp "
				+ "GROUP BY "
				+ "USER_ID,APP_ID,FLAG "
				+ "order by DATE_TIME ";
		mysqlConn.insertSQL(insert);
		mysqlConn.release();	
	}

	
	public static void main(String[] args){
		process();
	
	}

}
