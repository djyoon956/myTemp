package AOP_Basic_01;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;

/*
 	간단한 계산기 프로그램
 		- 주 관심 : 사직연산 기능
 		- 보조 관심(공통 관심) :연산에 걸린 시간 구하기
								  log 출력(console 출력 : 시스템이 출력하는 것처엄 Red 색으로)
 */

public class Calc {

	public int Add(int x, int y) {
		Log log = LogFactory.getLog(this.getClass());
		// System.currentTimeMillis();
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		log.info("[ 타이머 시작 ]");

		int result = x + y; // 주관심

		stopWatch.stop();
		log.info("[ 타이머 종료 ]");
		log.info("[ TIME LOG METHOD : ADD ]");
		log.info("[ TIME LOG METHOD TIME : " + stopWatch.getTotalTimeMillis() + "ms ]");
		
		return result;
	}

	public int Mul(int x, int y) {
		Log log = LogFactory.getLog(this.getClass());
		// System.currentTimeMillis();
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		log.info("[ 타이머 시작 ]");

		int result = x * y; // 주관심

		stopWatch.stop();
		log.info("[ 타이머 종료 ]");
		log.info("[ TIME LOG METHOD : MUL ]");
		log.info("[ TIME LOG METHOD TIME : " + stopWatch.getTotalTimeMillis() + "ms ]");
		
		return result;
	}
}
