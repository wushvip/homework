package com.chinamobile.cmss.bcse.tool.tools;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;

import javax.validation.ConstraintValidatorContext;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;
import com.chinamobile.cmss.bcse.tool.exception.PackageExceptionInfoException;


public class Tool {
	
	public static void main(String[] args){
		String msg= "2013-12-12T00:00:00Z";
		
		System.out.println(getUuid());
		
		
	}
	
	
	/**
	 * 
	 * @param context
	 * @param msg
	 */
	public static void writeMsg(ConstraintValidatorContext context,String msg){
		
		context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
		context.disableDefaultConstraintViolation();
		
	}
	
	/**
	 * 当下层程序抛出异常时，返回结果信息处理
	 * 
	 * @Title: operaExceptionInfo
	 * @Description: TODO
	 * @param resultBean
	 * @return
	 * @return: ResultBean
	 */
	public static void validateParamsError(ResultBean resultBean, BindingResult result) {

		List<ObjectError> ls = result.getAllErrors();
		String message="";
		for (int i = 0; i < ls.size(); i++) {
			message=message+ls.get(i).getDefaultMessage();
			break;
		}
		resultBean.setStatus(Config.RESULT_FAIL);
		resultBean.setCode(Config.FAIL_SERIVICE_CODE);
		resultBean.setMessage(message);

	}

	/**
	 * 
	 * @Title: sqlTransferred 
	 * @Description: sql特殊字符转义
	 * @param searchWord
	 * @return: void
	 */
	public static String sqlTransferred(String searchWord){
		String[] sqlTransferred = {"%","_"};
		for (int i = 0; i < sqlTransferred.length; i++) {
			if (searchWord.contains(sqlTransferred[i])) {
				searchWord = searchWord.replaceAll(sqlTransferred[i], "\\\\"+sqlTransferred[i]);
			}
			
		}
		return searchWord;
	}
	
	/**
	 * 当下层程序抛出异常时，返回结果信息处理
	 * @Title: operaExceptionInfo 
	 * @Description: TODO
	 * @param resultBean
	 * @return
	 * @return: ResultBean
	 */
	public static void operateExceptionInfo(ResultBean resultBean,Exception e) {
		
		resultBean.setStatus(Config.RESULT_FAIL);
		resultBean.setCode(Config.FAIL_SYS_CODE);
		resultBean.setMessage(e.getMessage());
		
	}
	
	public static void serviceException(ResultBean resultBean,String msg) {
		
		resultBean.setStatus(Config.RESULT_FAIL);
		resultBean.setCode(Config.FAIL_SERIVICE_CODE);
		resultBean.setMessage(msg);
		
	}
	
	/**
	 * 下层程序在抛异常时，统一在这里进行封装
	 * @Title: operateExceptionInContent 
	 * @Description: TODO
	 * @param className
	 * @param code
	 * @return
	 * @return: RuntimeException
	 */
	public static RuntimeException operateExceptionPackage(String className,String code,String userId,String appId,String logType) {
		RuntimeException userDefineException = new RuntimeException(ExceptionConstants.OtherException);
		
		try {
			Class<?> c = Class.forName(Config.EXCEPTION_ROOT_PATH+className);
			
			Constructor<?> constructor = c.getConstructor(String.class);
			
			userDefineException = (RuntimeException) constructor.newInstance(code);
			
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			LogUtil.loggerEntrance(userId, appId, code, logType, e);
			throw new PackageExceptionInfoException(ExceptionConstants.PackageException);
		}
		LogUtil.loggerEntrance(userId, appId, code, logType, userDefineException);
		return userDefineException;
	}
	
	/**
	 * 
	 * @Title: operateExceptionPackage 
	 * @Description: 处理try{}catch{}块中的异常
	 * @param className
	 * @param code
	 * @param userId
	 * @param appId
	 * @param logType
	 * @param systemException
	 * @return
	 * @return: RuntimeException
	 */
	public static RuntimeException operateExceptionPackage(String className,String code,String userId,String appId,String logType,Exception systemException) {
		RuntimeException userDefineException = new RuntimeException(ExceptionConstants.OtherException);
		
		try {
			Class<?> c = Class.forName(Config.EXCEPTION_ROOT_PATH+className);
			
			Constructor<?> constructor = c.getConstructor(String.class);
			
			userDefineException = (RuntimeException) constructor.newInstance(code);
			
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			LogUtil.loggerEntrance(userId, appId, code, logType, e);
			throw new PackageExceptionInfoException(ExceptionConstants.PackageException);
		}
		//打印日志时要打印堆栈信息
		LogUtil.loggerEntrance(userId, appId, code, logType, systemException);
		return userDefineException;
	}
	
    /** 
     * @Title: getStandTime 
     * @Description: TODO 
     * @param dateString
     * @return
     * @return: String
     */
    public static String getStandTime(String dateString){
		
		SimpleDateFormat sfEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sfEnd.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		SimpleDateFormat sfStart = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",java.util.Locale.UK) ;
		  
		try {
			 /*long timeLong = sfStart.parse(dateString).getTime() ;  
			 timeLong =timeLong - 8 * 60 * 60 * 1000;*/
			
			 //dateString=new Date(timeLong).toString();
			 dateString= sfEnd.format(sfStart.parse(dateString));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateString;
	} 

	/**
	 * 随机生成唯一标识
	 * 
	 * @return String
	 * @date:2015-10-29下午2:33:36
	 * @author:lijingjing
	 */
	public static String getUuid() {

		UUID id = UUID.randomUUID();
		String idString=id.toString();
		idString=idString.replaceAll("-", "");
		return idString;

	}
	/**
	 * 获取yyyy-MM-dd形式的当前时间
	 * 
	 * @return String
	 * @date:2015-10-29下午2:48:49
	 * @author:lijingjing
	 */
	public static String getCurrentDate() {

		Date dtDate = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String dateString = sdf.format(dtDate);

		return dateString;
	}
	
	public static String getCurrentDateHour() {

		Date dtDate = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String dateString = sdf.format(dtDate);

		return dateString;

	}
	/**
	 * 根据随机源，生成随机数
	 * 
	 * @return String
	 * @date:2015-11-5下午4:37:29
	 * @author:lijingjing
	 */
	public static String getRandomString(int length) {
		Random random = new Random();
		StringBuffer msg = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(Config.charSequence.length);
			msg.append(String.valueOf(Config.charSequence[index]));
		}
		return msg.toString();
	}

	/**
	 * 获取标准化时间
	 * 
	 * @return
	 */
	public static String getDayTime(int time) {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, time);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}

	public static String getDateTime(String date, int time) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(formatter.parse(date));
		calendar.add(Calendar.DATE, time);// 把日期往后增加一天.整数往后推,负数往前移动
		Date dt = calendar.getTime();
		return formatter.format(dt);
	}

	/**
	 * 将带时分秒格式的日期转为不带时分秒的
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 *             String
	 * @date:2015-12-8上午10:44:43
	 * @author:lijingjing
	 */
	public static String formatDateString(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Date dt = formatter.parse(date);
		String retDate = formatter.format(dt);
		return retDate;
	}
	
	/**
	 * 将yyyy-MM-dd类型转为date
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date formatStringToDate(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Date dt = formatter.parse(date);
		return dt;
	}

	/**
	 * 在返回的数据中，可能存在部分因没有数据而缺失的日期
	 * 
	 * @return LinkedHashMap<String,String>
	 * @date:2015-12-4下午4:53:29
	 * @author:lijingjing
	 * @throws ParseException
	 */
	public static LinkedHashMap<String, String> getWholeDateData(
			LinkedHashMap<String, String> resultMap, int start, int end)
			throws ParseException {

		LinkedHashMap<String, String> sortMap = new LinkedHashMap<String, String>();
		String initDate = getDayTime(start);

		for (int i = 0; i < end - start + 1; i++) {

			if (resultMap.containsKey(initDate)) {

				sortMap.put(initDate, resultMap.get(initDate));

			} else {
				sortMap.put(initDate, "0");
			}
			initDate = getDateTime(initDate, 1);
		}
		return sortMap;
	}

	/**
	 * 补全一天所有小时的数据
	 * 
	 * @param resultMap
	 * @return LinkedHashMap<String,String>
	 * @date:2015-12-7下午4:41:19
	 * @author:lijingjing
	 */
	public static LinkedHashMap<String, String> getWholeHourData(
			LinkedHashMap<String, String> resultMap) {

		LinkedHashMap<String, String> sortMap = new LinkedHashMap<String, String>();
		for (int i = 0; i <= 23; i++) {
			String key = String.valueOf(i);
			if (resultMap.containsKey(key)) {
				sortMap.put(key, resultMap.get(key));
			} else {
				sortMap.put(key, "0");
			}
		}
		return sortMap;

	}
	
}
