package factory_03;

import factory_03.store.*;

public class program {
	public static void main(String[] args) {
		ShoesStore krStore = new KoreaShoesStore();

		Shoes shoes = krStore.orderShoes("blueShoes");
		System.out.println("블루 매장에서 산 구두는 ? --> " + shoes.getName());
	}
}
