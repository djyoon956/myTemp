package factory_03.store;

import factory_03.Shoes;
import factory_03.shoes.france.FRStyleBlackShoes;
import factory_03.shoes.france.FRStyleBrownShoes;
import factory_03.shoes.france.FRStyleRedShoes;

public class FranceShoesStore extends ShoesStore {

	@Override
	public Shoes makeShoes(String name) {
		if (name.equals("blackShoes")) {
			return new FRStyleBlackShoes();

		} else if (name.equals("brownShoes")) {

			return new FRStyleBrownShoes();

		} else if (name.equals("redShoes")) {

			return new FRStyleRedShoes();

		} else {

			return null;
		}
	}

}
