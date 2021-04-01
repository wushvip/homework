package collection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;



public class TimeTool {
	
	protected static final Logger LOG = Logger.getLogger(TimeTool.class);
	

	/**
	 * 日期比较
	 * 
	 * @param date1
	 * @param date2
	 * @param fmt
	 * @return date1小于date2返回<0�?
	 *         date1等于date2返会=0�?
	 *         date1大于date2返会>0
	 */
	public static int compare(String date1, String date2, String fmt) {
		int result = -1;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(fmt);
			Calendar c1 = Calendar.getInstance();
			c1.setTime(sdf.parse(date1));
			Calendar c2 = Calendar.getInstance();
			c2.setTime(sdf.parse(date2));
			result = c1.compareTo(c2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}


	/**
	 * 格式化日�?
	 * 
	 * @param fmt
	 * @param date
	 * @return
	 */
	public static String fmtDate(String fmt, Long millis) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millis);
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		return sdf.format(c.getTime());
	}

	public static void main(String[] args) {
		
//		System.out.println(TimeTool.dateToStamp("2018-1-31 00:00:00") - System.currentTimeMillis());
		
		System.out.println(compare("2018-10-10 12:00:01","2018-12-09 10:00:00","yyyy-MM-dd HH:mm:ss"));
		
	}
}
