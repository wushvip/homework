/**
 * 
 */
package time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.log4j.Logger;

/**
 * @author wushuai
 * @date 2018��9��10��
 * @Description	TODO
 */
public class TimeConverterUtil {
	
	private static Logger logger = Logger.getLogger(TimeConverterUtil.class);

    /**
     * ������������:UTCʱ��ת����ʱ���ʽ
     * @param utcTime UTCʱ��
     * @param utcTimePatten UTCʱ���ʽ
     * @param localTimePatten   ����ʱ���ʽ
     * @return ����ʱ���ʽ��ʱ��
     * eg:utc2Local("2017-06-14 09:37:50.788+08:00", "yyyy-MM-dd HH:mm:ss.SSSXXX", "yyyy-MM-dd HH:mm:ss.SSS")
     */
    public static String utc2Local(String utcTime, String utcTimePatten, String localTimePatten) {
        SimpleDateFormat utcFormater = new SimpleDateFormat(utcTimePatten);
        utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));//ʱ�����岢����ʱ���ȡ
        Date gpsUTCDate = null;
        try {
            gpsUTCDate = utcFormater.parse(utcTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return utcTime;
        }
        SimpleDateFormat localFormater = new SimpleDateFormat(localTimePatten);
        localFormater.setTimeZone(TimeZone.getDefault());
        String localTime = localFormater.format(gpsUTCDate.getTime());
        return localTime;
    }

    /**
     * ������������:UTCʱ��ת����ʱ���ʽ
     * @param utcTime UTCʱ��
     * @param localTimePattern ����ʱ���ʽ(Ҫת���ı���ʱ���ʽ)
     * @return ����ʱ���ʽ��ʱ��
     */
    public static String utc2Local(String utcTime, String localTimePattern){
        String utcTimePattern = "yyyy-MM-dd";
        String subTime = utcTime.substring(10);//UTCʱ���ʽ�� yyyy-MM-dd ��ͷ,��utcʱ���ǰ10λ��ȡ��,֮���Ǻ��ж�ʱ��ʱ���ʽ��Ϣ������

        //������׺Ϊ:+8:00ʱ,ת��Ϊ:+08:00 �� -8:00ת��Ϊ-08:00
        if(subTime.indexOf("+") != -1){
            subTime = changeUtcSuffix(subTime, "+");
        }
        if(subTime.indexOf("-") != -1){
            subTime = changeUtcSuffix(subTime, "-");
        }
        utcTime = utcTime.substring(0, 10) + subTime;

        //���ݴ��뺯����utcʱ��,�õ���Ӧ��utcʱ���ʽ
        //����һ:���� T
        if(utcTime.indexOf("T") != -1){
            utcTimePattern = utcTimePattern + "'T'";
        }

        //�����:�������SSS
        if(utcTime.indexOf(".") != -1){
            utcTimePattern = utcTimePattern + " HH:mm:ss.SSS";
        }else{
            utcTimePattern = utcTimePattern + " HH:mm:ss";
        }

        //������:����ʱ������
        if(subTime.indexOf("+") != -1 || subTime.indexOf("-") != -1){
            utcTimePattern = utcTimePattern + "XXX";
        }
        else if(subTime.indexOf("Z") != -1){
            utcTimePattern = utcTimePattern + "'Z'";
        }

        if("yyyy-MM-dd HH:mm:ss".equals(utcTimePattern) || "yyyy-MM-dd HH:mm:ss.SSS".equals(utcTimePattern)){
            return utcTime;
        }

        SimpleDateFormat utcFormater = new SimpleDateFormat(utcTimePattern);
        utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date gpsUtcDate = null;
        try {
            gpsUtcDate = utcFormater.parse(utcTime);
        } catch (Exception e) {
            logger.error("utcTime converter localTime failed!!!", e);
            return utcTime;
        }
        SimpleDateFormat localFormater = new SimpleDateFormat(localTimePattern);
        localFormater.setTimeZone(TimeZone.getDefault());
        String localTime = localFormater.format(gpsUtcDate.getTime());
        return localTime;
    }

    /**
     * ������������:�޸�ʱ���ʽ��׺
     * ����ʹ�ó���:������׺Ϊ:+8:00ʱ,ת��Ϊ:+08:00 �� -8:00ת��Ϊ-08:00
     * @param subTime
     * @param sign
     * @return
     */
    private static String changeUtcSuffix(String subTime, String sign){
        String timeSuffix = null;
        String[] splitTimeArrayOne = subTime.split("\\" + sign);
        String[] splitTimeArrayTwo = splitTimeArrayOne[1].split(":");
        if(splitTimeArrayTwo[0].length() < 2){
            timeSuffix = "+" + "0" + splitTimeArrayTwo[0] + ":" + splitTimeArrayTwo[1];
            subTime = splitTimeArrayOne[0] + timeSuffix;
            return subTime;
        }
        return subTime;
    }

    /**
     * ������������:��ȡ����ʱ���ı�ʾ(����:�ڰ���-->+08:00)
     * @return
     */
    public static String getTimeZoneByNumExpress(){
        Calendar cal = Calendar.getInstance();
        TimeZone timeZone = cal.getTimeZone();
        int rawOffset = timeZone.getRawOffset();
        int timeZoneByNumExpress = rawOffset/3600/1000;
        String timeZoneByNumExpressStr = "";
        if(timeZoneByNumExpress > 0 && timeZoneByNumExpress < 10){
            timeZoneByNumExpressStr = "+" + "0" + timeZoneByNumExpress + ":" + "00";
        }
        else if(timeZoneByNumExpress >= 10){
            timeZoneByNumExpressStr = "+" + timeZoneByNumExpress + ":" + "00";
        }
        else if(timeZoneByNumExpress > -10 && timeZoneByNumExpress < 0){
            timeZoneByNumExpress = Math.abs(timeZoneByNumExpress);
            timeZoneByNumExpressStr = "-" + "0" + timeZoneByNumExpress + ":" + "00";
        }else if(timeZoneByNumExpress <= -10){
            timeZoneByNumExpress = Math.abs(timeZoneByNumExpress);
            timeZoneByNumExpressStr = "-" + timeZoneByNumExpress + ":" + "00";
        }else{
            timeZoneByNumExpressStr = "Z";
        }
        return timeZoneByNumExpressStr;
    } 

}
