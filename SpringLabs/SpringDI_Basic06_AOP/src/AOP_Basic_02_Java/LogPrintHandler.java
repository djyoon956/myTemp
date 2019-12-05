package AOP_Basic_02_Java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;

/*
  보조관심(공통관심) cross-cutting-conern
  
  	invoke : 하나의 함수가 여러개의 함수를 대리 처리한다.
  	
 */
public class LogPrintHandler implements InvocationHandler {
	private Object target; // 실 객체의 주소값

	public LogPrintHandler(Object target) {
		System.out.println("LogPrintHandler : 보조 관심 생성자 호출");
		this.target = target;
	}

	// 주업무(함수)
	// ADD, MUL, SUB 함수 대리해서 처리

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		System.out.println("invoke 함수 호출");
		System.out.println("Method : " + method);
		System.out.println("args : " + Arrays.toString(args));

		// 보조 공통 업무 (advice)
		Log log = LogFactory.getLog(this.getClass());
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		log.info("[ 타이머 시작 ]");

		// 주업무(core-conern) : 실객체의 함수 호풀
		int result = (int) method.invoke(this.target, args);

		// 보조 공통 업무 (advice)
		stopWatch.stop();
		log.info("[ 타이머 종료 ]");
		log.info("[ TIME LOG METHOD " + method.getName() + "]");
		log.info("[ TIME LOG METHOD TIME : " + stopWatch.getTotalTimeMillis() + "ms ]");

		return result;
	}
}
