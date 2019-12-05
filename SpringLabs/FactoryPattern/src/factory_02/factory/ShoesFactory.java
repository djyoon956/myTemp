package factory_02.factory;

import factory_02.Shoes;
import factory_02.shoes.*;

public class ShoesFactory {
	public Shoes makingShoes(String name) {
		Shoes shoes = null;
		if (name.equals("blackShoes")) {

			shoes = new BlackShoes();

		} else if (name.equals("brownShoes")) {

			shoes = new BrownShoes();

		} else if (name.equals("redShoes")) {

			shoes = new RedShoes();
		}

		return shoes;
	}
}
