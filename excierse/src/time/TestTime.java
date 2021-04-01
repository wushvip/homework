/**
 * 
 */
package time;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author wushuai
 * @date 2018��8��31��
 * @Description	TODO
 */
public class TestTime {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long begin = System.currentTimeMillis();
		Date date = new Date(begin);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE,-5);
		System.out.println(sdf.format(calendar.getTime()));

		System.out.println(sdf.format(System.currentTimeMillis()));
		System.out.println(sdf.format(new Date(System.currentTimeMillis())));
		System.out.println(sdf.format(date));

	}

}
