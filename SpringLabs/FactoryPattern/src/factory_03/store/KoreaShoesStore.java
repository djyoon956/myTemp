package factory_03.store;

import factory_03.Shoes;
import factory_03.shoes.BlueShoes;
import factory_03.shoes.france.FRStyleBlackShoes;
import factory_03.shoes.france.FRStyleBrownShoes;
import factory_03.shoes.france.FRStyleRedShoes;

public class KoreaShoesStore extends ShoesStore {

	@Override
	public Shoes makeShoes(String name) {
		if (name.equals("blackShoes")) {
			return new FRStyleBlackShoes();

		} else if (name.equals("brownShoes")) {

			return new FRStyleBrownShoes();

		} else if (name.equals("redShoes")) {

			return new FRStyleRedShoes();

		} else if (name.equals("blueShoes")) {

			return new BlueShoes();

		}else {

			return null;
		}
	}

}
