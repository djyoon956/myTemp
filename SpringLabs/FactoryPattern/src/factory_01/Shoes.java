package factory_01;

public class Shoes {
	public String name;
	public String bottom;
	public String leather;
	public boolean hasPattern;

	public void prepare() {
		System.out.println("완성된 신발을 준비 중 입니다..");
	}

	public void packing() {
		System.out.println("신발을 포장 하고 있습니다..");
	}

	public String getName() {
		return name;
	}
}
