package pizza_01;

import pizza_01.pizza.Pizza;
import pizza_01.pizza.newyork.NYStyleCheesePizza;
import pizza_01.pizza.newyork.NYStyleClamPizza;
import pizza_01.pizza.newyork.NYStylePepperoniPizza;
import pizza_01.pizza.newyork.NYStyleVeggiePizza;

public class NYPizzaStore extends PizzaStore {

	@Override
	Pizza createPizza(String type) {
		Pizza pizza = null;

		if (type.equals("cheese"))
			pizza = new NYStyleCheesePizza();

		if (type.equals("peper"))
			pizza = new NYStylePepperoniPizza();

		if (type.equals("clam"))
			pizza = new NYStyleClamPizza();

		if (type.equals("veggie"))
			pizza = new NYStyleVeggiePizza();

		return pizza;

	}

}
