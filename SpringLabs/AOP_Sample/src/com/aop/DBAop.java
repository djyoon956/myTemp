package com.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class DBAop {
	Log log = LogFactory.getLog(this.getClass());

	// @Pointcut의 속성에 핵심코드의 어느 부분까지 공통기능을 사용하겠다고 명시
	@Pointcut("within(com.dao.*)")
	private void pointcutMethod() {
	}

	@Around("pointcutMethod()")
	public Object DbProcess(ProceedingJoinPoint joinpoint) throws Throwable {
		String target = joinpoint.getSignature().getName();
		log.info("[" + target + "] AROUND 시작");

		boolean result = false;
		try {
			log.info("[" + target + "] DB CONNECTION");
			log.info("[" + target + "] START");

			result = (boolean) joinpoint.proceed();
			log.info("[" + target + "] result : " + result);
			log.info("[" + target + "] END");
			if (result)
				log.info("[" + target + "] DB COMMIT");
			else
				log.info("[" + target + "] DB ROLLBACK");
			
		} finally {
			log.info("[" + target + "] DB CLOSE");
			log.info("[" + target + "] AROUND 끝");
		}
		return result;
	}

	@Before("pointcutMethod()")
	public void beforeMethod() {
		log.info("beforeMethod() start");
	}

	@After("pointcutMethod()")
	public void afterMethod() {
		log.info("afterMethod() start");
	}

	@AfterReturning("pointcutMethod()")
	public void afterReturningMethod() {
		log.info("afterReturningMethod start");
	}

	@AfterThrowing(pointcut = "pointcutMethod()", throwing = "ex")
	public void afterThrowingMethod(JoinPoint joinpoint, Throwable ex) {
		log.info("[ Exception ] " + ex.getMessage());
	}
}