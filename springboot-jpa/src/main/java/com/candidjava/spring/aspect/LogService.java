package com.candidjava.spring.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogService {

	private static final Logger LOGGER = Logger.getLogger(LogService.class);

	/**
	 * we can use both service and controller together
	 * 
	 * @Pointcut("within(com.candidjava.spring.service.*) ||
	 * within(com.candidjava.spring.controller.*)") public void logMethod() { }
	 * 
	 */

	@Pointcut("within(com.candidjava.spring.service.*)")
	public void logServiceMethod() {

	}

	@Pointcut("within(com.candidjava.spring.controller.*)")
	public void logControllerMethod() {

	}

	@Before(value = "logServiceMethod()", argNames = "joinPoint")
	public void logBefore(JoinPoint joinpoint) {
		LOGGER.info("=========Logging Before Method==========");
		LOGGER.info("Method name :" + joinpoint.getSignature().toString());
		Object[] signatureArgs = joinpoint.getArgs();
		for (Object signatureArg : signatureArgs) {
			LOGGER.info("method arguments :" + signatureArg.toString());
		}

	}

	@AfterReturning(value = "logControllerMethod()", returning = "result")
	public void logReturning(JoinPoint joinpoint, Object result) {
		LOGGER.info("=========Logging After Returning==========");
		System.out.println(result);

	}

	@AfterThrowing(value = "logControllerMethod()", throwing = "throwing")
	public void logThrowing(JoinPoint throwing) {
		LOGGER.info("=========Logging After throwing==========");
		System.out.println(throwing);

	}

	@After(value = "logServiceMethod()")
	public void logAfter(JoinPoint joinpoint) {
		LOGGER.info("=========Logging After Method==========");
		LOGGER.info("Method name :" + joinpoint.getSignature().toString());
		Object[] signatureArgs = joinpoint.getArgs();
		for (Object signatureArg : signatureArgs) {
			LOGGER.info("method arguments :" + signatureArg);
		}
	}

}
