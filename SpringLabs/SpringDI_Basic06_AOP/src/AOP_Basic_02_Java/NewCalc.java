package AOP_Basic_02_Java;

public class NewCalc implements Calc {

	@Override
	public int ADD(int x, int y) {
		// 보조업무( 공통 업부) cross-cutting-concern

		int result = x + y; // 주업무 core-concern

		// 보조업무( 공통 업부) cross-cutting-concern

		return result;
	}

	@Override
	public int MUL(int x, int y) {
		// 보조업무( 공통 업부) cross-cutting-concern

		int result = x * y; // 주업무 core-concern

		// 보조업무( 공통 업부) cross-cutting-concern

		return result;
	}

	@Override
	public int SUB(int x, int y) {
		// 보조업무( 공통 업부) cross-cutting-concern

		int result = x - y; // 주업무 core-concern

		// 보조업무( 공통 업부) cross-cutting-concern

		return result;
	}

}
