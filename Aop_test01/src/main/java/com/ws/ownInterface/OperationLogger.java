/**
 * 
 */
package com.ws.ownInterface;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wushuai
 * @date 2018��9��17��
 * @Description	TODO
 */
//��������ע���ʹ�÷�Χ
@Target(ElementType.METHOD)
//��������ע�����������
@Retention(RetentionPolicy.RUNTIME)
//���ڼ̳б��и�ע��ĸ�������࣬����Ҫ��Ӹ�ע�⣬Ҳ����Ч
@Inherited
//����ʹ�øñ�Ǿ��Ǹ���jdk����Ҳ��annotation���ɵ�doc��ȥ
@Documented
public @interface OperationLogger {
	String getName();
	int getAge();
}
