package com.chinamobile.cmss.bcse.validate.annotation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.chinamobile.cmss.bcse.validate.annotation.InInclude;


public class InRangeValidator implements ConstraintValidator<InInclude, Object>{
	String[] values; 
	
	@Override
	public void initialize(InInclude constraintAnnotation) {
		
		values = constraintAnnotation.rangeValue();
		
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		if (object == null || "".equals(String.valueOf(object))) {
			return true;
		}
		if (values != null && values.length>0 ) {
			for (String value : values) {
				if (String.valueOf(object).equals(value)) {
					return true;
				}
			}
			
		}
//		context.buildConstraintViolationWithTemplate("分页的页码和每页显示数据的数量必须为正整数").addConstraintViolation();
//		context.disableDefaultConstraintViolation();
		return false;
	}






}
