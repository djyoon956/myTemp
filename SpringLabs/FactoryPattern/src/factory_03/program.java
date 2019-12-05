package factory_03;

import factory_03.store.*;

public class program {
	public static void main(String[] args) {
		ShoesStore jpStore = new JapanShoesStore();
		ShoesStore frStore = new FranceShoesStore();

		Shoes shoes = jpStore.orderShoes("blackShoes");
		System.out.println("일본 매장에서 산 구두는 ? --> " + shoes.getName());
		System.out.println();

		shoes = frStore.orderShoes("blackShoes");
		System.out.println("프랑스 매장에서 산 구두는 ? --> " + shoes.getName());
	}
}
