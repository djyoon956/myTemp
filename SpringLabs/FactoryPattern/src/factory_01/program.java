package factory_01;

public class program {
	public static void main(String[] args) {
		ShoesStore store = new ShoesStore();
		Shoes shoes = store.orderShoes("blackShoes");

		System.out.println("주문 구두 : " + shoes.getName());
	}
}
