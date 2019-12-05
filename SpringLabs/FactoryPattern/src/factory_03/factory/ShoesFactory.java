package factory_03.factory;

import factory_03.Shoes;
import factory_03.shoes.BlackShoes;
import factory_03.shoes.BrownShoes;
import factory_03.shoes.RedShoes;

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
