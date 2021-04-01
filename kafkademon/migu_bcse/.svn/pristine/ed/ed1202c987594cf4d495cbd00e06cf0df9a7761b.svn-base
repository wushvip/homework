package com.chinamobile.cmss.bcse.validate.annotation.impl;


import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

import com.chinamobile.cmss.bcse.tool.tools.Tool;
import com.chinamobile.cmss.bcse.validate.annotation.DateCompare;
import com.chinamobile.cmss.bcse.validate.annotation.DateType;

public class DateCompareValidator implements ConstraintValidator<DateCompare, Object>{
	private String start;
	private String end;
	private DateType type;
	@Override
	public void initialize(DateCompare constraintAnnotation) {
		this.start = constraintAnnotation.start();
		this.end = constraintAnnotation.end();
		this.type=constraintAnnotation.type();
		
	}
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		try {
			//获取指定属性的值
			String startStr = BeanUtils.getProperty(value, start);
			String endStr = BeanUtils.getProperty(value, end);
			if (startStr == null || endStr == null) {
				context.buildConstraintViolationWithTemplate("日期不能为空").addConstraintViolation();
				context.disableDefaultConstraintViolation();
				return false;
			}
			
			Date startDate = null;
			Date endDate  = null;
			if (type.equals(DateType._INT)) {
				//存在问题，当输入值为0时，容易与本身初始值的0混淆，区分不开，后续可以采用Integer
				startDate = Tool.formatStringToDate(Tool.getDayTime(Integer.parseInt(startStr)));
				endDate = Tool.formatStringToDate(Tool.getDayTime(Integer.parseInt(endStr)));
			}
			else {
				//type.equals(DateType._STRING) 默认为yyyy-MM-dd字符串类型
				startDate = Tool.formatStringToDate(BeanUtils.getProperty(value, start));
				endDate = Tool.formatStringToDate(BeanUtils.getProperty(value, end));
			}

			if (startDate!= null && endDate!=null&& startDate.after(endDate)) {
				context.buildConstraintViolationWithTemplate("起始日期不能大于截止日期").addConstraintViolation();
				context.disableDefaultConstraintViolation();
				return false;
			}
		} catch (IllegalAccessException | NumberFormatException | ParseException | InvocationTargetException | NoSuchMethodException e) {
			context.buildConstraintViolationWithTemplate("校验日期时出现异常").addConstraintViolation();
			context.disableDefaultConstraintViolation();
			
			e.printStackTrace();
			return false;
		} 
		
		return true;
	}
	

}
