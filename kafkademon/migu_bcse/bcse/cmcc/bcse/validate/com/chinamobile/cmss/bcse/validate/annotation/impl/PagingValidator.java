package com.chinamobile.cmss.bcse.validate.annotation.impl;


import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.config.MsgConfig;
import com.chinamobile.cmss.bcse.validate.annotation.Paging;

public class PagingValidator implements ConstraintValidator<Paging, Object>{
	//页码
	private String pageIndex;
	//每页显示的数据
	private String pageNum;
	@Override
	public void initialize(Paging constraintAnnotation) {
		this.pageIndex = constraintAnnotation.pageIndex();
		this.pageNum = constraintAnnotation.pageNum();
		
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		boolean ret = true;
		try {
			final int pnInt = Integer.parseInt(BeanUtils.getProperty(value, pageIndex));
			final int numInt =Integer.parseInt(BeanUtils.getProperty(value, pageNum));
			
			boolean pnBoolean = (pnInt>=1 && pnInt<Integer.MAX_VALUE)? true:false;
			boolean numBoolean = (numInt>=1 && numInt<=Config.PAGENUM_MAX)? true:false;
			
			if (!pnBoolean || !numBoolean) {
				context.buildConstraintViolationWithTemplate(MsgConfig.PAGE_RAGE_MSG).addConstraintViolation();
				context.disableDefaultConstraintViolation();
				ret = false;
			}
		} catch (IllegalAccessException | NumberFormatException | InvocationTargetException | NoSuchMethodException e) {
			ret = false;
			e.printStackTrace();
		} 
		return ret;
	}

}
