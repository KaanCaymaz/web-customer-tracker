package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	
	Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void forController() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	private void forService() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	private void forDao() {}
	
	@Pointcut("forController() || forService() || forDao()")
	private void forAllApp() {}
	
	
	@Before("forAllApp()")
	public void beforeApp(JoinPoint theJoinPoint) {
		
		String method = theJoinPoint.getSignature().toShortString();
		
		myLogger.info("\n===>> @Before: calling method: "+method );
		
		Object[] parameters = theJoinPoint.getArgs();
		
		for(Object param : parameters) {
			myLogger.info("===>> argument: "+ param);
		}
	}
		
	@AfterReturning(pointcut="forAllApp()",
					returning="theResult")
	public void afterReturning(JoinPoint theJoinPoint , Object theResult ) {
		
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n===>> @AfterReturning: calling method: "+method);
		
		myLogger.info("===> theResult: "+theResult);
		
	}
		
	
}
