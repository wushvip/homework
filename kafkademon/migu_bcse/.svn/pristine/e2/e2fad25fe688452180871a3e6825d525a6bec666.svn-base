package com.chinamobile.cmss.bcselogAnalyse.statistic;

import com.chinamobile.cmss.bcselogAnalyse.tools.MysqlConn;

/**
 * @ClassName: CostStatistic
 * @Description: 搜索耗时统计
 * @author: yangjing
 * @date: 2016年6月13日 上午11:09:06
 */
public class CostStatistic {

	public static void process() {
		MysqlConn mysqlConn = new MysqlConn();
		// 往搜索耗时日表插入前一天的数据，插入之前先清空前一天的数据
		String delete = "delete from SEARCH_COST_DAY where OPER_DATE=date_sub(curdate(),interval 1 day); ";
		mysqlConn.deleteSQL(delete);
		String insert = "insert into SEARCH_COST_DAY(USER_ID,APP_ID,OPER_DATE,MAX_COST,MIN_COST,AVG_COST,FLAG) "
				+ "SELECT "
				+ "USER_ID,APP_ID,date_sub(curdate(),interval 1 day) as OPER_DATE,MAX(MAX_COST),MIN(MIN_COST),AVG(AVG_COST),FLAG "
				+ "from SEARCH_COST_HOUR " + "where  date(OPER_DATE) = date_sub(curdate(),interval 1 day) "
				+ "GROUP BY " + "USER_ID,APP_ID,OPER_DATE,FLAG " + "order by USER_ID,APP_ID,OPER_DATE,FLAG ";
		mysqlConn.insertSQL(insert);

		mysqlConn.release();
	}

	public static void main(String[] args) {
		String insert = "insert into SEARCH_COST_DAY(USER_ID,APP_ID,OPER_DATE,MAX_COST,MIN_COST,AVG_COST,FLAG) "
				+ "SELECT "
				+ "USER_ID,APP_ID,date_sub(curdate(),interval 1 day) as OPER_DATE,MAX(MAX_COST),MIN(MIN_COST),AVG(AVG_COST),FLAG "
				+ "from SEARCH_COST_HOUR " + "where  date(OPER_DATE) = date_sub(curdate(),interval 1 day) "
				+ "GROUP BY " + "USER_ID,APP_ID,OPER_DATE,FLAG " + "order by USER_ID,APP_ID,OPER_DATE,FLAG ";
		process();
	}

}
