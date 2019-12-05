package factory_03.store;

import factory_03.Shoes;
import factory_03.shoes.japan.JPStyleBlackShoes;
import factory_03.shoes.japan.JPStyleBrownShoes;
import factory_03.shoes.japan.JPStyleRedShoes;

public class JapanShoesStore extends ShoesStore {

	@Override
	public Shoes makeShoes(String name) {
		if (name.equals("blackShoes")) {
			return new JPStyleBlackShoes();

		} else if (name.equals("brownShoes")) {

			return new JPStyleBrownShoes();

		} else if (name.equals("redShoes")) {

			return new JPStyleRedShoes();

		} else {

			return null;
		}
	}

}
