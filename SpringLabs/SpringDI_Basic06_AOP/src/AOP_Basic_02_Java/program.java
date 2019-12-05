package AOP_Basic_02_Java;

import java.lang.reflect.Proxy;

public class program {

	public static void main(String[] args) {
		Calc calc = new NewCalc();

		// proxy 객체 통해서 처리
		Calc cal = (Calc) Proxy.newProxyInstance(calc.getClass().getClassLoader()  // 실 객체의 메타 정보 제공
																	, calc.getClass().getInterfaces() // 행위정보 (어떠한 함수를 가지고 있는지)
																	, new LogPrintHandler(calc)); 	 // 보조 객체 >> parameter로 객체 주소
		
		
		// Proxy를 통해서 처리하지만, 사용자는 실제 Calc 객체를 사용하는 것처럼...
		int result = cal.ADD(100, 200);
		System.out.println("Main result : " + result);
		
		result = cal.MUL(100, 200);
		System.out.println("Main result : " + result);
	}
}
