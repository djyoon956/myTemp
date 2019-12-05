package factory_01;

import factory_01.shoes.BlackShoes;
import factory_01.shoes.BrownShoes;
import factory_01.shoes.RedShoes;

public class ShoesStore {

	public Shoes orderShoes(String name) {

		Shoes shoes = null;

		// 바뀔 수 있는 부분
		if (name.equals("blackShoes")) {

			shoes = new BlackShoes();

		} else if (name.equals("brownShoes")) {

			shoes = new BrownShoes();

		} else if (name.equals("redShoes")) {

			shoes = new RedShoes();
		}

		// 바뀌지 않는 부분
		shoes.prepare(); // 신발을 준비하고
		shoes.packing(); // 종이 가방에 넣음

		return shoes;
	}
}
