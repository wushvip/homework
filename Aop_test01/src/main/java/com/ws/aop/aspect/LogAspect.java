/**
 * 
 */
package com.ws.aop.aspect;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.ws.ownInterface.OperationLogger;

/**
 * @author wushuai
 * @date 2018��9��17��
 * @Description	TODO
 */
@Aspect
@Component
public class LogAspect {
	 private static final Logger logger = Logger.getLogger(LogAspect.class);

	    /**
	     * ����Pointcut��Pointcut�����ƣ��˷��������з���ֵ���÷���ֻ��һ����ʾ
	     */
//	    @Pointcut("@annotation(OperationLogger)")
		@Pointcut("execution(* com.ws.controller.*.*(..))")
	    public void controllerAspect()
	    {
	        System.out.println("����һ�������");
	    }

	    /**
	     * ǰ��֪ͨ��Before advice�� ����ĳ���ӵ㣨JoinPoint��֮ǰִ�е�֪ͨ�������֪ͨ������ֹ���ӵ�ǰ��ִ�С�
	     * @param joinPoint
	     */
	    @Before("controllerAspect()")
	    public void doBefore(JoinPoint joinPoint)
	    {
	        System.out.println("=====SysLogAspectǰ��֪ͨ��ʼ=====");
	        handleLog(joinPoint, null);
			System.out.println("=====SysLogAspectǰ��֪ͨ����=====");
	    }

	    /**
	     * ��֪ͨ��After advice�� ����ĳ���ӵ��˳���ʱ��ִ�е�֪ͨ���������������ػ����쳣�˳�����
	     * @param joinPoint
	     */
	    @AfterReturning(pointcut = "controllerAspect()")
	    public void doAfter(JoinPoint joinPoint)
	    {
	        System.out.println("=====SysLogAspect����֪ͨ��ʼ=====");
	        handleLog(joinPoint, null);
			System.out.println("=====SysLogAspect����֪ͨ����=====");
	    }

	    /**
	     * �׳��쳣��֪ͨ��After throwing advice�� �� �ڷ����׳��쳣�˳�ʱִ�е�֪ͨ��
	     * @param joinPoint
	     * @param e
	     */
	    @AfterThrowing(value = "controllerAspect()", throwing = "e")
	    public void doAfter(JoinPoint joinPoint, Exception e)
	    {
	        System.out.println("=====SysLogAspect�쳣֪ͨ��ʼ=====");
	        handleLog(joinPoint, e);
	    }

	    /**
	     * ����֪ͨ��Around advice�� ����Χһ�����ӵ��֪ͨ������Web��Servlet�淶�е�Filter��doFilter�����������ڷ����ĵ���ǰ������Զ������Ϊ��Ҳ����ѡ��ִ�С�
	     * @param joinPoint
	     */
	    @Around("controllerAspect()")
	    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable
	    {
	        System.out.println("=====SysLogAspect ����֪ͨ��ʼ=====");
	        handleLog(joinPoint, null);
	        Object obj= joinPoint.proceed();
	        System.out.println("=====SysLogAspect ����֪ͨ����=====");
	        return  obj;
	    }

	    /**
	     * ��־����
	     *
	     * @param joinPoint
	     * @param e
	     */
	    private void handleLog(JoinPoint joinPoint, Exception e)
	    {
	        try
	        {
	            //���ע��
	            OperationLogger logger = giveController(joinPoint);
	            if (logger == null)
	            {
	                return;
	            }

	            String signature = joinPoint.getSignature().toString(); // ��ȡĿ�귽��ǩ��
	            String methodName = signature.substring(signature.lastIndexOf(".") + 1,
	                    signature.indexOf("("));

	            String longTemp = joinPoint.getStaticPart().toLongString();
	            String classType = joinPoint.getTarget().getClass().getName();

	            Class<?> clazz = Class.forName(classType);

	            Method[] methods = clazz.getDeclaredMethods();
	            System.out.println("methodName: " + methodName);

	            for (Method method : methods)
	            {
	                if (method.isAnnotationPresent(OperationLogger.class)
	                        && method.getName().equals(methodName))
	                {
	                    //OpLogger logger = method.getAnnotation(OpLogger.class);
	                    String clazzName = clazz.getName();
	                    System.out.println("clazzName: " + clazzName + ", methodName: "
	                            + methodName);
	                }
	            }

	        } catch (Exception exp)
	        {
	            logger.error("�쳣��Ϣ:{}", exp);
	            exp.printStackTrace();
	        }
	    }

	    /**
	     * ���ע��
	     * @param joinPoint
	     * @return
	     * @throws Exception
	     */
	    private static OperationLogger giveController(JoinPoint joinPoint) throws Exception
	    {
	        Signature signature = joinPoint.getSignature();
	        MethodSignature methodSignature = (MethodSignature) signature;
	        Method method = methodSignature.getMethod();

	        if (method != null)
	        {
	            return method.getAnnotation(OperationLogger.class);
	        }
	        return null;
	    }

}
