package AOP_Basic_01;

public class program {

	public static void main(String[] args) {
		Calc calc = new Calc();
		int result = calc.Add(1000, 2000);

		System.out.println("Add result : " + result);

		result = calc.Mul(10000, 2000);
		System.out.println("Mul result : " + result);
	}
}
