package factory_02.store;

import factory_02.Shoes;
import factory_02.factory.ShoesFactory;

public class ShoesStore {

	private ShoesFactory factory;

	public ShoesStore() {
		factory = new ShoesFactory();
	}

	public Shoes orderShoes(String name) {

		Shoes shoes = factory.makingShoes(name);

		// 바뀌지 않는 부분
		shoes.prepare(); // 신발을 준비한다.
		shoes.packing(); // 신발을 포장한다.

		return shoes;
	}
}
