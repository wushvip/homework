package com.chinamobile.cmss.bcselogAnalyse.statistic;

import com.chinamobile.cmss.bcselogAnalyse.tools.MysqlConn;

/**
 * @ClassName: CountStatistic
 * @Description: 搜索次数统计
 * @author: yangjing
 * @date: 2016年6月13日 上午11:09:32
 */
public class CountStatistic {

	public static void process() {
		MysqlConn mysqlConn = new MysqlConn();
		
		 
		// 往搜索次数日表插入前一天的数据，插入之前先清空前一天的数据
		String delete = "delete from SEARCH_COUNT_DAY where OPER_DATE=date_sub(curdate(),interval 1 day); ";
		mysqlConn.deleteSQL(delete);
		String insert = "insert into SEARCH_COUNT_DAY(USER_ID,APP_ID,OPER_DATE,SEARCH_COUNT,FLAG) " + "SELECT "
				+ "USER_ID,APP_ID,OPER_DATE,SUM(SEARCH_COUNT),FLAG " + "from SEARCH_COUNT_HOUR "
				+ "where  OPER_DATE = date_sub(curdate(),interval 1 day) " + "GROUP BY "
				+ "USER_ID,APP_ID,OPER_DATE,FLAG " + "order by USER_ID,APP_ID,OPER_DATE; ";
		mysqlConn.insertSQL(insert);

		mysqlConn.release();

	}

	public static void main(String[] args) {
		process();

	}

}
